/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.maintenance;
import com.codename1.admob.AdMobManager;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
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
    public ReserveListForm(Resources res) {
//        super("Rdvs list", BoxLayout.y());
//
//        this.add(new SpanLabel(new TaskService().getAllRdvsReserved().toString()));
//
//        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
//            previous.showBack();
//        });

super("Rdv list", new BorderLayout());

            admob.loadAndShow();
    
     
        Container listRec = new Container(BoxLayout.y());
        listRec.setScrollableY(true);
        Button btn = new Button("Annuler");
        ArrayList<Rdv> List = new RdvService().getAllRdvsReserved();
        for (int i = 0; i<List.size(); i++) {
    try {
        Button btnR = new Button();
        FontImage.setMaterialIcon(btnR, FontImage.MATERIAL_DELETE);
        MultiButton mBtn = new MultiButton("Rdv n°"+i+":");
        mBtn.setTextLine1(List.get(i).getName_service());
        mBtn.setTextLine2(List.get(i).getName_garage());
        mBtn.setTextLine3(List.get(i).getDate_rdv().toString());
        FontImage.setMaterialIcon(mBtn, FontImage.MATERIAL_COMMENT);
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
            if (date1.getTime()-date2.getTime()<0){
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
        InfiniteProgress.setDefaultMaterialDesignMode(true);
//            SwipeableContainer swip = new SwipeableContainer(btn,mBtn);
listRec.addAll(mBtn,btnR);
    } catch (ParseException ex) {

    }
            

            
        }
        
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
  this.getContentPane().addPullToRefresh(new Runnable() {
    @Override
    public void run() {
        ArrayList<Rdv> List = new RdvService().getAllRdvsReserved();
        for (int i = 0; i<List.size(); i++) {
            Button btnR = new Button();
            FontImage.setMaterialIcon(btnR, FontImage.MATERIAL_DELETE);
            MultiButton mBtn = new MultiButton("Rdv n°"+i+":");
            mBtn.setTextLine1(List.get(i).getName_service());
            mBtn.setTextLine2(List.get(i).getName_garage());
            mBtn.setTextLine3(List.get(i).getDate_rdv().toString());
            FontImage.setMaterialIcon(mBtn, FontImage.MATERIAL_COMMENT);
            Rdv r = new Rdv(List.get(i).getId_rdv());
            btnR.addActionListener(es->{
                if (new RdvService().annulerRdv(r)) {
                        Dialog.show("SUCCESS", "Rdv annuler", "OK", null);
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                
//                mBtn.remove();
//                btnR.remove();
                
//                this.refreshTheme();
            });
InfiniteProgress.setDefaultMaterialDesignMode(true);
//            SwipeableContainer swip = new SwipeableContainer(btn,mBtn);
            listRec.removeAll();
            listRec.addAll(mBtn,btnR);
            listRec.revalidate();
            ReserveListForm f = new ReserveListForm(res);
            f.revalidate();
    }
        
}
});
}
}