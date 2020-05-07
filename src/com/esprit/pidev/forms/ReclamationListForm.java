/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import static com.codename1.ui.layouts.BoxLayout.x;
import com.codename1.ui.layouts.FlowLayout;
import com.esprit.pidev.models.Reclamation;
import com.esprit.pidev.services.ReclamationServices;
import java.util.ArrayList;

/**
 *
 * @author aissa
 */
public class ReclamationListForm extends Form {

    public ReclamationListForm(Form previous) {
        super("Tasks list", new BorderLayout());
        Container listRec = new Container(BoxLayout.y());
        listRec.setScrollableY(true);
        //listRec.setScrollableX(true);
        ArrayList<Reclamation> List = new ReclamationServices().getAllTasks();
        for (int i = 0; i<List.size(); i++) {
            Button btnR = new Button();
            FontImage.setMaterialIcon(btnR, FontImage.MATERIAL_DELETE);
            MultiButton mBtn = new MultiButton("Réc n°"+i+":");
            mBtn.setTextLine1(List.get(i).getType());
            mBtn.setTextLine2(List.get(i).getMessage());
            mBtn.setTextLine3(List.get(i).getEtat());
            mBtn.setTextLine4(List.get(i).getDate());
            String res = List.get(i).getReponse();
            FontImage.setMaterialIcon(mBtn, FontImage.MATERIAL_COMMENT);
            int id = List.get(i).getId_reclamation();
            listRec.addAll(mBtn,btnR);
            btnR.addActionListener(r->{
                if(new ReclamationServices().deleterec(id))
                {
                    ToastBar.showInfoMessage("Votre réclamation est supprimée avec succé");
                }else{
                    ToastBar.showErrorMessage("Erreur de suppression");
                }
                mBtn.remove();
                btnR.remove();
                this.refreshTheme();
            });
            
            mBtn.addActionListener(al->{
                new DetailsRecForm(this, res, id).show();
            });
            
        }
        this.add(CENTER, listRec);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
}
