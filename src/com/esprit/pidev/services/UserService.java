/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidev.models.User;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Oussama_RMILI
 */
public class UserService {
    
    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<User> users;
    public ArrayList<User> client;
    

    public UserService() {
        request = DataSource.getInstance().getRequest();
    }
    
    public Boolean user_login(String username, String pwd){
//      Boolean test = false;
        String url =  Statics.BASE_URL+"/T/MobileUser?username="+username+"&password="+pwd;
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseTasks(new String(request.getResponseData()));
                
                    for (int i = 0; i < users.size(); i++) {
                    Statics.sessionID=users.get(i).getId();
                    Statics.type=users.get(i).getType();
                }
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });        
        
            NetworkManager.getInstance().addToQueueAndWait(request);
           return responseResult;
    }
     public ArrayList<User> lastcnx(int id){
//      Boolean test = false;
        String url =  Statics.BASE_URL+"/T/findlastcnx/"+id;
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUser(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return users;
    }

    public boolean register(String prenom, String nom, int tel, String email, String username, String dtn, int permis, int exp, int rib, String pwd,String type) {
        String url = Statics.BASE_URL+"/T/MobileRegister?prenom="+prenom+"&nom="+nom+"&tel="+tel+
                "&email="+email+"&usern="+username+"&dtn="+dtn+"&permis="+permis+
                "&exp="+exp+"&rib="+rib+"&pwd="+pwd+"&type="+type;
        
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
    public boolean EditProfile(String prenom, String nom, String email, String username, String pwd) {
        String url = Statics.BASE_URL+"/T/EditProfile/"+Statics.sessionID+"?prenom="+prenom+"&nom="+nom+
                "&email="+email+"&usern="+username+"&pwd="+pwd;
        
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
    public ArrayList<User> getAllTasks() {
        String url = Statics.BASE_URL +"/T/listJson";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseTasks(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return users;
    }
    
    public ArrayList<User> parseTasks(String jsonText) {
        try {
            users = new ArrayList<>();
             System.out.println(jsonText);   
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            //System.out.println(list.size());
            for (Map<String, Object> obj : list) {
                int idU = (int)Float.parseFloat(obj.get("id").toString());
                String type=obj.get("type").toString();
                users.add(new User(idU,type));
            }

        } catch (IOException ex) {
        }
        return users;
    }
    public ArrayList<User> parseUser(String jsonText) {
        try {
            users = new ArrayList<>();
             System.out.println(jsonText);   
            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            //System.out.println(list.size());
            for (Map<String, Object> obj : list) {
                int idU = (int)Float.parseFloat(obj.get("id").toString());
                String type=obj.get("type").toString();
                String username=obj.get("username").toString();
                String email=obj.get("email").toString();
                String event=obj.get("event").toString();
                users.add(new User(idU,type,username,email,event));
            }

        } catch (IOException ex) {
        }
        return users;
    }
     public ArrayList<User> getClient(int id) {
        String url = Statics.BASE_URL + "/T/listwa/"+ id;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                client = parseClient(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return client;
    }
     public ArrayList<User> getProfile() {
        String url = Statics.BASE_URL + "/T/profile/"+ Statics.sessionID;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                client = parseProfile(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return client;
    }
 public ArrayList<User> parseClient(String jsonText) {
        try {
            client = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                String nom = obj.get("nom").toString();
                String prenom = obj.get("prenom").toString();
                String email = obj.get("email").toString();
                client.add(new User(id,nom,prenom,email));
            }

        } catch (IOException ex) {
        }

        return client;
    }
 
 public ArrayList<User> parseProfile(String jsonText) {
        try {
            client = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
//                int id = (int)Float.parseFloat(obj.get("id").toString());
                String nom = obj.get("nom").toString();
                String prenom = obj.get("prenom").toString();
                String username = obj.get("username").toString();
                String email = obj.get("email").toString();
                String password = obj.get("password").toString();
                
                client.add(new User(nom,prenom,username,email,password));
            }

        } catch (IOException ex) {
        }

        return client;
    }
}
