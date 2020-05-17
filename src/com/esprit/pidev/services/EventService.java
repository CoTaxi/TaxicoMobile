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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.models.Event;
import com.esprit.pidev.models.User;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;


/**
 *
 * @author mahdi
 */
public class EventService {
    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Event> Events;

    public EventService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean addEvent(Event e) {
        String url = Statics.BASE_URL+"/event/new?nomEvent="+e.getNom()+"&dateEventFin="+ e.getDate_fin()+"&emplacement="+e.getEmplacement()+"&capacite="+e.getCapacite()+"&dureeEvent="+ e.getDuree();

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

    public ArrayList<Event> getAllEvents() {
        String url = Statics.BASE_URL + "/event/allevent";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Events = parseEvents(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Events;
    }
    
    public ArrayList<User> user;
    public ArrayList<User> listuser(int idev) 
    {
        String url = Statics.BASE_URL + "/event/listuser/"+idev;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                user = new UserService().parseUser(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return user;
    }

    public ArrayList<Event> parseEvents(String jsonText) {
      
        try {
            Events = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                int Duree = (int)Float.parseFloat(obj.get("duree").toString());
                int Capacite = (int)Float.parseFloat(obj.get("capacite").toString());
                String Name = obj.get("nom").toString();
                String Empacement = obj.get("emplacement").toString();
                int Etat = (int)Float.parseFloat(obj.get("etat").toString());
                int place = (int)Float.parseFloat(obj.get("place").toString());
                Events.add(new Event(id, Duree, Capacite,Etat,Name,Empacement,place));

            }
        } catch (IOException ex) {
        }
        return Events;
    }
    public boolean ParticiperEvent(int idev,int iduser)
    {
      String url = Statics.BASE_URL + "/event/Participer/"+idev+"/"+iduser;
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
    public boolean QuitterEvent(int idev,int iduser)
    {
      String url = Statics.BASE_URL + "/event/Quitter/"+idev+"/"+iduser;
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
    public ArrayList<Event> getEvent(int id) {
        String url = Statics.BASE_URL + "/event/findevent/"+id;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Events = parseEvents(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Events;
    }
}
