package com.esprit.pidev.forms.maintenance;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;

import com.codename1.ui.Image;
import com.codename1.ui.Label;

import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

import com.codename1.ui.util.Resources;
import com.esprit.pidev.forms.reclamation.AjoutRec;
import com.esprit.pidev.forms.vehicule.AjoutVehicule;
import com.mycompany.myapp.Forms.BaseForm;

/**
 *
 * @author aissa
 */
public class MaintenanceForm extends BaseForm {
private Resources theme;
    public MaintenanceForm(Resources res) {
        
       super("Maintenance", BoxLayout.y());
       
        
        super.installSidemenu(res);
        
        addButton(res.getImage("reserverrdv.png"), "Reserver un rendez-vous",res);
        addButton(res.getImage("consulterrdv.png"), "Liste des rendez-vous",res);
    }
    
   private void addButton(Image img, String title,Resources res) {
       int height = Display.getInstance().convertToPixels(50f);
       int width = Display.getInstance().convertToPixels(65f);
       Button image = new Button(img.fill(width, height));
       //image.getStyle().setBorder(new Border());
       image.setUIID("Container");
       Label ll = new Label(" ");
       Label lll = new Label(" ");
       Label llll = new Label(" ");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title.toUpperCase());
       ta.setVisible(false);
       ta.setEditable(false);
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta
               ));
       
       addAll(cnt,ll,lll,llll);
       image.addActionListener((e) ->{
//           ToastBar.showMessage(title, FontImage.MATERIAL_INFO);
//           System.out.println(ta.getText());
           if(ta.getText().equals("LISTE DES RENDEZ-VOUS"))
           {
               //System.out.println(ta.getText());
               new ReserveListForm(res).show();
           }
           else if(ta.getText().equals("RESERVER UN RENDEZ-VOUS"))
           {//System.out.println(ta.getText());
             new ReserveRdvMaintenanceForm(res).show();
              
           }
               });
   }

}
