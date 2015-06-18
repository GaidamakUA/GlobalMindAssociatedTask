package com.blogspot.androidgaidamak.globalmindassociatedtask;

/**
 * Created by gaidamak on 6/18/15.
 */
public class Car {
    public String vehicleType;
    public String brand;
    public String color;
    public String state;

    public String contactName;
    public String contactPhone;

    public Car(String vehicleType, String brand, String color, String state, String contactName,
               String contactPhone) {
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.color = color;
        this.state = state;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
    }
}
