/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.colis;

import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.Constraint;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.pidev.forms.vehicule.AfficherVehicule;
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.models.User;
import com.esprit.pidev.services.ColisService;
import com.esprit.pidev.services.UserService;
import com.esprit.pidev.utils.Statics;
import com.mycompany.myapp.Forms.BaseForm;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class AjoutColis extends BaseForm
{

    public AjoutColis(Resources res) 
    {
        super("Ajouter Colis", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
//        tb.addCommandToLeftBar("Return", null, (evt) -> {
//         //  new ColisForm(res).show();
//        });  
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        
        super.installSidemenu(res);
           tb.addCommandToRightBar("Return", null, (evt) -> {
        new NosServices(res).showBack();
        });  
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("coli1.png"), spacer1, "15 Ride", "10 Colis", "Welcome Back To TaxiCo.");
        addTab(swipe, res.getImage("coli2.png"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton featured = RadioButton.createToggle("Colis", barGroup);
        featured.setUIID("SelectBar");
        Button btn = new Button("Envoyer Colis");
        TextModeLayout tl = new TextModeLayout(3, 2);
        AutoCompleteTextField tfDepart = new AutoCompleteTextField(
             "Ariana","Béja","Ben Arous","Bizerte","Gabès","Gafsa",
             "Jendouba","Kairouan","Kasserine","Kef","Mahdia","Manouba"
             ,"Médenine","Monastir","Nabeul","Sfax","Siliana","Sousse",
             "Tataouine","Tozeur","Tunis","Zaghouan"
        );
        tfDepart.setMinimumElementsShownInPopup(3);
        tfDepart.getStyle().setFgColor(0x000000);
        tfDepart.setHint("Depart");
        AutoCompleteTextField tfDestination = new AutoCompleteTextField(
             "Ariana","Béja","Ben Arous","Bizerte","Gabès","Gafsa",
             "Jendouba","Kairouan","Kasserine","Kef","Mahdia","Manouba"
             ,"Médenine","Monastir","Nabeul","Sfax","Siliana","Sousse",
             "Tataouine","Tozeur","Tunis","Zaghouan"
        );
        tfDestination.setMinimumElementsShownInPopup(3);
        tfDestination.getStyle().setFgColor(0x000000);
        tfDestination.setHint("Destination");
        //.errorMessage("Input is essential in this field");
        TextComponent tfNomExpediteur = new TextComponent().label("Nom Expediteur");
        TextComponent tfMailExpediteur = new TextComponent().label("Mail Expediteur");
        TextComponent tfPoids = new TextComponent().label("Poids(kg)");
        TextComponent tfNomDestinataire = new TextComponent().label("Nom Destinataire");
        TextComponent tfMailDestinataire = new TextComponent().label("Mail Destinataire");
        TextComponent tfTelDestinataire = new TextComponent().label("Tel Destinataire");
        ArrayList<User> us = new UserService().lastcnx(Statics.sessionID);
        tfNomDestinataire.text(us.get(0).getUsername());
        tfMailDestinataire.text(us.get(0).getEmail());
        Validator val = new Validator();
        val.addConstraint(tfMailExpediteur, RegexConstraint.validEmail("Mail Expediteur"));
        val.addConstraint(tfMailDestinataire, RegexConstraint.validEmail("Mail Expediteur"));
        val.addConstraint(tfTelDestinataire, new NumericConstraint(true), new LengthConstraint(8,"Tel"));
        val.addConstraint(tfPoids, new NumericConstraint(true));
       /* ArrayList<category> List = new CategoryService().AfficherCategorie();
        for (int i=0; i<List.size();i++){
        cat.addItem(List.get(i).getCategorie().toString());
        }*/
        
        
        btn.addActionListener((evt) -> {
        if(val.isValid())
        {
            if ((tfDepart.getText().length() == 0) || (tfDestination.getText().length() == 0)|| (tfPoids.getText().length() == 0)|| (tfNomDestinataire.getText().length() == 0)|| (tfNomExpediteur.getText().length() == 0)|| (tfMailDestinataire.getText().length() == 0)|| (tfMailExpediteur.getText().length() == 0)|| (tfTelDestinataire.getText().length() == 0)) 
            {
                Dialog.show("Erreur", "Veuillez Remplir Tout Les Champs", "OK", null);
            } 
            else {
                try {
                    Colis t = new Colis(tfDepart.getText(),tfDestination.getText(),tfNomExpediteur.getText(),tfMailExpediteur.getText(),Float.parseFloat(tfPoids.getText()),tfNomDestinataire.getText(),tfMailDestinataire.getText(),Integer.parseInt(tfTelDestinataire.getText()));
                    if (new ColisService().addcolis(t,Statics.sessionID)) {
                        Dialog.show("SUCCESS", "Colis Envoyé", "OK", null);
                        new AfficherColis(res).show();
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                  //  Dialog.show("ERROR", "Status must be a number", "OK", null);
                }

            }
        }
        else 
        {
          Dialog.show("Erreur", "Verifiez Vos Donnees !", "OK", null);
        } 
        });

        this.addAll(tfDepart, tfDestination,tfPoids,tfNomExpediteur,tfMailExpediteur,tfNomDestinataire,tfMailDestinataire,tfTelDestinataire, btn);

    }
    
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    

    
}
