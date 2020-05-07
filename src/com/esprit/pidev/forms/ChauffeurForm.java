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
 * @author ASUS
 */
public class ChauffeurForm extends Form
{

    public ChauffeurForm() 
    {
        super("Chauffeur", BoxLayout.y());
        
        Button btnAddTask = new Button("Liste Des Demandes ");
        Button btnTasksList = new Button("Archive");
        
        btnAddTask.addActionListener((evt) -> {
           new ListeDemandes().show();
        });
        btnTasksList.addActionListener((evt) -> {
            new ArchiveColis().show();
        });
        
        this.addAll(new Label("Choose an option :"), btnAddTask, btnTasksList);
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
          new HomeForm().show();
        });
    }
    
}
