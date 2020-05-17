/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.Forms;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.CN.getDisplayWidth;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.NORTH;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.forms.colis.AjoutColis;
import com.esprit.pidev.forms.vehicule.FindTaxi;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class NewsfeedForm extends BaseForm {

    public NewsfeedForm(Resources res) {
        super("Newsfeed", new BoxLayout(BoxLayout.Y_AXIS));
       
        setUIID("Maps");
        super.installSidemenu(res);
        //--------------------
        final Container layer = getLayeredPane(MapForm.class, true);
        Button back = new Button("", "TitleCommand");
        //FontImage.setMaterialIcon(back, FontImage.MATERIAL_ARROW_BACK);
        TextField from = new TextField("", "Choisir un point de départ", 40, TextField.ANY);
        TextField to = new TextField("", "Choisir un point de destination", 40, TextField.ANY);
        //TextField bt = new TextField("", "", 40, TextField.ANY);
        Label fromSelected = new Label();
        final Label toSelected = new Label();
        Button search = new Button();
        Image img1 = res.getImage("mark2.png");
        Image img = res.getImage("mark1.png");
        int height = Display.getInstance().convertToPixels(10f);
        int width = Display.getInstance().convertToPixels(10f);
        from.getHintLabel().setUIID("FromToTextFieldHint");
        from.setUIID("FromToTextField");
        to.getHintLabel().setUIID("FromToTextFieldHint");
        to.setUIID("FromToTextField");
        Container navigationToolbar = BoxLayout.encloseY(back, BorderLayout.centerCenterEastWest(from, new Label(img.fill(width, height)), fromSelected), BorderLayout.centerCenterEastWest(to, new Label(img1.fill(width, height)), toSelected),BorderLayout.centerCenterEastWest(null, new Button("Trouver Un Taxi"), search));
        navigationToolbar.setUIID("WhereToToolbar");
        navigationToolbar.getUnselectedStyle().setBgPainter((g1, rect) -> {
            g1.setAlpha(255);
            g1.setColor(0xffffff);

        });
        layer.setLayout(new BorderLayout());
        layer.add(NORTH, navigationToolbar);
        navigationToolbar.setWidth(getDisplayWidth());
        navigationToolbar.setHeight(getPreferredH());
        navigationToolbar.setY(-navigationToolbar.getHeight());
        layer.animateLayout(200);
        
        //---------------------
        Container cnt  =addButton(res.getImage("news-item-1.jpg"), "Reserver Taxi", false, 26, 32);
        Container cnt1 =addButton(res.getImage("news-item-2.jpg"), "Reserver Covoiturage", true, 15, 21);
        Container cnt2 =addButton(res.getImage("news-item-3.jpg"), "Envoyer Colis", false, 36, 15);
         TextField Depart1 = new TextField();
        TextField Destination1 = new TextField();
         TextField Depart2 = new TextField();
        TextField Destination2 = new TextField();
        Button searchtaxi = new Button(); 
        Button searchcov = new Button();
        Button searchcolis = new Button();
        Container dep1 = BorderLayout.centerEastWest(Depart1, new Label(img.fill(width, height)), null);
        Container dest1 = BorderLayout.centerEastWest(Destination1, new Label(img.fill(width, height)), null);
        Container dep2 = BorderLayout.centerEastWest(Depart2, new Label(img.fill(width, height)), null);
        Container dest2 = BorderLayout.centerEastWest(Destination2, new Label(img.fill(width, height)), null);
       
        Tabs swipe = new Tabs();

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Tout", barGroup);
        all.setUIID("SelectBar");
        RadioButton Services = RadioButton.createToggle("Taxi", barGroup);
        Services.setUIID("SelectBar");
        RadioButton Events = RadioButton.createToggle("Colis", barGroup);
        Events.setUIID("SelectBar");
        RadioButton Blog = RadioButton.createToggle("Co-Voiturage", barGroup);
        Blog.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, Services, Events, Blog),
                FlowLayout.encloseBottom(arrow)
        ));
        Services.addActionListener(l->{
            if(Services.isSelected())
            {
            System.out.println("Taxi");
            this.removeComponent(cnt);
            this.removeComponent(cnt1);
            this.removeComponent(cnt2);
            this.removeComponent(dep1);
            this.removeComponent(dest1);
            this.removeComponent(searchcolis);
            this.removeComponent(dep2);
            this.removeComponent(dest2);
            this.removeComponent(searchcov);
            searchtaxi.setText("Trouver Taxi");
            this.addAll(from,to,searchtaxi);
            this.refreshTheme();
            }
        });
        
        Events.addActionListener(l->{
            if(Events.isSelected())
            {
        System.out.println("Colis");
        this.removeComponent(cnt);
        this.removeComponent(cnt1);
        this.removeComponent(cnt2);
        this.removeComponent(from);
        this.removeComponent(to);
        this.removeComponent(searchtaxi);
        this.removeComponent(dep2);
        this.removeComponent(dest2);
        this.removeComponent(searchcov);
        searchcolis.setText("Envoyer Colis");
        this.addAll(dep1,dest1,searchcolis);
        this.refreshTheme();
            }
        });
        searchcolis.addActionListener(l->{
          new AjoutColis(res).show();
         //new AfficherVehicule(res).show();
        });
        searchtaxi.addActionListener(l->{
           new FindTaxi(res,from.getText(),to.getText()).show();
        });
        all.addActionListener(l->{
            if(all.isSelected())
            {
            this.removeComponent(from);
            this.removeComponent(to);
            this.removeComponent(searchtaxi);
            this.removeComponent(dep1);
            this.removeComponent(dest1);
            this.removeComponent(searchcolis);
            this.removeComponent(dep2);
            this.removeComponent(dest2);
            this.removeComponent(searchcov);
            this.addAll(cnt,cnt1,cnt2);
            this.refreshTheme();
            System.out.println("all");
            }
        });
        Blog.addChangeListener(l->
        {   System.out.println("co-voiturage");
            if(Blog.isSelected())
            {
            this.removeComponent(cnt);
            this.removeComponent(cnt1);
            this.removeComponent(cnt2);
            this.removeComponent(from);
            this.removeComponent(to);
            this.removeComponent(searchtaxi);
            this.removeComponent(dep1);
            this.removeComponent(dest1);
            this.removeComponent(searchcolis);
            searchcov.setText("Trouver Co-Voiturage");
            this.addAll(dep2,dest2,searchcov);
            this.refreshTheme();
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
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
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
    
   private Container addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
               ));
      
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
       return cnt;
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
