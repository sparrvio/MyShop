package com.shopapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long id;
    private String country;
    private String city;
    private String street;
}

