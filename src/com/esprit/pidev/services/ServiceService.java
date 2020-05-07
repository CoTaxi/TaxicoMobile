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
import com.codename1.ui.ComboBox;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidev.models.Service;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author walid
 */
public class ServiceService {
    
    public ArrayList<Service> services;
    private ConnectionRequest request;

    private boolean responseResult;
    
    public ServiceService() {
        request = DataSource.getInstance().getRequest();
    }
    
    
    
    public ArrayList<Service> FindServicesSelected(ComboBox c) {
        String url = Statics.BASE_URL + "/T/services/find?name=" + c.getSelectedItem();

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                services = parseService(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return services;
    }
    
    public ArrayList<Service> getAllServices() {
        String url = Statics.BASE_URL + "/T/services";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                services = parseService(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return services;
    }
    
    public ArrayList<Service> parseService(String jsonText) {
        try {
            services = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int idService = (int)Float.parseFloat(obj.get("idService").toString());
                String name = (obj.get("name").toString());
                services.add(new Service(idService, name));
            }

        } catch (IOException ex) {
        }

        return services;
    }
}
