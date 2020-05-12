/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.reclamation;

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
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.html.HTMLElement;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.services.ReclamationServices;
import com.mycompany.myapp.Forms.BaseForm;

/**
 *
 * @author ASUS
 */
/**
 *
 * @author Oussama_RMILI
 */
public class DetailsRecForm extends BaseForm{
 
    
    public DetailsRecForm(Resources theme,String respond, int id, String state){
       super("Ajouter Reclamation", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.addCommandToLeftBar("Return", null, (evt) -> {
         //  new ColisForm(res).show();
        });  
        getTitleArea().setUIID("Container");
        setTitle("TaxiCo-Vehicule");
        getContentPane().setScrollVisible(false);
        
        super.installSidemenu(theme);
        tb.addSearchCommand(e -> {});
        
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
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton featured = RadioButton.createToggle("Reclamation", barGroup);
        featured.setUIID("SelectBar");
        SpanLabel sl = new SpanLabel(respond);
        sl.getStyle().setBorder(Border.createDashedBorder(CENTER, HTMLElement.COLOR_BLUE));
        TextComponent txt = new TextComponent().label("Modifier votre réclamation") ;
        Button edit = new Button("Valider");
        Button email = new Button("E-mail");
        edit.setIcon(theme.getImage(respond));
        FontImage.setMaterialIcon(sl, FontImage.MATERIAL_MESSAGE);
        FontImage.setMaterialIcon(edit, FontImage.MATERIAL_SAVE);
        FontImage.setMaterialIcon(email, FontImage.MATERIAL_EMAIL);
        if(state.equals("Traitée")||state.equals("Archivée"))
        {
            txt.setEnabled(false);
            edit.setEnabled(false);
        }
        
        this.getToolbar().addCommandToLeftBar("Retourner", null, (evt) -> {
          //  new ReclamationListForm(this).showBack();      
        });
        edit.addActionListener(up->{
            String NewMsg = txt.getText().toString();
            if (NewMsg.length()==0) 
            {
                ToastBar.showErrorMessage("Veuillez saisir votre nouveau message");
            } else {
                if (new ReclamationServices().updaterec(id, NewMsg)) 
                {
                    ToastBar.showInfoMessage("Votre réclamation a été modifiée avec succée");
                    
                } else {
                    ToastBar.showErrorMessage("Erreur de serveur");
                }
            }

        });
        email.addActionListener(em->{
            new ReclamationServices().sendEmail();
        });
//        cn1.add(sl);
        this.addAll(sl,txt,edit,email);
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