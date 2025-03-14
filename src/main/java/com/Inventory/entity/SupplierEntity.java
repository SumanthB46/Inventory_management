package com.Inventory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data  // Lombok automatically generates getters & setters
@Entity
@Table(name = "suppliers")
public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactNumber;  // ✅ Add this field
    private String email;
    private String address;
}
