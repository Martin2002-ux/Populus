/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.model.cp;

import edu.umn.ecology.populus.model.appd.APPD3DProtoParamInfo;

import java.util.ResourceBundle;

public class CP3DParamInfo extends APPD3DProtoParamInfo {
    ResourceBundle res = ResourceBundle.getBundle("edu.umn.ecology.populus.model.cp.Res");

    public CP3DParamInfo(double N0, double P0, double Q0, double l, double a1, double a2, double k1, double k2, int gens, boolean vsTime) {
        discProc = new CPProc(l, a1, a2, k1, k2);
        this.gens = gens;
        plotType = vsTime ? NvsT : NvsN;
        initialConditions = new double[]{
                N0, P0, Q0
        };
        mainCaption = res.getString("Discrete_Predator");
        xCaption = res.getString("Predator_2_Q_");
        yCaption = res.getString("Prey_N_");
        zCaption = res.getString("Predator_1_P_");
    }
}
