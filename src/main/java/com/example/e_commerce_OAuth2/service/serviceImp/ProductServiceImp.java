package com.example.e_commerce_OAuth2.service.serviceImp;

import com.example.e_commerce_OAuth2.dto.CategoryDto;
import com.example.e_commerce_OAuth2.dto.ProductDto;
import com.example.e_commerce_OAuth2.dto.ProductResponseDto;
import com.example.e_commerce_OAuth2.exceptions.coustomexceptions.CategoryNotFoundException;
import com.example.e_commerce_OAuth2.model.Category;
import com.example.e_commerce_OAuth2.model.Products;
import com.example.e_commerce_OAuth2.repository.CategoryRepository;
import com.example.e_commerce_OAuth2.repository.ProductRepository;
import com.example.e_commerce_OAuth2.service.CloudinaryService;
import com.example.e_commerce_OAuth2.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImp implements ProductService {
    private final CloudinaryService cloudinaryService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public ResponseEntity<HttpStatus> addProduct(ProductDto productDto, MultipartFile image) {
        try {
            if (image == null || image.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Products product = new Products();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            Category category = categoryRepository.findById(productDto.getCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
            product.setCategory(category);


            String imageLink = cloudinaryService.imageUpload(image);
            if (imageLink == null || imageLink.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            product.setImageLink(imageLink);
            productRepository.save(product);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error("Error adding product", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<HttpStatus> editProduct(Long productId, ProductDto productDto, MultipartFile image) {
        try {
            // Find the existing product
            Optional<Products> optionalProduct = productRepository.findById(productId);
            if (!optionalProduct.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            Products product = optionalProduct.get();

            // Update product details
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            Category category = categoryRepository.findById(productDto.getCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
            product.setCategory(category);

            // Handle image update
            if (image != null && !image.isEmpty()) {
                String newImageLink = cloudinaryService.imageUpload(image);
                if (newImageLink == null || newImageLink.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
                product.setImageLink(newImageLink);
            }

            // Save updated product
            productRepository.save(product);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            log.error("Error editing product", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public List<ProductResponseDto> findAllProduct() {
       List<Products>products=productRepository.findAll();
       return    products.stream().map(product->new ProductResponseDto(product.getId(),
                  product.getName(),
                  product.getDescription(),
                  product.getPrice(),
                  categoryHelper(product.getCategory()),
                  product.getImageLink())).collect(Collectors.toList());

    }

    private CategoryDto categoryHelper(Category category){
        return new CategoryDto(category.getId(), category.getName(), category.getDescription());
    }

    @Override
    public ProductResponseDto findByProductId(Long productId) {
        Optional<Products> optionalProducts=productRepository.findById(productId);
        if (optionalProducts.isPresent()){
            Products products =optionalProducts.get();
            return  new ProductResponseDto(products.getId(),
                    products.getName(),
                    products.getDescription(),
                    products.getPrice(),
                    categoryHelper(products.getCategory()),
                    products.getImageLink());
        }
       return null;
    }
}
