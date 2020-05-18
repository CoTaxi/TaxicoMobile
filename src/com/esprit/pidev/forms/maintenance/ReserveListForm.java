/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.maintenance;
import com.codename1.admob.AdMobManager;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.InteractionDialog;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
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
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Rdv;
import com.esprit.pidev.services.RdvService;
import com.mycompany.myapp.Forms.BaseForm;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author aissa
 */
public class ReserveListForm extends BaseForm 
{
public AdMobManager admob= new AdMobManager("ca-app-pub-4209362622009586/9753595425");
    public ReserveListForm(Resources theme) {


        super("Liste des rendez-vous", BoxLayout.y());

            admob.loadAndShow();
            
            Toolbar tb = new Toolbar(true);
        setToolbar(tb); 
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        
        super.installSidemenu(theme);
        tb.addCommandToRightBar("Return", null, (evt) -> {
         //page tsawer
        });
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, theme.getImage("bg.png"), spacer1, "15 Ride", "10 Colis", "Welcome Back To TaxiCo.");
        addTab(swipe, theme.getImage("bg.png"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
        
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
                
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
        
 
        Container listRec = new Container(BoxLayout.y());
        //listRec.setScrollableY(true);
        Button btn = new Button("Annuler");
        ArrayList<Rdv> List = new RdvService().getAllRdvsReserved();
        for (int i = 0; i<List.size(); i++) {
    try {
        Button btnR = new Button();
        FontImage.setMaterialIcon(btnR, FontImage.MATERIAL_DELETE);
        MultiButton mBtn = new MultiButton("Rdv n°"+i+":");
        mBtn.setTextLine1("🔧  " +List.get(i).getName_service());
        mBtn.setTextLine2("🚙  " +List.get(i).getName_garage());
        mBtn.setTextLine3("📅  " +List.get(i).getDate_rdv().toString());
        Rdv r = new Rdv(List.get(i).getId_rdv());
        java.util.Date dt = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date =format.format(dt);
        String date25 = format.format(List.get(i).getDate_rdv());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = sdf.parse(date);
        Date date1 = sdf.parse(date25);
        System.out.println(date2);
        System.out.println(date1);
//        System.out.println(date);
        btnR.addActionListener(es->{
            InteractionDialog dialogverif = new InteractionDialog("Hello");
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
            if (date1.getTime()-date2.getTime()>0){
            if (new RdvService().annulerRdv(r)) {
                Dialog.show("SUCCESS", "Rdv annuler", "OK", null);
                mBtn.remove();
                btnR.remove();
                
                this.refreshTheme();
            } else {
                Dialog.show("ERROR", "Server error", "OK", null);
            }
            } else {
                Dialog.show("ERROR", "Date est depasser", "OK", null);
            }
         }); 
Dimension pre = dialogverif.getContentPane().getPreferredSize();
int height = Display.getInstance().convertToPixels(9f);
int width = Display.getInstance().convertToPixels(10f);
int top = Display.getInstance().convertToPixels(95f);
int bottom = Display.getInstance().convertToPixels(0f);
dialogverif.show(top, bottom, height, width);
});        
InfiniteProgress.setDefaultMaterialDesignMode(true);
//            SwipeableContainer swip = new SwipeableContainer(btn,mBtn);
listRec.addAll(mBtn,btnR);
    } catch (ParseException ex) {

    }
        }
        
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