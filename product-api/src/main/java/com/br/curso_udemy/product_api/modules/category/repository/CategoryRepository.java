package com.br.curso_udemy.product_api.modules.category.repository;

import com.br.curso_udemy.product_api.modules.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
