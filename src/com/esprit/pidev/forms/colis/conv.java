/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.colis;

import com.codename1.capture.Capture;
import com.codename1.components.SpanLabel;
import com.codename1.system.NativeLookup;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.pidev.models.User;
import com.esprit.pidev.services.UserService;
import com.esprit.pidev.utils.Statics;
import com.mycompany.myapp.Forms.BaseForm;
import com.mycompany.myapp.Forms.Chat;
import com.mycompany.myapp.Forms.TTS;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class conv extends BaseForm
{
    private String userName;
    private Image userPicture,AgentPicture;
    private Form current;
    private TTS tts;
    private Resources theme,theme1;
    public conv() 
    {
        super("Chat", BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        theme1 = UIManager.initFirstTheme("/theme_2");
        // Enable Toolbar on all Forms by default

        userPicture = theme1.getImage("profile-pic.jpg");
        int height = Display.getInstance().convertToPixels(9f);
        int width = Display.getInstance().convertToPixels(10f);
        AgentPicture = theme1.getImage("logo.png").fill(width, height);
        tts = (TTS)NativeLookup.create(TTS.class);
         showSbaitso();
    }
    
    
    
       private DataChangedListener createSearchListener(final TextField searchField, final Container discussion, final Button ask) {
        return (type, index) -> {
            String t = searchField.getText();
            int count = discussion.getComponentCount();
            if(t.length() == 0) {
                ask.setEnabled(true);
                for(Component c : discussion) {
                    c.setHidden(false);
                    c.setVisible(true);
                }
                animateChanage(discussion);
                return;
            }
            t = t.toLowerCase();
            ask.setEnabled(false);
            for(Component c : discussion) {
                SpanLabel tt = (SpanLabel)c;
                if(tt.getText().toLowerCase().indexOf(t) < 0) {
                    tt.setHidden(true);
                    tt.setVisible(false);
                } else {
                    tt.setHidden(false);
                    tt.setVisible(true);
                }
            }
            animateChanage(discussion);
        };        
    }
       private boolean animateLock;
    void animateChanage(Container discussion) {
        if(!animateLock) {
            animateLock = true;
            discussion.animateLayoutAndWait(300);
            animateLock = false;
        }
    }
    
    void showSbaitso() {
        Form sb = new Form(new BorderLayout());
        sb.setFormBottomPaddingEditingMode(true);
        Toolbar t = sb.getToolbar();
        setToolbar(t);
        getContentPane().setScrollVisible(false);
        
        super.installSidemenu(theme1);
        final TextField searchField = new TextField("", "Search For Answers...", 20, TextField.ANY);
        t.setTitleComponent(searchField);
        final TextField ask = new TextField("", "Ask The Agent.", 20, TextField.ANY);
        ask.getStyle().setFgColor(0x000000);
        Button askButton = new Button("Ask");
        final Container discussion = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        sb.add(BorderLayout.SOUTH, BorderLayout.center(ask).
                    add(BorderLayout.EAST, askButton)).
                add(BorderLayout.CENTER, discussion);
        
        discussion.setScrollableY(true);

        sb.show();
        Display.getInstance().callSerially(() -> {
            
        ArrayList<User> us = new UserService().lastcnx(Statics.sessionID);
            String w = "HELLO " + us.get(0).getUsername() +", MY NAME IS Agent M From TaxiCo Team.\n\nI AM HERE TO HELP YOU.\n" +
                    "SAY WHATEVER IS IN YOUR MIND FREELY," +
                    "OUR CONVERSATION WILL BE KEPT IN STRICT CONFIDENCE.\n" +
                    "MEMORY CONTENTS WILL BE WIPED OFF AFTER YOU LEAVE.";
            say(discussion, w, false);
            if(tts != null && tts.isSupported()) {
                tts.say(w);
            }
        });
        searchField.addDataChangeListener(createSearchListener(searchField, discussion, askButton));
        searchField.getStyle().setFgColor(0x000000);
        ActionListener askEvent = (e) -> {
            String t1 = ask.getText();
            if (t1.length() > 0) {
                ask.setText("");
                say(discussion, t1, true);
                answer(t1, discussion);
            }
        };
        ask.setDoneListener(askEvent);
        askButton.addActionListener(askEvent);
    }
    
    void answer(String question, Container dest) {
        String resp = Chat.getResponse(question);
        say(dest, resp, false);
        if(tts != null && tts.isSupported()) {
            tts.say(resp);
        }
    }
    
    void say(Container destination, String text, boolean question) {
        SpanLabel t = new SpanLabel(text);
        t.setWidth(destination.getWidth());
        t.setX(0);
        t.setHeight(t.getPreferredH());
        
        if(question) {
            t.setY(Display.getInstance().getDisplayHeight());
            t.setTextUIID("BubbleUser");
            t.setIconPosition(BorderLayout.EAST);
            t.setIcon(userPicture);
            t.setTextBlockAlign(Component.RIGHT);
        } else {
            t.setY(0);
            t.setTextUIID("BubbleSbaitso");
            t.setIconPosition(BorderLayout.WEST);
            t.setIcon(AgentPicture);
            t.setTextBlockAlign(Component.LEFT);
        }
        destination.add(t);
        destination.animateLayoutAndWait(400);
        destination.scrollComponentToVisible(t);
    }
    
    private Image roundImage(Image img) {
        int width = img.getWidth();
        Image roundMask = Image.createImage(width, width, 0xff000000);
        Graphics gr = roundMask.getGraphics();
        gr.setColor(0xffffff);
        gr.fillArc(0, 0, width, width, 0, 360);
        Object mask = roundMask.createMask();
        img = img.applyMask(mask);
        return img;
    }
    
    private Image captureRoundImage() {
        try {
            int width = userPicture.getWidth();
            String result = Capture.capturePhoto(width, -1);
            if(result == null) {
                return userPicture;
            }
            Image capturedImage = Image.createImage(result);
            if(capturedImage.getHeight() != width) {
                if(capturedImage.getWidth() < capturedImage.getHeight()) {
                    capturedImage = capturedImage.subImage(0, capturedImage.getHeight() / 2 - width / 2, width, width, false);
                } else {
                    Image n = Image.createImage(width, width);
                    n.getGraphics().drawImage(capturedImage, 0, width / 2- capturedImage.getHeight() / 2);
                    capturedImage = n;
                }
            }
            return roundImage(capturedImage);
        } catch (IOException err) {
            err.printStackTrace();
            return userPicture;
        }
    }
}
