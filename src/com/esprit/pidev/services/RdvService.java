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
import com.esprit.pidev.models.Rdv;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.esprit.pidev.services.ServiceService;
import com.esprit.pidev.services.GarageService;

/**
 *
 * @author walid
 */
public class RdvService {
    private ConnectionRequest request;
    public ArrayList<Rdv> rdvs;
    private boolean responseResult;
    public RdvService() {
        request = DataSource.getInstance().getRequest();
    }
    public boolean updateRdv(Rdv rdv) {
        String url = Statics.BASE_URL + "/T/rdvs/update/"+rdv.getId_rdv();

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
public boolean annulerRdv(Rdv rdv) {
        String url = Statics.BASE_URL + "/T/rdvs/annuler/"+rdv.getId_rdv();

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

public ArrayList<Rdv> getAllRdvsReserved() {
        String url = Statics.BASE_URL + "/T/rdvs?status=nondisponible";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                rdvs = parseRdvsReserved(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return rdvs;
    }

public ArrayList<Rdv> FindRdvsSelected(ComboBox c) {
        
        String url = Statics.BASE_URL + "/T/rdvs/findselected?dateRdv=" + c.getSelectedItem();

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                rdvs = parseRdvs(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return rdvs;
    }

public ArrayList<Rdv> FindRdvs(ComboBox c,ComboBox c2) {
        String url = Statics.BASE_URL + 
                "/T/rdvs/find?idservice=" + 
                new ServiceService().FindServicesSelected(c).get(0).getId_service() + 
                "&idgarage=" + new GarageService().FindGaragesSelected(c2).get(0).getId_garage() ;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                rdvs = parseRdvs(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return rdvs;
    }

 public ArrayList<Rdv> parseRdvs(String jsonText) {
        try {
            rdvs = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int idRdv = (int)Float.parseFloat(obj.get("idRdv").toString());
                int idChauffeur = (int)Float.parseFloat(obj.get("idChauffeur").toString());
                int idService = (int)Float.parseFloat(obj.get("service").toString());
                int idGarage = (int)Float.parseFloat(obj.get("garage").toString());
                String dateRdv = (obj.get("dateRdv").toString());
                String status = obj.get("status").toString();
                rdvs.add(new Rdv(idRdv, idChauffeur, idService, idGarage, dateRdv, status));
            }

        } catch (IOException ex) {
        }

        return rdvs;
    }
        public ArrayList<Rdv> parseRdvsReserved(String jsonText) {
        try {
            rdvs = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int idRdv = (int)Float.parseFloat(obj.get("idRdv").toString());
                int idChauffeur = (int)Float.parseFloat(obj.get("idChauffeur").toString());
                String NameService = obj.get("service").toString();
                String NameGarage = obj.get("garage").toString();
                String dateRdv = (obj.get("dateRdv").toString());
                String status = obj.get("status").toString();
                rdvs.add(new Rdv(idRdv, idChauffeur, NameService, NameGarage, dateRdv, status));
            }

        } catch (IOException ex) {
        }

        return rdvs;
    }
}
