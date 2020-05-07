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
import com.esprit.pidev.models.Garage;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.esprit.pidev.services.ServiceService;
/**
 *
 * @author walid
 */
public class GarageService {
    private ConnectionRequest request;
public ArrayList<Garage> garages;
    private boolean responseResult;
    
    public GarageService() {
        request = DataSource.getInstance().getRequest();
    }
    
    public ArrayList<Garage> FindGarages(ComboBox c) {
        
        String url = Statics.BASE_URL + "/T/garages/find?idservice=" + new ServiceService().FindServicesSelected(c).get(0).getId_service();

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                garages = parseGarage(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return garages;
    }
    public ArrayList<Garage> FindGaragesSelected(ComboBox c) {
        
        String url = Statics.BASE_URL + "/T/garages/findSelected?name=" + c.getSelectedItem();

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                garages = parseGarage(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return garages;
    }
    
    public ArrayList<Garage> getAllGarages() {
        String url = Statics.BASE_URL + "/T/garages";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                garages = parseGarage(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return garages;
    }
    
    public ArrayList<Garage> parseGarage(String jsonText) {
        try {
            garages = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int idGarage = (int)Float.parseFloat(obj.get("idGarage").toString());
                String name = (obj.get("name").toString());
                String address = obj.get("address").toString();
                int idService = (int)Float.parseFloat(obj.get("service").toString());
                
                garages.add(new Garage(idGarage, name, address, idService));
            }

        } catch (IOException ex) {
        }

        return garages;
    }
    
}
