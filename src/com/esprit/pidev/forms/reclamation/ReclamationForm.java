package com.esprit.pidev.forms.reclamation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static com.codename1.charts.util.ColorUtil.red;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.forms.reclamation.AjoutRec;
import com.esprit.pidev.models.Reclamation;
import com.esprit.pidev.services.ReclamationServices;
import com.mycompany.myapp.Forms.BaseForm;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author aissa
 */
public class ReclamationForm extends BaseForm {


    public ReclamationForm(Resources theme) {
        super(null, BoxLayout.yCenter());
        Label lOption = new Label("Choisir option : ");
        lOption.getStyle().setFgColor(0xf99f1b);
        Button btnAddTask = new Button("Ajouter réclamation(s)");
        Button btnTasksList = new Button("Afficher réclamation(s)");
        FontImage.setMaterialIcon(btnAddTask, FontImage.MATERIAL_ADD_COMMENT);
        FontImage.setMaterialIcon(btnTasksList, FontImage.MATERIAL_VIEW_LIST);
        btnAddTask.getStyle().setFgColor(0xf99f1b);
        btnTasksList.getStyle().setFgColor(0xf99f1b);
        btnTasksList.getStyle().setBorder(Border.createDashedBorder(CENTER, 0xf99f1b));
        btnAddTask.getStyle().setBorder(Border.createDashedBorder(CENTER, 0xf99f1b));
        //ComboBox cbx = new ComboBox();
//        ArrayList<Reclamation> List = new ReclamationServices().getAllTasks();
//        for (int i = 0; i<List.size(); i++) {
////            List.get(i).getEtat();
//        cbx.addItem(List.get(i).getEtat());
//        }
        
//        TextField txtEmail = new TextField(null, "Saisir Email...", CENTER, TextField.EMAILADDR);
//        TextField txtPass = new TextField(null, "Saisir pwd...", CENTER, TextField.PASSWORD);
//        Button btnConnecter = new Button("Connecter");
//        ImageViewer logo = new ImageViewer(theme.getImage("logo3vv.png"));
//        Label lblIns1 = new Label("Vous n'avez pas un compte ?");
//        Label lblIns2 = new Label("S'inscrire ici.");
//        lblIns1.getStyle().setFgColor(0xf99f1b);
//        lblIns1.setAlignment(CENTER);
//        lblIns2.setAlignment(CENTER);
//        lblIns2.getStyle().setFgColor(0xf99f1b);
//        lblIns2.getStyle().setUnderline(true);
//        txtPass.getUnselectedStyle().setBorder(
//                RoundBorder.create().rectangle(true).color(0xffffff)
//        );
//        txtEmail.getUnselectedStyle().setBorder(
//                RoundBorder.create().rectangle(true).color(0xffffff)
//        );
//        btnConnecter.getUnselectedStyle().setBorder(
//                RoundBorder.create().rectangle(true).color( 0xf99f1b)
//        );
//        logo.getStyle().setMarginBottom(0);
//        
//        txtEmail.getStyle().setMarginTop(0);
//        txtPass.getStyle().setMarginTop(1);
//        btnConnecter.getStyle().setMarginTop(30);
//        txtEmail.getStyle().setOpacity(210);
//        txtPass.getStyle().setOpacity(210);
//        btnConnecter.getStyle().setBgTransparency(BASELINE);
//        btnConnecter.getStyle().setMarginLeft(180);
//        btnConnecter.getStyle().setMarginRight(180);
        btnAddTask.addActionListener((evt) -> {
            new AjoutRec(theme).show();
        });
        btnTasksList.addActionListener((evt) -> {
            new ReclamationListForm(theme).show();
        });
//        this.getToolbar().getStyle().setBgColor(0x36324D);
        this.getToolbar().setTitle("Bienvenu dans TaxiCo");
        //this.getToolbar().add(BorderLayout.CENTER ,theme.getImage("taxi1.png"));
//                addCommandToOverflowMenu("back", theme.getImage("taxi.png"));
        this.getToolbar().add(BorderLayout.WEST, theme.getImage("driver.png"));
        
        this.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
        this.setBgImage(theme.getImage("Affiche_final.png"));
//        this.addAll(logo,txtEmail,txtPass,btnConnecter,lblIns1,lblIns2);
        this.addAll(lOption,btnAddTask, btnTasksList);
        
    }
    
}
