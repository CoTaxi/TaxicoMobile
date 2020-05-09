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
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Forms.ActivateForm;
import com.mycompany.myapp.Forms.BaseForm;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    public SignUpForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public SignUpForm(com.codename1.ui.util.Resources res) 
    {   super(new BorderLayout());
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
        TextField emailTF = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(new java.util.Date());
        TextField permisTF = new TextField(null, "Num de permis", 20, TextField.NUMERIC);
        TextField ribTF = new TextField(null, "Rib bancaire", 20, TextField.NUMERIC);
        TextField expTF = new TextField(null, "Nb année d'exp", 20, TextField.NUMERIC);
        TextField usernameTF = new TextField("", "Username", 20, TextField.ANY);
        TextField passwordTF = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        TextField password2TF = new TextField("", "Confirmer mot de passe", 20, TextField.PASSWORD);
        password2TF.setSingleLineTextArea(false);
        passwordTF.setSingleLineTextArea(false);
        usernameTF.setSingleLineTextArea(false);
        expTF.setSingleLineTextArea(false);
        ribTF.setSingleLineTextArea(false);
        permisTF.setSingleLineTextArea(false);
        emailTF.setSingleLineTextArea(false);
        telTF.setSingleLineTextArea(false);
        nameTF.setSingleLineTextArea(false);
        prenameTF.setSingleLineTextArea(false);
        usernameTF.setSingleLineTextArea(false);
        passwordTF.setSingleLineTextArea(false);
        password2TF.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Se connecter");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Vous avez déja un compte?");
        
        Container content = BoxLayout.encloseY(
                new Label("S'inscrire", "LogoLabel"),
                new FloatingHint(prenameTF),
                createLineSeparator(),
                new FloatingHint(nameTF),
                createLineSeparator(),
                new FloatingHint(telTF),
                createLineSeparator(),
                new FloatingHint(emailTF),
                createLineSeparator(),
                new Picker(),
                createLineSeparator(),
                new FloatingHint(usernameTF),
                createLineSeparator(),
                new FloatingHint(permisTF),
                createLineSeparator(),
                new FloatingHint(expTF),
                createLineSeparator(),
                new FloatingHint(ribTF),
                createLineSeparator(),
                new FloatingHint(passwordTF),
                createLineSeparator(),
                new FloatingHint(password2TF),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener(e -> new ActivateForm(res).show());
    }
    

    
}
