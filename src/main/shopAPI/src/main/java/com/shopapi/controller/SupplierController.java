package com.shopapi.controller;

import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ProductDTO;
import com.shopapi.dto.SupplierDTO;
import com.shopapi.service.ProductService;
import com.shopapi.service.SupplierService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@OpenAPIDefinition(info = @Info(title = "Supplier API", version = "v1"))
@RestController
@RequestMapping("/api/v1")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ProductService productService;


    @Operation(summary = "Get supplier by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad request - invalid ID"),
            @ApiResponse(responseCode = "404", description = "Supplier not found")
    })
    @GetMapping("/supplier/supplierId")
    public ResponseEntity<?> getClient(@RequestParam(required = false) Long supplierId) {
        if (supplierId < 1 || supplierId == null) {
            return new ResponseEntity<>("Invalid ID", HttpStatus.BAD_REQUEST);
        }
        Optional<SupplierDTO> supplierDtoOpt = supplierService.findById(supplierId);
        if (supplierDtoOpt.isEmpty()) {
            return new ResponseEntity<>("Supplier not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplierDtoOpt.get(), HttpStatus.OK);
    }


    @Operation(summary = "Get all suppliers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suppliers fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Bad request - invalid client data or gender"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("supplier/getAll")
    public ResponseEntity<?> getAllSuppliers() {
        List<SupplierDTO> suppliers = supplierService.findAll();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @Operation(summary = "Create new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "222", description = "Supplier created successfully"),
            @ApiResponse(responseCode = "404", description = "Bad request - invalid client data or gender"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/supplier/create")
    public ResponseEntity<?> createSupplier(@RequestParam String name, @RequestParam String phone) {

        if (!phone.matches("^\\+7[-.\\s]*(\\d{3})[-.\\s]*(\\d{3})[-.\\s]*(\\d{2})[-.\\s]*(\\d{2})$")) {
            return new ResponseEntity<>("Bad request - invalid format of phone number", HttpStatus.BAD_REQUEST);
        }
        SupplierDTO supplierDTO = SupplierDTO.builder()
                .supplierName(name)
                .supplierPhone(phone)
                .build();
        supplierService.save(supplierDTO);
        return new ResponseEntity<>("Supplier created successfully", HttpStatus.CREATED);
    }

    @Operation(hidden = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @GetMapping("/supplier/updateAddress") // PUT через GET для отправки данных через HTML форму в теле запроса
    public ResponseEntity<?> updateAddressForHtml(@RequestParam Long id, @RequestParam String country, @RequestParam String city, @RequestParam String street) {
        Optional<SupplierDTO> supplierDTOOptional = supplierService.findById(id);
        if (supplierDTOOptional.isEmpty()) {
            return new ResponseEntity<>("Supplier not found", HttpStatus.NOT_FOUND);
        }
        AddressDTO newAddress = AddressDTO.builder()
                .country(country)
                .city(city)
                .street(street)
                .build();
        supplierService.updateAddress(id, newAddress);
        return new ResponseEntity<>("Address updated successfully", HttpStatus.OK);
    }

    @Operation(summary = "Update address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @PutMapping("/supplier/{id}/updateAddress")
    public ResponseEntity<?> updateAddressForPostman(@PathVariable Long id, @RequestBody AddressDTO newAddress) {
        Optional<SupplierDTO> supplierDTOOptional = supplierService.findById(id);
        if (supplierDTOOptional.isEmpty()) {
            return new ResponseEntity<>("Supplier not found", HttpStatus.NOT_FOUND);
        }
        supplierService.updateAddress(id, newAddress);
        return new ResponseEntity<>("Address updated successfully", HttpStatus.OK);
    }

    @Operation(summary = "Delete supplier by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supplier deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Supplier not found")
    })
    @DeleteMapping(value = "/supplier/deleteSupplierById/{idDelete}")
    public ResponseEntity<?> deleteSupplierByIdForPostman(@PathVariable Long idDelete)  {
        Optional<SupplierDTO> supplierDTOOptional = supplierService.findById(idDelete);
        if (supplierDTOOptional.isEmpty()) {
            return new ResponseEntity<>("Supplier not found", HttpStatus.NOT_FOUND);
        }
        supplierService.deleteById(idDelete);
        return new ResponseEntity<>("Supplier deleted successfully", HttpStatus.OK);
    }
    @Operation(hidden = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supplier deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Supplier not found")
    })
    @GetMapping(value = "/supplier/deleteSupplierById")
    public ResponseEntity<?> deleteSupplierById(@RequestParam @Valid Long idDelete) {
        Optional<SupplierDTO> supplierDTOOptional = supplierService.findById(idDelete);
        if (supplierDTOOptional.isEmpty()) {
            return new ResponseEntity<>("Supplier not found", HttpStatus.NOT_FOUND);
        }
        supplierService.deleteById(idDelete);
        return new ResponseEntity<>("Supplier deleted successfully", HttpStatus.OK);
    }

    @Operation(summary = "Add product for supplier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added successfully"),
            @ApiResponse(responseCode = "404", description = "Product or supplier not found")
    })
    @PutMapping("/supplier/{idSupplier}/addProduct")
    public ResponseEntity<?> addProductForPostman(@PathVariable Long idSupplier, @RequestBody() Object body)  {
        long idProduct = (Long) body;
        Optional<SupplierDTO> supplierDTOOptional = supplierService.findById(idSupplier);
        Optional<ProductDTO> productDTOOptional = productService.getById(idProduct);
        if (supplierDTOOptional.isEmpty() || productDTOOptional.isEmpty()) {
            return new ResponseEntity<>("Supplier or Product not found", HttpStatus.NOT_FOUND);
        }
        try {
            supplierService.updateProductInSupplier(idSupplier, productDTOOptional.get());
        } catch (NoSuchElementException ex)  {
            return new ResponseEntity<>("This product  already exists with this supplier", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Product added successfully", HttpStatus.OK);
    }
    @Operation(hidden = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added successfully"),
            @ApiResponse(responseCode = "404", description = "Product or supplier not found")
    })
    @GetMapping("/supplier/addProduct")
    public ResponseEntity<?> addProduct(@RequestParam Long idSupplier, @RequestParam Long idProduct) {
        Optional<SupplierDTO> supplierDTOOptional = supplierService.findById(idSupplier);
        Optional<ProductDTO> productDTOOptional = productService.getById(idProduct);
        if (supplierDTOOptional.isEmpty() || productDTOOptional.isEmpty()) {
            return new ResponseEntity<>("Supplier or Product not found", HttpStatus.NOT_FOUND);
        }
        try {
            supplierService.updateProductInSupplier(idSupplier, productDTOOptional.get());
        } catch (NoSuchElementException ex)  {
            return new ResponseEntity<>("This product  already exists with this supplier", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Product added successfully", HttpStatus.OK);
    }
}
