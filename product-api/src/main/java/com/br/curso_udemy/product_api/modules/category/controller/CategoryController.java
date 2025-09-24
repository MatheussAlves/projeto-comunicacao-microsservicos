package com.br.curso_udemy.product_api.modules.category.controller;


import com.br.curso_udemy.product_api.modules.category.dto.CategoryRequest;
import com.br.curso_udemy.product_api.modules.category.dto.CategoryResponse;
import com.br.curso_udemy.product_api.modules.category.service.CategoryService;
import com.br.curso_udemy.product_api.modules.product.dto.ProductRequest;
import com.br.curso_udemy.product_api.modules.product.dto.ProductResponse;
import com.br.curso_udemy.product_api.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {



    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest request){
        return categoryService.save(request);
    }
}
