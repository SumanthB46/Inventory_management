package com.Inventory.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    private String description;  // ✅ Category Description

    // ✅ Self-referencing for Parent-Child category structure
    @ManyToOne
    @JoinColumn(name = "parent_id")  // Renamed from categoryId for clarity
    private CategoryEntity parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude  // ✅ Prevents recursion issues
    @JsonIgnore        // ✅ Prevents infinite loop in JSON serialization
    private List<CategoryEntity> subcategories;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    private List<ProductEntity> products;
}
