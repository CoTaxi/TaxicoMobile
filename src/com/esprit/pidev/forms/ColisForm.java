/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author aissa
 */
public class ColisForm extends Form {

    public ColisForm() {
        super("Home", BoxLayout.y());
        
        Button btnAddColis = new Button("Client");
        Button btnColisList = new Button("Chauffeur");
        
        btnAddColis.addActionListener((evt) -> {
           new ClientForm().show();
        });
        btnColisList.addActionListener((evt) -> {
           new ChauffeurForm().show();
          //new MapForm();
        });
        
        this.addAll(new Label("Choose an option :"), btnAddColis, btnColisList);
    }
    
}
