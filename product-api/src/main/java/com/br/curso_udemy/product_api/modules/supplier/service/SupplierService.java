package com.br.curso_udemy.product_api.modules.supplier.service;

import com.br.curso_udemy.product_api.config.exception.SuccessResponse;
import com.br.curso_udemy.product_api.config.exception.ValidationException;
import com.br.curso_udemy.product_api.modules.category.model.Category;
import com.br.curso_udemy.product_api.modules.product.service.ProductService;
import com.br.curso_udemy.product_api.modules.supplier.dto.SupplierRequest;
import com.br.curso_udemy.product_api.modules.supplier.dto.SupplierResponse;
import com.br.curso_udemy.product_api.modules.supplier.model.Supplier;
import com.br.curso_udemy.product_api.modules.supplier.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@AllArgsConstructor(onConstructor_ = { @Lazy})
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Lazy
    private final ProductService productService;

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

    public List<SupplierResponse> findAll() {
        return supplierRepository
                .findAll()
                .stream()
                .map(SupplierResponse::of)
                .collect(Collectors.toList());
    }

    public List<SupplierResponse> findByName(String name) {
        if (isEmpty(name)) {
            throw new ValidationException("The supplier name must be informed.");
        }
        return supplierRepository
                .findByNameIgnoreCaseContaining(name)
                .stream()
                .map(SupplierResponse::of)
                .collect(Collectors.toList());
    }

    public SupplierResponse findByIdResponse(Integer id) {
        return SupplierResponse.of(findById(id));
    }


    private void validateInformedId(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The category ID must be informed.");
        }
    }

    public SupplierResponse update(SupplierRequest request,
                                   Integer id) {
        validateSupplierNameInformed(request);
        validateInformedId(id);
        var supplier = Supplier.of(request);
        supplier.setId(id);
        supplierRepository.save(supplier);
        return SupplierResponse.of(supplier);
    }

    public SuccessResponse delete(Integer id) {
        validateInformedId(id);
        if (productService.existsBySupplierId(id)) {
            throw new ValidationException("You cannot delete this supplier because it's already defined by a product.");
        }
        supplierRepository.deleteById(id);
        return SuccessResponse.create("The supplier was deleted.");
    }

    private void validateSupplierNameInformed(SupplierRequest request) {
        if (isEmpty(request.getName())) {
            throw new ValidationException("The supplier's name was not informed.");
        }
    }
}
