/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.OnOffSwitch;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.services.ServicesVehicule;
import com.mycompany.myapp.entities.Vehicule;


/**
 *
 * @author achref
 */
public class addvehicule extends Form {

    Resources theme;

    public addvehicule(Form previous) {
        setTitle("Ajouter une vehicule");
        setLayout(BoxLayout.y());
        Toolbar.setGlobalToolbar(true);
        TextField matricule = new TextField("", "matricule");
        //TextField marque= new TextField("", "marque:");  
        ComboBox cbmarquee = new ComboBox();
        cbmarquee.addItem("Alfa Romeo");
        cbmarquee.addItem("Citroen");
        cbmarquee.addItem("Peugeot");
        //TextField modele= new TextField("", "modele:");   
        ComboBox cbmodele = new ComboBox();
      // TextField couleur = new TextField("", "couleur");
      ComboBox couleur = new ComboBox();
      
     couleur.addItem("Blanc");
    couleur.addItem("Bleu");
    couleur.addItem("Jaune");
    couleur.addItem("Rouge");
    couleur.addItem("Vert");
    couleur.addItem("Noir");
        TextField cartegrise = new TextField("", "cartegrise");
        //TextField places = new TextField("", "places");
        Label lplaces = new Label("Places Disponibles :");

        Slider splace = new Slider();
        splace.setHeight(10);
        splace.setIncrements(1);
        splace.setMaxValue(5);
        splace.setMinValue(1);
        splace.setProgress(1);
        splace.setEnabled(true);
        splace.setEditable(true);

        TextField position = new TextField("", "position");

        //Location position = LocationManager.getLocationManager().getCurrentLocationSync();
        //TextField acceptc = new TextField("", "acceptc");
        //CheckBox coli = new CheckBox("Tranfert des colis");
        Label question = new Label("Acceptez-vous le tranfert des colis?");
        OnOffSwitch coli = new OnOffSwitch();
        coli.setOff("Non");
        coli.setOn("Oui");
        Label lcoli = new Label("");
        TextField destination = new TextField("", "destination");

        Button btnValider = new Button("Ajouter vehicule");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((matricule.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Vehicule t = new Vehicule(matricule.getText(), cbmarquee.getSelectedItem().toString(), cbmodele.getSelectedItem().toString(), cartegrise.getText(), couleur.getSelectedItem().toString(), splace.getProgress(), position.getText(), lcoli.getText(), destination.getText());
                        if (ServicesVehicule.getInstance().addvehicule(t)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        cbmarquee.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if (cbmarquee.getSelectedItem().toString().equalsIgnoreCase("Alfa Romeo")) {

                    cbmodele.addItem("4C");
                    cbmodele.addItem("Giula");
                    cbmodele.addItem("Giulietta");
                } else if (cbmarquee.getSelectedItem().toString().equalsIgnoreCase("Citroen")) {
                    cbmodele.addItem("C3");
                    cbmodele.addItem("C4");
                    cbmodele.addItem("Cactus");
                } else if (cbmarquee.getSelectedItem().toString().equalsIgnoreCase("Peugeot")) {

                    cbmodele.addItem("206+");
                    cbmodele.addItem("308");
                    cbmodele.addItem("408");

                }
            }
        });
        splace.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                lplaces.setText("Places Disponibles  " + splace.getProgress());
            }

        });
        coli.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if (coli.isValue()) {
                    lcoli.setText( coli.getOn());
                } else {
                    lcoli.setText(coli.getOff());
                }

            }

        });
        

        
        
        

        addAll(matricule,cartegrise, cbmarquee, cbmodele, couleur,  lplaces, splace, position, destination,question, coli, lcoli, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
