package com.cg.service.productAvatar;

import com.cg.model.ProductAvatar;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductAvatarServiceImpl implements IProductAvatarService{
    @Override
    public List<ProductAvatar> findAll() {
        return null;
    }

    @Override
    public Optional<ProductAvatar> findById(String id) {
        return Optional.empty();
    }

    @Override
    public ProductAvatar save(ProductAvatar productAvatar) {
        return null;
    }

    @Override
    public void delete(ProductAvatar productAvatar) {

    }

    @Override
    public void deleteById(String id) {

    }
}
