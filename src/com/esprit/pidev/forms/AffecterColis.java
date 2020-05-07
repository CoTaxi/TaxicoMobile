/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.services.ColisService;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class AffecterColis extends Form
{

    public AffecterColis(int Id)
    {
        super("Colis list",new BoxLayout(BoxLayout.Y_AXIS));
    //----------Declaration
   ArrayList<Map<String, Object>> data = new ArrayList<>();
//   ArrayList<Vehicule> List = new VehiculeServices().AfficherVehicule();
   Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));
   Dialog d = new Dialog();
   content.setScrollableY(true);
   //-------------End 
   
for (int i = 0; i < 10; i++) {
        final MultiButton mb = new MultiButton();
        mb.setTextLine1("Polo 7");
        mb.setTextLine2("Tunis -> Bizerte");
        mb.setTextLine4("963");
        mb.addActionListener(new ActionListener() {
          
            @Override
            public void actionPerformed(ActionEvent evt) {
              String  Depart = mb.getTextLine1(); // or anything you want it to be
              String  Destination=mb.getTextLine2();
                //show the profile form here
             Dialog.show("Felicitation", "Votre Colis sera Affecté a cette voiture", "OK", null);
             if (new ColisService().affecterColis(Id,mb.getTextLine4())) {
                        Dialog.show("SUCCESS", "Colis Affecté", "OK", null);
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
            }
        });
        this.addComponent(mb);
}
content.revalidate();
this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
           new ShowDetailsColis(Id).show();
        });    
    }
    
}
