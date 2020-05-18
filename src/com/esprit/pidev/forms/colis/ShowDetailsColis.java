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
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
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
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.services.ColisService;
import com.mycompany.myapp.Forms.BaseForm;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ShowDetailsColis extends BaseForm
{
    public ShowDetailsColis(int Id,Resources res) {
        super("Détails des colis", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        
//        tb.addCommandToLeftBar("Return", null, (evt) -> {
//           new AfficherColis(res).show();
//        });  
        getTitleArea().setUIID("Container");
        //setTitle("TaxiCo-Colis");
        getContentPane().setScrollVisible(false);
        
        super.installSidemenu(res);
        tb.addSearchCommand(e -> {});
        
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
          Label dep = new Label();
        Label dest = new Label();
        Label poids = new Label();
        Label nomexp = new Label();
        Label nomdest = new Label();
        Label mailexp = new Label();
        Label maildest = new Label();
        Label etat = new Label();
        Label letat = new Label("Etat :");
        letat.setUIID("Bold");
        letat.getStyle().setFgColor(0xf99f1b);
        Label ldep = new Label("Depart :");
        ldep.setUIID("Bold");
        ldep.getStyle().setFgColor(0xf99f1b);
        Label ldest = new Label("Destination :");
        ldest.setUIID("Bold");
        ldest.getStyle().setFgColor(0xf99f1b);
        Label lpoids = new Label("poids :");
        lpoids.setUIID("Bold");
        lpoids.getStyle().setFgColor(0xf99f1b);
        Label lnomexp = new Label("Nom Expediteur :");
        lnomexp.setUIID("Bold");
        lnomexp.getStyle().setFgColor(0xf99f1b);
        Label lnomdest = new Label("Nom Destinataire :");
        lnomdest.setUIID("Bold");
        lnomdest.getStyle().setFgColor(0xf99f1b);
        Label lmailexp = new Label("Mail Expediteur :");
        lmailexp.setUIID("Bold");
        lmailexp.getStyle().setFgColor(0xf99f1b);
        Label lmaildest = new Label("Mail Destinataire :");
        lmaildest.setUIID("Bold");
        lmaildest.getStyle().setFgColor(0xf99f1b);
        Label abcolis = new Label("----A Propos Du Colis----");
        abcolis.setUIID("Bold");
        abcolis.getStyle().setFgColor(0x36324D);
        Label abuser = new Label("----A Propos Du Client----");
        abuser.setUIID("Bold");
        abuser.getStyle().setFgColor(0x36324D);
        Container cdep = new Container();
        Container cdest = new Container();
        Container cpoids = new Container();
        Container cnomexp = new Container();
        Container cnomdest = new Container();
        Container cmailexp = new Container();
        Container cmaildest = new Container();
        Container cetat = new Container();
        Container colisdet = new Container(BoxLayout.y());
        Container userdet = new Container(BoxLayout.y());
   Container cn= new Container();
   Container cn1= new Container();
   Button modify = new Button("Modifier Colis");
   Button delete = new Button("Supprimer Colis");
   Button affect = new Button("Affecter Colis");
   Button ok = new Button("Terminer Ma Commande");
    ArrayList<Colis> det = new ColisService().getColis(Integer.valueOf(Integer.toString(Id)));
              for (int i=0;i<det.size();i++)
              {
              if(det.get(i).getEtat()==0)
              etat.setText("Colis Non Affecté");
              else if(det.get(i).getEtat()==1)
              etat.setText("En Attente De comfirmation Du Chauffeur");
              else if(det.get(i).getEtat()==2)
              etat.setText("Colis Affecté");
              else if(det.get(i).getEtat()==3)
              etat.setText("Colis Livré");
              dep.setText(det.get(i).getDepart());   
              dest.setText(det.get(i).getDestination());
              poids.setText(Float.toString(det.get(i).getPoids())+" Kg");
              nomexp.setText(det.get(i).getNomExpediteur());
              nomdest.setText(det.get(i).getNomDestinataire());
              mailexp.setText(det.get(i).getMailExpediteur());
              maildest.setText(det.get(i).getMailDestinataire());
              }
              cdep.addAll(ldep,dep);
              cdest.addAll(ldest,dest);
              cpoids.addAll(lpoids,poids);
              cetat.addAll(letat,etat);
              cnomexp.addAll(lnomexp,nomexp);
              cnomdest.addAll(lnomdest,nomdest);
              cmailexp.addAll(lmailexp,mailexp);
              cmaildest.addAll(lmaildest,maildest);
              colisdet.addAll(cdep,cdest,cpoids,cetat);
              userdet.addAll(cnomexp,cmailexp,cnomdest,cmaildest);
              this.addAll(abcolis,colisdet,abuser,userdet);
                  
              modify.addActionListener((ev) -> {
                  for (int i=0;i<det.size();i++)
              {
              int et=det.get(i).getEtat();
                  if((et==0)||(et==1))
                  {
                 new ModifierColis(Id,res).show();
                   }
                  else 
                  {
                      Dialog.show("ERREUR", "Vous Ne Pouvez Pas Modifier Un Colis Deja Affecté", "OK", null);
                  }
              }
              });
              delete.addActionListener((ev) -> {
                  for (int i=0;i<det.size();i++)
              {
              int et=det.get(i).getEtat();
                  if((et==0)||(et==1))
                  {
                  if(new ColisService().deletecolis(Id))
                  new   AfficherColis(res).show();  
                  }
                  else 
                      Dialog.show("ERREUR", "Vous Ne Pouvez Pas Supprimer Un Colis Deja Affecté", "OK", null);
                  
              }
              });
                 affect.addActionListener((ev) -> {
                  for (int i=0;i<det.size();i++)
              {
              int et=det.get(i).getEtat();
                  if((et==0))
                  {
                  new   AffecterColis(Id,res).show();
                  }
                  else 
                  {
                      Dialog.show("ERREUR", "Ce Colis Est Deja Affecté", "OK", null);
                  }
              }
              });
              ok.addActionListener((ev) -> {
                for (int i=0;i<det.size();i++)
              {
              int et=det.get(i).getEtat();
                  if((et==2))
                  {
                  new  pickuplocation(res, Id);
                  }
                  else 
                  {
                    ok.setEnabled(false);
                  }
              }
              });
               
                 this.addAll(modify,delete);
                   for (int i=0;i<det.size();i++)
              {
                if(det.get(i).getEtat()==0)
                {
                 this.add(affect);
                }
                if(det.get(i).getEtat()==2)
                {
                 this.add(ok);
                }
              } 
   
    this.setScrollableY(true);
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