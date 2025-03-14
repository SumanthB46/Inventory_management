package com.Inventory.service;

import java.util.List;
import java.util.Optional;

import com.Inventory.entity.SupplierEntity;

public interface SupplierService {

	    List<SupplierEntity> getAllSuppliers();
	    Optional<SupplierEntity> getSupplierById(Long id);
	    SupplierEntity addSupplier(SupplierEntity supplier);
	    SupplierEntity updateSupplier(Long id, SupplierEntity updatedSupplier);
	    void deleteSupplier(Long id);
	}


