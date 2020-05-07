/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.ui.Label;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.services.ServicesVehicule;
import com.mycompany.myapp.entities.Vehicule;
import java.util.ArrayList;

/**
 *
 * @author achref
 */
public class ListVehicule extends Form {

    public ListVehicule(Form previous) {
        setTitle("Liste vehicule");
        // Form f2 = new Form();
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServicesVehicule.getInstance().getAllVehicules().toString());
//        add(sp);

        Container listvec = new Container(BoxLayout.y());
        listvec.setScrollableY(true);
        ArrayList<Vehicule> List = new ServicesVehicule().getAllVehicules();
        for (int i = 0; i < List.size(); i++) {
            MultiButton mBtn = new MultiButton("Réc n°" + i + ":");
            mBtn.setTextLine1(List.get(i).getMatricule());
            mBtn.setTextLine2(List.get(i).getCartegrise());
            Label lp = new Label(List.get(i).getPosition());
             Label ld = new Label(List.get(i).getDestination());
             Label lmodele = new Label(List.get(i).getModele());
             Label lmarque = new Label(List.get(i).getMarque());
             Button btn=new Button();
            Label l = new Label("details");
            FontImage.setMaterialIcon(mBtn, FontImage.MATERIAL_ADD_LOCATION);
            listvec.add(mBtn);
            mBtn.addActionListener(al -> {
                    
                  Dialog.show("Vehicule:", "Position : " + lp.getText()+ " \n Destination: : " + ld.getText()+ " \n Marque : " + lmarque.getText()+ " \n Modele : " + lmodele.getText(), "Ok",null);
                  
            });
            
          

        }
        this.add(listvec);
        this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            previous.showBack();
        });
//               getToolbar().addCommandToOverflowMenu("Vérifier", null, new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//               SearchbyId a = new SearchbyId(previous);
//                a.show();
//            }
//        });

        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        // f2.show();}
    }
}
