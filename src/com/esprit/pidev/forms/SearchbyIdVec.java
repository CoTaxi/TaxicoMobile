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
import com.mycompany.myapp.entities.Vehicule;

/**
 *
 * @author achref
 */
public class SearchbyIdVec extends Form {

    public SearchbyIdVec(Form previous) {
        setTitle("Rechercher");
        setLayout(BoxLayout.y());

        TextField tfid = new TextField("", "Rechercher");
        Button btnValider = new Button("rechercher");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfid.getText().length() == 0) ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));}
                            else if ((tfid.getText().length() == 6)||(tfid.getText().length() == 5)||(tfid.getText().length() == 4)||(tfid.getText().length() == 3) ) {
                    Dialog.show("Alert", "Désolé", new Command("OK"));}
                 else {
                    
                        Vehicule v = new Vehicule(tfid.getText());
                       
                          
                            Dialog.show("Success", "Vehicule disponible", new Command("OK"));
                         
                    

                }

            }
        });

        addAll(tfid,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
