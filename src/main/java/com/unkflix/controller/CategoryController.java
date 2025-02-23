package com.unkflix.controller;

import com.unkflix.controller.request.CategoryRequest;
import com.unkflix.controller.response.CategoryResponse;
import com.unkflix.entity.Category;
import com.unkflix.mapper.CategoryMapper;
import com.unkflix.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/unkflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest request) {
        Category newCategory = CategoryMapper.toCategory(request);
        Category savedCategory = categoryService.save(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(savedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long id) {
        if (categoryService.findById(id).isPresent()) {
            categoryService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Categoria de ID: " + id + " não encontrada!");
        }
    }

}
