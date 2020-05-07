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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.services.ColisService;
import com.esprit.pidev.utils.Statics;

/**
 *
 * @author aissa
 */
public class AjoutColis extends Form {

    public AjoutColis(Form previous) {
        super("Envoyer Un Colis", BoxLayout.y());
        if(Statics.sessionID!=0)
        {
        ComboBox<String> cat = new ComboBox<>();
        Button btn = new Button("Envoyer Colis");
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
       /* ArrayList<category> List = new CategoryService().AfficherCategorie();
        for (int i=0; i<List.size();i++){
        cat.addItem(List.get(i).getCategorie().toString());
        }*/
        
        
        btn.addActionListener((evt) -> {
        if(val.isValid())
        {
            if ((tfDepart.getText().length() == 0) || (tfDestination.getText().length() == 0)|| (tfPoids.getText().length() == 0)|| (tfNomDestinataire.getText().length() == 0)|| (tfNomExpediteur.getText().length() == 0)|| (tfMailDestinataire.getText().length() == 0)|| (tfMailExpediteur.getText().length() == 0)|| (tfTelDestinataire.getText().length() == 0)) 
            {
                Dialog.show("Erreur", "Veuillez Remplir Tout Les Champs", "OK", null);
            } 
            else {
                try {
                    Colis t = new Colis(tfDepart.getText(),tfDestination.getText(),tfNomExpediteur.getText(),tfMailExpediteur.getText(),Float.parseFloat(tfPoids.getText()),tfNomDestinataire.getText(),tfMailDestinataire.getText(),Integer.parseInt(tfTelDestinataire.getText()));
                    if (new ColisService().addcolis(t,Statics.sessionID)) {
                        Dialog.show("SUCCESS", "Colis Envoyé", "OK", null);
                    } else {
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
            previous.showBack();
        });
       }
        else if (Statics.sessionID==0)
        {
             login su = new login();
             su.getF().show(); 
        }
    }

}
