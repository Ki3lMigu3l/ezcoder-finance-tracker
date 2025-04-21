package br.dev.ezcoder.ezfinancepro.service;

import br.dev.ezcoder.ezfinancepro.model.entity.Category;
import br.dev.ezcoder.ezfinancepro.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category create(Category request) {
        return categoryRepository.save(new Category(request));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findCategoryById(String id) {
        return categoryRepository.findById(id);
    }

    public Category update(Category request) {
        return categoryRepository.save(request);
    }

    public void delete(String id) {
        categoryRepository.deleteById(id);
    }

}
