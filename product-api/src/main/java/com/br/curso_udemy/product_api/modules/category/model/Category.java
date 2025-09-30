package com.br.curso_udemy.product_api.modules.category.model;


import com.br.curso_udemy.product_api.modules.category.dto.CategoryRequest;
import com.br.curso_udemy.product_api.modules.supplier.dto.SupplierRequest;
import com.br.curso_udemy.product_api.modules.supplier.model.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;


    public static Category of(CategoryRequest request) {
        var category = new Category();
        BeanUtils.copyProperties(request,category);
        return category;
    }
}
