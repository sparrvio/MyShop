package com.shopapi.service;

import java.util.Optional;

public interface BaseService {
    Optional<Object> getById(long id, Class<?> type);
}
