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
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidev.models.Reclamation;
import com.esprit.pidev.models.typeReclamation;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ReclamationServices {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Reclamation> tasks;
    public ArrayList<typeReclamation> type;

    public ReclamationServices() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean addrec(Reclamation rec, int id) {
        String url = Statics.BASE_URL + "/T/addMobileRec?message=" + rec.getMessage() + "&IdType=" + id+ 
                "&idu="+Statics.sessionID;

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
    
    public boolean deleterec(int id) {
        String url = Statics.BASE_URL + "/T/removeRec?id=" +id;

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
    
    public boolean updaterec(int id, String msg) {
        String url = Statics.BASE_URL + "/T/updateRec?id=" +id+ "&message=" +msg;

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

    public ArrayList<Reclamation> getAllRec() {
        String url = Statics.BASE_URL +"/T/listJsonRec?idu="+Statics.sessionID;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return tasks;
    }
    
    public ArrayList<typeReclamation> getAllType() {
        //String url = Statics.BASE_URL +"/listTypeJson";
        String url1 = Statics.BASE_URL +"/T/listeTypeRec";
        request.setUrl(url1);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                type = parseType(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return type;
    }
    
    public ArrayList<typeReclamation> getAllIdType(ComboBox cbx) {
        //String url = Statics.BASE_URL +"/listTypeJson";
        String url2 = Statics.BASE_URL +"/T/listeIdTypeRec?titre="+cbx.getSelectedItem();
        request.setUrl(url2);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                type = parseType(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return type;
    }

    public ArrayList<Reclamation> parseTasks(String jsonText) {
        try {
            tasks = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int idR = (int)Float.parseFloat(obj.get("idR").toString());
                String message = obj.get("message").toString();
                String etat = obj.get("etat").toString();
                String dateRec = obj.get("dateRec").toString();
                String Objet = obj.get("Objet").toString();
                String rep = obj.get("Reponse").toString();
                tasks.add(new Reclamation(idR, message, etat, Objet, dateRec, rep));
            }

        } catch (IOException ex) {
        }

        return tasks;
    }
    
    public ArrayList<typeReclamation> parseType(String jsonText) {
        try {
            type = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                String titre = obj.get("titre").toString();
                
                type.add(new typeReclamation(id, titre));
            }

        } catch (IOException ex) {
        }

        return type;
    }
    public void sendEmail(){
        Message m = new Message("Bonjour Monsieur,\n Pouvez vous me répondre.\n Merci. ");
//        m.getAttachments().put(textAttachmentUri, "text/plain");
//        m.getAttachments().put(imageAttachmentUri, "image/png");
        Display.getInstance().sendMessage(new String[] {"rmilissou@gmail.com"}, "Réclamation", m);
    }
    
    public void Notification(){
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_beep-01a.mp3");
            // alert sound file name must begin with notification_sound

        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
    }
    public void localNotificationReceived(String notificationId){
        
    }
}