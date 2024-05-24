package com.shopapi.mapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<Date, String> {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public String convertToDatabaseColumn(Date attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            return dateFormat.format(attribute);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error formatting date: " + e.getMessage(), e);
        }
    }

    @Override
    public Date convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return dateFormat.parse(dbData);
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing date: " + e.getMessage(), e);
        }
    }
}
