/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;

/**
 *
 * @author ASUS
 */
public class Colis {
    int idC,etat,idKarhba,idExpediteur,TelDestinataire;
    String Depart,Destination,NomExpediteur,NomDestinataire,MailExpediteur,MailDestinataire,Categorie,Pickup;
    float Poids;
    int Nomcategorie;

    public Colis(int TelDestinataire) {
        this.TelDestinataire = TelDestinataire;
    }

    public Colis(String Depart, String Destination, float Poids,String Categorie) {
        this.Depart = Depart;
        this.Destination = Destination;
        this.Poids = Poids;
        this.Categorie=Categorie;
    }

    public Colis( int Id,int etat, int TelDestinataire, String Depart, String Destination, String NomExpediteur, String NomDestinataire, String MailExpediteur, String MailDestinataire, float Poids) {
        this.etat = etat;
        this.TelDestinataire = TelDestinataire;
        this.Depart = Depart;
        this.Destination = Destination;
        this.NomExpediteur = NomExpediteur;
        this.NomDestinataire = NomDestinataire;
        this.MailExpediteur = MailExpediteur;
        this.MailDestinataire = MailDestinataire;
        this.Poids = Poids;
        this.idC=Id;
    }
    public Colis(int etat, int TelDestinataire, String Depart, String Destination, String NomExpediteur, String NomDestinataire, String MailExpediteur, String MailDestinataire, float Poids) {
        this.etat = etat;
        this.TelDestinataire = TelDestinataire;
        this.Depart = Depart;
        this.Destination = Destination;
        this.NomExpediteur = NomExpediteur;
        this.NomDestinataire = NomDestinataire;
        this.MailExpediteur = MailExpediteur;
        this.MailDestinataire = MailDestinataire;
        this.Poids = Poids;
    }
    public Colis(String Depart, String Destination, String NomExpediteur, String MailExpediteur, float Poids, String NomDestinataire, String MailDestinataire,int TelDestinataire) {
        this.TelDestinataire = TelDestinataire;
        this.Depart = Depart;
        this.Destination = Destination;
        this.NomExpediteur = NomExpediteur;
        this.NomDestinataire = NomDestinataire;
        this.MailExpediteur = MailExpediteur;
        this.MailDestinataire = MailDestinataire;
        this.Poids = Poids;
    }

    public Colis() {
         }


    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdKarhba() {
        return idKarhba;
    }

    public void setIdKarhba(int idKarhba) {
        this.idKarhba = idKarhba;
    }

    public int getIdExpediteur() {
        return idExpediteur;
    }

    public void setIdExpediteur(int idExpediteur) {
        this.idExpediteur = idExpediteur;
    }

    public int getTelDestinataire() {
        return TelDestinataire;
    }

    public void setTelDestinataire(int TelDestinataire) {
        this.TelDestinataire = TelDestinataire;
    }

    public String getDepart() {
        return Depart;
    }

    public void setDepart(String Depart) {
        this.Depart = Depart;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public String getNomExpediteur() {
        return NomExpediteur;
    }

    public void setNomExpediteur(String NomExpediteur) {
        this.NomExpediteur = NomExpediteur;
    }

    public String getNomDestinataire() {
        return NomDestinataire;
    }

    public void setNomDestinataire(String NomDestinataire) {
        this.NomDestinataire = NomDestinataire;
    }

    public String getMailExpediteur() {
        return MailExpediteur;
    }

    public void setMailExpediteur(String MailExpediteur) {
        this.MailExpediteur = MailExpediteur;
    }

    public String getMailDestinataire() {
        return MailDestinataire;
    }

    public void setMailDestinataire(String MailDestinataire) {
        this.MailDestinataire = MailDestinataire;
    }

    public float getPoids() {
        return Poids;
    }

    public void setPoids(float Poids) {
        this.Poids = Poids;
    }

    public int getNomcategorie() {
        return Nomcategorie;
    }

    public void setNomcategorie(int Nomcategorie) {
        this.Nomcategorie = Nomcategorie;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    @Override
    public String toString() {
        return "Colis{" + "idC=" + idC + ", etat=" + etat + ", idKarhba=" + idKarhba + ", idExpediteur=" + idExpediteur + ", TelDestinataire=" + TelDestinataire + ", Depart=" + Depart + ", Destination=" + Destination + ", NomExpediteur=" + NomExpediteur + ", NomDestinataire=" + NomDestinataire + ", MailExpediteur=" + MailExpediteur + ", MailDestinataire=" + MailDestinataire + ", Categorie=" + Categorie + ", Poids=" + Poids + ", Nomcategorie=" + Nomcategorie + '}';
    }





    
}
