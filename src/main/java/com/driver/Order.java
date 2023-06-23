package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

//        convert the time into int by split the string
        this.id = id;
       String time[] = deliveryTime.split(":");
       this.deliveryTime = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }
}
