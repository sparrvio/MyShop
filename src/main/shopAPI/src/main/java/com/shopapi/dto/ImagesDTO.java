package com.shopapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@ApiModel(description = "Images details")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ImagesDTO {
    @Schema(description = "Image id", example = "1")
    private Long id;
    @Schema(description = "Image name", example = "image1.jpg")
    private Long productId;
    @Schema(description = "Image bytes", example = "image1.jpg")
    private byte[] imageBytes;

//    public Long getId() {
//        return id;
//    }
}
