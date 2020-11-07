/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.model.aidsbasic;

import edu.umn.ecology.populus.constants.ColorScheme;
import edu.umn.ecology.populus.math.Integrator;
import edu.umn.ecology.populus.plot.BasicPlot;
import edu.umn.ecology.populus.plot.BasicPlotInfo;

public class AIDSBASICParamInfo implements BasicPlot {

    public static final int vsT = 0;//x, y, v vs t
    public static final int xyvsT = 1;//x, y vs t
    public static final int vvsT = 2;//v vs t
    public static final int xvsYvsV = 3; //x vs y vs v
    final String mCapNvsT = "Viral Dynamics";
    final int plotType;
    final int numVars;

    final Integrator ig;
    String mCapN2vsN1 = mCapNvsT;
    final String xCap = "Time (<i>t</i>)";
    final double x;
    final double y;
    final double v;
    final double time;
    double k;
    double u;
    String yCap2 = "Host_Population" + "<b><i>" + ColorScheme.getColorString(0) + "N </font></i></b>, <b><i>" + ColorScheme.getColorString(1) + "S </font></i></b>, <b><i>" + ColorScheme.getColorString(2) + "I </font>" + "</i></b> )";


    @Override
    public BasicPlotInfo getBasicPlotInfo() {
        BasicPlotInfo bp = null;
        double[][][] points;
        double[] xlist;
        double[][] ylists;
        double[] initialConditions = new double[3];
        initialConditions[0] = x;
        initialConditions[1] = y;
        initialConditions[2] = v;
        if (time < 0) {
            ig.record.ss = true;
            ig.record.interval = false;
        }
        ig.doIntegration(initialConditions, 0.0, time);
        xlist = ig.getX();
        ylists = ig.getY();
		/*totalPop = new double[size];
      for(int i=0; i<totalPop.length; i++)
         for(int j=0; j<ylists.length; j++)
            totalPop[i] += ylists[j][i];
		 */
		switch(plotType){
		case vsT:
			points = new double[3][2][];
			points[0][0] = xlist;
			points[0][1] = ylists[0];
			points[1][0] = xlist;
			points[1][1] = ylists[1];
			points[2][0] = xlist;
			points[2][1] = ylists[2];

			String caption = "<b><i>" + ColorScheme.getColorString( 0 ) + "x </font></i></b>"+", "+"<b><i>" + ColorScheme.getColorString( 1 ) + "y</font></i></b>"+", "+"<b><i>" + ColorScheme.getColorString( 2 ) + "v</font></i></b>";
			bp = new BasicPlotInfo( points, mCapNvsT, xCap, caption );
			bp.vsTimeChars = new String[] {
					"x", "y", "v"
			};
			bp.isLogPlot =true;
			break;
		case xyvsT:
			points = new double[2][2][];
			points[0][0] = xlist;
			points[0][1] = ylists[0];
			points[1][0] = xlist;
			points[1][1] = ylists[1];
			String caption2 = "<b><i>" + ColorScheme.getColorString( 0 ) + "x </font></i></b>"+", "+"<b><i>" + ColorScheme.getColorString( 1 ) + "y</font></i></b>";
			bp = new BasicPlotInfo( points, mCapNvsT, xCap, caption2 );
			break;
		case vvsT:
			points = new double[1][2][];
			points[0][0] = xlist;
			points[0][1] = ylists[2];
			String caption3 = "v";
			bp = new BasicPlotInfo( points, mCapNvsT, xCap, caption3 );
			break;


		}
		return bp;
	}

    public AIDSBASICParamInfo(int plotType, double time, /*time < 0 for steady state*/ double X, double Y, double V, double lambda, double d, double k, double a, double beta, double u) {
        ig = new Integrator(new AIDSBASICDeriv(lambda, d, k, a, beta, u));
        this.x = X;
        this.y = Y;
        this.v = V;

        this.time = time;
        this.plotType = plotType;
        numVars = 3;
    }
}
