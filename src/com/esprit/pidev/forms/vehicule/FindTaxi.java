/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.vehicule;

import com.codename1.components.Accordion;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Vehicule;
import com.esprit.pidev.services.ServicesVehicule;
import com.mycompany.myapp.Forms.BaseForm;
import java.util.ArrayList;

public class FindTaxi extends BaseForm {

    public FindTaxi(Resources res, String depart, String dest) {
        super("Taxi", new BoxLayout(BoxLayout.Y_AXIS));
        setUIID("Maps");
        MultiButton mb = new MultiButton("taxi");
        ArrayList<Vehicule> List = new ServicesVehicule().findPosition(depart, "taxi");
        super.installSidemenu(res);
        this.setScrollableY(true);
        if (List.size() > 0) {
            for (int i = 0; i < List.size(); i++) {
                Accordion accr = new Accordion();
                Button book = new Button("Reserver");
                accr.getStyle().setBgImage(res.getImage("accordionfinal.png"));
                this.getStyle().setBgImage(res.getImage("BG999.png"));
                int height = Display.getInstance().convertToPixels(9f);
                int width = Display.getInstance().convertToPixels(10f);
                Label lab1 = new Label("Meriam Mhadhebi");
                lab1.getStyle().setFgColor(0xffffff);
                accr.addContent(BoxLayout.encloseY(new Label(res.getImage("noslog2.png").fill(width, height)), lab1), BoxLayout.encloseXCenter(new Label(List.get(i).getMarque()), new TextField(List.get(i).getCouleur()), book, new CheckBox("CheckBox")));
                book.addActionListener(l -> {
                    System.out.println("book now");
                });
                this.add(accr);
            }
        } else {
            ArrayList<Vehicule> List1 = new ServicesVehicule().findvec(depart, "taxi");
            if (List1.size() > 0) {
                for (int i = 0; i < List1.size(); i++) {
                    Accordion accr = new Accordion();
                    Button reserver = new Button("Reserver");
                    accr.addContent("Item3", BoxLayout.encloseY(new Label(List1.get(i).getPosition()), new TextField(List1.get(i).getCouleur()), reserver, new CheckBox("CheckBox")));
                    reserver.addActionListener(l -> {
                        System.out.println("reserver");
                    });
                    this.add(accr);
                }
            }
        }

        this.getToolbar().addCommandToOverflowMenu("Mode jour", res.getImage("back-command.png"), e -> {
            this.refreshTheme();
            this.getStyle().setBgImage(res.getImage("day0.png"));
            this.refreshTheme();

        });
        this.getToolbar().addCommandToOverflowMenu("Mode nuit", res.getImage("back-command.png"), e -> {
            this.refreshTheme();
            this.getStyle().setBgImage(res.getImage("BG999.png"));
            this.refreshTheme();

        });

        this.show();

    }

}
