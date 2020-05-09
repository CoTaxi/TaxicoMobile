/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.colis;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.WebBrowser;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class Map extends Form
{

    public Map(Resources res)   
    {   
        
this.setLayout(new BorderLayout());
this.setScrollable(false);
final MapComponent mc = new MapComponent();

//get the current location from the Location API
Location loc = LocationManager.getLocationManager().getCurrentLocationSync();

loc.setLongitude(10.185990461365737);
loc.setLatitude(36.87440921619324);
Coord lastLocation = new Coord(loc.getLatitude(), loc.getLongtitude());
Image i = res.getImage("marker1.png");
PointsLayer pl = new PointsLayer();
pl.setPointIcon(i);
PointLayer p = new PointLayer(lastLocation, "You Are Here", i);
System.out.println(lastLocation);
p.setDisplayName(true);
pl.addPoint(p);
mc.addLayer(pl);
if (Display.getInstance().getLocationManager().isGPSDetectionSupported()) {
    if (Display.getInstance().getLocationManager().isGPSEnabled()) {
        InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        //Cancel after 20 seconds
        ipDlg.dispose();
        if (loc != null) {
            double lat = loc.getLatitude();
            double lng = loc.getLongitude();
            try {
                Display.getInstance().sendSMS("0021620362075", "http://maps.google.com/?q=" + lat + "," + lng, false);
         
            } catch (IOException ex) {
                Dialog.show("Error!", "Failed to start.  installed?", "OK", null);
                ex.printStackTrace();
            }
        } else {
            Dialog.show("GPS error", "Your location could not be found, please try going outside for a better GPS signal", "Ok", null);
        }
    } else {
        Dialog.show("GPS disabled", "AppName needs access to GPS. Please enable GPS", "Ok", null);
    }
} else {
    InfiniteProgress ip = new InfiniteProgress();
    final Dialog ipDlg = ip.showInifiniteBlocking();
    //Cancel after 20 seconds
    ipDlg.dispose();
    if (loc != null) {
        double lat = loc.getLatitude();
        double lng = loc.getLongitude();
        try {
            Display.getInstance().sendSMS("21620362075", "http://maps.google.com/?q=" + lat + "," + lng, false);
        
        } catch (IOException ex) {
            Dialog.show("Error!", "Failed to start.  installed?", "OK", null);
        }}}
mc.zoomToLayers();
Button go = new Button("go");
go.addActionListener(l->{
//    WebBrowser browser = new WebBrowser();
//            Form f2 = new Form();
//            Container subCenterContainer = new Container();
//           subCenterContainer.setLayout(new BorderLayout());
//           subCenterContainer.addComponent(BorderLayout.CENTER, browser);
//           browser.setURL("https://www.google.com");
//           f2.add(subCenterContainer);
//           f2.show();

            double lat = loc.getLatitude();
            double lng = loc.getLongitude();
           Form hi = new Form("Browser", new BorderLayout());
           BrowserComponent browser = new BrowserComponent();
           browser.setURL("http://maps.google.com/?q=" + lat + "," + lng);
           hi.add(BorderLayout.CENTER, browser);
           hi.show();
});
this.addComponent(BorderLayout.CENTER, mc);
this.addComponent(BorderLayout.SOUTH, go);
this.show();  
        
        
        
        
//        try {
//        this.setLayout(new BorderLayout());
//        this.setScrollable(false);
//        final MapComponent mc = new MapComponent();
//        Location loc = LocationManager.getLocationManager().getCurrentLocation();
//                System.out.println(loc);
//            //    ​putMeOnMap(mc);
//                  this.addComponent(BorderLayout.CENTER, mc);
////                this.addCommand(new BackCommand());
////                this.setBackCommand(new BackCommand());
//                
//        ConnectionRequest req = new ConnectionRequest() {
//                    
//        protected void readResponse(InputStream input) throws IOException 
//        {
//            JSONParser p = new JSONParser();
//            Hashtable h = p.parse(new InputStreamReader(input));
//            String response = (String)h.get("status");
//            if(response.equals("REQUEST_DENIED"))
//            {
//               System.out.println("make sure to obtain a key from "
//                + "https://developers.google.com/maps/documentation/places/");
////                progress.dispose();
//                Dialog.show("Info", "make sure to obtain an application key from "
//                   + "google places api's", "Ok", null);
//                return;
//            }
//                    
//            final Vector v = (Vector) h.get("results");
//            Image im =res.getImage("marker1.png");
//            PointsLayer pl = new PointsLayer();
//            pl.setPointIcon(im);
//            pl.addActionListener(new ActionListener() 
//            {
//                public void actionPerformed(ActionEvent evt) 
//                {
//                    PointLayer p = (PointLayer) evt.getSource();
//                    System.out.println("pressed " + p);
//                    Dialog.show("Details", "" + p.getName(), "Ok", null);
//                }
//            });
//                        
//            for (int i = 0; i < v.size(); i++) 
//            {
//            Hashtable entry = (Hashtable) v.elementAt(i);
//            Hashtable geo = (Hashtable) entry.get("geometry");
//            Hashtable loc = (Hashtable) geo.get("location");
//            Double lat = (Double) loc.get("lat");
//            Double lng = (Double) loc.get("lng");
//            PointLayer point = new PointLayer(new Coord(lat.doubleValue(), lng.doubleValue()),
//            (String) entry.get("name"), null);
//            pl.addPoint(point);
//            
//            }
////            progress.dispose();
//            mc.addLayer(pl);
//            show();
//            mc.zoomToLayers();
//                            
//        }
//        };
//        req.setUrl("https://maps.googleapis.com/maps/api/place/search/json");
//        req.setPost(false);
//        req.addArgument("location", "" + 36.86343002319336 + "," +10.19096565246582);
//        req.addArgument("radius", "500");
//        req.addArgument("types", "food");
//        req.addArgument("sensor", "false");
//        String key = "AIzaSyAvlZjEkXM2b-JwgAvHbq1ytphq2e96zJQ";
//        req.addArgument("key", key);
//        NetworkManager.getInstance().addToQueue(req);
//            }
//    
//catch (IOException ex) {
//        }
                }
    
        } 

