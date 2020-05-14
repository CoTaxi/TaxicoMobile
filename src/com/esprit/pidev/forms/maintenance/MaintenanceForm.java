package com.esprit.pidev.forms.maintenance;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Forms.BaseForm;

/**
 *
 * @author aissa
 */
public class MaintenanceForm extends BaseForm {
private Resources theme;
    public MaintenanceForm(Resources res) {
        
        Button btnAddTask = new Button("Reserve Rdv");
        Button btnTasksList = new Button("Rdvs List");
        Button btnchauffeur = new Button("Chauffeur");
        
        btnAddTask.addActionListener((evt) -> {
            new ReserveRdvMaintenanceForm(res).show();
        });
        btnTasksList.addActionListener((evt) -> {
            new ReserveListForm(res).show();
        });
        btnchauffeur.addActionListener((evt) -> {
            new ChForm(res).show();
        });
//        btnsms.addActionListener((evt) -> {
//            new EnterMobileNumberForm().show();
//        });
//        this.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
//        this.setBgImage(theme.getImage("Affiche_final.png"));
        this.addAll(new Label("Choose an option :"), btnAddTask, btnTasksList,btnchauffeur);
    }
    
}
