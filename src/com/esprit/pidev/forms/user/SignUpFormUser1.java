/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.user;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.services.UserService;
import com.mycompany.myapp.Forms.BaseForm;

/**
 *
 * @author Oussama_RMILI
 */
public class SignUpFormUser1 extends BaseForm {
    
//    public SignUpFormUser1() {
//        this(com.codename1.ui.util.Resources.getGlobalResources(),com.codename1.ui.util.Resources.getGlobalResources());
//    }
    
    public SignUpFormUser1(com.codename1.ui.util.Resources res,
            String prenom, String nom, int numT, String dtn) 
    {   super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        
        TextField emailTF = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField usernameTF = new TextField("", "Username", 20, TextField.ANY);
        TextField passwordTF = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        TextField password2TF = new TextField("", "Confirmer mot de passe", 20, TextField.PASSWORD);
        emailTF.setSingleLineTextArea(false);
        usernameTF.setSingleLineTextArea(false);
        usernameTF.setSingleLineTextArea(false);
        passwordTF.setSingleLineTextArea(false);
        password2TF.setSingleLineTextArea(false);
        Button create = new Button("Valider  ✔");
        Button retourn = new Button("Retourner  ◀");
        //Button check = new Button("Vérifier 🔎");
        Button signIn = new Button("Se connecter");
       
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Vous avez déja un compte?");
        
        Container content = BoxLayout.encloseY(
                
                new Label("S'inscrire 2/2", "LogoLabel"),
     
                new FloatingHint(emailTF),
                createLineSeparator(),
                new FloatingHint(usernameTF),
                createLineSeparator(),
                createLineSeparator(),
                new FloatingHint(passwordTF),
                createLineSeparator(),
                new FloatingHint(password2TF),
                createLineSeparator()
        );

       
        content.setScrollableY(true);
//        add(BorderLayout.CENTER, content1);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                create,
                retourn,
              //  check,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
      //  check.requestFocus();
       // check.addActionListener(e -> new ActivateForm(resourceObjectInstance,resourceObjectInstance1).show());
        create.addActionListener(l->{
            //************call register service method ************
           if(new UserService().register(prenom,nom,numT,emailTF.getText(),usernameTF.getText(),dtn,0,0,0,passwordTF.getText(),"client"))
           {
               Dialog.show("SUCCESS", "Bienvenu Dans La Famille TaxiCo", "OK", null);
               new SignInForm(res).show();
           }
           else 
           {
               Dialog.show("ERROR", "Server error", "OK", null);
           }
            System.out.println(prenom);
            System.out.println(dtn);
        });
        retourn.addActionListener(l->{
            new SignUpForm(res, prenom, nom, numT, dtn).showBack();
        });
        
         signIn.addActionListener(e ->{
            new SignInForm(res);
        });
    }    
}
