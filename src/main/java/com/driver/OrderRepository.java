package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class OrderRepository {

    HashMap<String,Order> orderDb = new HashMap<>();
    HashMap<Integer,DeliveryPartner> delDb = new HashMap<>();
    public Order addOrder(Order order){
       if(!orderDb.containsKey(order.getId())){
           orderDb.put(order.getId(),order);
           return orderDb.get(order.getId());
       }
       else{
          return null;
       }
    }

    public String addPartner(String partnerId) {
        return "";
    }
}
