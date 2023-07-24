package com.cg.service.product;

import com.cg.model.Category;
import com.cg.model.Product;

import com.cg.model.dto.product.ProductCreReqDTO;
import com.cg.model.dto.product.ProductDTO;
import com.cg.model.dto.product.ProductUpReqDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IProductService extends IGeneralService<Product,Long> {
    List<ProductDTO> findAllProductDTO();
    Product create(ProductCreReqDTO productCreReqDTO, Category category);

    Product update(Long id, ProductUpReqDTO productUpReqDTO,Category category);
    void deleteByIdTrue(Product product);

    List<ProductDTO> findAllByCategoryLike(Long category);
}
