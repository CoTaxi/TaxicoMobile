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
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.models.Forum;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mahdi
 */
public class ForumService {
    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Forum> forums;

    public ForumService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean addForum(Forum f,int id) {
        String url = Statics.BASE_URL+"/F/newblog/"+id+"?title="+f.getTitle()+"&content="+ f.getContent()+"&image="+ f.getImage();

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

    public ArrayList<Forum> getAllEvents() {
        String url = Statics.BASE_URL + "/F/allblog";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                forums = parseForums(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return forums;
    }
    public boolean deleteForm(String Id)
     {
      String url = Statics.BASE_URL + "/F/Supprimerblog/"+Id;
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
     public boolean modifycolis(String Id,Forum f)
     {
      String url = Statics.BASE_URL + "/F/Modifierblog/"+Id+"?title="+f.getTitle()+"&content="+ f.getContent()+"&image="+ f.getImage();


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
    public ArrayList<Forum> parseForums(String jsonText) {
      
        try {
            forums = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
             
                int id = (int)Float.parseFloat(obj.get("id").toString());
                String title = obj.get("title").toString();
                String Content = obj.get("content").toString();
                String image = obj.get("image").toString();
                int iduser = (int)Float.parseFloat(obj.get("user").toString());
                forums.add(new Forum(title,Content,image,iduser));
                
            }
        } catch (IOException ex) {
        }
        return forums;
    }
        public void sendEmail(){
        Message m = new Message("Bonjour Equipe TaxiCo,\n A Propos Du Blog Posté .. ");
        Display.getInstance().sendMessage(new String[] {"mehdi.hrairi@esprit.tn"}, "Blog", m);
    }
}
