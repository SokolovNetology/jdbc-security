package ru.netology.jdbc.security.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import ru.netology.jdbc.security.entity.Customer;
import ru.netology.jdbc.security.entity.Order;
import ru.netology.jdbc.security.model.CustomerForRequest;
import ru.netology.jdbc.security.model.OrderForRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RepForHibernate implements Rep{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Customer addCustomer(CustomerForRequest customerForRequest) {
        Customer customer = Customer.builder()
                .name(customerForRequest.getName())
                .surname(customerForRequest.getSurname())
                .age(customerForRequest.getAge())
                .phone_number(customerForRequest.getPhone_number())
                .build();
        entityManager.persist(customer);
        return customer;
    }

    @Override
    @Transactional
    public Order addOrder(OrderForRequest orderForRequest) {
        Order order = Order.builder()
                .date(orderForRequest.getDate())
                .product_name(orderForRequest.getProduct_name())
                .customer(entityManager.getReference(Customer.class, orderForRequest.getCustomer_id()))
                .amount(orderForRequest.getAmount())
                .build();
        entityManager.persist(order);
        return order;
    }

    @Override
    public String getProductName(String name) {

        var query = entityManager.createQuery(
                "SELECT O FROM Order O JOIN O.customer C WHERE C.name = :name",
                Order.class
        );
        query.setParameter("name", name);
        List<Order> orders = query.getResultList();
        List<String> productNameList = new ArrayList<>();
        for (Order order:orders){
            productNameList.add(order.getProduct_name());
        }
        return productNameList.toString();
    }
}
