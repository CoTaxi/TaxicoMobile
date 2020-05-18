/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.user;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
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
import com.esprit.pidev.services.UserService;
import com.mycompany.myapp.Forms.BaseForm;

/**
 *
 * @author Oussama_RMILI
 */
public class SignUpFormDriver1 extends BaseForm {

//    
//    public SignUpFormDriver1() {
//        this(com.codename1.ui.util.Resources.getGlobalResources(),com.codename1.ui.util.Resources.getGlobalResources(),com.codename1.util.StringUtil, com.codename1.util.StringUtil, com.codename1.util.BigInteger, com.codename1.StringUtil);
//    }
//    
    public SignUpFormDriver1(com.codename1.ui.util.Resources res,
            String prenom, String nom, int numT, String dtn, String email, int permis) 
    {   super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField ribTF = new TextField(null, "Rib bancaire", 20, TextField.NUMERIC);
        TextField expTF = new TextField(null, "Nb année d'exp", 20, TextField.NUMERIC);
        TextField usernameTF = new TextField("", "Username", 20, TextField.ANY);
        TextField passwordTF = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        TextField password2TF = new TextField("", "Confirmer mot de passe", 20, TextField.PASSWORD);
        
        ribTF.setSingleLineTextArea(false);
        password2TF.setSingleLineTextArea(false);
        passwordTF.setSingleLineTextArea(false);
        usernameTF.setSingleLineTextArea(false); 
       
        expTF.setSingleLineTextArea(false);
    
        //Button create = new Button("Valider ✔");
        Button valid = new Button("Valider  ✔");
        Button retourn = new Button("Retourner  ◀");
       // Button check = new Button("Vérifier  🔎");
        Button signIn1 = new Button("Se connecter");
        signIn1.addActionListener(e -> previous.showBack());
        signIn1.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Vous avez déja un compte?");
        
        Container content = BoxLayout.encloseY(
                
                new Label("S'inscrire 2/2", "LogoLabel"),
     
      
                new FloatingHint(ribTF),
                createLineSeparator(),
                new FloatingHint(expTF),
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
                valid,
                retourn,
               // check,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn1)
        ));
//        check.requestFocus();
//        check.addActionListener(e -> new ActivateForm(resourceObjectInstance,resourceObjectInstance1).show());

        retourn.addActionListener(l->{
            new SignUpFormDriver(res, prenom, nom, 
                    numT, email, dtn, permis).showBack();
        });
        
        valid.addActionListener(v->{
           if(new UserService().register(prenom,nom,numT,email,usernameTF.getText(),dtn,permis,Integer.valueOf(expTF.getText()),Integer.valueOf(ribTF.getText()),passwordTF.getText(),"chauffeur"))
           {
               Dialog.show("SUCCESS", "Bienvenu Dans La Famille TaxiCo", "OK", null);
               new SignInForm(res).show();
           }
           else 
           {
               Dialog.show("ERROR", "Server error", "OK", null);
           }
        });
        
        signIn1.addActionListener(sign->{
            new SignInForm(res);
        });
    }

}
