/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
//Title:        Populus
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Lars Roe, under Don Alstad
//Company:      University of Minnesota
//Description:  First Attempt at using Java 1.2
//with Populus
package edu.umn.ecology.populus.visual;

import javax.swing.*;
import java.awt.*;

/**
 * This class implements the HTMLLabel utility for formatting the text
 * for radio buttons.
 */

public class StyledRadioButton extends JRadioButton {
    /**
     *
     */
    private static final long serialVersionUID = -4655637662372692469L;
    //Vector v;
    private String formattedText = "";

    public StyledRadioButton() {
		/*
               if (edu.umn.ecology.populus.core.Version.isDev()) {
          Object[] localUIDefaults1 = new String[]{"StyledRadioButtonUI",
             "edu.umn.ecology.populus.visual.StyledRadioButtonUI"};
          UIManager.getLookAndFeelDefaults().putDefaults(localUIDefaults1);
               }
		 */
    }

    //Try using a clear method instead of 'new'ing the StringBuffer
    @Override
    public void setText(String text) {
        super.setText(Utilities.PopHTMLToSwingHTML(text));
    }

    public String getFormattedText() {
        return formattedText;
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        //This should make the font look disabled, similar to HTMLLabel
        Color currentColor = Color.gray;
        if (b) {
            currentColor = (Color) UIManager.get("Label.foreground");
        }
        this.setForeground(currentColor);
    }

    /**
     * This variable is true when used in an actual application instead of an
     * IDE.
     private static boolean inited = false;

     static {
     inited = true;
     }
     static {
     Object[] localUIDefaults1 = new String[]{"StyledRadioButtonUI",
     "edu.umn.ecology.populus.visual.StyledRadioButtonUI"};
     UIManager.getLookAndFeelDefaults().putDefaults(localUIDefaults1);
     }
     */

    //If we wanted to use Swing's HTML functionality, we need to uncomment this line
    // and add code to parse into true HTML code.
	/*
    private final String uiClassID = "StyledRadioButtonUI";
    public String getUIClassID() {
        return uiClassID;
    }
	 */

}
