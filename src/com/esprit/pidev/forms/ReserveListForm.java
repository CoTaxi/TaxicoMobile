/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.models.Rdv;
import com.esprit.pidev.services.GarageService;
import com.esprit.pidev.services.RdvService;
import java.util.ArrayList;
import com.esprit.pidev.services.ServiceService;
import com.esprit.pidev.services.GarageService;

/**
 *
 * @author aissa
 */
public class ReserveListForm extends Form {

    public ReserveListForm(Form previous) {
//        super("Rdvs list", BoxLayout.y());
//
//        this.add(new SpanLabel(new TaskService().getAllRdvsReserved().toString()));
//
//        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
//            previous.showBack();
//        });
super("Rdv list", new BorderLayout());
        Container listRec = new Container(BoxLayout.y());
        listRec.setScrollableY(true);
        Button btn = new Button("Annuler");
        ArrayList<Rdv> List = new RdvService().getAllRdvsReserved();
        for (int i = 0; i<List.size(); i++) {
            Button btnR = new Button();
            FontImage.setMaterialIcon(btnR, FontImage.MATERIAL_DELETE);
            MultiButton mBtn = new MultiButton("Rdv n°"+i+":");
            mBtn.setTextLine1(List.get(i).getName_service());
            mBtn.setTextLine2(List.get(i).getName_garage());
            mBtn.setTextLine3(List.get(i).getDate_rdv());
            FontImage.setMaterialIcon(mBtn, FontImage.MATERIAL_COMMENT);
            Rdv r = new Rdv(List.get(i).getId_rdv());
            btnR.addActionListener(es->{
                if (new RdvService().annulerRdv(r)) {
                        Dialog.show("SUCCESS", "Rdv annuler", "OK", null);
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                
                mBtn.remove();
                btnR.remove();
                this.refreshTheme();
            });

//            SwipeableContainer swip = new SwipeableContainer(btn,mBtn);
            listRec.addAll(mBtn,btnR);
            

            
        }
        this.add(CENTER, listRec);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
        //////////////////////////////////////////////////////////////////////////////
//        Form hi = new Form("Rdv List", new BoxLayout(BoxLayout.Y_AXIS));
//
//Container cont = new Container(new BorderLayout());
//
//Button button = new Button("Press me to see details"); //shouldn't be pressed when the container is just swiped!!
//button.setAutoRelease(true);
//cont.add(BorderLayout.CENTER, button);
//Container leftSwipeCont = new Container();
//leftSwipeCont.add(new Button("XX"));
//SwipeableContainer swipe = new SwipeableContainer(leftSwipeCont, cont);
//hi.add(swipe);
//hi.show();
    
}
}