package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    public Order addOrder(Order order) {
        return orderRepository.addOrder(order);
    }

    public String addpartner(String partnerId) {
        return orderRepository.addPartner(partnerId);
    }
}
