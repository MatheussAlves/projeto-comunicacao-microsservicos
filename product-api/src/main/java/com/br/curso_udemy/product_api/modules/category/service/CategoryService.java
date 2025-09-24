package com.br.curso_udemy.product_api.modules.category.service;

import com.br.curso_udemy.product_api.config.exception.ValidationException;
import com.br.curso_udemy.product_api.modules.category.controller.CategoryController;
import com.br.curso_udemy.product_api.modules.category.dto.CategoryRequest;
import com.br.curso_udemy.product_api.modules.category.dto.CategoryResponse;
import com.br.curso_udemy.product_api.modules.category.model.Category;
import com.br.curso_udemy.product_api.modules.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;



    public CategoryResponse save(CategoryRequest request) {
        validateCategoryNameInformed(request);
        var category = categoryRepository.save(Category.of(request));
        return CategoryResponse.of(category);
    }

    public Category findById(Integer id){
        validateInformedId(id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ValidationException("There's no category for the given ID."));

    }
    public List<CategoryResponse> findByDescription(String description) {
        if (isEmpty(description)) {
            throw new ValidationException("The category description must be informed.");
        }
        return categoryRepository
                .findByDescriptionIgnoreCaseContaining(description)
                .stream()
                .map(CategoryResponse::of) // ou category -> CategoryResponse.of(category)
                .collect(Collectors.toList());
    }

    public CategoryResponse findByIdResponse(Integer id) {
        return CategoryResponse.of(findById(id));
    }

    public List<CategoryResponse> findAll(){
       return categoryRepository.findAll().stream()
               .map(CategoryResponse::of).collect(Collectors.toList());
    }

    private void validateInformedId(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The category ID must be informed.");
        }
    }

    private void validateCategoryNameInformed(CategoryRequest request) {
        if (isEmpty(request.getDescription())) {
            throw new BeanDefinitionValidationException("The category description was not informed.");
        }
    }
}
