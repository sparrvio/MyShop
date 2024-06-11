package com.shopapi.mapper;

import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ProductDTO;
import com.shopapi.dto.SupplierDTO;
import com.shopapi.model.Supplier;
import io.swagger.v3.oas.annotations.media.Schema;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, ProductMapper.class})
public interface SupplierMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target  =  "supplierName")
    @Mapping(source  =  "address_id", target  =  "supplierAddress")
    @Mapping(source  =  "phone_number", target  =  "supplierPhone")
    SupplierDTO toDTO(Supplier supplier);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "supplierName", target  =  "name")
    @Mapping(source  =  "supplierAddress", target  =  "address_id")
    @Mapping(source  =  "supplierPhone", target  =  "phone_number")
    Supplier toSupplier(SupplierDTO supplierDTO);
}