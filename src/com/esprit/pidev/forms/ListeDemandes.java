/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.services.ColisService;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author aissa
 */
public class ListeDemandes extends Form {

    public ListeDemandes() {
        super("Vehicules Chauffeur",new BoxLayout(BoxLayout.Y_AXIS));
    //----------Declaration
   ArrayList<Map<String, Object>> data = new ArrayList<>();
//   ArrayList<Vehicule> List = new VehiculeServices().AfficherVehicule();
   Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));
   Dialog d = new Dialog();
   content.setScrollableY(true);
   Button archive = new Button ("Archive");
   //-------------End 
   

        final MultiButton mb = new MultiButton();
        mb.setTextLine1("Polo 7");
        mb.setTextLine2("Tunis -> Bizerte");
        mb.setTextLine4("963");
        mb.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2=new Form("Liste Des Demandes",new BoxLayout(BoxLayout.Y_AXIS));
                ArrayList<Colis> List = new ColisService().ListeDemandes(mb.getTextLine4());
                for (int i = 0; i < List.size(); i++) {
                    if(List.get(i).getEtat()!=3)
                    {
        final MultiButton listc = new MultiButton();
        listc.setTextLine1("Traget : "+List.get(i).getDepart()+"-->"+List.get(i).getDestination());
        listc.setTextLine2("Poids : "+String.valueOf(List.get(i).getPoids()));
        listc.setTextLine3("Client : "+String.valueOf(List.get(i).getNomExpediteur()));
        listc.setTextLine4(Integer.toString(List.get(i).getIdC()));
        listc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
              new Demande(Integer.valueOf(listc.getTextLine4()),mb.getTextLine4());
            }
        });
        
        f2.addComponent(listc);
         }
                content.revalidate();
                
                f2.show();
                f2.getToolbar().addCommandToLeftBar("Return", null, (fev) -> {
           new ListeDemandes().show();
        });  
                }
                
         }
          
        });
        this.addComponent(mb);
content.revalidate();

this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
           new ChauffeurForm().show();
        });  
    }
    
}
