package com.example.e_commerce_OAuth2.service.serviceImp;

import com.example.e_commerce_OAuth2.dto.CategoryDto;
import com.example.e_commerce_OAuth2.exceptions.coustomexceptions.CategoryNameExistsException;
import com.example.e_commerce_OAuth2.exceptions.coustomexceptions.CategoryNotFoundException;
import com.example.e_commerce_OAuth2.model.Category;
import com.example.e_commerce_OAuth2.repository.CategoryRepository;
import com.example.e_commerce_OAuth2.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public boolean isCategoryExists(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public ResponseEntity<String> addCategory(@Valid CategoryDto categoryDto) {
        try {
            if (isCategoryExists(categoryDto.getName())) {
                throw new CategoryNameExistsException("Category with this name already exists");
            }

            Category category = new Category();
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            categoryRepository.save(category);

            return ResponseEntity.status(HttpStatus.CREATED).body("Category added successfully");
        } catch (CategoryNameExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the category");
        }
    }

    @Override
    public void editCategory(Long id, CategoryDto categoryDto) throws CategoryNameExistsException,
            CategoryNotFoundException {
        // Fetch the existing category by ID
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + id));

        // Check if the new category name already exists (excluding the current category)
        if (!existingCategory.getName().equals(categoryDto.getName()) && isCategoryExists(categoryDto.getName())) {
            throw new CategoryNameExistsException("Category name already exists. Please choose a different name.");
        }

        // Update the category details
        existingCategory.setName(categoryDto.getName());
        existingCategory.setDescription(categoryDto.getDescription());

        // Save the updated category
        categoryRepository.save(existingCategory);
    }

    @Override
    public List<CategoryDto> findAllCategory() {
        List<Category>allCategory=categoryRepository.findAll();
      return allCategory.stream().map(category -> new CategoryDto(category.getId(), category.getName(),category.getDescription())
        ).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findByCategoryId(Long categoryId) {
        Optional<Category>optionalCategory=categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()){
            Category category=optionalCategory.get();
            return new CategoryDto(category.getId(),category.getName(),category.getDescription());
        }
        return null;
    }
}
