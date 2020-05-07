/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;

/**
 *
 * @author Oussama_RMILI
 */
public class typeReclamation {
    
    private int id ; 
    private String titre ; 

    public typeReclamation(String titre) {
        this.titre = titre;
    }

    public typeReclamation(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
}
