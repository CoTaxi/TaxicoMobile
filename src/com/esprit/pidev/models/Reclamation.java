/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;

import java.text.SimpleDateFormat;

/**
 *
 * @author Oussama_RMILI
 */
public class Reclamation {
    private int id_reclamation ; 
    private String message ; 
    private String etat;
    private String reponse  ;
    private int id_vh ; 
    private String prename; 
    private String type ; 
    private String date ;
    
    //private String date_rec ;
   java.util.Date dt = new java.util.Date();
   java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   String date_rec = sdf.format(dt);

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public Reclamation() {
    }
  
    public Reclamation(String message) {
        
        this.message = message;
    }

//    public Reclamation(int id_reclamation, String type, String message, String etat, String date_rec, String reponse) {
//        this.id_reclamation=id_reclamation; 
//        this.type=type;
//        this.message = message;
//        this.etat = etat;
//        this.date_rec=date_rec;
//        this.reponse=reponse;
//    }

    public Reclamation(int id_reclamation, String type, String message, String etat, String date_rec, String reponse, String prename) {
        this.id_reclamation = id_reclamation;
        this.type=type ; 
        this.message = message;
        this.etat = etat;
        this.date_rec=date_rec;
        this.reponse=reponse;
        this.prename=prename;
    }

    public Reclamation(int id_reclamation, String message, String etat, String type, String date, String rep) {
        this.id_reclamation = id_reclamation;
        this.message = message;
        this.reponse = rep;
        this.etat = etat;
        this.type = type;
        this.date = date;
        
    }

    public Reclamation(int idR, String message, String etat) {
        
    }

    public Reclamation(String message, String etat) {
        this.message = message;
        this.etat = etat;
    }
    
    

    public int getId_vh() {
        return id_vh;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public String getMessage() {
        return message;
    }

    public String getEtat() {
        return etat;
    }
    
    public String getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(String date_rec) {
        this.date_rec = date_rec;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
      public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getReponse() {
        return reponse;
    }

    public void setId_vh(int id_vh) {
        this.id_vh = id_vh;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getPrename() {
        return prename;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", message=" + message + ", etat=" + etat + '}';
    }
    
    
    
}
