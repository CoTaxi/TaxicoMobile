/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.vehicule;

import com.codename1.components.OnOffSwitch;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Vehicule;
import com.esprit.pidev.services.ServicesVehicule;
import com.mycompany.myapp.Forms.BaseForm;

/**
 *
 * @author ASUS
 */
public class AjoutVehicule extends BaseForm
{

    public AjoutVehicule(Resources res) 
    {
        super("Ajouter Vehicule", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.addCommandToLeftBar("Return", null, (evt) -> {
         //  new ColisForm(res).show();
        });  
        getTitleArea().setUIID("Container");
        //setTitle("TaxiCo-Vehicule");
        getContentPane().setScrollVisible(false);
        
        super.installSidemenu(res);
         tb.addCommandToRightBar("Return", null, (evt) -> {
         new VehiculeForm(res).showBack();
        });
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("vec1.png"), spacer1, "15 Ride", "10 Colis", "Welcome Back To TaxiCo.");
        addTab(swipe, res.getImage("vec2.png"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
                
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
        RadioButton featured = RadioButton.createToggle("Vehicule", barGroup);
        featured.setUIID("SelectBar");
        TextComponent matricule = new TextComponent().label("matricule");
        ComboBox cbmarquee = new ComboBox();
        cbmarquee.addItem("Alfa Romeo");
        cbmarquee.addItem("Citroen");
        cbmarquee.addItem("Peugeot");
        ComboBox cbmodele = new ComboBox();
        ComboBox couleur = new ComboBox();
        cbmodele.getStyle().setMarginTop(10);
        couleur.getStyle().setMarginTop(10);
        couleur.addItem("Blanc");
        couleur.addItem("Bleu");
        couleur.addItem("Jaune");
        couleur.addItem("Rouge");
        couleur.addItem("Vert");
        couleur.addItem("Noir");
        TextComponent cartegrise = new TextComponent().label("cartegrise");
        Label lplaces = new Label("Places Disponibles :");

        Slider splace = new Slider();
        splace.setHeight(10);
        splace.setIncrements(1);
        splace.setMaxValue(5);
        splace.setMinValue(1);
        splace.setProgress(1);
        splace.setEnabled(true);
        splace.setEditable(true);
        
        TextComponent position = new TextComponent().label("position");
        TextComponent destination = new TextComponent().label("destination");
        Label question = new Label("Acceptez-vous le tranfert des colis?");
        OnOffSwitch coli = new OnOffSwitch();
        coli.setOff("Non");
        coli.setOn("Oui");
        Label lcoli = new Label("");

        Button btnValider = new Button("Ajouter vehicule");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((matricule.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Vehicule t = new Vehicule(matricule.getText(), cbmarquee.getSelectedItem().toString(), cbmodele.getSelectedItem().toString(), cartegrise.getText(), couleur.getSelectedItem().toString(), splace.getProgress(), position.getText(), lcoli.getText(), destination.getText());
                        if (ServicesVehicule.getInstance().addvehicule(t)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                            new AfficherVehicule(res).show();
                            new ServicesVehicule().Notification();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        cbmarquee.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if (cbmarquee.getSelectedItem().toString().equalsIgnoreCase("Alfa Romeo")) {

                    cbmodele.addItem("4C");
                    cbmodele.addItem("Giula");
                    cbmodele.addItem("Giulietta");
                } else if (cbmarquee.getSelectedItem().toString().equalsIgnoreCase("Citroen")) {
                    cbmodele.addItem("C3");
                    cbmodele.addItem("C4");
                    cbmodele.addItem("Cactus");
                } else if (cbmarquee.getSelectedItem().toString().equalsIgnoreCase("Peugeot")) {

                    cbmodele.addItem("206+");
                    cbmodele.addItem("308");
                    cbmodele.addItem("408");

                }
            }
        });
        splace.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                lplaces.setText("Places Disponibles  " + splace.getProgress());
            }

        });
        coli.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if (coli.isValue()) {
                    lcoli.setText( coli.getOn());
                } else {
                    lcoli.setText(coli.getOff());
                }

            }

        });
        

        
        
        

        addAll(matricule,cartegrise, cbmarquee, cbmodele, couleur,  lplaces, splace, position, destination,question, coli, lcoli, btnValider);
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
