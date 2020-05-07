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
public class ClientForm extends Form {

    public ClientForm() {
        super("Client", BoxLayout.y());
        
        Button btnAddTask = new Button("Envoyer Colis ");
        Button btnTasksList = new Button("Afficher Mes Colis");
        
        btnAddTask.addActionListener((evt) -> {
           new AjoutColis(this).show();
        });
        btnTasksList.addActionListener((evt) -> {
            new AfficherColis().show();
        });
        
        this.addAll(new Label("Choose an option :"), btnAddTask, btnTasksList);
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
          new HomeForm().show();
        });
    }
    
}
