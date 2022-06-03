package edu.example.loginapp.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.example.loginapp.filter.entities.Pagination;

@Service
public class FilterService implements IFilterService {
    @Autowired
    private FilterRepo filterRepo;

    @Override
    public <T> List<T> filter(Pagination pagination, Class<T> clazz) {
        return this.filterRepo.filter(pagination, clazz);
    }

}
