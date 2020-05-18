package com.esprit.pidev.forms.reclamation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Reclamation;
import com.esprit.pidev.services.ReclamationServices;
import com.esprit.pidev.utils.Statics;
import com.mycompany.myapp.Forms.BaseForm;
import java.util.ArrayList;

/**
 *
 * @author aissa
 */
public class ReclamationListForm extends BaseForm {

    public ReclamationListForm(Resources theme) {
       super("Liste Des Réclamations", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
//        tb.addCommandToLeftBar("Return", null, (evt) -> {
//         //  new ColisForm(res).show();
//        });  
        getTitleArea().setUIID("Container");
//        setTitle("TaxiCo-Vehicule");
        getContentPane().setScrollVisible(false);
        this.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
        this.setBgImage(theme.getImage("bag.png"));
        
        super.installSidemenu(theme);

        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, theme.getImage("bg.png"), spacer1, "15 Ride", "10 Colis", "Welcome Back To TaxiCo.");
        addTab(swipe, theme.getImage("bg.png"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
                
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
        //Container content1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ButtonGroup barGroup = new ButtonGroup();
        Button btn = new Button();
        RadioButton featured = RadioButton.createToggle("Reclamation", barGroup);
        featured.setUIID("SelectBar");
            Container listRec = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        //listRec.setScrollableY(true);
        //listRec.setScrollableX(true);
        
        ArrayList<Reclamation> List = new ReclamationServices().getAllRec();
        for (int i = 0; i<List.size(); i++) {

            MultiButton mBtn = new MultiButton("Réc n°"+i+":");
            mBtn.setTextLine1(List.get(i).getType());
            mBtn.setTextLine2(List.get(i).getMessage());
            mBtn.setTextLine3(List.get(i).getEtat()+" -  👨‍ "+List.get(i).getPrename());
            mBtn.setTextLine4("📅  " +List.get(i).getDate_rec());
            String res = List.get(i).getReponse();
            String state = List.get(i).getEtat();
            FontImage.setMaterialIcon(mBtn, FontImage.MATERIAL_COMMENT);
            int id = List.get(i).getId_reclamation();
            
            Button btn_edit = new Button();
            Button btn_delete = new Button();
            FontImage.setMaterialIcon(btn_edit, FontImage.MATERIAL_EDIT);
            FontImage.setMaterialIcon(btn_delete, FontImage.MATERIAL_DELETE_OUTLINE);
            Container cntr = new Container(new FlowLayout());
            Container cntr1 = new Container(new FlowLayout());
            cntr.add(btn_edit);
            cntr1.add(btn_delete);
            SwipeableContainer sousou=  new SwipeableContainer(cntr1, cntr, mBtn);
            listRec.addAll(sousou);
                
                btn_edit.addActionListener(al->{
                new DetailsRecForm(theme, res, id, state).show();
                });
                
                btn_delete.addActionListener(r->{
                if(new ReclamationServices().deleterec(id))
                {
                    ToastBar.showInfoMessage("Votre réclamation est supprimée avec succé");
                }else{
                    ToastBar.showErrorMessage("Erreur de suppression");
                }
               // mBtn.remove();
                sousou.remove();
                this.refreshTheme();
            });
            
            mBtn.addActionListener(al->{
            new DetailsRecForm(theme, res, id, state).show();
            });   
        }
        System.out.println(Statics.sessionID);
        //listRec.setScrollableY(true);
        this.add(listRec);
        
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
        this.setScrollableY(true);
        
    }    
}
