package com.esprit.pidev.forms.user;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.Forms.BaseForm;

/**
 *
 * @author Oussama_RMILI
 */
public class SignUpFormDriver extends BaseForm{
    
    
    public SignUpFormDriver(com.codename1.ui.util.Resources res,
            String prenom, String nom, int numT, String email,String dtn,int permis) 
    {   
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        
        TextField nameTF = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenameTF = new TextField("", "Prénom", 20, TextField.ANY);
        TextField telTF = new TextField(null, "Num de Tel", 20, TextField.PHONENUMBER);
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(new java.util.Date());
        // Initialisation des champs 
        TextField emailTF = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField permisTF = new TextField(null, "Num de permis", 20, TextField.NUMERIC);
        prenameTF.setText(prenom);
        nameTF.setText(nom);
        telTF.setText(String.valueOf(numT));
        datePicker.setText(dtn);
        permisTF.setText(String.valueOf(permis));
        
        telTF.setSingleLineTextArea(false);
        nameTF.setSingleLineTextArea(false);
        prenameTF.setSingleLineTextArea(false);
        permisTF.setSingleLineTextArea(false);
        emailTF.setSingleLineTextArea(false);
//        usernameTF.setSingleLineTextArea(false);
//        passwordTF.setSingleLineTextArea(false);
//        password2TF.setSingleLineTextArea(false);
        Button suivCh = new Button("Suivant  ▶");
        //Button check = new Button("Vérifier  🔎");
        Button signIn = new Button("Se connecter");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Vous avez déja un compte?");

        Container content = BoxLayout.encloseY(
                
                new Label("S'inscrire 1/2", "LogoLabel"),
                new FloatingHint(prenameTF),
                createLineSeparator(),
                new FloatingHint(nameTF),
                createLineSeparator(),
                new FloatingHint(telTF),
                createLineSeparator(),
                new Picker(),
                createLineSeparator(),
                new FloatingHint(emailTF),
                createLineSeparator(),
                new FloatingHint(permisTF),
                createLineSeparator()
        );

       
        content.setScrollableY(true);
//        add(BorderLayout.CENTER, content1);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                suivCh,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
//        String nom = nameTF.getText();
//        String prenom = prenameTF.getText();
//        int num = Integer.valueOf(telTF.getText());   
//        int dtn = Integer.valueOf(datePicker.getText()); 
        signIn.addActionListener(sign->{
            new SignInForm(res);
        });
        suivCh.addActionListener(e -> new SignUpFormDriver1(res,
                prenameTF.getText(), nameTF.getText(), Integer.valueOf(telTF.getText()), 
                datePicker.getText(), emailTF.getText(),Integer.valueOf(permisTF.getText())).show());  
    }

}
