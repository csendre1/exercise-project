package edu.example.loginapp.services.impl;

import java.util.List;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.example.loginapp.model.ItemCondition;
import edu.example.loginapp.services.FilterService;

@Service
public class FilterServiceImpl implements FilterService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public <T> List<T> filter(String value, String column, Class<T> clazz, int pageNum, int numberOfResults) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);

        Root<T> root = cq.from(clazz);

        Predicate predicate = !column.equals("itemCondition") ? getContainsPredicate(root, cb, value, column)
                : getEqualPredicate(root, cb, value, column);

        cq.where(predicate);
        cq.orderBy(cb.asc(root.get(column)));
        TypedQuery<T> tq = entityManager.createQuery(cq);

        tq.setMaxResults(numberOfResults);
        tq.setFirstResult(pageNum * numberOfResults);

        return tq.getResultList();
    }

    private <T> Predicate getEqualPredicate(Root<T> root, CriteriaBuilder cb, String param, String column) {
        return cb.equal(root.get(column), ItemCondition.values()[Integer.parseInt(param)]);
    }

    private <T> Predicate getContainsPredicate(Root<T> root, CriteriaBuilder cb, String param, String column) {
        return cb.like(root.get(column), "%" + param + "%");
    }

}
