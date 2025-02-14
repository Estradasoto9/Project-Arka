package com.projectArkaSuppliers.suppliers.services;

import com.projectArkaSuppliers.suppliers.dtos.CreateSupplierDto;
import com.projectArkaSuppliers.suppliers.dtos.UpdateSupplierDto;
import com.projectArkaSuppliers.suppliers.entities.Product;
import com.projectArkaSuppliers.suppliers.entities.Supplier;
import com.projectArkaSuppliers.suppliers.entities.SupplierContact;
import com.projectArkaSuppliers.suppliers.repositories.ProductRepository;
import com.projectArkaSuppliers.suppliers.repositories.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Supplier> getAll() {
        return supplierRepository.findAllByActive(true);
    }

    public Supplier get(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + id));
    }

    public Supplier create(CreateSupplierDto createSupplierDto) {
        Supplier newSupplier = new Supplier();
        newSupplier.setName(createSupplierDto.getName());
        newSupplier.setActive(true);
        newSupplier.setDescription(createSupplierDto.getDescription());
        newSupplier.setEmail(createSupplierDto.getEmail());
        newSupplier.setPhone(createSupplierDto.getPhone());
        newSupplier.setWebSite(createSupplierDto.getWebSite());
        newSupplier.setAddress(createSupplierDto.getAddress());

        /** INTSTACIANDO EL OBJETO**/
        SupplierContact supplierContactNew =  new SupplierContact();
        BeanUtils.copyProperties(createSupplierDto.getContact(), supplierContactNew);

        newSupplier.setContact(supplierContactNew);
        return supplierRepository.save(newSupplier);
    }

    public Supplier update(Long id, UpdateSupplierDto updateSupplierDto) {
        Supplier existingSupplier = get(id);

        if (updateSupplierDto.getName() != null) {
            existingSupplier.setName(updateSupplierDto.getName());
        }
        if (updateSupplierDto.getDescription() != null) {
            existingSupplier.setDescription(updateSupplierDto.getDescription());
        }
        if (updateSupplierDto.getEmail() != null) {
            existingSupplier.setEmail(updateSupplierDto.getEmail());
        }
        if (updateSupplierDto.getPhone() != null) {
            existingSupplier.setPhone(updateSupplierDto.getPhone());
        }
        if (updateSupplierDto.getWebSite() != null) {
            existingSupplier.setWebSite(updateSupplierDto.getWebSite());
        }

        return supplierRepository.save(existingSupplier);
    }

    public String delete(Long id) {
        Supplier supplier =this.get(id);

        if (supplier.getFirstPurchase() != null) {
            throw new RuntimeException("No se puede eliminar un proveedor con ventas registradas.");
        }

        supplierRepository.deleteById(id);
        return "Se ha eliminado el proveedor";
    }

    public String registrarVenta(Long id) {
        Supplier supplier = this.get(id);
        supplier.setFirstPurchase(new Date());
        supplierRepository.save(supplier);
        return "Compra registrada para el proveedor.";
    }

    public Supplier addProductToSupplier(Long supplierId, Long productId) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (supplierOptional.isPresent() && productOptional.isPresent()) {
            Supplier supplier = supplierOptional.get();
            Product product = productOptional.get();

            supplier.getProducts().add(product);
            return supplierRepository.save(supplier);
        } else {
            throw new RuntimeException("Proveedor o producto no encontrado");
        }
    }
}
