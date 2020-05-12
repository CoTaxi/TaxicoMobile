/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.colis;


import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Form;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class combo extends Form
{

    public combo()   
    { 
    
    final DefaultListModel<String> options = new DefaultListModel<>();
 AutoCompleteTextField adresse = new AutoCompleteTextField(options ) {
     @Override
     protected boolean filter(String text) {
         if(text.length() == 0) {
             return false;
         }
         String[] l = searchLocations(text);
         if(l == null || l.length == 0) {
             return false;
         }
         options.removeAll();
         for(String s : l) {
             options.addItem(s);
         }
         return true;
     }
          String[] searchLocations(String text) {
    try {
        if(text.length() > 0) {
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json");
            r.addArgument("key","AIzaSyAvlZjEkXM2b-JwgAvHbq1ytphq2e96zJQ");
            r.addArgument("input", "tunisia");
            NetworkManager.getInstance().addToQueueAndWait(r);
            Map<String,Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            String[] res = Result.fromContent(result).getAsStringArray("//description");
            return res;
        }
    } catch(Exception err) {
        Log.e(err);
    }
    return null;
}
 };
 adresse.setMinimumElementsShownInPopup(5);
 AutoCompleteTextField name = new AutoCompleteTextField("chasse des vertebres ", "chasse des mollusques", "chasse des amphibiens", "chasse des reptiles" , " nom saison ");
name.setMinimumElementsShownInPopup(5);
this.addAll(adresse,name);
}


}