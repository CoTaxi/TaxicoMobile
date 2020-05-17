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
public class User {
    private int id ; 
    private String nom ; 
    private String prenom ; 
    private int tel;
    private String mail;
    private String mdp ; 
    private String naissance;
    private String creation ; 
    private int active ; 
    private String image;
    private String type;
    private int cin=0;
    private int permis=0;
    private String nomCompte="none";
    private int ribCompte=0;
    private int experience = 0 ;
    private int nbrCourse=0;
    private int pointFidelite=0;
    private String nomEvent="none";
    private String username ; 
    private String username_canonical;
    private String email ; 
    private String email_canonical;
    private Boolean enabled ; 
    private String salt ; 
    private String password;
    private String last_login ; 
    private String confirmation_token;
    private String password_requested_at;
    private String roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public User(String nom, String prenom, String username, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(int id,String type) 
    {
        this.id = id;
        this.type=type;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public User(int id, String type, String username, String email,String nomEvent) {
        this.id = id;
        this.type = type;
        this.username = username;
        this.email = email;
        this.nomEvent=nomEvent;
    }
    public User(int id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.prenom=prenom;
    }
    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public User(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = email;
        this.mdp = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public String getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(String password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", mail=" + mail + ", mdp=" + mdp + ", naissance=" + naissance + ", creation=" + creation + ", active=" + active + ", image=" + image + ", type=" + type + ", cin=" + cin + ", permis=" + permis + ", nomCompte=" + nomCompte + ", ribCompte=" + ribCompte + ", experience=" + experience + ", nbrCourse=" + nbrCourse + ", pointFidelite=" + pointFidelite + ", nomEvent=" + nomEvent + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", last_login=" + last_login + ", confirmation_token=" + confirmation_token + ", password_requested_at=" + password_requested_at + ", roles=" + roles + '}';
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNaissance() {
        return naissance;
    }

    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }

    public String getCreation() {
        return creation;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getPermis() {
        return permis;
    }

    public void setPermis(int permis) {
        this.permis = permis;
    }

    public String getNomCompte() {
        return nomCompte;
    }

    public void setNomCompte(String nomCompte) {
        this.nomCompte = nomCompte;
    }

    public int getRibCompte() {
        return ribCompte;
    }

    public void setRibCompte(int ribCompte) {
        this.ribCompte = ribCompte;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getNbrCourse() {
        return nbrCourse;
    }

    public void setNbrCourse(int nbrCourse) {
        this.nbrCourse = nbrCourse;
    }

    public int getPointFidelite() {
        return pointFidelite;
    }

    public void setPointFidelite(int pointFidelite) {
        this.pointFidelite = pointFidelite;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }
     
       
}
