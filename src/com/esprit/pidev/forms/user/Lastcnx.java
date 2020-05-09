/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of tthiss software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and tthiss permission notice shall be included in all copies or substantial portions 
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

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.User;
import com.esprit.pidev.services.UserService;
import com.esprit.pidev.utils.Statics;
import com.mycompany.myapp.Forms.BaseForm;
import java.util.ArrayList;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class Lastcnx extends BaseForm {

    public Lastcnx(Resources res) 
    {
        super("SignIn", BoxLayout.y());
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        Container cnt = new Container(new BorderLayout());
        cnt.add(BorderLayout.NORTH, new Label(res.getImage("logo.png"), "LogoLabel"));
        add(cnt);
        ArrayList<User> List = new UserService().lastcnx(Statics.Lastcnx);
       
  
MultiButton b = new MultiButton("Se Connecter En Tant Que ..");
b.addActionListener(e -> {
    Dialog d = new Dialog();
    d.setLayout(BoxLayout.y());
    d.getContentPane().setScrollableY(true);
     for (int i = 0; i < List.size(); i++) {
        System.out.println(List.get(i).getUsername());
        MultiButton mb = new MultiButton(List.get(i).getUsername());
        mb.setTextLine2(List.get(i).getEmail());
        int height = Display.getInstance().convertToPixels(9f);
       int width = Display.getInstance().convertToPixels(10f);
        mb.setIcon(res.getImage("marker1.png").fill(width, height));
        d.add(mb);
        mb.addActionListener(ee -> {
            b.setTextLine1(mb.getTextLine1());
            b.setTextLine2(mb.getTextLine2());
            b.setIcon(mb.getIcon());
            d.dispose();
            b.revalidate();
        });
    }
    d.showPopupDialog(b);
});
this.add(b);
Button signIn = new Button("Se connecter");
Label anotheraccount = new Label("Se Connecter Avec  Un Autre compte !");
add(FlowLayout.encloseCenter(anotheraccount, signIn));
this.show();
    }

    
    
}

