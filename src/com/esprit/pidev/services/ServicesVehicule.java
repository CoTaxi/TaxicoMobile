/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidev.utils.Statics;
import com.mycompany.myapp.entities.Vehicule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author achref
 */
public class ServicesVehicule {

    public ArrayList<Vehicule> vehicules;

    public static ServicesVehicule instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServicesVehicule() {
        req = new ConnectionRequest();
    }

    public static ServicesVehicule getInstance() {
        if (instance == null) {
            instance = new ServicesVehicule();
        }
        return instance;
    }

//        public boolean addTask(Vehicule v) {
//        String url = Statics.BASE_URL + "/tasks/new?name=" + v.getName() + "&status=" + v.getStatus();
//        req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
    public ArrayList<Vehicule> parseTasks(String jsonText) {
        try {
            vehicules = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Vehicule v = new Vehicule();
                float id = Float.parseFloat(obj.get("id").toString());
                v.setId((int) id);
//                

                //  v.setArchive(((int)Float.parseFloat(obj.get("archive").toString())));
                v.setMatricule(obj.get("matricule").toString());
                v.setMarque(obj.get("marque").toString());
                v.setModele(obj.get("modele").toString());
                v.setCouleur(obj.get("couleur").toString());
                v.setCartegrise(obj.get("cartegrise").toString());
                v.setPlaces(((int) Float.parseFloat(obj.get("places").toString())));
//                v.setType(obj.get("type").toString());
                // v.setDispo(((int)Float.parseFloat(obj.get("dispo").toString())));
                v.setPosition(obj.get("position").toString());
                v.setDestination(obj.get("destination").toString());

                // v.setEtat(((int)Float.parseFloat(obj.get("etat").toString())));
                // v.setDateco(obj.get("dateco").toString());       
//                v.setAccept_c(obj.get("accept_c").toString());
                //  v.setAccept_c(( obj.get("accept_c").toString()));
                // v.setAccept_c((String.(obj.get("accept_c").toString())));
                // v.setZone(obj.get("zone").toString());
                vehicules.add(v);
            }

        } catch (IOException ex) {

        }
        return vehicules;
    }

    public ArrayList<Vehicule> getAllVehicules() {
        String url = Statics.BASE_URL +"/T/listjsonVec";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                vehicules = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return vehicules;
    }
    
    

    public ArrayList<Vehicule> getVehicules(Vehicule v) {
        String url = Statics.BASE_URL + "/T/findjsonVec?matricule=" +v.getMatricule();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                vehicules = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return vehicules;
    }

    
    
    
    public boolean addvehicule(Vehicule t) {
        String url = Statics.BASE_URL + "/T/newVec?matricule=" + t.getMatricule() + "&marque=" + t.getMarque() + "&modele=" + t.getModele() + "&cartegrise=" + t.getCartegrise() + "&couleur=" + t.getCouleur() + "&place=" + t.getPlaces() + "&position=" + t.getPosition() + "&accept_c=" + t.getAccept_c() + "&destination=" + t.getDestination();
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

       public boolean deletevehicule(int t) {
        String url = Statics.BASE_URL + "/T/deletejson1Vec/"+t;
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

}
