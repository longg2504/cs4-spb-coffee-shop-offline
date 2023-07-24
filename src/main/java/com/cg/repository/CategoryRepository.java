package com.cg.repository;

import com.cg.model.Category;
import com.cg.model.dto.category.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("SELECT NEW com.cg.model.dto.category.CategoryDTO (" +
            "ca.id, " +
            "ca.title) " +
            "FROM Category AS ca " +
            "WHERE ca.deleted = false ")
    List<CategoryDTO> findCategoriesDTO();
}
