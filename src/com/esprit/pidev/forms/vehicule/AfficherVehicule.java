/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.vehicule;

import com.codename1.components.InteractionDialog;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
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
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextComponent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Vehicule;
import com.esprit.pidev.services.ColisService;
import com.esprit.pidev.services.ServicesVehicule;
import com.mycompany.myapp.Forms.BaseForm;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class AfficherVehicule extends BaseForm
{

    public AfficherVehicule(Resources res) 
    {
       super("Liste Des Véhicules", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");

        this.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
        this.setBgImage(res.getImage("bag.png"));

        getContentPane().setScrollVisible(false);
        
        super.installSidemenu(res);

        tb.addCommandToRightBar("Return", null, (evt) -> {
         //page tsawer

         tb.addCommandToRightBar("Return", null, (evt) -> {
         new VehiculeForm(res).showBack();

        });
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();

        addTab(swipe, res.getImage("vec1.png"), spacer1, "15 Ride", "10 Colis", "Welcome Back To TaxiCo.");

        addTab(swipe, res.getImage("vehicule.png"), spacer1, "15 Ride", "10 Colis", "Welcome Back To TaxiCo.");

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
        RadioButton featured = RadioButton.createToggle("Reclamation", barGroup);
        featured.setUIID("SelectBar");

        Container listvec = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        //listvec.setScrollableY(true);
        ArrayList<Vehicule> List = new ServicesVehicule().getAllVehicules();
        for (int i = 0; i < List.size(); i++) {
            int id = List.get(i).getId();
            MultiButton mBtn = new MultiButton("vec n°" + i + ":");

            mBtn.setTextLine1("🔢 Matricule : "+List.get(i).getMatricule());
            mBtn.setTextLine2("📰 Carte grise :  "+List.get(i).getCartegrise());

            mBtn.setTextLine1("🔢 Matricule "+List.get(i).getMatricule());
            mBtn.setTextLine2("📰 Carte Grise "+List.get(i).getCartegrise());
            mBtn.setTextLine3("🚖 Modele "+List.get(i).getMarque()+" , "+List.get(i).getModele());

            // Button btn_edit = new Button();
            Button btn_delete = new Button();
            Button btnup = new Button();
            //FontImage.setMaterialIcon(btn_edit, FontImage.MATERIAL_EDIT);
            FontImage.setMaterialIcon(btn_delete, FontImage.MATERIAL_DELETE_OUTLINE);
            FontImage.setMaterialIcon(btnup, FontImage.MATERIAL_UPDATE);
            Container cntr = new Container(new FlowLayout());
            Container cntr1 = new Container(new FlowLayout());
            cntr.add(btn_delete);
            cntr1.add(btnup);

            SwipeableContainer sousou=  new SwipeableContainer(cntr1,cntr, mBtn);
        
            Label lp = new Label(List.get(i).getPosition());
            Label ld = new Label(List.get(i).getDestination());
            Label lmodele = new Label(List.get(i).getModele());
            Label lmarque = new Label(List.get(i).getMarque());
           // FontImage.setMaterialIcon(mBtn, FontImage.MATERIAL_ADD_LOCATION);
            listvec.addAll(sousou);
            mBtn.addActionListener(al -> {

                Dialog.show("Vehicule:", "Position : " + lp.getText() + " \n Destination: : " + ld.getText() + " \n Marque : " + lmarque.getText() + " \n Modele : " + lmodele.getText(), "Ok", null);

            });

            btn_delete.addActionListener(l->
            {
                InteractionDialog dialogverif = new InteractionDialog("SUPPRESSION!");
                Container c = new Container(new BorderLayout());
                dialogverif.setLayout(new BorderLayout());
                dialogverif.add(BorderLayout.CENTER, new Label("Voulez vous vraiment annuler rdv"));
                Button oui = new Button("Oui");
                Button non = new Button("Non");
                non.addActionListener((ee) -> dialogverif.dispose());
                c.addComponent(BorderLayout.EAST,non);
                c.addComponent(BorderLayout.WEST,oui);
                dialogverif.addComponent(BorderLayout.SOUTH,c);
                oui.addActionListener(tt->
                {
                dialogverif.dispose();
                
                if(new ServicesVehicule().deletevehicule(id))
                {
                    ToastBar.showInfoMessage("Votre Vehicule  est supprimée avec succé");


            btn_delete.addActionListener(l->{
         
 InteractionDialog dialogverif = new InteractionDialog("SUPPRESSION!");
  Container c = new Container(new BorderLayout());
            
dialogverif.setLayout(new BorderLayout());
dialogverif.add(BorderLayout.CENTER, new Label("Voulez vous vraiment annuler rdv"));
Button oui = new Button("Oui");
Button non = new Button("Non");
non.addActionListener((ee) -> dialogverif.dispose());
c.addComponent(BorderLayout.EAST,non);
c.addComponent(BorderLayout.WEST,oui);
dialogverif.addComponent(BorderLayout.SOUTH,c);

oui.addActionListener(tt->{
    
dialogverif.dispose();
                
                if(new ServicesVehicule().deletevehicule(id))
                {
                    ToastBar.showInfoMessage("Suppression avec succès");

                }else{
                    ToastBar.showErrorMessage("Erreur de suppression");
                }
               // mBtn.remove();
                sousou.remove();
                this.refreshTheme();

                }); 
                Dimension pre = dialogverif.getContentPane().getPreferredSize();
                int height = Display.getInstance().convertToPixels(9f);
                int width = Display.getInstance().convertToPixels(10f);
                int top = Display.getInstance().convertToPixels(95f);
                int bottom = Display.getInstance().convertToPixels(0f);
                dialogverif.show(top, bottom, height, width);
            });  

         }); 
Dimension pre = dialogverif.getContentPane().getPreferredSize();
int height = Display.getInstance().convertToPixels(9f);
int width = Display.getInstance().convertToPixels(10f);
int top = Display.getInstance().convertToPixels(95f);
int bottom = Display.getInstance().convertToPixels(0f);
dialogverif.show(top, bottom, height, width);
 });  

            btnup.addActionListener(update -> {
                InteractionDialog d = new InteractionDialog();
                TextComponent mat = new TextComponent().label("Matricule");
                Button valid = new Button("Modifier vehicule");
                d.getStyle().setBgColor(0xfffff);
                d.getStyle().setBgImage(res.getImage("accordionfinal.png"));
                d.setLayout(new FlowLayout());
                d.add(new Label("Modifier"));
                d.addComponent(mat);
                d.addComponent(valid);
                d.getStyle().setMarginBottom(500);
                d.getStyle().setMarginLeft(500);
                d.getStyle().setMarginRight(200);
                d.getStyle().setMarginTop(200);
                d.getStyle().setPaddingTop(500);
                d.show(TOP, BOTTOM, LEFT, RIGHT);
                valid.addActionListener(va -> {
                    if (new ServicesVehicule().updatevec(id,mat.getText())) {
                        ToastBar.showInfoMessage("Votre matricule a été modifiée avec succès");
                         new AfficherVehicule(res).showBack();

                    } else {
                        ToastBar.showErrorMessage("Erreur de serveur");
                    }

                });
            });

        }
//               getToolbar().addCommandToOverflowMenu("Vérifier", null, new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//               SearchbyId a = new SearchbyId(previous);
//                a.show();
//            }
//        });
        Button ajout = new Button ("Ajouter Vehicule");
        ajout.addActionListener(l->{
            new AjoutVehicule(res).show();
        });
        this.addAll(listvec,ajout);
        this.setScrollableY(true);
        this.show();
//               getToolbar().addCommandToOverflowMenu("Vérifier", null, new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//               SearchbyId a = new SearchbyId(previous);
//                a.show();
//            }
//        });

        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        // f2.show();}
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
