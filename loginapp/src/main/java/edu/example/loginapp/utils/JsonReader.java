package edu.example.loginapp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

    public static <T> T convertJsonToObject(final String json, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (Exception e) {
            throw new IllegalArgumentException("Can't convert Json to object.");
        }
    }
}
