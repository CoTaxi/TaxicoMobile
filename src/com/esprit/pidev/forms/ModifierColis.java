/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.services.ColisService;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ModifierColis extends Form
{
        public ModifierColis(int Id) {
        super("Edit Colis", BoxLayout.y());
        
        //-------------Declaration Start
        ComboBox<String> cat = new ComboBox<>();
        Button btn = new Button("Edit Colis");
        TextModeLayout tl = new TextModeLayout(3, 2);
        TextComponent tfDepart = new TextComponent().label("Depart");
        //.errorMessage("Input is essential in this field");
        TextComponent tfDestination = new TextComponent().label("Destination");
        TextComponent tfNomExpediteur = new TextComponent().label("Nom Expediteur");
        TextComponent tfMailExpediteur = new TextComponent().label("Mail Expediteur");
        TextComponent tfPoids = new TextComponent().label("Poids");
        TextComponent tfNomDestinataire = new TextComponent().label("Nom Destinataire");
        TextComponent tfMailDestinataire = new TextComponent().label("Mail Destinataire");
        TextComponent tfTelDestinataire = new TextComponent().label("Tel Destinataire");
        Validator val = new Validator();
        val.addConstraint(tfMailExpediteur, RegexConstraint.validEmail("Mail Expediteur"));
        val.addConstraint(tfMailDestinataire, RegexConstraint.validEmail("Mail Expediteur"));
        val.addConstraint(tfTelDestinataire, new NumericConstraint(true), new LengthConstraint(8,"Telephone"));
        val.addConstraint(tfPoids, new NumericConstraint(true));
        ArrayList<Colis> det = new ColisService().getColis(Integer.valueOf(Integer.toString(Id)));
        
        //------------------Declaration End
        
          for (int i=0;i<det.size();i++)
              {
              tfDestination.text(det.get(i).getDestination());
              tfDepart.text(det.get(i).getDepart());
              tfNomExpediteur.text(det.get(i).getNomExpediteur());
              tfMailExpediteur.text(det.get(i).getMailExpediteur());
              tfPoids.text(Float.toString(det.get(i).getPoids()));
              tfNomDestinataire.text(det.get(i).getNomDestinataire());
              tfMailDestinataire.text(det.get(i).getMailDestinataire());
              tfTelDestinataire.text(Integer.toString(det.get(i).getTelDestinataire()));
              
              }
         btn.addActionListener((ActionEvent evt) -> {
             if(val.isValid())
        {
            if ((tfDepart.getText().length() == 0) || (tfDestination.getText().length() == 0)|| (tfPoids.getText().length() == 0)|| (tfNomDestinataire.getText().length() == 0)|| (tfNomExpediteur.getText().length() == 0)|| (tfMailDestinataire.getText().length() == 0)|| (tfMailExpediteur.getText().length() == 0)|| (tfTelDestinataire.getText().length() == 0)) {
                Dialog.show("Erreur", "Veuillez Remplir Tout Les Champs", "OK", null);
            } else {
                try {
                    Colis col = new Colis(tfDepart.getText(),tfDestination.getText(),tfNomExpediteur.getText(),tfMailExpediteur.getText(),Float.parseFloat(tfPoids.getText()),tfNomDestinataire.getText(),tfMailDestinataire.getText(),Integer.parseInt(tfTelDestinataire.getText()));
                    if (new ColisService().modifycolis(Id,col)) 
                    {
                        Dialog.show("SUCCESS", "Colis Modifié", "OK", null);
                    } 
                    else 
                    {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                  //  Dialog.show("ERROR", "Status must be a number", "OK", null);
                }

            }
        }
        else 
        {
          Dialog.show("Erreur", "Verifiez Vos Donnees !", "OK", null);
        } 
        });

        this.addAll(tfDepart, tfDestination,tfPoids,tfNomExpediteur,tfMailExpediteur,tfNomDestinataire,tfMailDestinataire,tfTelDestinataire, btn);
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
          new  ShowDetailsColis(Id).show();
        });   
        }
}
