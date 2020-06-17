package com.esprit.pidev.forms.colis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.codename1.components.InfiniteProgress;
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
import static com.codename1.ui.Component.LEFT;
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
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.models.Vehicule;
import com.esprit.pidev.services.ColisService;
import com.esprit.pidev.services.ServicesVehicule;
import com.mycompany.myapp.Forms.BaseForm;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Archive extends BaseForm
{

    public Archive(String matricule,Resources res) 
    {
            super("Archive Des Livraisons", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        
        super.installSidemenu(res);
        tb.addCommandToRightBar("Return", null, (evt) -> {
           new ListeDemandes(matricule,res).showBack();
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
        
   ArrayList<Colis> List = new ColisService().ListeDemandes(matricule);
   Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));
   //content.setScrollableY(true);
   Container content1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
   //content1.setScrollableY(true);
        for (int i = 0; i < List.size(); i++) 
        {
          
               if(List.get(i).getEtat()==3)
            {    
        //FontImage.setMaterialIcon(btn, FontImage.MATERIAL_CHECK);
        final MultiButton mb = new MultiButton();
        Button btn_edit = new Button();
        Button btn_delete = new Button();
        FontImage.setMaterialIcon(btn_edit, FontImage.MATERIAL_EDIT);
        FontImage.setMaterialIcon(btn_delete, FontImage.MATERIAL_DELETE_OUTLINE);
        Container cntr = new Container(new FlowLayout());
        Container cntr1 = new Container(new FlowLayout());
        cntr.add(btn_edit);
        cntr1.add(btn_delete);
        mb.setTextLine1("🗺 Traget: "+List.get(i).getDepart()+"➡"+List.get(i).getDestination());
        mb.setTextLine2("🔠 Poids: "+String.valueOf(List.get(i).getPoids()));
        mb.setTextLine3("👨 Client: "+String.valueOf(List.get(i).getNomExpediteur()));
        mb.setTextLine4(Integer.toString(List.get(i).getIdC()));
        int id = List.get(i).getIdC();
        mb.addActionListener(col->{
            new Demande(id,matricule,res).show();
        });
        SwipeableContainer sousou=  new SwipeableContainer(cntr1, cntr, mb);
        content.addComponent(sousou);
        btn_edit.addActionListener(l->{
              new ModifierColis(id,res).show();
            });

            btn_delete.addActionListener(l->{
         
 InteractionDialog dialogverif = new InteractionDialog("SUPPRESSION!");
 dialogverif.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
  Container c = new Container(new BorderLayout());
dialogverif.add(BorderLayout.CENTER, new Label("Voulez vous vraiment annuler rdv"));
Button oui = new Button("Oui");
Button non = new Button("Non");
non.addActionListener((ee) -> dialogverif.dispose());
c.addComponent(BorderLayout.EAST,non);
c.addComponent(BorderLayout.WEST,oui);
dialogverif.addComponent(BorderLayout.SOUTH,c);

oui.addActionListener(tt->{
    
dialogverif.dispose();
                
                if( new ColisService().deletecolis(id))
                {
                    ToastBar.showInfoMessage("Votre Colis  est supprimée avec succé");
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
//dialogverif.show(top, bottom, height, width);dialogverif.show(0, 0, Display.getInstance().getDisplayWidth() - (pre.getWidth() + pre.getWidth() / 6), 0);
 });        
InfiniteProgress.setDefaultMaterialDesignMode(true);       

        
//        mb.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//             
//            }
//        }); 
      
        }

        }
        content.revalidate();
        Button tri = new Button ("Trier");
        this.addAll(tri,content);
        this.setScrollableY(true);
        tri.addActionListener(l->
        {
        ArrayList<Vehicule> mat = new ServicesVehicule().vecmatricule(matricule);
        ArrayList<Colis> Listtrie = new ColisService().TriColis(mat.get(0).getId());
        this.removeComponent(content);
                for (int i = 0; i < Listtrie.size(); i++) 
        {
            if(Listtrie.get(i).getEtat()==3)
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
        mb.setTextLine1("🗺 Traget: "+Listtrie.get(i).getDepart()+"➡"+Listtrie.get(i).getDestination());
        mb.setTextLine2("🔠 Poids: "+String.valueOf(Listtrie.get(i).getPoids()));
        mb.setTextLine3("👨 Client: "+String.valueOf(Listtrie.get(i).getNomExpediteur()));
        mb.setTextLine4(Integer.toString(Listtrie.get(i).getIdC()));
        mb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new Demande(Integer.valueOf(mb.getTextLine4()),matricule,res).show();
            }
        });
        int id=Listtrie.get(i).getIdC();
        SwipeableContainer sousou=  new SwipeableContainer(cntr1, cntr, mb);
        content1.addComponent(sousou);
        btn_ed.addActionListener(m->{
              new ModifierColis(id,res).show();
            });
    btn_del.addActionListener(ok->{
         
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
                
                if( new ColisService().deletecolis(id))
                {
                    ToastBar.showInfoMessage("Votre Colis  est supprimée avec succé");
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
      
            }

        }
        tri.setEnabled(false);
        this.addAll(content1);
        this.refreshTheme();
        });
content1.revalidate();
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
    
