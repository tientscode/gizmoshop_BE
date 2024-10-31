package com.gizmo.gizmoshop.repository;

import com.gizmo.gizmoshop.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE (:productName IS NULL OR p.name LIKE %:productName%) AND (:active IS NULL OR p.deleted = :active)")
    Page<Product> findAllByCriteria(@Param("productName") String productName, @Param("active") Boolean active, Pageable pageable);

}
