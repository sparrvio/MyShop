package com.shopapi.service;

import com.shopapi.dto.ProductDTO;
import com.shopapi.mapper.ProductMapper;
import com.shopapi.model.Product;
import com.shopapi.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@AllArgsConstructor
@Transactional
@Data
@Component
@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private ProductMapper productMapper;

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

}
