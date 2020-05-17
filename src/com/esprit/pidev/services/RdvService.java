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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Calendar;
import com.codename1.ui.ComboBox;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidev.models.Rdv;
import com.esprit.pidev.models.Vehicule;
import com.esprit.pidev.utils.DataSource;
import com.esprit.pidev.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

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
        String url = Statics.BASE_URL + "/T/rdvs/update/"+rdv.getId_rdv()+"?idChauffeur="+Statics.sessionID;

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
        String url = Statics.BASE_URL + "/T/rdvs?status=nondisponible&idChauffeur="+ Statics.sessionID;

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
public void updateDispo(int iddispo,int id) {
        String url = Statics.BASE_URL + "/T/updatevec/"+id+"?dispo="+iddispo;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

//        return responseResult;
    }
 public void doing(int id) {
        String url = Statics.BASE_URL + "/T/doing/"+id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

//        return responseResult;
    }
 public void done(int id) {
        String url = Statics.BASE_URL + "/T/done/"+id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

//        return responseResult;
    }


public ArrayList<Rdv> FindRdvsSelected(String date) {
        String url = Statics.BASE_URL + "/T/rdvs/findselected?dateRdv=" +date;

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
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date dateRdv = (Date)format.parse( obj.get("dateRdv").toString() ) ;
//String dateRdv = obj.get("dateRdv").toString();
                System.out.println(obj.get("dateRdv").toString());
                System.out.println(dateRdv);
        
            // DateFormatPatterns.ISO8601
                    String status = obj.get("status").toString();
                rdvs.add(new Rdv(idRdv, idChauffeur, idService, idGarage, dateRdv, status));
               
                
            }

        } catch (IOException ex) {
        } 
        catch (ParseException ex) {
            
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
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date dateRdv = (Date)format.parse( obj.get("dateRdv").toString() ) ;

                String status = obj.get("status").toString();
                rdvs.add(new Rdv(idRdv, idChauffeur, NameService, NameGarage, dateRdv, status));
            }

        } catch (IOException ex) {
        }
catch (ParseException ex) {
            
        }
        return rdvs;
    }
}
