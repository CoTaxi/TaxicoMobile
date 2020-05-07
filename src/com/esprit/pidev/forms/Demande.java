/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.util.Base64;
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.services.ColisService;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class Demande extends Form
{

    public Demande(int Id,String matricule) 
    {
        super("Demande",BoxLayout.y());
        System.out.println("hetha el colis id : "+Id);
        System.out.println("hethi el matricule :"+matricule);
        Label dep = new Label();
        Label dest = new Label();
        Label poids = new Label();
        Label nomexp = new Label();
        Label nomdest = new Label();
        Label mailexp = new Label();
        Label maildest = new Label();
        Label ldep = new Label("Depart :");
        Label ldest = new Label("Destination :");
        Label lpoids = new Label("poids :");
        Label lnomexp = new Label("Nom Expediteur :");
        Label lnomdest = new Label("Nom Destinataire :");
        Label lmailexp = new Label("Mail Expediteur :");
        Label lmaildest = new Label("Mail Destinataire :");
        Label abcolis = new Label("----A Propos Du Colis----");
        Label abuser = new Label("----A Propos Du Client----");
        Container cdep = new Container();
        Container cdest = new Container();
        Container cpoids = new Container();
        Container cnomexp = new Container();
        Container cnomdest = new Container();
        Container cmailexp = new Container();
        Container cmaildest = new Container();
        Container colisdet = new Container(BoxLayout.y());
        Container userdet = new Container(BoxLayout.y());
        Button accept = new Button("Accepter");
        Button refus = new Button ("Refuser");
        Label etat2 = new Label ("Vous Avez Accepté Ce Colis");
        Label etat3 = new Label("Ce Colis Est Deja Livré");
        Button maj = new Button ("Marqué Comme Livré");
        int etat=-1;
        //---------------------Declaration End
        
            ArrayList<Colis> det = new ColisService().getColis(Integer.valueOf(Integer.toString(Id)));
              for (int i=0;i<det.size();i++)
              {
              etat=det.get(i).getEtat();
              dep.setText(det.get(i).getDepart());   
              dest.setText(det.get(i).getDestination());
              poids.setText(Float.toString(det.get(i).getPoids())+" Kg");
              nomexp.setText(det.get(i).getNomExpediteur());
              nomdest.setText(det.get(i).getNomDestinataire());
              mailexp.setText(det.get(i).getMailExpediteur());
              maildest.setText(det.get(i).getMailDestinataire());
              }
              cdep.addAll(ldep,dep);
              cdest.addAll(ldest,dest);
              cpoids.addAll(lpoids,poids);
              cnomexp.addAll(lnomexp,nomexp);
              cnomdest.addAll(lnomdest,nomdest);
              cmailexp.addAll(lmailexp,mailexp);
              cmaildest.addAll(lmaildest,maildest);
              colisdet.addAll(cdep,cdest,cpoids);
              userdet.addAll(cnomexp,cmailexp,cnomdest,cmaildest);
              this.addAll(abcolis,colisdet,abuser,userdet);
              if((etat!=2)&&(etat!=3))
              this.addAll(accept,refus);
              else if(etat==2)
              {
              this.addAll(etat2,maj);
              }
              else if(etat==3)
              this.add(etat3);
              maj.addActionListener((etatf)->{
                  
                  if (new ColisService().majColis(Id)) {
                String accountSID ="AC2c8c457ccb41bbdc4376ffad45b331c3";
                String authToken="ee7a35e489e95f817eae2a7d8b1a6e22";
                Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
                queryParam("To", "+21620362075").
                queryParam("From","+19144915971").
                queryParam("Body", "Felicitation (Mr/M) "+nomexp.getText()+" Votre Colis a éte Livré \n Rendez Vous Sur Notre Site WWW.Taxico.com <3 \n Bonne Journée.").
        header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
        getAsJsonMap();
                        Dialog.show("Livré", "Felicitation Vous Avez Livré Un Nouveau Colis (Archive)", "OK", null);
                    }
                  else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
               
              });
              accept.addActionListener((l)->{
                  if (new ColisService().accepterColis(Id)) {
                        Dialog.show("Accepté", "Vous Avez Accepté La Livraison Du Colis", "OK", null);
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
              });
              refus.addActionListener((l)->{
                  
                  if (new ColisService().refusererColis(Id)) {
                        Dialog.show("Reffusé", "Ce Colis Ne Fait Plus Partie De Votre Liste", "OK", null);
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
              });
              
         this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
           new ChauffeurForm().show();
        });  
              this.show();
         //Affichage Colis Details 
         //Affichage User
         //Accepter et Refuser 
         //Retour
         //this.add();
    }
    
}
