package edu.example.loginapp.filter;

import java.util.List;

public interface IFilterService {

    <T> List<T> filter(String value, String column, Class<T> clazz, int pageNum, int numberOfResults);
}
