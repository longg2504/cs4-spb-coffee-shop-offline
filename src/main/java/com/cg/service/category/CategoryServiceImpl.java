package com.cg.service.category;

import com.cg.model.Category;

import com.cg.model.dto.category.*;
import com.cg.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public void deleteById(Long id) {

    }



    @Override
    public List<CategoryDTO> findCategoriesDTO() {
        return categoryRepository.findCategoriesDTO();
    }

    @Override
    public CategoryCreResDTO createCategory(CategoryCreReqDTO categoryCreReqDTO) {
        Category category = categoryCreReqDTO.toCategory();
        categoryRepository.save(category);
        CategoryCreResDTO categoryCreResDTO = category.toCategoryCreResDTO();

        return categoryCreResDTO;
    }
//
    @Override
    public CategoryUpResDTO updateCategory(Long categoryId , CategoryUpReqDTO categoryUpReqDTO) {
         Category category = categoryUpReqDTO.toCategory(categoryId);
         categoryRepository.save(category);
         CategoryUpResDTO categoryUpResDTO = category.toCategoryUpResDTO();
         return categoryUpResDTO;
    }

    @Override
    public void deleteByIdTrue(Category category) {
        category.setDeleted(true);
        categoryRepository.save(category);
    }
}
