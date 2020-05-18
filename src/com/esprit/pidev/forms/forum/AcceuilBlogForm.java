package com.esprit.pidev.forms.forum;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
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
import com.esprit.pidev.forms.vehicule.AjoutVehicule;
import com.mycompany.myapp.Forms.BaseForm;

/**
 *
 * @author aissa
 */
public class AcceuilBlogForm extends BaseForm {


    public AcceuilBlogForm(Resources res) {
        super("Blog", BoxLayout.y());

        super.installSidemenu(res);

        addButton(res.getImage("ajouterevent.jpg"), "Ajouter blog",res);
        addButton(res.getImage("consulterBlog.jpg"), "Afficher blog",res);
    }
    
   private void addButton(Image img, String title,Resources res) {
       int height = Display.getInstance().convertToPixels(65f);
       int width = Display.getInstance().convertToPixels(65f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Container");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
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
           if(ta.getText().equals("Ajouter blog"))
           {
               new AjoutBlog(res).show();
           }
           else if(ta.getText().equals("Afficher blog"))
           {
             new AfficherBlog(res).show();
              
           }
               });
   }    
}
