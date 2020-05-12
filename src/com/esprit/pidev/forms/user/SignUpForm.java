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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
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
import java.util.Date;


/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {
    
    public SignUpForm(com.codename1.ui.util.Resources res,
            String prenom, String nom, int numT, String dtn) 
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
            Picker datePicker = new Picker();
            datePicker.setType(Display.PICKER_TYPE_DATE);
            //datePicker.setDate(new java.util.Date());
            prenameTF.setText(prenom);
            nameTF.setText(nom);
            telTF.setText(String.valueOf(numT));
            
            Date date1;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dtn);
              datePicker.setDate(date1);
        } catch (ParseException ex) {
           
        }

        telTF.setSingleLineTextArea(false);
        nameTF.setSingleLineTextArea(false);
        prenameTF.setSingleLineTextArea(false);

        Button suiv = new Button("Suivant  ▶");
        Button check = new Button("Vérifier  🔎");
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
                createLineSeparator()
//                new FloatingHint(usernameTF),
//                createLineSeparator(),
//                new FloatingHint(permisTF),
//                createLineSeparator(),
//                new FloatingHint(expTF),
//                createLineSeparator(),
//                new FloatingHint(ribTF),
//                createLineSeparator(),
//                new FloatingHint(passwordTF),
//                createLineSeparator(),
//                new FloatingHint(password2TF),
//                createLineSeparator()
);


content.setScrollableY(true);
//        add(BorderLayout.CENTER, content1);
add(BorderLayout.CENTER, content);
add(BorderLayout.SOUTH, BoxLayout.encloseY(
        suiv,
        FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
));
//        String nom = nameTF.getText();
//        String prenom = prenameTF.getText();
//        int num = Integer.valueOf(telTF.getText());
//        int dtn = Integer.valueOf(datePicker.getText());

suiv.addActionListener(e -> new SignUpFormUser1(res,
        prenameTF.getText(), nameTF.getText(), Integer.valueOf(telTF.getText()), datePicker.getDate().toString()).show());  

    }
}
