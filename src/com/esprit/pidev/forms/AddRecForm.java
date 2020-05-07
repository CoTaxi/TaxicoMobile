/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.models.Reclamation;
import com.esprit.pidev.models.typeReclamation;
import com.esprit.pidev.services.ReclamationServices;
import java.util.ArrayList;

/**
 *
 * @author aissa
 */
public class AddRecForm extends Form {

    public AddRecForm(Form previous) {
        super("Ajouter réclamation", BoxLayout.y());

        TextField Message = new TextField(null, "Saisir message...");
        
        ComboBox Type = new ComboBox();
        Label lMsg = new Label("Message : ");
        lMsg.getStyle().setMarginTop(10);
        Label lType = new Label("Objet : ");
        Type.getStyle().setMarginTop(10);
        //Type.getStyle().setBgColor(0xf99f1b);
        FontImage.setMaterialIcon(lMsg, FontImage.MATERIAL_MESSAGE);
        FontImage.setMaterialIcon(lType, FontImage.MATERIAL_SUBJECT);
        Button btn = new Button("Envoyer");
        btn.getStyle().setMarginTop(10);
        FontImage.setMaterialIcon(btn, FontImage.MATERIAL_SEND);
        ArrayList<typeReclamation> List = new ReclamationServices().getAllType();
        
        for (int i = 0; i < List.size(); i++) {
            Type.addItem(List.get(i).getTitre());
        }
        btn.addActionListener((evt) -> {
            
                if ((Message.getText().length() == 0)) {
                Dialog.show("Alerte", "Veuillez entrer tous les champs", "OK", null);
            } else {
              ArrayList<typeReclamation> ListId = new ReclamationServices().getAllIdType(Type);
                    Reclamation t = new Reclamation(Message.getText());
                    if (new ReclamationServices().addrec(t,ListId.get(0).getId())) {
                        ToastBar.showInfoMessage("Votre réclamation est ajoutée avec succée");
                    } else {
                       Dialog.show("Erreur", "Server error", "OK", null);
                }
            }
        });

        this.addAll(lType,Type,lMsg,Message,btn);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }

}
