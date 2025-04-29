package br.dev.ezcoder.ezfinancepro.controller;

import br.dev.ezcoder.ezfinancepro.model.dto.request.CategoryRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.response.CategoryResponse;
import br.dev.ezcoder.ezfinancepro.model.entity.Category;
import br.dev.ezcoder.ezfinancepro.model.entity.UserModel;
import br.dev.ezcoder.ezfinancepro.service.CategoryService;
import br.dev.ezcoder.ezfinancepro.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory (@RequestBody @Valid CategoryRequest request) {
        UserModel userFound = userService.findUserById(request.userId());
        Category category = categoryService.registerCategory(request);
        URI location = buildUserUri(category.getId());

        return ResponseEntity.created(location).body(CategoryResponse.entityToResponse(category));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAllCategories () {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findCategory (@PathVariable String id) {
        return ResponseEntity.ok(CategoryResponse.entityToResponse(categoryService.findCategory(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory (@PathVariable String id,
                                                            @RequestBody @Valid CategoryRequest request) {

        return ResponseEntity.ok(CategoryResponse.entityToResponse(categoryService.updateCategory(id, request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory (@PathVariable String id) {
        categoryService.deleteCategory(id);
    }

    private URI buildUserUri(String id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
