/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;

/**
 *
 * @author walid
 */
public class Garage {
    private int id_garage,id_service ;
    private String name,address,name_service;

    public Garage(int id_garage, String name, String address, int id_service) {
        this.id_garage = id_garage;
        this.id_service = id_service;
        this.name = name;
        this.address = address;
    }

    public Garage(int id_garage,String name, String address, String name_service) {
        this.id_garage = id_garage;
        this.id_service = id_service;
        this.name = name;
        this.address = address;
        this.name_service = name_service;
    }

    public Garage(String name, String address,int id_service) {
        this.id_service = id_service;
        this.name = name;
        this.address = address;
    }

    public Garage(int id_garage) {
        this.id_garage = id_garage;
    }

    public int getId_garage() {
        return id_garage;
    }

    public int getId_service() {
        return id_service;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getName_service() {
        return name_service;
    }

    public void setName_service(String name_service) {
        this.name_service = name_service;
    }

    public void setId_garage(int id_garage) {
        this.id_garage = id_garage;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
