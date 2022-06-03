package edu.example.loginapp.filter;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.example.loginapp.filter.entities.Pagination;
import edu.example.loginapp.filter.entities.ProductOrder;
import edu.example.loginapp.products.entities.ItemCondition;

@Repository
public class FilterRepo {

    @Autowired
    private EntityManager entityManager;

    public <T> List<T> filter(final Pagination pagination, final Class<T> clazz) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);

        Root<T> root = cq.from(clazz);

        Predicate predicate = !pagination.column().equals("itemCondition")
                ? getContainsPredicate(root, cb, pagination.value(), pagination.column())
                : getEqualPredicate(root, cb, pagination.value(), pagination.column());

        cq.where(predicate);
        Order order = isAscending(pagination.order())
                ? cb.asc(root.get(pagination.column()))
                : cb.desc(root.get(pagination.column()));

        cq.orderBy(order);
        TypedQuery<T> tq = entityManager.createQuery(cq);

        tq.setMaxResults(pagination.numberOfResults());
        tq.setFirstResult(pagination.startingPosition() * pagination.numberOfResults());

        List<T> items = tq.getResultList();

        return items;
    }

    private <T> Predicate getEqualPredicate(Root<T> root, CriteriaBuilder cb, String param, String column) {
        return cb.equal(root.get(column), ItemCondition.values()[Integer.parseInt(param)]);
    }

    private <T> Predicate getContainsPredicate(Root<T> root, CriteriaBuilder cb, String param, String column) {
        return cb.like(root.get(column), "%" + param + "%");
    }

    private boolean isAscending(final ProductOrder order) {
        return order.equals(ProductOrder.ASC);
    }

}
