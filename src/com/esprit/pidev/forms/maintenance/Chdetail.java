/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.maintenance;

import com.codename1.components.MultiButton;
import com.codename1.components.OnOffSwitch;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Commande;
import com.esprit.pidev.models.User;
import com.esprit.pidev.models.Vehicule;
import com.esprit.pidev.services.RdvService;
import java.util.ArrayList;

/**
 *
 * @author walid
 */
public class Chdetail extends Form{
//    private Resources theme_2;
    public Chdetail(Form previous,String t1,String t2,String t3,int id,int idCommande){
      super("Ch list", new BorderLayout());
//      this.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
//        this.setBgImage(theme_2.getImage("bg.png"));
        Container listRec = new Container(BoxLayout.y());
        listRec.setScrollableY(true);
            MultiButton mBtn = new MultiButton();
            mBtn.setTextLine1 (t1);
            mBtn.setTextLine2(t2);
            mBtn.setTextLine3 (t3);
            FontImage.setMaterialIcon(mBtn, FontImage.MATERIAL_COMMENT);
            OnOffSwitch dispo = new OnOffSwitch();
        dispo.setOff("Non");
        dispo.setOn("Oui");
        dispo.setValue(false);
        dispo.addActionListener(l->{
            if (dispo.isValue()) {
                    new RdvService().updateDispo(1,id);
                    new RdvService().done(idCommande);
                } else {
                    new RdvService().updateDispo(0,id);
                }
        });
            listRec.addAll(mBtn,dispo);
            

            
        
        this.add(CENTER, listRec);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });  
    }}
    

