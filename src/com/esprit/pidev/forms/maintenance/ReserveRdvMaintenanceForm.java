/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.maintenance;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Calendar;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.Garage;
import com.esprit.pidev.models.Rdv;
import com.esprit.pidev.models.Service;
import com.esprit.pidev.services.GarageService;
import com.esprit.pidev.services.RdvService;
import com.esprit.pidev.services.ServiceService;
import com.mycompany.myapp.Forms.BaseForm;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;


/**
 *
 * @author walid
 */
public class ReserveRdvMaintenanceForm extends BaseForm
{
    
    private TimeZone tmz;
    public ReserveRdvMaintenanceForm(Resources res) 
    {
                super("Ajouter Rdv", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
//        tb.addCommandToLeftBar("Return", null, (evt) -> {
//         //  new ColisForm(res).show();
//        });  
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        
        super.installSidemenu(res);
//        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("bg.png"), spacer1, "15 Ride", "10 Colis", "Welcome Back To TaxiCo.");
        addTab(swipe, res.getImage("bg.png"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
                
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
        RadioButton featured = RadioButton.createToggle("Maintenance", barGroup);
        featured.setUIID("SelectBar");

        Label l = new Label();
        ComboBox cbService= new ComboBox();
        ComboBox cbGarage= new ComboBox();
        cbGarage.getStyle().setMarginTop(30);
        ComboBox cbRdv= new ComboBox();
        Picker datePicker = new Picker();
        Calendar cld = new Calendar();
        Collection<Date> datelist = new ArrayList();
        
datePicker.setType(Display.PICKER_TYPE_DATE);
        cbRdv.getStyle().setBgColor(0xf99f1b);
        cbRdv.getStyle().setMarginTop(30);
        Picker p = new Picker();
        Button btnSms = new Button("Send SMS");
//        Button btnMail = new Button("Send Mail");
        Button btn = new Button("Reserve Rdv");
//        Button btntest = new Button("Notification");
        ArrayList<Service> List = new ServiceService().getAllServices();
        ArrayList<String> List2 = new ArrayList();
        String [] ListNameService = new String[20];
        for (int i=0; i<List.size();i++){
        cbService.addItem(List.get(i).getName().toString());
//        ListNameService[i] = List.get(i).getName();
//        List2.add(List.get(i).getName()) ;
        }
//        p.setStrings(ListNameService);
        cbService.addActionListener(e->{ 
           
      
        ArrayList<Garage> ListGarage = new GarageService().FindGarages(cbService);
        for (int i=0; i<ListGarage.size();i++){
        cbGarage.addItem(ListGarage.get(i).getName().toString());
        }
                });
        cbGarage.addActionListener(e->{
            ArrayList<Rdv>ListRdv = new RdvService().FindRdvs(cbService,cbGarage);
            for (int i=0;i<ListRdv.size();i++){
                
                    cbRdv.addItem(ListRdv.get(i).getDate_rdv().toString());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                    Date dateRdv = format.parse( ListRdv.get(i).getDate_rdv().toString() ) ;
                    System.out.println(ListRdv.get(i).getDate_rdv());
//                    System.out.println(dateRdv);
                 
                    
                    datelist.add(ListRdv.get(i).getDate_rdv());
                   cld.setDate(ListRdv.get(i).getDate_rdv());
               
                
            }
            System.out.println(datelist);
//            for (Date datelists : datelist) {
//            java.util.Calendar cal = java.util.Calendar.getInstance(tmz);
//            cal.setTime(datelists);
//            cal.set(java.util.Calendar.HOUR, 1);
//            cal.set(java.util.Calendar.HOUR_OF_DAY, 1);
//            cal.set(java.util.Calendar.MINUTE, 0);
//            cal.set(java.util.Calendar.SECOND, 0);
//            cal.set(java.util.Calendar.MILLISECOND, 0);
//            datelist.add(cal.getTime());
//        }
//        selectedDaysUIID = "CalendarMultipleDay";
//        mv.setCurrentDay(SELECTED_DAY, true);
//        componentChanged();

         
//           cld.setSelectedDays(datelist);
        });
       btn.addActionListener((evt) -> {
//            if ((tfidRdv.getText().length() == 0) || (tfStatus.getText().length() == 0)) {
//                Dialog.show("Alert", "Please fill all the fields", "OK", null);
//            } else {
//                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String date =format.format(cld.getDate());
                    ArrayList<Rdv>ListRdv2 = new RdvService().FindRdvsSelected(date);
                    Rdv r = new Rdv(ListRdv2.get(0).getId_rdv());
                    System.out.println(r.getId_rdv());
                    if (new RdvService().updateRdv(r)) {
//                        Dialog.show("SUCCESS", "Rdv Reserved", "OK", null);
                      ToastBar.showInfoMessage("Rendez vous Reserved");
                       
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
//                } catch (NumberFormatException e) {
//                    Dialog.show("ERROR", "Status must be a number", "OK", null);
//                }
//
//            }
  
                String myURL = "https://rest.nexmo.com/sms/json?api_key=3f027ab2&api_secret=11AZ1yktyp8xwt04&to=21624515801" + "&from=TaxiCo&text=Rdv Reserved";
                ConnectionRequest cntRqst = new ConnectionRequest() {
                    protected void readResponse(InputStream in) throws IOException {
                    }

                    @Override
                    protected void postResponse() {
                        Dialog.show("SMS", "sms successfully sent", "OK", null);

                    }
                };
                cntRqst.setUrl(myURL);
                NetworkManager.getInstance().addToQueue(cntRqst);
            

        });
//       btntest.addActionListener(e -> {
//           LocalNotification n = new LocalNotification();
//        n.setId("demo-notification");
//        n.setAlertBody("It's time to take a break and look at me");
//        n.setAlertTitle("Break Time!");
//        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound
//
//
//        Display.getInstance().scheduleLocalNotification(
//                n,
//                System.currentTimeMillis() , // fire date/time
//                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
//        );
//       });
     


        this.addAll(cbService, cbGarage,l, cld,  btn);

        
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
    }  

    }

