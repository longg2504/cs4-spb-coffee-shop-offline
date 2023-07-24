package com.cg.repository;

import com.cg.model.Category;
import com.cg.model.Product;

import com.cg.model.dto.product.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.loader.Loader.SELECT;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
@Query("SELECT NEW com.cg.model.dto.product.ProductDTO (" +
            "pro.id, " +
            "pro.title, " +
            "pro.price, " +
            "pro.unit, " +
            "pro.category, " +
            "pro.productAvatar" +
            ")" +
            "FROM Product as pro " +
            "WHERE pro.deleted = false"
    )
List<ProductDTO> findAllProductDTO();


@Query("SELECT NEW com.cg.model.dto.product.ProductDTO (" +
        "pr.id, " +
        "pr.title, " +
        "pr.price, " +
        "pr.unit, " +
        "pr.category, " +
        "pr. productAvatar " +
        ")" +
        "From Product AS pr " +
        "WHERE pr.category.id = :categoryId")
List<ProductDTO> findAllByCategoryLike(Long categoryId);
}
