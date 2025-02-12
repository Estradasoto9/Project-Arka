package com.projectArkaSuppliers.suppliers.services;

import com.projectArkaSuppliers.suppliers.dtos.CreateSupplierDto;
import com.projectArkaSuppliers.suppliers.dtos.UpdateSupplierDto;
import com.projectArkaSuppliers.suppliers.entities.Product;
import com.projectArkaSuppliers.suppliers.entities.Supplier;
import com.projectArkaSuppliers.suppliers.entities.SupplierContact;
import com.projectArkaSuppliers.suppliers.repositories.ProductRepository;
import com.projectArkaSuppliers.suppliers.repositories.SupplierRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository repository;

    @Autowired
    ProductRepository productRepository;

    public List<Supplier> getAll(){
        return repository.findAllByIsActive(true);
    }

    public Supplier get(Long id){
        return repository.findById(id).orElseThrow( /* Excepcion*/);
    }

    public Supplier create(@Valid CreateSupplierDto createSuplierDto) {
        Supplier newSuplier = new Supplier();
        newSuplier.setName(createSuplierDto.getName());
        newSuplier.setIsActive(true);
        newSuplier.setDescription(createSuplierDto.getDescription());
        newSuplier.setEmail(createSuplierDto.getEmail());
        newSuplier.setPhone(createSuplierDto.getPhone());
        newSuplier.setWebsite(createSuplierDto.getWebSite());
        newSuplier.setAddress(createSuplierDto.getAddress());

        /** INTSTACIANDO EL OBJETO**/
        SupplierContact supplierContactNew =  new SupplierContact();
        BeanUtils.copyProperties(createSuplierDto.getContact(), supplierContactNew);

        newSuplier.setContact(supplierContactNew);
        return repository.save(newSuplier);
    }

    public Supplier update(Long id, @Valid UpdateSupplierDto updateSuplierDto) {
        Supplier existingSupplier = get(id);

        if (updateSuplierDto.getName() != null) {
            existingSupplier.setName(updateSuplierDto.getName());
        }
        if (updateSuplierDto.getDescription() != null) {
            existingSupplier.setDescription(updateSuplierDto.getDescription());
        }
        if (updateSuplierDto.getEmail() != null) {
            existingSupplier.setEmail(updateSuplierDto.getEmail());
        }
        if (updateSuplierDto.getPhone() != null) {
            existingSupplier.setPhone(updateSuplierDto.getPhone());
        }
        if (updateSuplierDto.getWebSite() != null) {
            existingSupplier.setWebsite(updateSuplierDto.getWebSite());
        }

        return repository.save(existingSupplier);
    }

    public  String delete(Long id){
        Supplier suplier = this.get(id);

        if (suplier.getFirstPurchase() != null){
            //Lanzar Exception indicado que no se pueden eliminar spluier con venta
        }
        repository.deleteById(id);
        return "Se ha eliminado el proveedor";
    }

    public String registrarVenta(Long id){
        Supplier suplier = this.get(id);
        suplier.setFirstPurchase(new Date());
        repository.save(suplier);
        return "Se ha comprado al proveedor";
    }

    public Supplier addProductToSupplier(Long supplierId, Long productId) {
        Optional<Supplier> supplierOptional = repository.findById(supplierId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (supplierOptional.isPresent() && productOptional.isPresent()) {
            Supplier supplier = supplierOptional.get();
            Product product = productOptional.get();

            supplier.getProducts().add(product);
            return repository.save(supplier);
        } else {
            throw new RuntimeException("Proveedor o producto no encontrado");
        }
    }
}
