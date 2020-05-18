/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.esprit.pidev.forms.user;

import com.codename1.components.FloatingHint;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.forms.maintenance.ChForm;
import com.esprit.pidev.services.UserService;
import com.esprit.pidev.utils.Statics;
import com.mycompany.myapp.Forms.BaseForm;
import com.mycompany.myapp.Forms.NewsfeedForm;
import com.esprit.pidev.forms.colis.NosServices;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

    public SignInForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
        
    }

    
    public SignInForm(com.codename1.ui.util.Resources res) 
    {
        super(new BorderLayout());
        System.out.println("last"+Statics.Lastcnx);
        System.out.println("id"+Statics.sessionID);
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        add(BorderLayout.NORTH, new Label(res.getImage("logo.png"), "LogoLabel"));
        
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Se connecter");
        Button signUp = new Button("Créer un compte");
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Vous n'avez pas un compte?");
        Button Driver = new Button("🚖 Chauffeur");
        Button user = new Button("🙋‍ Client -");
        Driver.setUIID("Link");
        Driver.setUIID("Bold");
        user.setUIID("Link");
        user.setUIID("Bold");
        Driver.getStyle().setFgColor(0xf99f1b);
        user.getStyle().setFgColor(0xf99f1b);
        user.addActionListener(u->{
            String prenom="", nom="", dtn="" ;
             int numT=0;
            new SignUpForm(res,prenom, nom, numT,dtn).show();
        });
        
        Driver.addActionListener(D->{
            String prenom="", nom="", email="", dtn="" ;
             int numT=0, permis=0;
            new SignUpFormDriver(res,prenom, nom, 
                    numT, email, dtn, permis).show();
        });
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp),
                FlowLayout.encloseCenter(user, Driver)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        signIn.addActionListener(log->{
             new UserService().user_login(username.getText(), password.getText());
             System.out.println(Statics.sessionID);
             System.out.println(Statics.type);
           if(Statics.sessionID!=0)
           {
               ToastBar.showInfoMessage("Bienvenu");
               if(Statics.type.equals("client"))
               new  NewsfeedForm(res).show();
               else if(Statics.type.equals("chauffeur"))
               new ChForm(res).show();
           }
           else
           {
               Dialog.show("Erreur","Username ou mot de passe sont érronés" , "Ok", null);
               Statics.sessionID=0;
               Statics.type="";
           }
        });
        
    }
    
}

