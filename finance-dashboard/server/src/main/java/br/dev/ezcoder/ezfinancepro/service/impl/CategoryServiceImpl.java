package br.dev.ezcoder.ezfinancepro.service.impl;

import br.dev.ezcoder.ezfinancepro.exception.CategoryNotFoundException;
import br.dev.ezcoder.ezfinancepro.model.dto.request.CategoryRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.request.TransactionRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.response.CategoryResponse;
import br.dev.ezcoder.ezfinancepro.model.entity.Category;
import br.dev.ezcoder.ezfinancepro.repository.CategoryRepository;
import br.dev.ezcoder.ezfinancepro.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category registerCategory(TransactionRequest category) {
        return categoryRepository.save(new Category(category));
    }

    @Override
    public Category registerCategory(CategoryRequest request) {
        return categoryRepository.save(new Category(request));
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        return mapCategoryToResponseDTOs(categoryRepository.findAll());
    }

    @Override
    public Category findCategory(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public Category updateCategory(String id, CategoryRequest request) {
        Category category = findCategory(id);
        BeanUtils.copyProperties(request, category);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(String id) {
        Category category = findCategory(id);
        categoryRepository.delete(category);
    }

    public List<CategoryResponse> mapCategoryToResponseDTOs (List<Category> categories) {
        Objects.requireNonNull(categories, "Categories cannot be Null");

        return categories.stream()
                .filter(Objects::nonNull)
                .map(this::entityToResponseDTO)
                .toList();
    }

    private CategoryResponse entityToResponseDTO(Category category) {
        return CategoryResponse.entityToResponse(category);
    }

}
