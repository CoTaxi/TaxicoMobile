/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.event;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.forms.colis.AffecterColis;
import com.esprit.pidev.forms.colis.AfficherColis;
import com.esprit.pidev.forms.colis.ModifierColis;
import com.esprit.pidev.forms.colis.ShowDetailsColis;
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.models.Event;
import com.esprit.pidev.models.User;
import com.esprit.pidev.services.ColisService;
import com.esprit.pidev.services.EventService;
import com.esprit.pidev.utils.Statics;
import com.mycompany.myapp.Forms.BaseForm;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ShowDetailsEvent extends BaseForm
{

    public ShowDetailsEvent(int id,Resources res) 
    {
        super("Event Details", BoxLayout.y());
        System.out.println("id----"+id);
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        
        this.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
        this.setBgImage(res.getImage("bag.png"));
        super.installSidemenu(res);
        tb.addCommandToRightBar("Return", null, (evt) -> {
          new AfficherEvent(res).showBack();
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
        RadioButton featured = RadioButton.createToggle("Our Services", barGroup);
        featured.setUIID("SelectBar");
//------------------------------------------------------------------------------------
        Label nom = new Label();
        Label date_debut = new Label();
        Label date_fin = new Label();
        Label duree = new Label();
        Label emplacement = new Label();
        Label lnom = new Label("Nom Event :");
        Label ldate_debut = new Label("Date Debut :");
        Label ldate_fin = new Label("Date Fin :");
        Label lduree = new Label("Duree :");
        Label lemplacement = new Label("Emplacement :");
        Container cnom = new Container();
        Container cdate_debut = new Container();
        Container cdate_fin = new Container();
        Container cduree = new Container();
        Container cemplacement = new Container();
   Container cn= new Container();
   Container cn1= new Container();
   Label orange = new Label();
   Label red = new Label();
   Button affect = new Button("Participer");
   Button delete = new Button("Quitter");
    ArrayList<Event> det = new EventService().getEvent(id);
              Event e =det.get(0);
              if(e.getEtat()==0)
              {
              nom.setText(e.getNom());   
              date_debut.setText("date_debut event");
              date_fin.setText("date_fin event");
              duree.setText(Integer.toString(e.getDuree()));
              emplacement.setText(e.getEmplacement());
              cnom.addAll(lnom,nom);
              cdate_debut.addAll(ldate_debut,date_debut);
              cdate_fin.addAll(ldate_fin,date_fin);
              cduree.addAll(lduree,duree);
              cemplacement.addAll(lemplacement,emplacement);
              }

              if(e.getCapacite()-e.getPlace()>3)
              {
                 int reste=e.getCapacite()-e.getPlace();
                 orange.setText("Place Restante :"+reste);
              }
              if(e.getCapacite()-e.getPlace()<=3)
              {
                  int rest=e.getCapacite()-e.getPlace();
                 red.setText("Il Ne Reste Que "+rest+" Places Disponible");
                 orange.setText("");
              }
              if(e.getCapacite()<=e.getPlace())
              {
                  red.setText("");
                  affect.setEnabled(false);
              }
               ArrayList<User> list = new EventService().listuser(Statics.sessionID);
                      User get = list.get(0);
                      if(get.getNomEvent().equals(e.getNom()))
                      {
                       
                        this.add(delete);
                        this.refreshTheme();
                          System.out.println("mawjoud fil event hetha");
                      }
                      else if((!get.getNomEvent().equals(e.getNom()))&&(!get.getNomEvent().equals("none")))
                      {
                          affect.setEnabled(false);
                          System.out.println("mawjoud fi event ekher");
                      }
                      else 
                      {
                          this.add(affect);
                        this.refreshTheme();
                          System.out.println("makch mawjouda fi hatta event");
                      }
                  
                           
              affect.addActionListener(l->
              {
                  if(new EventService().ParticiperEvent(id,Statics.sessionID))
                         {
                            Dialog.show("Felicitation", "Vous etes Maintenant inscrit a cet evennement", "OK", null);
                         }
                        else 
                         {
                            Dialog.show("ERROR", "Server error", "OK", null);
                         }  
             });
             delete.addActionListener(l->{
               if(new EventService().QuitterEvent(id,Statics.sessionID))
                         {
                            Dialog.show("Information", "Vous n'etes plus inscrit a cet evennement", "OK", null);
                         }
                        else 
                         {
                            Dialog.show("ERROR", "Server error", "OK", null);
                         }     
             });
              this.addAll(cnom,cdate_debut,cdate_fin,cduree,cemplacement,orange,red); 
              
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
    

