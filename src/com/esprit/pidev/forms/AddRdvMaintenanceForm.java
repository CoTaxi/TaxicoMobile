/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.models.Rdv;
import com.esprit.pidev.services.RdvService;
import com.esprit.pidev.services.GarageService;
import com.esprit.pidev.services.ServiceService;

/**
 *
 * @author aissa
 */
public class AddRdvMaintenanceForm extends Form {

    public AddRdvMaintenanceForm(Form previous) {
//        super("Add a new task", BoxLayout.y());
//
//        TextField tfName = new TextField(null, "Task name");
//        TextField tfStatus = new TextField(null, "Status (0 or 1)");
//        Button btn = new Button("Add the task");
//
//        btn.addActionListener((evt) -> {
//            if ((tfName.getText().length() == 0) || (tfStatus.getText().length() == 0)) {
//                Dialog.show("Alert", "Please fill all the fields", "OK", null);
//            } else {
//                try {
//                    Task t = new Task(Integer.parseInt(tfStatus.getText()), tfName.getText());
//                    if (new TaskService().addTask(t)) {
//                        Dialog.show("SUCCESS", "Task sent", "OK", null);
//                    } else {
//                        Dialog.show("ERROR", "Server error", "OK", null);
//                    }
//                } catch (NumberFormatException e) {
//                    Dialog.show("ERROR", "Status must be a number", "OK", null);
//                }
//
//            }
//        });
//
//        this.addAll(tfName, tfStatus, btn);
//
//        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
//            previous.showBack();
//        });
super("Update Rdv", BoxLayout.y());

        TextField tfidRdv = new TextField(null, "Entrer id du Rdv");
        TextField tfStatus = new TextField(null, "Status (disponible or nondisponible)");
        Button btn = new Button("Update Rdv");

        btn.addActionListener((evt) -> {
            if ((tfidRdv.getText().length() == 0) || (tfStatus.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            } else {
                try {
                    Rdv r = new Rdv(Integer.parseInt(tfidRdv.getText()), tfStatus.getText());
                    if (new RdvService().updateRdv(r)) {
                        Dialog.show("SUCCESS", "Rdv updated", "OK", null);
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", "OK", null);
                }

            }
        });

        this.addAll(tfidRdv, tfStatus, btn);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
    
    
   

}
