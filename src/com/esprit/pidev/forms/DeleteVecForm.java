/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.services.ServicesVehicule;


/**
 *
 * @author achref
 */
public class DeleteVecForm extends Form {
        public DeleteVecForm(Form previous) {
        setTitle("Supprimer");
        setLayout(BoxLayout.y());

        TextField tfid = new TextField("", "Supprimer");
        Button btnValider = new Button("Supprimer");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                
                       // Vehicule t = new Vehicule(tfid.getText());
                        if (ServicesVehicule.getInstance().deletevehicule(Integer.parseInt(tfid.getText()))) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
         

                }

            
        });


        addAll(tfid,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
