/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.reclamation;

import com.codename1.components.OnOffSwitch;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Reclamation;
import com.esprit.pidev.models.typeReclamation;
import com.esprit.pidev.services.ReclamationServices;
import com.esprit.pidev.services.ServicesVehicule;
import com.mycompany.myapp.Forms.BaseForm;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class AjoutRec extends BaseForm
{

    public AjoutRec(Resources res) 
    {
       super("Ajouter Réclamation", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
//        tb.addCommandToLeftBar("Return", null, (evt) -> {
//         //  new ColisForm(res).show();
//        });  
        getTitleArea().setUIID("Container");
//        setTitle("TaxiCo-Vehicule");
        getContentPane().setScrollVisible(false);
        
        super.installSidemenu(res);
        tb.addCommandToRightBar("Return", null, (evt) -> {
         //page tsawer
        });
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("bg.png"), spacer1, "15 Ride", "10 Colis", "Welcome Back To TaxiCo.");
        addTab(swipe, res.getImage("bg.png"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
                
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
        RadioButton featured = RadioButton.createToggle("Reclamation", barGroup);
        featured.setUIID("SelectBar");
        
        
        TextComponent Message = new TextComponent().label("Message") ;
        
        ComboBox Type = new ComboBox();
        ComboBox C_prenom= new ComboBox();
        ComboBox C_nom= new ComboBox();
        C_prenom.setEnabled(false);
        C_nom.setEnabled(false);
        Label lprenom = new Label("Prenom : ");
        Label lnom = new Label("Nom : ");
        Label lMsg = new Label("Message : ");
        lMsg.getStyle().setMarginTop(10);
        Label lType = new Label("Objet : ");
        
        Type.getStyle().setMarginTop(10);
        lnom.getStyle().setMarginTop(10);
        lprenom.getStyle().setMarginTop(10);
       
        FontImage.setMaterialIcon(lMsg, FontImage.MATERIAL_MESSAGE);
        FontImage.setMaterialIcon(lType, FontImage.MATERIAL_SUBJECT);
        FontImage.setMaterialIcon(lprenom, FontImage.MATERIAL_PERSON);
        FontImage.setMaterialIcon(lnom, FontImage.MATERIAL_PERSON);
        Button btn = new Button("Envoyer");
        btn.getStyle().setMarginTop(10);
        FontImage.setMaterialIcon(btn, FontImage.MATERIAL_SEND);
        ArrayList<typeReclamation> List = new ReclamationServices().getAllType();
        ArrayList<Reclamation> ListPrename = new ReclamationServices().getPrename();   

            for (int i = 0; i < List.size(); i++) {
            Type.addItem(List.get(i).getTitre());
            }
        
        // Test sur le type :
        Type.addActionListener(ct->{
            if(Type.getSelectedItem().toString().equals("Application TaxiCo")||Type.getSelectedItem().toString().equals("Autres")){
                C_prenom.setEnabled(false);
                C_nom.setEnabled(false);
              
        }else if(Type.getSelectedItem().toString().equals("Voiture/Chauffeur")){
            C_prenom.setEnabled(true);
            C_nom.setEnabled(true);
                for (int i = 0; i < ListPrename.size(); i++) {
             
                 C_prenom.addItem(ListPrename.get(i).getPrenameChauff());
                }
            C_prenom.addActionListener(pre->{
            String selected = C_prenom.getSelectedItem().toString();
             ArrayList<Reclamation> listName = new ReclamationServices().getName(selected);
            for (int i = 0; i < listName.size(); i++) {
                C_nom.addItem(listName.get(i).getPrenameChauff());
            }
        });
        }
        });
        
        btn.addActionListener((evt) -> {
            
                if ((Message.getText().length() == 0)) {
                Dialog.show("Alerte", "Veuillez entrer tous les champs", "OK", null);
                } else if(Message.getText().length()!=0) {
//                  ArrayList<typeReclamation> ListId = new ReclamationServices().getAllIdType(Type);
//                    Reclamation t = new Reclamation(Message.getText());
                    if(C_nom.isEnabled()){
                        String boxName = C_nom.getSelectedItem().toString();
                        String boxPrename = C_prenom.getSelectedItem().toString();
                        Reclamation t = new Reclamation(Message.getText());
                        
                        ArrayList<typeReclamation> ListId = new ReclamationServices().getAllIdType(Type);
                        if (new ReclamationServices().addrec(t,ListId.get(0).getId(),boxPrename,boxName)) {
                            ToastBar.showInfoMessage("Votre réclamation est ajoutée avec succée");
                        } else {
                           Dialog.show("Erreur", "Server error", "OK", null);
                        }
                    }else{
                        Reclamation t = new Reclamation(Message.getText());
                        ArrayList<typeReclamation> ListId = new ReclamationServices().getAllIdType(Type);
                        String boxName = "None";
                        String boxPrename = "None";
                         
                        if (new ReclamationServices().addrec(t,ListId.get(0).getId(),boxPrename,boxName)) {
                            ToastBar.showInfoMessage("Votre réclamation est ajoutée avec succée");
                        } else {
                           Dialog.show("Erreur", "Server error", "OK", null);
                        } 
                    }     
            }
        });

        this.addAll(lType,Type,lprenom,C_prenom,lnom,C_nom,lMsg,Message,btn);


        
        
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
