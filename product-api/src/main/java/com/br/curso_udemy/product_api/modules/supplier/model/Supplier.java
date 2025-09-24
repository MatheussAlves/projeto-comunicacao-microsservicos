package com.br.curso_udemy.product_api.modules.supplier.model;


import com.br.curso_udemy.product_api.modules.supplier.dto.SupplierRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SUPPLIER")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;



    public static Supplier of(SupplierRequest request) {
        var supplier = new Supplier();
        BeanUtils.copyProperties(request,supplier);
        return supplier;
    }
}
