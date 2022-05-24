package com.example.odbcapi.service;

import com.example.odbcapi.constant.Constants;
import com.example.odbcapi.model.order.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(transactionManager = "orderTransactionManager")
public class OrderService {

    private final ApplicationContext context;

    @Autowired
    @Qualifier("orderEntityManager")
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_ORDER)
    private EntityManager entityManager;

    public Order addNewOrder(Order order) {
        String sql = "INSERT INTO `order`(name, quality) VALUES (:name, :quality)";
        Query query = entityManager.createNativeQuery(sql, Order.class).setParameter("name", order.getName()).setParameter("quality", order.getQuality());
        query.executeUpdate();
        Query query1 = entityManager.createNativeQuery("select * from `order` ORDER BY id DESC LIMIT 1", Order.class);
        return (Order) query1.getSingleResult();
    }

    public List<Order> getAll() {
        String sql = "SELECT * FROM `order`";
        Query query = entityManager.createNativeQuery(sql, Order.class);
        return (List<Order>) query.getResultList();
    }

}
