/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import static com.codename1.charts.util.ColorUtil.red;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Reclamation;
import com.esprit.pidev.services.ReclamationServices;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author aissa
 */
public class HomeForm extends Form {
    private Resources theme;

    public HomeForm() {
        super(null, BoxLayout.yCenter());
        theme = UIManager.initFirstTheme("/theme");
        Label lOption = new Label("Choisir option : ");
        lOption.getStyle().setFgColor(0xf99f1b);
        Button rec = new Button("Réclamation");
        Button Maintenance = new Button("Maintenance");
        Button Vehicule = new Button("Véhicule");
        Button colis = new Button("Colis");
        Button commande = new Button("Commande");
        Button event = new Button("Evénement");
        Button forum = new Button("Forum");
        Button user = new Button("User");
        
        rec.addActionListener((evt) -> {
            new ReclamationForm().show();
        });
        Maintenance.addActionListener((evt) -> {
            new MaintenanceForm().show();
        });        
        Vehicule.addActionListener((evt) -> {
            new VecForm().show();
        });  
        
        colis.addActionListener((evt) -> {
            new ColisForm().show();
        });  
        
        commande.addActionListener((evt) -> {
            
        });
        
        event.addActionListener((evt) -> {
            
        });
        
        forum.addActionListener((evt) -> {
            
        });
        
        user.addActionListener(u->{
            login log = new login();
            log.getF().show();
        });
        this.addAll(rec,Maintenance,colis,Vehicule,commande,forum,event,user);
        
    }
    
}
