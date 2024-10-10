package com.gizmo.gizmoshop.repository;

import com.gizmo.gizmoshop.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    boolean existsByInventoryName(String inventoryName);
}
