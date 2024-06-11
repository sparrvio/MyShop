package com.shopapi.repository;

import com.shopapi.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Images, Long> {
    // В ImagesRepository
    @Query("SELECT i FROM Images i WHERE i.product_id.id = :productId")
    List<Images> findByProductId(@Param("productId") Long productId);

}
