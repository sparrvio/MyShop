package com.shopapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@ApiModel(description = "Images details")
@Data
@Builder
public class ImagesDTO {
    @Schema(description = "Image id", example = "1")
    private Long id;
    @Schema(description = "Image name", example = "image1.jpg")
    private Long productId;
    @Schema(description = "Image bytes", example = "image1.jpg")
    private byte[] imageBytes;
}
