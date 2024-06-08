package com.shopapi.controller;

import com.shopapi.service.ImageService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@OpenAPIDefinition(info = @Info(title = "Images API", version = "v1"))
@RestController
@RequestMapping("/api/v1")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Operation(summary = "Create new image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "222", description = "Client created successfully"),
            @ApiResponse(responseCode = "404", description = "Bad request - invalid client data or gender"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/image/create") // для отправки данных через HTML форму в теле запроса (POST)
    public ResponseEntity<String> saveImage(@RequestParam byte[] imageData,   @RequestParam Long product_id)  {

    }


    }
