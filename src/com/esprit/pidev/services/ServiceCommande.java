package com.esprit.pidev.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidev.models.Commande;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author revecom
 */
public class ServiceCommande {
    public ArrayList<Commande> commande;
    
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCommande() {
         req =DataSource.getInstance().getRequest();
    }


    public boolean addCommande(Commande c) {
        String url = Statics.BASE_URL + "/T/newcmd?ptDepart=" + c.getPtDepart() + 
                "&ptArrivee="+ c.getPtArrivee() + 
                "&modePaiement="+ c.getModep() +
                "&prix="+ c.getPrix()+
                "&date="+ c.getDate() + 
                "&time="+ c.getTime(); 
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean deleteCommande(int id) {
        String url = Statics.BASE_URL + "/T/deletecmd?idCommande=" +id;

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; // Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }

    public ArrayList<Commande> parseCommandes(String jsonText){
        try {
            commande=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> commandesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)commandesListJson.get("root");
            for(Map<String,Object> obj : list){
                Commande c = new Commande();
                float id = Float.parseFloat(obj.get("idCommande").toString());
                String d=obj.get("ptDepart").toString();
                String a=obj.get("ptArrivee").toString();
                String m=obj.get("modepaiement").toString();
                String da=obj.get("date").toString();
                String t=obj.get("time").toString();
                commande.add(new Commande(d, a, m, da, t, 22f));
            }
            
            
        } catch (IOException ex) {
            
        }
        return commande;
    }
    
    public ArrayList<Commande> getAllCommandes(){
        String url = Statics.BASE_URL+"/T/allcmd";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commande = parseCommandes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commande;
    }
 public ArrayList<Commande> getCommande(int id) {
        String url = Statics.BASE_URL + "/T/findwa/"+ id;

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commande = parseCommande(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return commande;
    }
 public ArrayList<Commande> parseCommande(String jsonText) {
        try {
            commande = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int client = (int)Float.parseFloat(obj.get("client").toString());
                
           
                
                commande.add(new Commande(client));
            }

        } catch (IOException ex) {
        }

        return commande;
    }    
}
