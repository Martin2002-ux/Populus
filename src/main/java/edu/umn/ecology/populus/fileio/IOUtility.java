/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.fileio;

import edu.umn.ecology.populus.core.DesktopWindow;
import edu.umn.ecology.populus.core.PopPreferencesStorage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URI;

public class IOUtility {
    public static Exception exception = null;

    static class ExtensionFileFilter implements java.io.FileFilter {
        private final String ext;

        ExtensionFileFilter(String str) {
            this.ext = str;
        }

        @Override
        public boolean accept(File f) {
            String basename = f.getName();
            return basename.endsWith(ext);
        }
    }

    /**
     * returns a String with the file name, or null if cancelled
     *
     * @param type Use either FileDialog.SAVE, or FileDialog.LOAD
     */
    //TODO Lars - Need to verify
    public static String getFileName(String baseName, String extension, String title, int type) {
        //Find first name of form  baseName + int + extension, where int starts with 1 and goes until
        // we don't have a file like that.
        String fullName = baseName + "1" + extension;
        {
            File dir = new File(PopPreferencesStorage.getDirectory());
            if (dir.isDirectory()) {
                File[] files = dir.listFiles(new ExtensionFileFilter(extension));
                int index = 0;
                boolean fileExists = true;
                do {
                    index++;
                    fileExists = false;
                    for (File file : files) {
                        if (file.getName().equals(baseName + index + extension)) {
                            fileExists = true;
                            break;
                        }
                    }
                } while (fileExists);
                fullName = baseName + index + extension;
            }
        }
        return getFileName(fullName, title, type);
    }

    /**
     * returns a String with the file name, or null if cancelled
     *
     * @param type Use either FileDialog.SAVE, or FileDialog.LOAD
     */
    public static String getFileName(String defaultName, String title, int type) {
        String filename = null;
        if (PopPreferencesStorage.isUseAWTFileDialog()) {
            FileDialog fd;

            //get filename
            fd = new FileDialog(DesktopWindow.defaultWindow, title, type);
            fd.setDirectory(PopPreferencesStorage.getDirectory());
            fd.setFile(defaultName);

            //get user input
            Point pos = DesktopWindow.defaultWindow.getLocationOnScreen();

			/*
         Dimension d0 = DesktopWindow.defaultWindow.getSize();
         fd.validate();
         Dimension d1 = fd.getPreferredSize();
         edu.umn.ecology.populus.fileio.Logging.log(pos);
         edu.umn.ecology.populus.fileio.Logging.log(d0);
         edu.umn.ecology.populus.fileio.Logging.log(d1);
			 */
            fd.setVisible(true);
            fd.setLocation(pos.x + 20, pos.y + 40);
            fd.setResizable(true);
            filename = fd.getFile();
        } else {
            JFileChooser fc;
            int state;

            //get filename
            fc = new JFileChooser(PopPreferencesStorage.getDirectory());
            fc.setSelectedFile(new File(fc.getCurrentDirectory(), defaultName));
            fc.setFileFilter(new BasicFilenameFilter(defaultName.substring(1 + defaultName.indexOf('.'))));

            //get user input
            if (type == FileDialog.SAVE) {
                state = fc.showSaveDialog(DesktopWindow.defaultWindow);
            } else {
                state = fc.showOpenDialog(DesktopWindow.defaultWindow);
            }
            if (state == JFileChooser.APPROVE_OPTION) {
                filename = fc.getSelectedFile().toString();
                PopPreferencesStorage.setDirectory(fc.getCurrentDirectory().toString());
            }
        }
        return filename;
    }

    /**
     * Converts File to URI.  Returns defaultStr if error.
     */
    public static String convertPathToURI(String fileStr, String defaultStr) {
        try {
            String uriText = new File(fileStr).toURI().toString();
            //Hack - convert file:/blah to file:///blah
            //This is because Desktop API on Mac and Windows doesn't like the single slash.
            //See http://stackoverflow.com/questions/1131273/java-file-touri-tourl-on-windows-file
            if (uriText.startsWith("file:/") && !uriText.startsWith("file://")) {
                uriText = uriText.replaceFirst("file:", "file://");
            }
            Logging.log("File " + fileStr + " converted to URI " + uriText);
            return uriText;
        } catch (Exception e) {
            Logging.log("Cannot convert " + fileStr + " to URI");
            return defaultStr;
        }
    }

    /**
     * Converts URI to File.  Returns defaultStr if error.
     */
    public static String convertURIToPath(String uriStr, String defaultStr) {
        try {
            String pathText = new File(new URI(uriStr)).getAbsolutePath();
            Logging.log("URI " + uriStr + " converted to File " + pathText);
            return pathText;
        } catch (Exception e) {
            Logging.log("Cannot convert " + uriStr + " to file");
            return defaultStr;
        }
    }
}
