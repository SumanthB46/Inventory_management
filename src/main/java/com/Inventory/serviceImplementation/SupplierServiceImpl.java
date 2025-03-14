package com.Inventory.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Inventory.entity.SupplierEntity;
import com.Inventory.repository.SupplierRepo;

@Service
public class SupplierServiceImpl {


	    @Autowired
	    private SupplierRepo supplierRepository;

	    public List<SupplierEntity> getAllSuppliers() {
	        return supplierRepository.findAll();
	    }

	    public Optional<SupplierEntity> getSupplierById(Long id) {
	        return supplierRepository.findById(id);
	    }

	    public SupplierEntity addSupplier(SupplierEntity supplier) {
	        return supplierRepository.save(supplier);
	    }

	    public SupplierEntity updateSupplier(Long id, SupplierEntity updatedSupplier) {
	        return supplierRepository.findById(id).map(supplier -> {
	            supplier.setName(updatedSupplier.getName());
	            supplier.setContactNumber(updatedSupplier.getContactNumber());
	            supplier.setEmail(updatedSupplier.getEmail());
	            supplier.setAddress(updatedSupplier.getAddress());
	            return supplierRepository.save(supplier);
	        }).orElseThrow(() -> new RuntimeException("Supplier not found"));
	    }

	    public void deleteSupplier(Long id) {
	        supplierRepository.deleteById(id);
	    }
	}

