package com.esprit.pidev.forms.maintenance;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.components.Accordion;
import com.codename1.components.MultiButton;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Commande;
import com.esprit.pidev.models.User;
import com.esprit.pidev.models.Vehicule;
import com.esprit.pidev.services.RdvService;
import com.esprit.pidev.services.ServiceCommande;
import com.esprit.pidev.services.ServicesVehicule;
import com.esprit.pidev.services.UserService;
import com.mycompany.myapp.Forms.BaseForm;
import java.util.ArrayList;

/**
 *
 * @author walid
 */
public class ChForm extends BaseForm{
    public ChForm (Resources res){
        super("Ch list", new BorderLayout());
        Accordion accr = new Accordion();
        this.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
        this.setBgImage(res.getImage("bg.png"));
        ArrayList<Vehicule> List2 = new ServicesVehicule().getPosition();
                    Button actualiser = new Button("Actualiser");
                    int height = Display.getInstance().convertToPixels(9f);
                    int width = Display.getInstance().convertToPixels(10f);
                    Label lab1 = new Label(List2.get(0).getPosition());
                    TextField t = new TextField();
                    lab1.getStyle().setFgColor(0xffffff);
                    accr.getStyle().setBgImage(res.getImage("accordionfinal.png"));
                    accr.addContent(BoxLayout.encloseY(new Label(res.getImage("noslog2.png").fill(width, height)),lab1), BoxLayout.encloseY( t,actualiser));
                    actualiser.addActionListener(l -> {
                        Vehicule v = new Vehicule(t.getText());
                        if(new ServicesVehicule().updateposition(v))
            {
                ToastBar.showInfoMessage("Position Modifier");
                       
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                    });
//                    this.add(BorderLayout.CENTER,accr);
        Container listRec = new Container(BoxLayout.y());
        listRec.setScrollableY(true);
        Button btn = new Button("Annuler");
        ArrayList<Vehicule> List = new ServicesVehicule().getReservedCar();
        ArrayList<Commande> ListC = new ServiceCommande().getCommande(List.get(0).getId());
        ArrayList<User> ListU = new UserService().getClient(ListC.get(0).getClient());
        System.out.println(ListU.get(0).getNom());
        for (int i = 0; i<ListU.size(); i++) {
            Button btnR = new Button();
            FontImage.setMaterialIcon(btnR, FontImage.MATERIAL_DELETE);
            MultiButton mBtn = new MultiButton("Commande n°"+i+":");
            System.out.println(ListU.get(i).getNom());
            System.out.println(ListU.get(i).getPrenom());
            mBtn.setTextLine1 (ListU.get(i).getNom());
            mBtn.setTextLine2(ListU.get(i).getPrenom());
            mBtn.setTextLine3 (ListU.get(i).getEmail());
             String n = ListU.get(i).getNom();
            String p = ListU.get(i).getPrenom();
            String e =  ListU.get(i).getEmail();
            int id = List.get(0).getId();
            int idCommande = ListC.get(0).getIdCommande();
            FontImage.setMaterialIcon(mBtn, FontImage.MATERIAL_COMMENT);
            mBtn.addActionListener(zzz->{
                
                new RdvService().doing(ListC.get(0).getIdCommande());
                new Chdetail(this, n, p, e,id,idCommande).show();
            });
            OnOffSwitch dispo = new OnOffSwitch();
        dispo.setOff("Non");
        dispo.setOn("Oui");
        dispo.setValue(false);
        dispo.addActionListener(l->{
            if (dispo.isValue()) {
                    new ServicesVehicule().updateDispo(1,List.get(0).getId());
                } else {
                    new ServicesVehicule().updateDispo(0,List.get(0).getId());
                }
        });
//            Rdv r = new Rdv(List.get(i).getId_rdv());
//            btnR.addActionListener(es->{
//                if (new RdvService().annulerRdv(r)) {
//                        Dialog.show("SUCCESS", "Rdv annuler", "OK", null);
//                    } else {
//                        Dialog.show("ERROR", "Server error", "OK", null);
//                    }
//                
//                mBtn.remove();
//                btnR.remove();
//                this.refreshTheme();
//            });

//            SwipeableContainer swip = new SwipeableContainer(btn,mBtn);
            listRec.addAll(mBtn,dispo);
            

            
        }
        this.add(BorderLayout.NORTH,accr);
        this.add(CENTER, listRec);

        //////////////////////////////////////////////////////////////////////////////
//        Form hi = new Form("Rdv List", new BoxLayout(BoxLayout.Y_AXIS));
//
//Container cont = new Container(new BorderLayout());
//
//Button button = new Button("Press me to see details"); //shouldn't be pressed when the container is just swiped!!
//button.setAutoRelease(true);
//cont.add(BorderLayout.CENTER, button);
//Container leftSwipeCont = new Container();
//leftSwipeCont.add(new Button("XX"));
//SwipeableContainer swipe = new SwipeableContainer(leftSwipeCont, cont);
//hi.add(swipe);
//hi.show();

    }
}
