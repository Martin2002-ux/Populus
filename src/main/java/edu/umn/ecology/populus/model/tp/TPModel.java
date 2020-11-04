/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.model.tp;

import edu.umn.ecology.populus.plot.BasicPlotModel;

import java.util.ResourceBundle;

public class TPModel extends BasicPlotModel {
    static ResourceBundle res = ResourceBundle.getBundle("edu.umn.ecology.populus.model.tp.Res");

    @Override
    public Object getModelHelpText() {
        return "TPHELP";
    }

    public TPModel() {
        this.setModelInput(new TPPanel());
    }

    public static String getModelName() {
        return res.getString("Bacterial");
    }

    @Override
    protected String getHelpId() {
        return "TP.overview";
    }
}
