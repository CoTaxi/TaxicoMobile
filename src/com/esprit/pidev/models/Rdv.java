/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;

import java.util.Date;

/**
 *
 * @author walid
 */
public class Rdv {
    private int id_rdv,id_chauffeur,id_garage,id_service ;
    private Date date_rdv;
    private String status,name_garage,name_service;

    public Rdv() {
    }

    public Rdv(Date date_rdv) {
        this.date_rdv = date_rdv;
    }

    public Rdv(int id_rdv, int id_chauffeur, Date date_rdv, int id_garage, int id_service, String status) {
        this.id_rdv = id_rdv;
        this.id_chauffeur = id_chauffeur;
        this.date_rdv = date_rdv;
        this.id_garage = id_garage;
        this.id_service = id_service;
        this.status = status;
    }

    public Rdv(int id_rdv, int id_chauffeur, int id_service, int id_garage, Date date_rdv, String status) {
        this.id_rdv = id_rdv;
        this.id_chauffeur = id_chauffeur;
        this.id_service = id_service;
        this.id_garage = id_garage;
        this.date_rdv = date_rdv;
        this.status = status;
    }
    

    public Rdv(int id_rdv, int id_chauffeur, String name_service, String name_garage,Date date_rdv, String status) {
        this.id_rdv = id_rdv;
        this.id_chauffeur = id_chauffeur;
        this.name_service = name_service;
        this.name_garage = name_garage;
        this.date_rdv = date_rdv;
        this.status = status;
    }

    public Rdv(int id_chauffeur, Date date_rdv, int id_garage, int id_service,  String status) {
        this.id_chauffeur = id_chauffeur;
        this.id_garage = id_garage;
        this.id_service = id_service;
        this.date_rdv = date_rdv;
        this.status = status;
    }

    public Rdv(int id_rdv, String status) {
        this.id_rdv = id_rdv;
        this.status = status;
    }

    public Rdv(Date date_rdv,int id_garage, int id_service, String status) {
        this.id_garage = id_garage;
        this.id_service = id_service;
        this.date_rdv = date_rdv;
        this.status = status;
    }

    public Rdv(int id_rdv) {
        this.id_rdv = id_rdv;
    }


    public String getName_garage() {
        return name_garage;
    }

    public void setName_garage(String name_garage) {
        this.name_garage = name_garage;
    }

    public void setName_service(String name_service) {
        this.name_service = name_service;
    }

    /*public Rdventitie(int id_rdv, Date date_rdv) {
    this.id_rdv = id_rdv;
    this.date_rdv = date_rdv;
    }*/
    public String getName_service() {
        return name_service;
    }

    public int getId_rdv() {
        return id_rdv;
    }

    public int getId_chauffeur() {
        return id_chauffeur;
    }

    public int getId_garage() {
        return id_garage;
    }

    public int getId_service() {
        return id_service;
    }

    public Date getDate_rdv() {
        return date_rdv;
    }

    public String getStatus() {
        return status;
    }

    public void setId_rdv(int id_rdv) {
        this.id_rdv = id_rdv;
    }

    public void setId_chauffeur(int id_chauffeur) {
        this.id_chauffeur = id_chauffeur;
    }

    public void setId_garage(int id_garage) {
        this.id_garage = id_garage;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public void setDate_rdv(Date date_rdv) {
        this.date_rdv = date_rdv;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String toString() {
        return "Rdv{" + "idRdv= " + id_rdv + ", idChauffeur= " + id_chauffeur + ", Service= " + name_service +", Garage= " + name_garage +", dateRdv= " + date_rdv +", Status= " + status + '}';
    }
     
}
