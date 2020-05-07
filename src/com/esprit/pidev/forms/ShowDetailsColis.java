/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidev.models.Colis;
import com.esprit.pidev.services.ColisService;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ShowDetailsColis extends Form
{
    public ShowDetailsColis(int Id) {
   super("Colis Details",new BoxLayout(BoxLayout.Y_AXIS));
        System.out.println(Id);
        Label dep = new Label();
        Label dest = new Label();
        Label poids = new Label();
        Label nomexp = new Label();
        Label nomdest = new Label();
        Label mailexp = new Label();
        Label maildest = new Label();
        Label etat = new Label();
        Label letat = new Label("Etat :");
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
        Container cetat = new Container();
        Container colisdet = new Container(BoxLayout.y());
        Container userdet = new Container(BoxLayout.y());
   Container cn= new Container();
   Container cn1= new Container();
   Button modify = new Button("Modifier Colis");
   Button delete = new Button("Supprimer Colis");
   Button affect = new Button("Affecter Colis");
   Container atent= new Container();
   Button ok = new Button("Comfirmer");
   Button non = new Button("Annuler");
   atent.addAll(ok,non);
    ArrayList<Colis> det = new ColisService().getColis(Integer.valueOf(Integer.toString(Id)));
              for (int i=0;i<det.size();i++)
              {
              if(det.get(i).getEtat()==0)
              etat.setText("Colis Non Affecté");
              else if(det.get(i).getEtat()==1)
              etat.setText("En Attente De comfirmation Du Chauffeur");
              else if(det.get(i).getEtat()==2)
              etat.setText("Colis Affecté");
              else if(det.get(i).getEtat()==3)
              etat.setText("Colis Livré");
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
              cetat.addAll(letat,etat);
              cnomexp.addAll(lnomexp,nomexp);
              cnomdest.addAll(lnomdest,nomdest);
              cmailexp.addAll(lmailexp,mailexp);
              cmaildest.addAll(lmaildest,maildest);
              colisdet.addAll(cdep,cdest,cpoids,cetat);
              userdet.addAll(cnomexp,cmailexp,cnomdest,cmaildest);
              this.addAll(abcolis,colisdet,abuser,userdet);
                  
              modify.addActionListener((ev) -> {
                  for (int i=0;i<det.size();i++)
              {
              int et=det.get(i).getEtat();
                  if((et==0)||(et==1))
                  {
                  new ModifierColis(Id).show();
                   }
                  else 
                  {
                      Dialog.show("ERREUR", "Vous Ne Pouvez Pas Modifier Un Colis Deja Affecté", "OK", null);
                  }
              }
              });
              delete.addActionListener((ev) -> {
                  for (int i=0;i<det.size();i++)
              {
              int et=det.get(i).getEtat();
                  if((et==0)||(et==1))
                  {
                  if(new ColisService().deletecolis(Id))
                  new   AfficherColis().show();  
                  else 
                      Dialog.show("ERREUR", "Vous Ne Pouvez Pas Supprimer Un Colis Deja Affecté", "OK", null);
                  
                   }
              }
              });
                 affect.addActionListener((ev) -> {
                  for (int i=0;i<det.size();i++)
              {
              int et=det.get(i).getEtat();
                  if((et==0))
                  {
                  new   AffecterColis(Id).show();
                  }
                  else 
                  {
                      Dialog.show("ERREUR", "Ce Colis Est Deja Affecté", "OK", null);
                  }
              }
              });
               
                 this.addAll(modify,delete);
                   for (int i=0;i<det.size();i++)
              {
                  if(det.get(i).getEtat()==0)
                 this.add(affect);
              } 
   this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
          new   AfficherColis().show();
        });
   
    }
}
