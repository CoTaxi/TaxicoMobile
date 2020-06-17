/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.event;

import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextComponent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
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
import com.esprit.pidev.forms.colis.AfficherColis;
import com.esprit.pidev.forms.colis.ShowDetailsColis;
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.models.Event;
import com.esprit.pidev.services.ColisService;
import com.esprit.pidev.services.EventService;
import com.esprit.pidev.utils.Statics;
import com.mycompany.myapp.Forms.BaseForm;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class AfficherEvent extends BaseForm
{

    public AfficherEvent(Resources res) 
    {
        super("Afficher Evennement", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        Button trier=new Button("tri par capacite");
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        super.installSidemenu(res);
        tb.addCommandToRightBar("Return", null, (evt) -> {
         new eventForm(res).showBack();
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
        RadioButton featured = RadioButton.createToggle("Colis", barGroup);
        featured.setUIID("SelectBar");

      this.add(trier);
      //-------------------------------------------------------------------------------
      
        Button passe = new Button("Consulter Les evennements Passes");
        Container content1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        content1.setScrollableY(true);    
        ArrayList<Event> List = new EventService().getAllEvents();
        System.out.println(List.size());
    for (int i = 0; i < List.size(); i++) 
    {    if(List.get(i).getEtat()==0)
    {
        System.out.println("---nom---"+List.get(i).getNom());
        final MultiButton mb = new MultiButton();
        mb.setTextLine1("🔠 Nom : "+List.get(i).getNom());
        mb.setTextLine2("⏳ Duree : "+String.valueOf(List.get(i).getDuree()));
        mb.setTextLine3("🗺 Emplacement : "+List.get(i).getEmplacement());
        mb.setTextLine4(Integer.toString(List.get(i).getId()));
        mb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            new ShowDetailsEvent(Integer.valueOf(mb.getTextLine4()),res).show();
            }
        });
       content1.addAll(mb);
    }
    }
    passe.addActionListener(l->{
        new ArchiveEvent(res).show();
        
    });
       this.addAll(content1,passe);
       Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));
      trier.addActionListener(l->{
          ArrayList<Event> Listtrie = new EventService().TriEvent();
        this.removeComponent(content1);
                for (int i = 0; i < Listtrie.size(); i++) 
        {
            
        final MultiButton mb = new MultiButton();
        Button btn_ed = new Button();
        Button btn_del = new Button();
        FontImage.setMaterialIcon(btn_ed, FontImage.MATERIAL_EDIT);
        FontImage.setMaterialIcon(btn_del, FontImage.MATERIAL_DELETE_OUTLINE);
        Container cntr = new Container(new FlowLayout());
        Container cntr1 = new Container(new FlowLayout());
        cntr.add(btn_ed);
        cntr1.add(btn_del);
        mb.setTextLine1("🔠 Nom : "+Listtrie.get(i).getNom());
        mb.setTextLine2("⏳ Duree : "+String.valueOf(Listtrie.get(i).getDuree()));
        mb.setTextLine3("🗺 Emplacement : "+Listtrie.get(i).getEmplacement());
        mb.setTextLine4(Integer.toString(Listtrie.get(i).getId()));
        mb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            new ShowDetailsEvent(Integer.valueOf(mb.getTextLine4()),res).show();
            }
        });
        content.addAll(mb);
         }
                trier.setEnabled(false);
                this.add(content);
                this.refreshTheme();
      });
      
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
    

