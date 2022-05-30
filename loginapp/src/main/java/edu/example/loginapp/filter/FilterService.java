package edu.example.loginapp.filter;

import java.util.List;

public class FilterService implements IFilterService {

    @Override
    public <T> List<T> filter(String value, String column, Class<T> clazz, int pageNum, int numberOfResults) {
        return null;
    }

}
