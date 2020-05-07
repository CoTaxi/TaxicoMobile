/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.services.ColisService;
import com.esprit.pidev.utils.Statics;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class AfficherColis extends Form {

    public AfficherColis() {
        
    super("Colis list",new BoxLayout(BoxLayout.Y_AXIS));
    if(Statics.sessionID!=0)
    {
    //----------Declaration
   ArrayList<Map<String, Object>> data = new ArrayList<>();
   ArrayList<Colis> List = new ColisService().getAllColis(Statics.sessionID);
   Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));
   content.setScrollableY(true);
   Button btn = new Button("test");
   //-------------End 
   
for (int i = 0; i < List.size(); i++) {
        final MultiButton mb = new MultiButton();
        mb.setTextLine1(List.get(i).getDepart()+"---->"+List.get(i).getDestination());
        mb.setTextLine2(String.valueOf(List.get(i).getPoids()));
        mb.setTextLine4(Integer.toString(List.get(i).getIdC()));
        mb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              String  Depart = mb.getTextLine1(); // or anything you want it to be
              String  Destination=mb.getTextLine2();
                //show the profile form here
             
              new ShowDetailsColis(Integer.valueOf(mb.getTextLine4())).show();
            }
        });
        this.addComponent(mb);
}
content.revalidate();
this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
           new ClientForm().show();
        });
    }
    else if (Statics.sessionID==0)
    {
    login su = new login();
    su.getF().show(); 
    }
}
}