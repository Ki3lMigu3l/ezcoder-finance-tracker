package br.dev.ezcoder.ezfinancepro.controller;

import br.dev.ezcoder.ezfinancepro.model.dto.request.CategoryRequestDTO;
import br.dev.ezcoder.ezfinancepro.model.entity.Category;
import br.dev.ezcoder.ezfinancepro.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory (@RequestBody @Valid CategoryRequestDTO request)  {
        Category createdCategory = new Category(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(createdCategory));
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories () {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory (@PathVariable String id) {
        Category categoryFound = categoryService.findCategoryById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!"));

        return ResponseEntity.status(HttpStatus.OK).body(categoryFound);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateTransaction (@PathVariable String id,
                                                          @RequestBody @Valid Category request) {

        Category categoryFound = categoryService.findCategoryById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction Not found!"));

        request.setId(categoryFound.getId());
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction (@PathVariable String id) {

        Category transactionFound = categoryService.findCategoryById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction Not found!"));

        categoryService.delete(id);
    }
}
