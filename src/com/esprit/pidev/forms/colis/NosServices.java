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

package com.esprit.pidev.forms.colis;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.forms.vehicule.FindTaxi;
import com.esprit.pidev.forms.colis.AjoutColis;
import com.esprit.pidev.forms.colis.Map;
//import com.esprit.pidev.forms.colis.Map;
import com.esprit.pidev.forms.colis.combo;
import com.esprit.pidev.forms.reclamation.AjoutRec;
import com.esprit.pidev.forms.vehicule.AjoutVehicule;
import com.mycompany.myapp.Forms.BaseForm;
/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class NosServices extends BaseForm {

    public NosServices(Resources res) {
        super("Nos Services", BoxLayout.y());
       
        
        super.installSidemenu(res);
        addButton(res.getImage("ajoutcolis.png"), "Envoyer Colis.",res);
        addButton(res.getImage("consultercolis.png"), "Consulter colis.",res);
         }
   private void addButton(Image img, String title,Resources res) {
       int height = Display.getInstance().convertToPixels(65f);
       int width = Display.getInstance().convertToPixels(65f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Container");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title.toUpperCase());
       ta.setEditable(false);
       ta.setVisible(false);
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta
               ));
       add(cnt);
       image.addActionListener((e) ->{
           ToastBar.showMessage(title, FontImage.MATERIAL_INFO);
           //System.out.println(ta.getText());
           if(ta.getText().equals("ENVOYER COLIS."))
           {
              new AfficherColis(res).show();
           }
           else if(ta.getText().equals("CONSULTER COLIS."))
           {
             System.out.println("CONSULTER COLIS.");
             new AjoutColis(res).show();
           }
           
        //    new ColisForm(res).show();
               });
   }
    
}
