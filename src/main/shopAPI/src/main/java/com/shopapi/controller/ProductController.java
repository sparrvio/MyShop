package com.shopapi.controller;

import com.shopapi.dto.ProductDTO;
import com.shopapi.mapper.ProductMapper;
import com.shopapi.model.Product;
import com.shopapi.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@OpenAPIDefinition(info = @Info(title = "Product API", version = "v1"))
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;


    @Operation(summary = "Get product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad request - invalid page or size parameters"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/product/id")
    public ResponseEntity<?> getProduct(@RequestParam(required = false) Long productID) {
        if (productID == null || productID < 1) {
            return new ResponseEntity<>("Invalid product id", HttpStatus.BAD_REQUEST);
        }
        Optional<ProductDTO> productDTOOptional = productService.getById(productID);
        if (productDTOOptional.isEmpty()) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productDTOOptional.get(), HttpStatus.OK);
    }


    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of products"),
            @ApiResponse(responseCode = "400", description = "Bad request - invalid page or size parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/product/all")
    public ResponseEntity<?> getAllProducts() {
        List<ProductDTO> products = productService.getAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>("No products found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Create new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "222", description = "Product created successfully"),
            @ApiResponse(responseCode = "404", description = "Bad request - invalid product data or category"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/product/create") // для отправки данных через HTML форму в теле запроса (POST)
    public ResponseEntity<?> createProduct(@RequestParam String name, @RequestParam String category,
                                           @RequestParam Double price, @RequestParam Long available_stock) {
        ProductDTO productDTO = ProductDTO.builder()
                .name(name)
                .category(category)
                .price(price)
                .available_stock(available_stock)
                .build();
        productService.save(productDTO);
        return new ResponseEntity<>("Product created successfully", HttpStatus.OK);
    }

    @Operation(summary = "Create new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "222", description = "Product created successfully"),
            @ApiResponse(responseCode = "404", description = "Bad request - invalid product data or category"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/product/createProductForPostman") // для отправки данных через json for Postman (POST)
    public ResponseEntity<?> createProductForPostman(@RequestBody ProductDTO productDTO)  {
        if(productDTO  == null || productDTO.getName()  == null || productDTO.getPrice() == null
        || productDTO.getAvailable_stock() == null)  {
            return  new ResponseEntity<>("Invalid product", HttpStatus.BAD_REQUEST);
        }
        productService.save(productDTO);
        return new ResponseEntity<>("Product created successfully", HttpStatus.OK);
    }

    @Operation(summary = "Update quantity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantity updated successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PostMapping("/product/updateQuantity")
    public ResponseEntity<String> updateQuantity(
            @RequestParam("id") long id,
            @RequestParam("quantity") long quantity) {
        Optional<ProductDTO> productDTOOptional = productService.getById(id);
        if (productDTOOptional.isEmpty()) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        Product product = productMapper.toEntity(productDTOOptional.get());

        if (product.getAvailable_stock() < quantity) {
            return new ResponseEntity<>("Not enough stock", HttpStatus.NOT_FOUND);
        }
        try {
            productService.updateQuantity(id, quantity);
        } catch  (RuntimeException ex)  {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Quantity updated successfully", HttpStatus.OK);
    }

    @Operation(summary = "Delete product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping(value = "/product/deleteProductById")
    public ResponseEntity<?> deleteProductById(@RequestParam Long idDelete) {
        Optional<ProductDTO> productDTOOptional = productService.getById(idDelete);
        if (productDTOOptional.isEmpty()) {
            return new ResponseEntity<>("Supplier not found", HttpStatus.NOT_FOUND);
        }
        try {
            productService.delete(idDelete);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }
}
