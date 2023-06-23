package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

//    this map mapping order with order Id
    HashMap<String,Order> orderDb = new HashMap<>();

//    this map for partner and partner Id
    HashMap<String,DeliveryPartner> partnerDb = new HashMap<>();

//    this map is for pair of partner and order pairs
    HashMap<String, String> orderPartnerDb = new HashMap<>();

//    this map for partner Id and all order that partner carrying
    HashMap<String, List<String>> partnerOrderDb = new HashMap<>();

    public void addOrder(Order order){
      orderDb.put(order.getId(),order);
    }

    public void addPartner(String partnerId) {
        partnerDb.put(partnerId,new DeliveryPartner(partnerId));
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        if(orderDb.containsKey(orderId) && partnerDb.containsKey(partnerId)){
            orderPartnerDb.put(orderId,partnerId);

            List<String> currOrders = new ArrayList<>();

            if(partnerOrderDb.containsKey(partnerId)){
                currOrders = partnerOrderDb.get(partnerId);
            }

            currOrders.add(orderId);

            partnerOrderDb.put(partnerId,currOrders);

//            increase the size of orders carrying by partner
            DeliveryPartner deliveryPartner = partnerDb.get(partnerId);
            deliveryPartner.setNumberOfOrders(currOrders.size());
        }
    }


    public Order getOrderById(String orderId) {
        return orderDb.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return partnerDb.get(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return partnerOrderDb.get(partnerId).size();
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return partnerOrderDb.get(partnerId);
    }

    public List<String> getAllOrders() {
        List<String> curr = new ArrayList<>();

        for(String order : orderDb.keySet()){
            curr.add(order);
        }

        return curr;
    }

    public Integer getCountOfUnassignedOrders() {
        return orderDb.size() - orderPartnerDb.size();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(int time, String partnerId) {
        int count = 0;

        List<String> orders = partnerOrderDb.get(partnerId);

        for(String orderId : orders){
            int deliveryTime = orderDb.get(orderId).getDeliveryTime();
            if(deliveryTime > time){
                count++;
            }
        }

        return count;
    }

    public int getLastDeliveryTimeByPartnerId(String partnerId) {
        int maxTime = 0;

        List<String> orders = partnerOrderDb.get(partnerId);

        for(String orderId : orders){
            int currTime = orderDb.get(orderId).getDeliveryTime();
            maxTime = Math.max(maxTime,currTime);
        }

        return maxTime;
    }

    public void deletePartnerById(String partnerId) {
        partnerDb.remove(partnerId);

        List<String> listOfOrder = partnerOrderDb.get(partnerId);
        partnerOrderDb.remove(partnerId);

        for(String order : listOfOrder){
            orderPartnerDb.remove(order);
        }


    }

    public void deleteOrderById(String orderId) {
        orderDb.remove(orderId);

        String partnerId = orderPartnerDb.get(orderId);
        orderPartnerDb.remove(orderId);

        partnerOrderDb.get(partnerId).remove(orderId);

        partnerDb.get(partnerId).setNumberOfOrders(partnerOrderDb.get(partnerId).size());
    }
}
