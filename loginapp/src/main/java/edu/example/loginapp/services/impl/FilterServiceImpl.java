package edu.example.loginapp.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.example.loginapp.services.FilterService;

@Service
public class FilterServiceImpl implements FilterService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public <T> List<T> filter(String value, String column, Class<T> clazz) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);

        Root<T> item = cq.from(clazz);

        Predicate filterColumnPredicate = cb.equal(item.get(column), value);
        cq.where(filterColumnPredicate);

        return entityManager.createQuery(cq).getResultList();
    }

}
