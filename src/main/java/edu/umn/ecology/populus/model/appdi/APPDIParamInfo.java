/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.model.appdi;

import edu.umn.ecology.populus.constants.ColorScheme;
import edu.umn.ecology.populus.model.appd.APPDProtoParamInfo;

import java.util.ResourceBundle;

public class APPDIParamInfo extends APPDProtoParamInfo {
    public static final int DDGrowth = 1; //one of these supposed to be 0?
    public static final int DIGrowth = 1;
    protected int modelType = 0;
    final ResourceBundle res = ResourceBundle.getBundle("edu.umn.ecology.populus.model.appdi.Res");

    public APPDIParamInfo(double n0, double p0, double l, double a, double b, double c, double T_, double Tw, double m, double Q, int gens, boolean linear, boolean vsTime) {
        discProc = new APPDIProc(linear, l, a, b, c, T_, Tw, m, Q);
        this.gens = gens;
        plotType = vsTime ? NvsT : NvsN;
        modelType = linear ? DIGrowth : DDGrowth;
        initialConditions = new double[]{
                n0, p0
        };
        mainCaption = res.getString("Discrete_Predator");
        xCaption = vsTime ? res.getString("Time_i_t_i_") : "<i> N </i>";
        yCaption = vsTime ? "<i>" + ColorScheme.getColorString(0) + " N</>, " + ColorScheme.getColorString(1) + "<i>P</>" : ColorScheme.getColorString(1) + "<i>P</>";
    }
}
