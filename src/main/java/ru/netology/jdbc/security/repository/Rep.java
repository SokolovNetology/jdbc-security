package ru.netology.jdbc.security.repository;

import ru.netology.jdbc.security.entity.Customer;
import ru.netology.jdbc.security.entity.Order;
import ru.netology.jdbc.security.model.CustomerForRequest;
import ru.netology.jdbc.security.model.OrderForRequest;

public interface Rep {
    public Customer addCustomer(CustomerForRequest customer);
    public Order addOrder(OrderForRequest order);
    public String getProductName(String name);
}
