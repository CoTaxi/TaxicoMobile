/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.html.HTMLElement;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.BaseSpinner;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.services.ReclamationServices;

/**
 *
 * @author Oussama_RMILI
 */
public class DetailsRecForm extends Form{
    private Resources theme;
    
    public DetailsRecForm(Form previous,String respond, int id){
        super(null, BoxLayout.y());
//        Container cn1 = new Container(BoxLayout.y());
//        cn1.setScrollableY(true);
        theme = UIManager.initFirstTheme("/theme");
        SpanLabel sl = new SpanLabel(respond);
        sl.getStyle().setBorder(Border.createDashedBorder(CENTER, HTMLElement.COLOR_BLUE));
        TextField txt = new TextField(null, "Modifier votre réclamation");
        Button edit = new Button("Valider");
        Button email = new Button("E-mail");
        edit.setIcon(theme.getImage(respond));
        FontImage.setMaterialIcon(sl, FontImage.MATERIAL_MESSAGE);
        FontImage.setMaterialIcon(edit, FontImage.MATERIAL_SAVE);
        FontImage.setMaterialIcon(email, FontImage.MATERIAL_EMAIL);
        
        this.getToolbar().addCommandToLeftBar("Retourner", null, (evt) -> {
            new ReclamationListForm(this).showBack();      
        });
        edit.addActionListener(up->{
            String NewMsg = txt.getText().toString();
            if (NewMsg.length()==0) 
            {
                ToastBar.showErrorMessage("Veuillez saisir votre nouveau message");
            } else {
                if (new ReclamationServices().updaterec(id, NewMsg)) 
                {
                    ToastBar.showInfoMessage("Votre réclamation a été modifiée avec succée");
                    
                } else {
                    ToastBar.showErrorMessage("Erreur de serveur");
                }
            }

        });
        email.addActionListener(em->{
            new ReclamationServices().sendEmail();
        });
//        cn1.add(sl);
        this.addAll(sl,txt,edit,email);
    }
    
}
