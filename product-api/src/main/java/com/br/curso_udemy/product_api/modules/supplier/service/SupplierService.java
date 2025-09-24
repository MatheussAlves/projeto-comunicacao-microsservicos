package com.br.curso_udemy.product_api.modules.supplier.service;

import com.br.curso_udemy.product_api.config.exception.ValidationException;
import com.br.curso_udemy.product_api.modules.category.model.Category;
import com.br.curso_udemy.product_api.modules.supplier.dto.SupplierRequest;
import com.br.curso_udemy.product_api.modules.supplier.dto.SupplierResponse;
import com.br.curso_udemy.product_api.modules.supplier.model.Supplier;
import com.br.curso_udemy.product_api.modules.supplier.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;



    public SupplierResponse save(SupplierRequest request) {
        validateSupplierNameInformed(request);
        var supplier = supplierRepository.save(Supplier.of(request));
        return SupplierResponse.of(supplier);
    }

    public Supplier findById(Integer id){
        validateInformedId(id);
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ValidationException("There's no category for the given ID."));

    }

    private void validateInformedId(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The category ID must be informed.");
        }
    }

    private void validateSupplierNameInformed(SupplierRequest request) {
        if (isEmpty(request.getName())) {
            throw new ValidationException("The supplier's name was not informed.");
        }
    }
}
