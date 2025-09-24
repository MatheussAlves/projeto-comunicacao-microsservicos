package com.br.curso_udemy.product_api.modules.supplier.repository;

import com.br.curso_udemy.product_api.modules.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer>{
}
