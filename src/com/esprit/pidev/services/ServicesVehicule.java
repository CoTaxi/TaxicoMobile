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
import com.esprit.pidev.models.Vehicule;
import com.esprit.pidev.utils.Statics;
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
                v.setMatricule(obj.get("matricule").toString());
                v.setMarque(obj.get("marque").toString());
                v.setModele(obj.get("modele").toString());
                v.setCouleur(obj.get("couleur").toString());
                v.setCartegrise(obj.get("cartegrise").toString());
                v.setPlaces(((int) Float.parseFloat(obj.get("places").toString())));
                v.setPosition(obj.get("position").toString());
                v.setDestination(obj.get("destination").toString());
                vehicules.add(v);
            }

        } catch (IOException ex) {

        }
        return vehicules;
    }
    
    public ArrayList<Vehicule> parsePositon(String jsonText) {
        try {
            vehicules = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Vehicule v = new Vehicule();
                v.setPosition(obj.get("position").toString());
                vehicules.add(v);
            }

        } catch (IOException ex) {

        }
        return vehicules;
    }
    public ArrayList<Vehicule> parseVehicule(String jsonText) {
        try {
            vehicules = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Vehicule v = new Vehicule();
                float id = Float.parseFloat(obj.get("id").toString());
                v.setId((int) id);  
                v.setMatricule(obj.get("matricule").toString());
                v.setMarque(obj.get("marque").toString());
                v.setModele(obj.get("modele").toString());
                v.setCouleur(obj.get("couleur").toString());
                v.setPlaces(((int) Float.parseFloat(obj.get("places").toString())));
                v.setType(obj.get("type").toString());
//                v.setDispo(((int)Float.parseFloat(obj.get("dispo").toString())));
                v.setUser(((int)Float.parseFloat(obj.get("user").toString())));
                v.setPosition(obj.get("position").toString());
                v.setDestination(obj.get("destination").toString());
               // v.setDateco(obj.get("dateco").toString());       
                vehicules.add(v);
            }

        } catch (IOException ex) {

        }
        return vehicules;
    }

    public ArrayList<Vehicule> getAllVehicules() {
        String url = Statics.BASE_URL +"/T/listjsonVec?user="+Statics.sessionID;
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
    public ArrayList<Vehicule> getPosition() {
        String url = Statics.BASE_URL +"/T/position/"+Statics.sessionID;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                vehicules = parsePositon(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return vehicules;
    }
    
    public ArrayList<Vehicule> toutvec() {
        String url = Statics.BASE_URL +"/T/toutvec";
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

    public ArrayList<Vehicule> findPosition(String position,String type) {
        String url = Statics.BASE_URL + "/T/findPosition/"+position+"/"+type;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                vehicules = parseVehicule(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return vehicules;
    }   
    public ArrayList<Vehicule> findvec(String position,String type) {
        String url = Statics.BASE_URL + "/T/findvec/"+position+"/"+type;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                vehicules = parseVehicule(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return vehicules;
    }      
    
    public boolean addvehicule(Vehicule t) {
        String url = Statics.BASE_URL + "/T/newVec?matricule=" + t.getMatricule() + "&marque=" + t.getMarque() + "&modele=" + t.getModele()  + "&couleur=" + t.getCouleur() + "&cartegrise=" + t.getCartegrise()+ "&place=" + t.getPlaces() + "&position=" + t.getPosition() + "&accept_c=" + t.getAccept_c() + "&destination=" + t.getDestination()+"&user="+Statics.sessionID;
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
    public boolean updateposition(Vehicule t) {
        String url = Statics.BASE_URL + "/T/updateposition/" + Statics.sessionID + "?position=" + t.getPosition();
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

 public void updateDispo(int iddispo,int id) {
        String url = Statics.BASE_URL + "/T/updatevec/"+id+"?dispo="+iddispo;

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; // Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

//        return responseResult;
    }
 
 public ArrayList<Vehicule> parseVec(String jsonText) {
        try {
            vehicules = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                int dispo =  (int)Float.parseFloat(obj.get("dispo").toString());
           
                
                vehicules.add(new Vehicule(id, dispo));
            }

        } catch (IOException ex) {
        }

        return vehicules;
    }

 public ArrayList<Vehicule> getReservedCar() {
        String url = Statics.BASE_URL + "/T/finddispo/"+ Statics.sessionID;

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                vehicules = parseVec(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return vehicules;
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
       
        public boolean updatevec(int id, String matricule) {
        String url = Statics.BASE_URL + "/T/modifiervec/"+id+"/"+matricule;

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }   

}
