package com.shopapi.controller;

import com.shopapi.service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseController {
    private BaseService baseService;
//    protected ResponseEntity<?> getEntityById(long id){
//
//    }
}
