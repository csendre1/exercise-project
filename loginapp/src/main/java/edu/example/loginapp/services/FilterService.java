package edu.example.loginapp.services;

import java.util.List;

public interface FilterService {

    <T> List<T> filter(String value, String column, Class<T> clazz);
}
