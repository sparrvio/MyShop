package com.shopapi.controller;

import com.shopapi.dto.ProductDTO;
import com.shopapi.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@OpenAPIDefinition(info = @Info(title = "Product API", version = "v1"))
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

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


    @Operation(summary = "Retrieve all clients with pagination parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of clients"),
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
}