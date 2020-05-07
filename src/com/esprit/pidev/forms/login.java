/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.NORTH;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.services.UserService;
import com.esprit.pidev.utils.Statics;

/**
 *
 * @author achref
 */
public class login extends Form {

    
   Form f;
    Label username;
    Label password;
    TextField loginTF;
    TextField passwordTF;
    Button login;
    Button signup;
    Style s;

    public login() {
        f= new Form(BoxLayout.yCenter());
        ImageViewer img = new ImageViewer();
//        img.setImage(theme.getImage("logo3.png").scaled(600,511));
        username = new Label("Nom d'utilisateur");
        loginTF = new TextField("", "Nom d'utilisateur", 10, 0);
        password = new Label("Mot de passe");
        passwordTF = new TextField("", "Mot de passe", 10, TextField.PASSWORD);
        login = new Button("se connecter");
        signup = new Button("Créer un compte");
        username.getStyle().setMarginTop(70);
        f.getToolbar().getStyle().setBgColor(0x36324D);
        f.getToolbar().setTitle("Bienvenu dans TaxiCo");
       // f.getToolbar().add(BorderLayout.WEST, theme.getImage("driver.png"));
        //f.getToolbar().add(BorderLayout.EAST, theme.getImage("cov1.png"));
        f.getToolbar().getStyle().setPaddingLeft(30);
        f.getToolbar().getStyle().setPaddingRight(30);
//        f.add(img);
        f.add(username);
        f.add(loginTF);
        f.add(password);
        f.add(passwordTF);
        f.add(login);

        f.add(signup);
        
        login.addActionListener(log->{
             new UserService().user_login(loginTF.getText(), passwordTF.getText());
             System.out.println(Statics.sessionID);
           if(Statics.sessionID!=0){
               ToastBar.showInfoMessage("Bienvenu");
//               signup su = new signup();
//                su.getS().show(); 
               new  HomeForm().show();
           }else{
               Dialog.show("Erreur","Username ou mot de passe sont érronés" , "Ok", null);
               Statics.sessionID=0;

           }
        });
        signup.addActionListener((evt) -> {
            signup su = new signup();
            su.getS().show();
   
        });
    
    }
        
    

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
    
}

