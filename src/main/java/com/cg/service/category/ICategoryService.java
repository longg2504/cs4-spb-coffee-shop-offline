package com.cg.service.category;

import com.cg.model.Category;
import com.cg.model.dto.category.*;
import com.cg.service.IGeneralService;

import java.util.List;

public interface ICategoryService extends IGeneralService<Category,Long> {
    List<CategoryDTO> findCategoriesDTO();
//
    CategoryCreResDTO createCategory(CategoryCreReqDTO categoryCreReqDTO);
//
    CategoryUpResDTO updateCategory(Long categoryId, CategoryUpReqDTO categoryUpReqDTO);
//
    void deleteByIdTrue(Category category);
}
