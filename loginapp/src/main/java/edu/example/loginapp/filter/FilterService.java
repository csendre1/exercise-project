package edu.example.loginapp.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilterService implements IFilterService {
    @Autowired
    private FilterRepo filterRepo;

    @Override
    public <T> List<T> filter(String value, String column, Class<T> clazz, int pageNum, int numberOfResults) {
        return null;
    }

}
