/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.forms.forum;

import com.codename1.components.Accordion;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ASUS
 */
public class AjoutForum extends Form
{

    public AjoutForum() 
    {
          super("Accordion", new BoxLayout(BoxLayout.Y_AXIS));
this.setScrollableY(true);
Accordion accr = new Accordion();
accr.addContent("Item1", new SpanLabel("The quick brown fox jumps over the lazy dog\n"
        + "The quick brown fox jumps over the lazy dog"));
accr.addContent("Item2", new SpanLabel("The quick brown fox jumps over the lazy dog\n"
        + "The quick brown fox jumps over the lazy dog\n "
        + "The quick brown fox jumps over the lazy dog\n "
        + "The quick brown fox jumps over the lazy dog\n "
        + ""));

accr.addContent("Item3", BoxLayout.encloseY(new Label("Label"), new TextField(), new Button("Button"), new CheckBox("CheckBox")));

this.add(accr);
this.show();  
    }
    

}
