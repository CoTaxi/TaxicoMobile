package com.esprit.pidev.models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author revecom
 */
public class Commande {
    
    private int idCommande,client,vehicule;
    private String ptDepart,ptArrivee, modep,date,time;
    
    private float prix;
   

    public Commande() {
    }

    public Commande(int client) {
        this.client = client;
    }

    public Commande(String ptDepart, String ptArrivee, String modep, String date, String time, float prix) {
        this.ptDepart = ptDepart;
        this.ptArrivee = ptArrivee;
        this.modep = modep;
        this.date = date;
        this.time = time;
        this.prix = prix;
    }

    public Commande(int idCommande, String ptDepart, String ptArrivee, String modep, String date, String time, float prix) {
        this.idCommande = idCommande;
        this.ptDepart = ptDepart;
        this.ptArrivee = ptArrivee;
        this.modep = modep;
        this.date = date;
        this.time = time;
        this.prix = prix;
    }

    public Commande(int client, int vehicule, String ptDepart, String ptArrivee, String modep, String date, String time) {
        this.client = client;
        this.vehicule = vehicule;
        this.ptDepart = ptDepart;
        this.ptArrivee = ptArrivee;
        this.modep = modep;
        this.date = date;
        this.time = time;
    }

    

    

    public String getModep() {
        return modep;
    }

    public void setModep(String modep) {
        this.modep = modep;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public String getPtDepart() {
        return ptDepart;
    }

    public void setPtDepart(String ptDepart) {
        this.ptDepart = ptDepart;
    }

    public String getPtArrivee() {
        return ptArrivee;
    }

    public void setPtArrivee(String ptArrivee) {
        this.ptArrivee = ptArrivee;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getVehicule() {
        return vehicule;
    }

    public void setVehicule(int vehicule) {
        this.vehicule = vehicule;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCommande=" + idCommande + ", client=" + client + ", vehicule=" + vehicule + ", ptDepart=" + ptDepart + ", ptArrivee=" + ptArrivee + ", modep=" + modep + ", date=" + date + ", time=" + time + ", prix=" + prix + '}';
    }

    
    
    
    

}