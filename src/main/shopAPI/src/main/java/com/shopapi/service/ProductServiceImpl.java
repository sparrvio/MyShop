package com.shopapi.service;

import com.shopapi.dto.ProductDTO;
import com.shopapi.mapper.ProductMapper;
import com.shopapi.model.Product;
import com.shopapi.repository.ImageRepository;
import com.shopapi.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Transactional
@Data
@Component
@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private ImageRepository imagesRepository;

    @Override
    public Optional<ProductDTO> getById(long id) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) {
            return Optional.empty();
        }

        Product product  = productOpt.get();
        ProductDTO productDTO= productMapper.toDto(product);
        productDTO.setId(id);
        return Optional.of(productDTO);
    }


    @Override
    public  Product save(ProductDTO productDTO)  {
        Product product = productMapper.toEntity(productDTO);
        return productRepository.save(product);
    }

    @Override
    public List<ProductDTO> getAll() {
        List<Product> products= productRepository.findAll();
        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

@Override
public void updateQuantity(long id, long quantity) {
    Optional<Product> productOptional = productRepository.findById(id);
    if (productOptional.isPresent()) {
        Product product = productOptional.get();
        product.setAvailable_stock(product.getAvailable_stock() - quantity);
        productRepository.save(product);
    } else {
        throw new RuntimeException("Product not found");
    }
}


    @Override
    public void delete(long id) {
        Optional<ProductDTO> productDTOOptional  = getById(id);
        if (productDTOOptional.isPresent())   {
            Product product = productMapper.toEntity(productDTOOptional.get());
            productRepository.delete(product);
        } else  {
            throw new RuntimeException("Product not found");
        }
    }
}
