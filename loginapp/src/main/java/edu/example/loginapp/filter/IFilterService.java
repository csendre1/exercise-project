package edu.example.loginapp.filter;

import java.util.List;

import edu.example.loginapp.filter.entities.Pagination;

public interface IFilterService {

    <T> List<T> filter(Pagination pagination, Class<T> clazz);
}
