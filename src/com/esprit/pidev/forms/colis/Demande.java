package com.esprit.pidev.forms.colis;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.services.ColisService;
import java.util.ArrayList;
import com.mycompany.myapp.Forms.BaseForm;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ASUS
 */
public class Demande extends BaseForm
{

    public Demande(int Id,String matricule,Resources res)
    {
        super("Archive", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.addCommandToLeftBar("Return", null, (evt) -> {
           new ListeDemandes(matricule,res).show();
        });  
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        
        super.installSidemenu(res);
        tb.addSearchCommand(e -> {});
        
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

          Label dep = new Label();
          dep.setUIID("Bold");
        Label dest = new Label();
        dest.setUIID("Bold");
        Label poids = new Label();
        poids.setUIID("Bold");
        Label nomexp = new Label();
        nomexp.setUIID("Bold");
        Label nomdest = new Label();
        nomdest.setUIID("Bold");
        Label mailexp = new Label();
        mailexp.setUIID("Bold");
        Label maildest = new Label();
        maildest.setUIID("Bold");
        Label ldep = new Label("Depart :");
        
        ldep.setUIID("Bold");
        ldep.getStyle().setFgColor(0xf99f1b);
        Label ldest = new Label("🗺 Destination :");
        ldest.setUIID("Bold");
        ldest.getStyle().setFgColor(0xf99f1b);
        Label lpoids = new Label("🔢 poids :");
        lpoids.setUIID("Bold");
        lpoids.getStyle().setFgColor(0xf99f1b);
        Label lnomexp = new Label("🔠 Nom Expediteur :");
        lnomexp.setUIID("Bold");
        lnomexp.getStyle().setFgColor(0xf99f1b);
        Label lnomdest = new Label("🔤 Nom Destinataire :");
        lnomdest.setUIID("Bold");
        lnomdest.getStyle().setFgColor(0xf99f1b);
        Label lmailexp = new Label("📧 Mail Expediteur :");
        lmailexp.setUIID("Bold");
        lmailexp.getStyle().setFgColor(0xf99f1b);
        Label lmaildest = new Label("📨 Mail Destinataire :");
        lmaildest.setUIID("Bold");
        lmaildest.getStyle().setFgColor(0xf99f1b);
        Label abcolis = new Label("           ----A Propos Du Colis----");
        abcolis.setUIID("Bold");
        abcolis.getStyle().setFgColor(0x36324D);
        Label abuser = new Label("            ----A Propos Du Client----");
        abuser.setUIID("Bold");
        abuser.getStyle().setFgColor(0x36324D);
        Container cdep = new Container();
        Container cdest = new Container();
        Container cpoids = new Container();
        Container cnomexp = new Container();
        Container cnomdest = new Container();
        Container cmailexp = new Container();
        Container cmaildest = new Container();
        Container colisdet = new Container(BoxLayout.y());
        Container userdet = new Container(BoxLayout.y());
        Button accept = new Button("Accepter");
        Button refus = new Button ("Refuser");
        Label etat2 = new Label ("Vous Avez Accepté Ce Colis");
        etat2.setUIID("Bold");
        etat2.getStyle().setFgColor(0x009300);
        Label etat3 = new Label("Ce Colis Est Deja Livré");
        etat3.setUIID("Bold");
        etat3.getStyle().setFgColor(0x009300);
        Button maj = new Button ("Marqué Comme Livré");
        FontImage.setMaterialIcon(maj, FontImage.MATERIAL_CHECK);
        int etat=-1;
        //---------------------Declaration End
        
            ArrayList<Colis> det = new ColisService().getColis(Integer.valueOf(Integer.toString(Id)));
              for (int i=0;i<det.size();i++)
              {
              etat=det.get(i).getEtat();
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
              cnomexp.addAll(lnomexp,nomexp);
              cnomdest.addAll(lnomdest,nomdest);
              cmailexp.addAll(lmailexp,mailexp);
              cmaildest.addAll(lmaildest,maildest);
              colisdet.addAll(cdep,cdest,cpoids);
              userdet.addAll(cnomexp,cmailexp,cnomdest,cmaildest);
              this.addAll(abcolis,colisdet,abuser,userdet);
              if((etat!=2)&&(etat!=3))
              this.addAll(accept,refus);
              else if(etat==2)
              {
              this.addAll(etat2,maj);
              }
              else if(etat==3)
              this.add(etat3);
              maj.addActionListener((etatf)->{
                  
                  if (new ColisService().majColis(Id)) {
                String accountSID ="AC2c8c457ccb41bbdc4376ffad45b331c3";
                String authToken="ee7a35e489e95f817eae2a7d8b1a6e22";
                Response<java.util.Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
                queryParam("To", "+21620362075").
                queryParam("From","+19144915971").
                queryParam("Body", "Felicitation (Mr/M) "+nomexp.getText()+" Votre Colis a éte Livré \n Rendez Vous Sur Notre Site WWW.Taxico.com <3 \n Bonne Journée.").
        header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
        getAsJsonMap();
                Dialog.show("Livré", "Felicitation Vous Avez Livré Un Nouveau Colis (Archive)", "OK", null);
                    }
                  else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
               
              });
              accept.addActionListener((l)->{
                  if (new ColisService().accepterColis(Id)) {
                        Dialog.show("Accepté", "Vous Avez Accepté La Livraison Du Colis", "OK", null);
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
              });
              refus.addActionListener((l)->{
                  
                  if (new ColisService().refusererColis(Id)) {
                        Dialog.show("Reffusé", "Ce Colis Ne Fait Plus Partie De Votre Liste", "OK", null);
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
              });

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
