/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.BaseSpinner;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Garage;
import com.esprit.pidev.models.Rdv;
import com.esprit.pidev.models.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import com.esprit.pidev.services.ServiceService;
import com.esprit.pidev.services.GarageService;
import com.esprit.pidev.services.RdvService;

/**
 *
 * @author walid
 */
public class ReserveRdvMaintenanceForm extends Form{
    private Resources theme;
    public ReserveRdvMaintenanceForm(Form previous) {
    
super("Reserve Rdv", BoxLayout.y());

//        TextField tfidRdv = new TextField(null, "Entrer id du Rdv");
//        TextField tfStatus = new TextField(null, "Status (disponible or nondisponible)");
        ComboBox cbService= new ComboBox();
        ComboBox cbGarage= new ComboBox();
        cbGarage.getStyle().setMarginTop(30);
        ComboBox cbRdv= new ComboBox();
        cbRdv.getStyle().setBgColor(0xf99f1b);
        cbRdv.getStyle().setMarginTop(30);
        Picker p = new Picker();
        Button btnSms = new Button("Send SMS");
//        Button btnMail = new Button("Send Mail");
        Button btn = new Button("Reserve Rdv");
//        Button btntest = new Button("Notification");
        ArrayList<Service> List = new ServiceService().getAllServices();
        ArrayList<String> List2 = new ArrayList();
        String [] ListNameService = new String[20];
        for (int i=0; i<List.size();i++){
        cbService.addItem(List.get(i).getName().toString());
//        ListNameService[i] = List.get(i).getName();
//        List2.add(List.get(i).getName()) ;
        }
//        p.setStrings(ListNameService);
        cbService.addActionListener(e->{ 
           
      
        ArrayList<Garage> ListGarage = new GarageService().FindGarages(cbService);
        for (int i=0; i<ListGarage.size();i++){
        cbGarage.addItem(ListGarage.get(i).getName().toString());
        }
                });
        cbGarage.addActionListener(e->{
            ArrayList<Rdv>ListRdv = new RdvService().FindRdvs(cbService,cbGarage);
            for (int i=0;i<ListRdv.size();i++){
                cbRdv.addItem(ListRdv.get(i).getDate_rdv().toString());
            }
        });
       btn.addActionListener((evt) -> {
//            if ((tfidRdv.getText().length() == 0) || (tfStatus.getText().length() == 0)) {
//                Dialog.show("Alert", "Please fill all the fields", "OK", null);
//            } else {
//                try {
                    ArrayList<Rdv>ListRdv2 = new RdvService().FindRdvsSelected(cbRdv);
                    Rdv r = new Rdv(ListRdv2.get(0).getId_rdv());
                    System.out.println(r.getId_rdv());
                    if (new RdvService().updateRdv(r)) {
//                        Dialog.show("SUCCESS", "Rdv Reserved", "OK", null);
ToastBar.showInfoMessage("Rendez vous Reserved");
                       
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
//                } catch (NumberFormatException e) {
//                    Dialog.show("ERROR", "Status must be a number", "OK", null);
//                }
//
//            }
  
                String myURL = "https://rest.nexmo.com/sms/json?api_key=3f027ab2&api_secret=11AZ1yktyp8xwt04&to=21624515801" + "&from=TaxiCo&text=Rdv Reserved";
                ConnectionRequest cntRqst = new ConnectionRequest() {
                    protected void readResponse(InputStream in) throws IOException {
                    }

                    @Override
                    protected void postResponse() {
                        Dialog.show("SMS", "sms successfully sent", "OK", null);

                    }
                };
                cntRqst.setUrl(myURL);
                NetworkManager.getInstance().addToQueue(cntRqst);
            

        });
//       btntest.addActionListener(e -> {
//           LocalNotification n = new LocalNotification();
//        n.setId("demo-notification");
//        n.setAlertBody("It's time to take a break and look at me");
//        n.setAlertTitle("Break Time!");
//        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound
//
//
//        Display.getInstance().scheduleLocalNotification(
//                n,
//                System.currentTimeMillis() , // fire date/time
//                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
//        );
//       });
     


        this.addAll(cbService, cbGarage, cbRdv,  btn);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
}

    
    
}
