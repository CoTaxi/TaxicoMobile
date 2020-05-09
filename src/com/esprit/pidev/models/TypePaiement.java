/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.models;

import java.util.Date;

/**
 *
 * @author revecom
 */
public class TypePaiement {
    private int id,numCarte,codeSec;
    private Date dateExp;

    public TypePaiement(int numCarte, int codeSec, Date dateExp) {
        this.numCarte = numCarte;
        this.codeSec = codeSec;
        this.dateExp = dateExp;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumCarte() {
        return numCarte;
    }

    public void setNumCarte(int numCarte) {
        this.numCarte = numCarte;
    }

    public int getCodeSec() {
        return codeSec;
    }

    public void setCodeSec(int codeSec) {
        this.codeSec = codeSec;
    }

    public Date getDateExp() {
        return dateExp;
    }

    public void setDateExp(Date dateExp) {
        this.dateExp = dateExp;
    }
    
    
}
