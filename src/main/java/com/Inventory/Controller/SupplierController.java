package com.Inventory.Controller;

import com.Inventory.entity.SupplierEntity;
import com.Inventory.serviceImplementation.SupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierServiceImpl supplierService;

    @GetMapping
    public String viewSuppliers(Model model) {
        List<SupplierEntity> suppliers = supplierService.getAllSuppliers();
        model.addAttribute("suppliers", suppliers);
        return "supplier";
    }

    @PostMapping("/save")
    public String addSupplier(@ModelAttribute SupplierEntity supplier) {
        supplierService.addSupplier(supplier);
        return "supplier";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return "redirect:/suppliers";
    }
}
