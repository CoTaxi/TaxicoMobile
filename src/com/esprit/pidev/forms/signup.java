/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.services.UserService;
import com.esprit.pidev.utils.Statics;


/**
 *
 * @author achref
 */
public class signup extends Form{

    

    Form f;
    Label username;
    Label email;
    Label password;
    Label password2;

    TextField usernameTF;
    TextField emailTF;
    TextField passwordTF;
    TextField password2TF;

    Button signup;
    Button annuler;

    public signup() {
        
        f = new Form(BoxLayout.y());
        f.setScrollableY(true);
        ImageViewer img = new ImageViewer();
        //img.setImage(theme.getImage("sign up.png").scaled(650, 650));
        username = new Label("nom utilisateur");
        email = new Label("E-mail");
        password = new Label("mot de passe");
        password2 = new Label("retaper mot de passe");
        Label dtn = new Label("Date de naissance");
        Label lname = new Label("Nom");
        Label lprename = new Label("Prénom");
        Label lTel = new Label("Tel");
        Label lpermis = new Label("Permis");
        Label lrib = new Label("RIB bancaire");
        Label lexp = new Label("Expérience");

        TextField nameTF = new TextField("", "Nom");
        TextField prenameTF = new TextField("", "Prénom");
        TextField telTF = new TextField(null, "Num de Tel", 10, TextField.PHONENUMBER);
        emailTF = new TextField(null, "Email", 10, TextField.EMAILADDR);
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(new java.util.Date());
        TextField permisTF = new TextField(null, "Num de permis", 10, TextField.NUMERIC);
        TextField ribTF = new TextField(null, "Rib bancaire", 10, TextField.NUMERIC);
        TextField expTF = new TextField(null, "Nb année d'exp", 10, TextField.NUMERIC);
//TextField telTF = new TextField("", "Num de Tel");
        usernameTF = new TextField("", "nom utilisateur");
        passwordTF = new TextField("", "Mot de passe",10,TextField.PASSWORD);
        password2TF = new TextField("", "Mot de passe pour confirmation",10,TextField.PASSWORD);

        signup = new Button("s'inscrire");
        annuler = new Button("Annuler");
        signup.addActionListener(up->{
            if(passwordTF.getText().toString().equals(password2TF.getText().toString()))
            {
                if(new UserService().register(prenameTF.getText(), nameTF.getText(), Integer.valueOf(telTF.getText()), 
                        emailTF.getText().toString(), usernameTF.getText().toString(), 
                        datePicker.getText(), Integer.valueOf(permisTF.getText()),
                        Integer.valueOf(expTF.getText()), Integer.valueOf(ribTF.getText()), 
                        password2TF.getText()))
                {
                    login log = new login();
                    log.getF().show();
                    Dialog.show("Sucess", "Vous êtes bien inscrit 👌❤😀", "OK", null);
                } else{
                    Dialog.show("Erreur", "Server error", "OK", null);
                }
            }else{
                Dialog.show("Erreur", "Veuillez saisir mot de passe correcte", "OK", null);
            }
        });
        annuler.addActionListener(d->{
            login log = new login();
            log.getF().show();
            Statics.sessionID=0;
        });
        
        f.getToolbar().setTitle("Bienvenu dans TaxiCo");
//        f.getToolbar().add(BorderLayout.WEST, theme.getImage("driver.png"));
//        f.getToolbar().add(BorderLayout.EAST, theme.getImage("cov1.png"));
//        f.getToolbar().getStyle().setPaddingLeft(30);
//        f.getToolbar().getStyle().setPaddingRight(30);
//        f.add(img);
        f.add(lprename);
        f.add(prenameTF);
        f.add(lname);
        f.add(nameTF);
        f.add(lTel);
        f.add(telTF);
        f.add(email);
        f.add(emailTF);
        f.add(username);
        f.add(usernameTF);
        f.add(dtn);
        f.add(datePicker);
        f.add(lpermis);
        f.add(permisTF);
        f.add(lexp);
        f.add(expTF);
        f.add(lrib);
        f.add(ribTF);
        f.add(password);
        f.add(passwordTF);
        f.add(password2);
        f.add(password2TF);
        f.add(signup);
        f.add(annuler);
        System.out.println(Statics.sessionID);

    }

    public Form getS() {
        return f;
    }

    public void setS(Form f) {
        this.f = f;
    }
}
