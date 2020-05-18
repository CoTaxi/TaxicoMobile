/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.maintenance;

import com.codename1.components.MultiButton;
import com.codename1.components.OnOffSwitch;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
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
 
    public Chdetail(Resources res,Form previous,String t1,String t2,String t3,int id,int idCommande,int t4,String t5){
      super("Ch list", new BorderLayout());
      this.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
     
        this.setBgImage(res.getImage("chform2.jpg"));
        Container listRec = new Container(BoxLayout.y());
        listRec.setScrollableY(true);
//            MultiButton mBtn = new MultiButton();
            Label lnomclient = new Label("Nom Client :");
        lnomclient.setUIID("Bold");
        lnomclient.getStyle().setFgColor(0x305399);
         Label lprenom = new Label("Prenom Client :");
        lprenom.setUIID("Bold");
        lprenom.getStyle().setFgColor(0x305399);
         Label lemail = new Label("Email Client :");
        lemail.setUIID("Bold");
        lemail.getStyle().setFgColor(0x305399);
        Label ltel = new Label("Tel Client :");
        ltel.setUIID("Bold");
        ltel.getStyle().setFgColor(0x305399);
        Label lnaissance = new Label("Naissance Client :");
        lnaissance.setUIID("Bold");
        lnaissance.getStyle().setFgColor(0x305399);
        lnomclient.setText(t1);
        lprenom.setText(t2);
        lemail.setText(t3);
        ltel.setText(String.valueOf(t4));
        lnaissance.setText(t5);
//            mBtn.setTextLine1 (t1);
//            mBtn.setTextLine2(t2);
//            mBtn.setTextLine3 (t3);
//            FontImage.setMaterialIcon(mBtn, FontImage.MATERIAL_COMMENT);
            OnOffSwitch dispo = new OnOffSwitch();
            CheckBox ck = new CheckBox();
            ck.setText("Done ?");
        dispo.setOff("Non");
        dispo.setOn("Oui");
        dispo.setValue(false);
        ck.addActionListener(l->{
            if (ck.isSelected()) {
                    new RdvService().updateDispo(1,id);
                    new RdvService().done(idCommande);
                } else {
                    new RdvService().updateDispo(0,id);
                }
        });
        listRec.getStyle().setBgTransparency(100);
            listRec.addAll(lnomclient,lprenom,lemail,ltel,lnaissance,ck);
            

            
        
        this.add(CENTER, listRec);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });  
    }}
    

