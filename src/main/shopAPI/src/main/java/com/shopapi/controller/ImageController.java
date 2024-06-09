package com.shopapi.controller;

import com.shopapi.dto.ImagesDTO;
import com.shopapi.dto.ProductDTO;
import com.shopapi.mapper.ProductMapper;
import com.shopapi.model.Images;
import com.shopapi.model.Product;
import com.shopapi.service.ImageService;
import com.shopapi.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@OpenAPIDefinition(info = @Info(title = "Images API", version = "v1"))
@RestController
@RequestMapping("/api/v1")
public class ImageController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

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

        assert contentType != null;
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

    @Operation(summary = "Get picture by ID image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad request - invalid page or size parameters"),
            @ApiResponse(responseCode = "404", description = "Image not found")
    })
    @GetMapping("/image/ImageId")
    public ResponseEntity<?> getImageByIdImage(@RequestParam Long ImageId) {
        if (ImageId == null || ImageId < 1) {
            return ResponseEntity.badRequest().body(null);
        }
        Optional<ImagesDTO> imagesDTOOptional = imageService.getImages(ImageId);
        if (imagesDTOOptional.isEmpty()) {
            return new ResponseEntity<>("Image not found", HttpStatus.NOT_FOUND);
        }

        byte[] imageBytes = imagesDTOOptional.get().getImageBytes();

        try {
            ImageIO.read(new ByteArrayInputStream(imageBytes));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            ByteArrayResource resource = new ByteArrayResource(imageBytes, headers.toString());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get picture by ID product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad request - invalid page or size parameters"),
            @ApiResponse(responseCode = "404", description = "Image not found")
    })
    @GetMapping("/image/productID")
    public ResponseEntity<?> getImageByIdProduct(@RequestParam Long productID) {
        if (productID == null || productID < 1) {
            return ResponseEntity.badRequest().body(null);
        }
        Optional<ProductDTO> productDTOOptional = productService.getById(productID);
        if (productDTOOptional.isEmpty()) {
            System.out.println("if (productDTOOptional.isEmpty())");
            return ResponseEntity.notFound().build();
        } else {
            ProductDTO productDTO = productDTOOptional.get();
            Product product = productMapper.toEntity(productDTO);
            Set<Images> images  = product.getImages();
            return new ResponseEntity<>(images, HttpStatus.OK);
        }
    }

    @Operation(summary  =  "Delete image by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Image not found")
    })
    @GetMapping(value = "/image/delete")
   public ResponseEntity<?> delete(@RequestParam @Valid Long imageIDForDelete) {
        Optional<ImagesDTO> imagesDTOOptional  = imageService.getImages(imageIDForDelete);
        if  (imagesDTOOptional.isEmpty())  {
            return new ResponseEntity<>("Image not found", HttpStatus.NOT_FOUND);
        }
        imageService.deleteImage(imageIDForDelete);
        return new ResponseEntity<>("Image deleted successfully", HttpStatus.OK);
    }
}
