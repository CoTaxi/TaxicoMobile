/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.vehicule;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Forms.BaseForm;

/**
 *
 * @author ASUS
 */
public class FindTaxi extends BaseForm
{

    public FindTaxi(Resources res) 
    {
       super("Taxi", BoxLayout.y());
       setUIID("Maps");
       super.installSidemenu(res);
       TextField Depart = new TextField();
       TextField Destination = new TextField();
      // cnt.setUIID("TextField");
      // textField.getAllStyles().setBorder(Border.createEmpty());
       Image img1=res.getImage("marker2.png");
       Image img=res.getImage("marker1.png");
       Button search = new Button("Trouver Un Taxi");
       int height = Display.getInstance().convertToPixels(9f);
       int width = Display.getInstance().convertToPixels(10f);
       Container cnt = BorderLayout.centerEastWest(Depart, new Label(img.fill(width, height)), null);
       Container cnt1 = BorderLayout.centerEastWest(Destination, new Label(img.fill(width, height)), null);
       Container cnt2 = new Container(new BorderLayout(this.BOTTOM));
       Container cnt3 = new Container(new BorderLayout());
       cnt2.add(BorderLayout.NORTH,cnt);
       cnt2.add(BorderLayout.SOUTH,cnt1);
       cnt3.add(BorderLayout.NORTH,search);
       this.addAll(cnt2,cnt3);
       
    }
 


    }
