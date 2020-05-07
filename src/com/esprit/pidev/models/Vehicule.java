/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author achref
 */
public class Vehicule {
 String matricule,marque,modele,cartegrise,couleur,type,position,destination,dateco,zone,accept_c; 
 int id,dispo,etat,archive;
 int places = 0;

    public Vehicule(String matricule, String marque, String modele, String cartegrise, String couleur,int places, String position, String accept_c, String destination) {
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.cartegrise = cartegrise;
        this.couleur = couleur;
        this.places=places;
        this.position = position;
        this.accept_c = accept_c;
        this.destination = destination;
        
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setCartegrise(String cartegrise) {
        this.cartegrise = cartegrise;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDateco(String dateco) {
        this.dateco = dateco;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setAccept_c(String accept_c) {
        this.accept_c = accept_c;
    }

    public void setDispo(int dispo) {
        this.dispo = dispo;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    
    
    
    

    public String getMatricule() {
        return matricule;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public String getCartegrise() {
        return cartegrise;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getType() {
        return type;
    }

    public String getPosition() {
        return position;
    }

    public String getDestination() {
        return destination;
    }

    public String getDateco() {
        return dateco;
    }

    public String getZone() {
        return zone;
    }

    public String getAccept_c() {
        return accept_c;
    }

    public int getDispo() {
        return dispo;
    }

    public int getEtat() {
        return etat;
    }

    public int getArchive() {
        return archive;
    }

    public int getPlaces() {
        return places;
    }

    public Vehicule(String matricule, String marque, String modele, String cartegrise, String couleur, String type, String position, String destination, String dateco, String zone, String accept_c, int dispo, int etat, int archive) {
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.cartegrise = cartegrise;
        this.couleur = couleur;
        this.type = type;
        this.position = position;
        this.destination = destination;
        this.dateco = dateco;
        this.zone = zone;
        this.accept_c = accept_c;
        this.dispo = dispo;
        this.etat = etat;
        this.archive = archive;
    }
        public Vehicule(String matricule){
        this.matricule = matricule;
    }
        public Vehicule(){
    }
            @Override
    public String toString() {
        return "\n Vehicule" + "N° " + id + "\n matricule :" + matricule + "\n marque :" + marque + "\n modele :" + modele +"\n couleur :" + couleur +"\n cartegrise :" + cartegrise +"\n places :" + places +"\n position :" + position +"\n Destination :" + destination +'"';
    //+"\n Tranfert coli :" +accept_c+
    }
}
