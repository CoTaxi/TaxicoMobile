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

package com.mycompany.myapp.Forms;


import com.esprit.pidev.forms.colis.NosServices;
import com.esprit.pidev.forms.user.ProfileForm;
import com.esprit.pidev.forms.colis.conv;
import com.esprit.pidev.forms.user.SignInForm;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.forms.maintenance.MaintenanceForm;
import com.esprit.pidev.forms.colis.AfficherColis;
import com.esprit.pidev.forms.colis.ListeVoituresForm;
import com.esprit.pidev.forms.event.AfficherEvent;
import com.esprit.pidev.forms.event.eventForm;
import com.esprit.pidev.forms.forum.AcceuilBlogForm;
import com.esprit.pidev.forms.forum.AfficherBlog;
import com.esprit.pidev.forms.maintenance.ChForm;
import com.esprit.pidev.forms.reclamation.ReclamationForm;
import com.esprit.pidev.forms.user.Lastcnx;
import com.esprit.pidev.forms.vehicule.AfficherVehicule;
import com.esprit.pidev.forms.vehicule.VehiculeForm;
import com.esprit.pidev.utils.Statics;

/**
 * Utility methods common to forms e.g. for binding the side menu
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {
        
    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
        shouldPaintStatusBar();
    }
        public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    public void installSidemenu(Resources res1) {
        Toolbar tb = getToolbar();
        tb.getStyle().setBgColor(0x36324D);
        Image img = res1.getImage("mahdi.png");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res1.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));
        if(Statics.type.equals("client"))
        {    
        tb.addMaterialCommandToSideMenu("Acceuil", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> new NewsfeedForm(res1).show());    
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> new ProfileForm(res1).show());    
        tb.addMaterialCommandToSideMenu("Mes Colis", FontImage.MATERIAL_MESSAGE, e -> new NosServices(res1).show());
        tb.addMaterialCommandToSideMenu("Réclamation", FontImage.MATERIAL_PUBLISH, e -> new ReclamationForm(res1).show());


       // tb.addMaterialCommandToSideMenu("Inbox", FontImage.MATERIAL_UPDATE, e -> new InboxForm(res1).show());
        //tb.addMaterialCommandToSideMenu("Trending", FontImage.MATERIAL_SETTINGS, e -> new TrendingForm(res1).show());
        tb.addMaterialCommandToSideMenu("Statistique", FontImage.MATERIAL_SETTINGS, e -> new ChartDemosForm().show());
        tb.addMaterialCommandToSideMenu("Chat Box", FontImage.MATERIAL_SETTINGS, e -> new conv());

        tb.addMaterialCommandToSideMenu("Réclamation", FontImage.MATERIAL_UPDATE, e -> new ReclamationForm(res1).show());
        tb.addMaterialCommandToSideMenu("Help", FontImage.MATERIAL_SETTINGS, e -> new conv());


        tb.addMaterialCommandToSideMenu("Evennement", FontImage.MATERIAL_PUBLISH, e -> new AfficherEvent(res1).show());
        tb.addMaterialCommandToSideMenu("Blog", FontImage.MATERIAL_PUBLISH, e -> new AfficherBlog(res1).show());
        tb.addMaterialCommandToSideMenu("Help", FontImage.MATERIAL_SETTINGS, e -> new conv());


        tb.addMaterialCommandToSideMenu("Evennement", FontImage.MATERIAL_PUBLISH, e -> new eventForm(res1).show());
        tb.addMaterialCommandToSideMenu("Blog", FontImage.MATERIAL_PUBLISH, e -> new AcceuilBlogForm(res1).show());
        tb.addMaterialCommandToSideMenu("Help", FontImage.MATERIAL_SETTINGS, e -> new conv());

        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res1).show());
        }
        if(Statics.type.equals("chauffeur"))
        {
       tb.addMaterialCommandToSideMenu("Acceuil", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> new ChForm(res1).show());    
       tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> new ProfileForm(res1).show());    
       tb.addMaterialCommandToSideMenu("Gestion Colis", FontImage.MATERIAL_UPDATE, e -> new ListeVoituresForm(res1).show());
       tb.addMaterialCommandToSideMenu("Mes Vehicule", FontImage.MATERIAL_UPDATE, e -> new VehiculeForm(res1).show());
       tb.addMaterialCommandToSideMenu("Raclamation", FontImage.MATERIAL_UPDATE, e -> new ReclamationForm(res1).show());
       tb.addMaterialCommandToSideMenu("Service Maintenance", FontImage.MATERIAL_UPDATE, e -> new MaintenanceForm(res1).show());


       tb.addMaterialCommandToSideMenu("Evennement", FontImage.MATERIAL_PUBLISH, e -> new AfficherEvent(res1).show());
       tb.addMaterialCommandToSideMenu("Blog", FontImage.MATERIAL_PUBLISH, e -> new AfficherBlog(res1).show());


       tb.addMaterialCommandToSideMenu("Evennement", FontImage.MATERIAL_PUBLISH, e -> new eventForm(res1).show());
       tb.addMaterialCommandToSideMenu("Blog", FontImage.MATERIAL_PUBLISH, e -> new AcceuilBlogForm(res1).show());
       tb.addMaterialCommandToSideMenu("Help", FontImage.MATERIAL_SETTINGS, e -> new conv());
       tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e ->new WalkthruForm(res1).show());
//       {
//           
//           Statics.Lastcnx=Statics.sessionID;
//           Statics.sessionID=0;
//           new Lastcnx(res1).show();
//       });
         }
        
    }

        
    protected boolean isCurrentInbox() {
        return false;
    }
    
    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }

    
    

}
