/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author achref
 */
public class VecForm extends Form{
    Form current;
    Resources theme;
    public VecForm() {
        theme = UIManager.initFirstTheme("/theme");
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        ImageViewer img = new ImageViewer();
//        img.setImage(theme.getImage("logo3.png").scaled(650, 550));
        Button btnAddvec = new Button("ajouter vehicule");
        Button btnListvecs = new Button("consulter vehicule");
        Button delete= new Button("supprimer"); 
        btnAddvec.addActionListener(e-> new addvehicule(current).show());
        btnListvecs.addActionListener(e-> new ListVehicule(current).show());
        delete.addActionListener(e-> new DeleteVecForm(current).show());
        
        addAll(btnAddvec,btnListvecs,delete);
        
        
    }
    
}
