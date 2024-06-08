package com.shopapi.controller;

import com.shopapi.dto.ImagesDTO;
import com.shopapi.dto.ProductDTO;
import com.shopapi.model.Images;
import com.shopapi.model.Product;
import com.shopapi.service.ImageService;
import com.shopapi.service.ProductService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@OpenAPIDefinition(info = @Info(title = "Images API", version = "v1"))
@RestController
@RequestMapping("/api/v1")
public class ImageController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private ProductService productService;

    @Operation(summary = "Create new image", description = "This endpoint allows creating a new image associated with a specific product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "222", description = "Client created successfully"),
            @ApiResponse(responseCode = "404", description = "Bad request - invalid client data or gender"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/image/create") // для отправки данных через HTML форму в теле запроса (POST)
    public ResponseEntity<String> saveImage(@RequestParam("product_id") Long product_id,
                                            @RequestParam("imageFile") MultipartFile file) {
        String contentType = file.getContentType();

        if (!contentType.startsWith("image/")) {
            return new ResponseEntity<>("Invalid image type", HttpStatus.BAD_REQUEST);
        }
        Optional<ProductDTO> productOptional = productService.getById(product_id);
        if (productOptional.isEmpty()) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        try {
            imageService.saveImages(file.getBytes(), product_id);
        } catch (IOException e) {
            throw new RuntimeException("Error uploading image", e);
        }
        return new ResponseEntity<>("Image created successfully", HttpStatus.OK);
    }

    @Operation(summary = "Get images by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad request - invalid page or size parameters"),
            @ApiResponse(responseCode = "404", description = "Image not found")
    })
    @GetMapping("/image/ImageId")
    public ResponseEntity<?> getImageByIdImage(@ApiParam(value = "Image ID") @RequestParam Long ImageId) {
        if (ImageId == null || ImageId < 1) {
            return new ResponseEntity<>("Invalid client ID", HttpStatus.BAD_REQUEST);
        }
        Optional <ImagesDTO> imagesDTOOptional = imageService.getImages(ImageId);
        if(imagesDTOOptional.isEmpty())  {
            return new ResponseEntity<>("Image not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(imagesDTOOptional.get(), HttpStatus.OK);
    }

}
