package br.dev.ezcoder.ezfinancepro.service;

import br.dev.ezcoder.ezfinancepro.model.dto.request.CategoryRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.request.TransactionRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.response.CategoryResponse;
import br.dev.ezcoder.ezfinancepro.model.entity.Category;

import java.util.List;

public interface CategoryService {

    Category registerCategory (TransactionRequest request);
    Category registerCategory(CategoryRequest request);
    List<CategoryResponse> findAllCategories ();
    Category findCategory (String id);
    Category updateCategory (String id, CategoryRequest request);
    void deleteCategory (String id);
}
