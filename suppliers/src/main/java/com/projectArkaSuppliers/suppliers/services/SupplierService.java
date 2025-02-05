package com.projectArkaSuppliers.suppliers.services;

import com.projectArkaSuppliers.suppliers.dtos.CreateSupplierDto;
import com.projectArkaSuppliers.suppliers.entities.Supplier;
import com.projectArkaSuppliers.suppliers.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository repository;

    private List<Supplier> getAll (){
        return repository.findAllByIsActive(true);
    }

    private Supplier get(Long id){
        return repository.findById(id).orElseThrow(/* Exception*/);
    }

    private  Supplier create(CreateSupplierDto createSupplierDto) {
        Supplier newSupplier = new Supplier();
        newSupplier.setName(createSupplierDto.getName());
        newSupplier.setActive(true);
        newSupplier.setDescription(createSupplierDto.getDescription());
        newSupplier.setEmail(createSupplierDto.getEmail());
        newSupplier.setPhone(createSupplierDto.getPhone());
        newSupplier.setWebsite(newSupplier.getWebsite());
        return repository.save(newSupplier);
    }

   /*

    private  Suplier update (Long id){

    }

    */

    private  String delete(Long id){
        Supplier suplier = this.get(id);

        if (suplier.getFirstPurchase() != null){
            //Lanzar Exception indicado que no se pueden eliminar supplier con venta
        }
        repository.deleteById(id);
        return "Se ha eliminado el proveedor";
    }

    private String registerSale(Long id){
        Supplier supplier = this.get(id);
        supplier.setFirstPurchase(new Date());
        repository.save(supplier);
        return "Se ha comprado al proveedor";
    }

}
