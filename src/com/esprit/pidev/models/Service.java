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
public class Service {
    private int id_service;
    private String name ;

    public Service(int id_service, String name) {
        this.id_service = id_service;
        this.name = name;
    }

    public Service(String name) {
        this.name = name;
    }

    public Service(int id_service) {
        this.id_service = id_service;
    }

    public int getId_service() {
        return id_service;
    }

    public String getName() {
        return name;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return  name ;
    }
    
}
