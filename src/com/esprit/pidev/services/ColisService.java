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
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aissa
 */
public class ColisService {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Colis> Colis;
    int teldest;


    public ColisService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean addcolis(Colis colis,int id) {
       String url = Statics.BASE_URL + "/C/new/"+id+"?Depart=" + colis.getDepart()+ "&Destination=" + colis.getDestination()+ "&NomExpediteur=" + colis.getNomExpediteur()+ "&MailExpediteur=" + colis.getMailExpediteur()+ "&Poids=" + colis.getPoids()+ "&NomDestinataire=" + colis.getNomDestinataire()+ "&MailDestinataire=" + colis.getMailDestinataire()+ "&TelDestinataire=" + colis.getTelDestinataire();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
     public boolean modifycolis(int Id,Colis colis)
     {
      String url = Statics.BASE_URL + "/C/Modifier/"+Id+"?Depart=" + colis.getDepart()+ "&Destination=" + colis.getDestination()+ "&NomExpediteur=" + colis.getNomExpediteur()+ "&MailExpediteur=" + colis.getMailExpediteur()+ "&Poids=" + colis.getPoids()+ "&NomDestinataire=" + colis.getNomDestinataire()+ "&MailDestinataire=" + colis.getMailDestinataire()+ "&TelDestinataire=" + colis.getTelDestinataire();


        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
     public boolean deletecolis(int Id)
     {
      String url = Statics.BASE_URL + "/C/Supprimer/"+Id;
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
return responseResult;
    }
     public boolean affecterColis(int Id,String matricule)
     {
      String url = Statics.BASE_URL + "/C/Affecter/"+Id+"/"+matricule;
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
return responseResult;
    }
     public boolean accepterColis(int Id)
     {
      String url = Statics.BASE_URL + "/C/accepter/"+Id;
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
return responseResult;
    }
     public boolean refusererColis(int Id)
     {
      String url = Statics.BASE_URL + "/C/Refus/"+Id;
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
return responseResult;
    }
     public boolean majColis(int Id)
     {
      String url = Statics.BASE_URL + "/C/MAJ/"+Id;
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
return responseResult;
    }

    public ArrayList<Colis> getAllColis(int id) {
        String url = Statics.BASE_URL + "/C/all/"+id;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Colis = parseColis(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Colis;
    }
    public ArrayList<Colis> getColis(int Id) {
        String url = Statics.BASE_URL + "/C/find/"+Id;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Colis = parseColis(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Colis;
    }
        public ArrayList<Colis> search(String depart) {
        String url = Statics.BASE_URL + "/C/findbydepart/"+depart;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Colis = parseColis(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Colis;
    }
    public ArrayList<Colis> ListeDemandes(String matricule) {
        String url = Statics.BASE_URL + "/C/ListeDemandes/"+matricule;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Colis = parseColis(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Colis;
    }
  
    public ArrayList<Colis> parseColis(String jsonText) {
        try {
            Colis = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {
                int teldest = (int)Float.parseFloat(obj.get("TelDestinataire").toString());
                int Id = (int)Float.parseFloat(obj.get("Id").toString());
                String depart = obj.get("Depart").toString();
                String destination = obj.get("Destination").toString();
                String nomexp = obj.get("NomExpediteur").toString();
                String nomdest = obj.get("NomDestinataire").toString();
                String mailexp = obj.get("MailExpediteur").toString();
                String maildest = obj.get("MailDestinataire").toString();
                float poids =  (float)Float.parseFloat(obj.get("Poids").toString());
                int etat = (int)Float.parseFloat(obj.get("Etat").toString());
                
                Colis.add(new Colis(Id,etat,teldest,depart,destination,nomexp,nomdest,mailexp,maildest,poids));   
            }

        } catch (IOException ex) {
        }

        return Colis;
    }
public ArrayList<Colis> TriColis(  ) {
            String url =Statics.BASE_URL +"/C/triColis";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Colis = parseColis(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return Colis;
    }

}
