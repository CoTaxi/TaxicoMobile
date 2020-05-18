/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.event;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;

import com.codename1.ui.Image;

import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.util.Resources;
import com.esprit.pidev.forms.reclamation.AjoutRec;
import com.esprit.pidev.forms.vehicule.AjoutVehicule;
import com.mycompany.myapp.Forms.BaseForm;

/**
 *
 * @author aissa
 */
public class eventForm extends BaseForm {
private Resources theme;
    public eventForm(Resources res) {
        
       super("Maintenance", BoxLayout.y());
       
        
        super.installSidemenu(res);
        
        addButton(res.getImage("viewevent.png"), "Afficher events",res);
        //addButton(res.getImage("consulterrdv.png"), "Liste des rendez-vous",res);
    }
    
   private void addButton(Image img, String title,Resources res) {
       int height = Display.getInstance().convertToPixels(65f);
       int width = Display.getInstance().convertToPixels(65f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Container");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title.toUpperCase());
       ta.setVisible(false);
       ta.setEditable(false);
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta
               ));
       add(cnt);
       image.addActionListener((e) ->{
//           ToastBar.showMessage(title, FontImage.MATERIAL_INFO);
//           System.out.println(ta.getText());
           if(ta.getText().equals("Afficher events"))
           {
               //System.out.println(ta.getText());
               new AfficherEvent(res).show();
           }
               });
   }

}
