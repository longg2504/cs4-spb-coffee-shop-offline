package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.dto.category.CategoryUpReqDTO;
import com.cg.model.dto.category.CategoryUpResDTO;
import com.cg.model.dto.product.*;
import com.cg.service.category.ICategoryService;
import com.cg.service.product.IProductService;
import com.cg.utils.AppUtils;
import com.cg.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ValidateUtils validateUtils;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<?> getAllProduct() {

        List<ProductDTO> productDTOS = productService.findAllProductDTO();


        return new ResponseEntity<>(productDTOS, HttpStatus.OK);

    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getById(@PathVariable Long productId) {
        Product product = productService.findById(productId).orElseThrow(() -> {
            throw new DataInputException("Mã sản phẩm không tồn tại");
        });
        ProductDTO productDTO = product.toProductDTO();
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@ModelAttribute ProductCreReqDTO productCreReqDTO, BindingResult bindingResult) {

        new ProductCreReqDTO().validate(productCreReqDTO,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }
        Category category = categoryService.findById(productCreReqDTO.getCategoryId()).orElseThrow(() -> {
            throw new DataInputException("Danh mục không tồn tại");
        });



        Product product = productService.create(productCreReqDTO, category);
        ProductCreResDTO productCreResDTO = product.toProductCreResDTO();
        return new ResponseEntity<>(productCreResDTO, HttpStatus.CREATED);
    }

    @PatchMapping("edit/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable("productId") String productIdStr, @ModelAttribute ProductUpReqDTO productUpReqDTO,BindingResult bindingResult) {
        if (!validateUtils.isNumberValid(productIdStr)) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã Sản phẩm không hợp lệ");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long productId = Long.parseLong(productIdStr);
        Optional<Product> productOptional = productService.findById(productId);
        if (!productOptional.isPresent()) {
            throw new DataInputException("Mã sản phẩm không tồn tại");
        }

        new ProductUpReqDTO().validate(productUpReqDTO,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }

        String categoryIdStr = productUpReqDTO.getCategoryId();
        if (!validateUtils.isNumberValid(categoryIdStr)) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã danh mục không hợp lệ");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
        Long categoryId = Long.parseLong(categoryIdStr);
        Category category = categoryService.findById(categoryId).orElseThrow(() -> {
            throw new DataInputException("Mã danh mục không tồn tại");
        });

        Product productUpdate = productService.update(productId, productUpReqDTO,category);
        ProductUpResDTO productUpResDTO = productUpdate.toProductUpResDTO();

        return new ResponseEntity<>(productUpResDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") String productIdStr) {
        if (!validateUtils.isNumberValid(productIdStr)) {
            throw new DataInputException("Mã sản phẩm không hợp lệ");
        }
        Long productId = Long.parseLong(productIdStr);

        Product product = productService.findById(productId).orElseThrow(() -> {
            throw new DataInputException("Mã sản phẩm không tồn tại");
        });

        productService.deleteByIdTrue(product);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductBycategory(@PathVariable("categoryId") String categoryIdStr){

        if (!validateUtils.isNumberValid(categoryIdStr)) {
            throw new DataInputException("Mã sản phẩm không hợp lệ");
        }
        Long categoryId = Long.parseLong(categoryIdStr);

         List<ProductDTO> productDTO  = productService.findAllByCategoryLike(categoryId);

         return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }
}
