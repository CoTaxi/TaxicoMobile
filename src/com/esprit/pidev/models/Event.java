package com.esprit.pidev.models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;

/**
 *
 * @author mahdi
 */
public class Event {
    int id,duree,capacite,etat,place;
    String nom,emplacement,date_debut;
    String date_fin;

    public Event(int id, int duree, int capacite, int etat, String nom, String emplacement,int place) {
        this.id = id;
        this.duree = duree;
        this.capacite = capacite;
        this.etat = etat;
        this.nom = nom;
        this.emplacement = emplacement;
        this.place=place;
    }

    public Event(int duree, int capacite, String nom, String emplacement, String date_fin) {
        this.duree = duree;
        this.capacite = capacite;
        this.nom = nom;
        this.emplacement = emplacement;
        this.date_fin = date_fin;
    }

    public int getId() {
        return id;
    }

    public int getDuree() {
        return duree;
    }

    public int getCapacite() {
        return capacite;
    }

    public int getEtat() {
        return etat;
    }

    public String getNom() {
        return nom;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
    
}
