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
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author aissa
 */
public class MaintenanceForm extends Form {
private Resources theme;
    public MaintenanceForm() {
        super("Home", BoxLayout.y());
        
        Button btnAddTask = new Button("Reserve Rdv");
        Button btnTasksList = new Button("Rdvs List");
        Button btnsms = new Button("SMS");
        
        btnAddTask.addActionListener((evt) -> {
            new ReserveRdvMaintenanceForm(this).show();
        });
        btnTasksList.addActionListener((evt) -> {
            new ReserveListForm(this).show();
        });
//        btnsms.addActionListener((evt) -> {
//            new EnterMobileNumberForm().show();
//        });
//        this.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
//        this.setBgImage(theme.getImage("Affiche_final.png"));
        this.addAll(new Label("Choose an option :"), btnAddTask, btnTasksList);
    }
    
}
