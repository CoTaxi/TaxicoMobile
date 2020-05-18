/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.colis;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.RIGHT;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.services.ColisService;
import com.mycompany.myapp.Forms.BaseForm;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ModifierColis extends BaseForm
{
        public ModifierColis(int Id,Resources res) 
        {
        super("Modifier Colis", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
         
        getTitleArea().setUIID("Container");
        setTitle("TaxiCo-Colis");
        getContentPane().setScrollVisible(false);
        
        super.installSidemenu(res);
        tb.addCommandToRightBar("Return", null, (evt) -> {
           new ShowDetailsColis(Id,res).show();
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
        RadioButton featured = RadioButton.createToggle("Our Services", barGroup);
        featured.setUIID("SelectBar");


              
        //-------------Declaration Start
        ComboBox<String> cat = new ComboBox<>();
        Button btn = new Button("Edit Colis");
        TextModeLayout tl = new TextModeLayout(3, 2);
        TextComponent tfDepart = new TextComponent().label("Depart");
        //.errorMessage("Input is essential in this field");
        TextComponent tfDestination = new TextComponent().label("Destination");
        TextComponent tfNomExpediteur = new TextComponent().label("Nom Expediteur");
        TextComponent tfMailExpediteur = new TextComponent().label("Mail Expediteur");
        TextComponent tfPoids = new TextComponent().label("Poids");
        TextComponent tfNomDestinataire = new TextComponent().label("Nom Destinataire");
        TextComponent tfMailDestinataire = new TextComponent().label("Mail Destinataire");
        TextComponent tfTelDestinataire = new TextComponent().label("Tel Destinataire");
        Validator val = new Validator();
        val.addConstraint(tfMailExpediteur, RegexConstraint.validEmail("Mail Expediteur"));
        val.addConstraint(tfMailDestinataire, RegexConstraint.validEmail("Mail Expediteur"));
        val.addConstraint(tfTelDestinataire, new NumericConstraint(true), new LengthConstraint(8,"Telephone"));
        val.addConstraint(tfPoids, new NumericConstraint(true));
        ArrayList<Colis> det = new ColisService().getColis(Integer.valueOf(Integer.toString(Id)));
        
        //------------------Declaration End
        
          for (int i=0;i<det.size();i++)
              {
              tfDestination.text(det.get(i).getDestination());
              tfDepart.text(det.get(i).getDepart());
              tfNomExpediteur.text(det.get(i).getNomExpediteur());
              tfMailExpediteur.text(det.get(i).getMailExpediteur());
              tfPoids.text(Float.toString(det.get(i).getPoids()));
              tfNomDestinataire.text(det.get(i).getNomDestinataire());
              tfMailDestinataire.text(det.get(i).getMailDestinataire());
              tfTelDestinataire.text(Integer.toString(det.get(i).getTelDestinataire()));
              
              }
         btn.addActionListener((ActionEvent evt) -> {
             if(val.isValid())
        {
            if ((tfDepart.getText().length() == 0) || (tfDestination.getText().length() == 0)|| (tfPoids.getText().length() == 0)|| (tfNomDestinataire.getText().length() == 0)|| (tfNomExpediteur.getText().length() == 0)|| (tfMailDestinataire.getText().length() == 0)|| (tfMailExpediteur.getText().length() == 0)|| (tfTelDestinataire.getText().length() == 0)) {
                Dialog.show("Erreur", "Veuillez Remplir Tout Les Champs", "OK", null);
            } else {
                try {
                    Colis col = new Colis(tfDepart.getText(),tfDestination.getText(),tfNomExpediteur.getText(),tfMailExpediteur.getText(),Float.parseFloat(tfPoids.getText()),tfNomDestinataire.getText(),tfMailDestinataire.getText(),Integer.parseInt(tfTelDestinataire.getText()));
                    if (new ColisService().modifycolis(Id,col)) 
                    {
                        Dialog.show("SUCCESS", "Colis Modifié", "OK", null);
                    } 
                    else 
                    {
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
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
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
    
   private void addButton(Image img, String title,Resources res) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title.toUpperCase());
       ta.setEditable(false);
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta
               ));
       add(cnt);
       image.addActionListener((e) ->{
           ToastBar.showMessage(title, FontImage.MATERIAL_INFO);
//            new ColisForm(res).show();
               });
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
      
}
