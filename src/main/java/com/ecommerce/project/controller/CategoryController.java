package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Tag(name = "Category APIs", description = "APIs for managing categories")
    @Operation(summary = "Create category", description = "API to create a new category")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "category is created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/public/categories")
    private ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
        CategoryResponse allCategories = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    private ResponseEntity<CategoryDTO> createCategories(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategoryDTO =
                categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    private ResponseEntity<CategoryDTO> deleteCategory(
            @Parameter(description = "Id you wish to delete")
            @PathVariable Long categoryId) {
        CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
    }

    @PutMapping("/public/categories/{categoryId}")
    private ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId) {
        CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
    }
}
