/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.model.sspg;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import edu.umn.ecology.populus.constants.ColorScheme;
import edu.umn.ecology.populus.math.Routines;
import edu.umn.ecology.populus.plot.BasicPlot;
import edu.umn.ecology.populus.plot.BasicPlotInfo;
import edu.umn.ecology.populus.plot.TableInterface;
import edu.umn.ecology.populus.resultwindow.BadUserException;
import edu.umn.ecology.populus.visual.HTMLLabel;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class SSPGParamInfo implements BasicPlot, TableInterface {
    final double[][] mat;
    final int type, gens, whichToView;
    final double[] initialPops;
    double[][] result;

    double[][] run() {
        double[][] populations = new double[gens + 1][mat.length];
        populations[0] = initialPops;

        for (int i = 0; i < gens; i++) {
            populations[i + 1] = Routines.multiplyMatricies(mat, populations[i]);
        }

        return populations;
    }


    public SSPGParamInfo(double[][] mat, double[] pops, int type, int gens, int whichToView) throws BadUserException {
        this.mat = mat;
        this.type = type;
        this.initialPops = pops;
        this.gens = gens;
        this.whichToView = whichToView;
    }

    @Override
    public BasicPlotInfo getBasicPlotInfo() {
        double[][][] points;
        BasicPlotInfo returnValue = null;
        result = run();


        double[] snx = new double[gens + 1];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++)
                snx[i] += result[i][j];
        }

        switch (type) {
            case SSPGPanel.kLambda -> {
                points = new double[1][2][gens];
                for (int i = 1; i < result.length; i++) {
                    points[0][0][i - 1] = i;
                    points[0][1][i - 1] = snx[i] == 0 || snx[i - 1] == 0 ? 0 : snx[i] / snx[i - 1];
                }
                returnValue = new BasicPlotInfo(points, "\u03bb vs <i>t</i>", "Generations", "\u03bb");
            }
            case SSPGPanel.kSNX -> {
                points = new double[1][2][gens + 1];
                for (int i = 0; i < result.length; i++) {
                    points[0][0][i] = i;
                    points[0][1][i] = snx[i];
                }
                returnValue = new BasicPlotInfo(points, "\u03a3<i>N<sub>x</> vs <i>t</i>", "Generations", "\u03a3<i>N<sub>x</>");
            }
            case SSPGPanel.kNXSNXT -> {
                points = new double[1][2][gens + 1];
                for (int i = 0; i < result.length; i++) {
                    points[0][0][i] = i;
                    points[0][1][i] = snx[i] == 0 || result[i][whichToView - 1] == 0 ? 0 : result[i][whichToView - 1] / snx[i];
                }
                String yCap = "<i>N<sub>x</>/\u03a3<i>N<sub>x</>";
                yCap += " ( " + ColorScheme.getColorString(0) + whichToView + "</>)  ";
                returnValue = new BasicPlotInfo(points, "<i>N<sub>x</>/\u03a3<i>N<sub>x</> vs <i>t</i>", "Generations", yCap);
            }
            case SSPGPanel.kNXSNXX -> {
                points = new double[1][2][result[0].length];
                for (int i = 0; i < result[0].length; i++) {
                    points[0][0][i] = i + 1;
                    points[0][1][i] = result[gens][i] / snx[gens];
                }
                returnValue = new BasicPlotInfo(points, "<i>N<sub>x</>/\u03a3<i>N<sub>x</> vs x (<i>t</i> = " + gens + ")", "Stage", "<i>N<sub>x</>/\u03a3<i>N<sub>x</>");
            }
            case SSPGPanel.kXSNXT -> {
                points = new double[gens + 1][3][result[0].length];
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[0].length; j++) {
                        points[i][0][j] = j + 1;
                        points[i][1][j] = snx[i] == 0 || result[i][j] == 0 ? 0 : result[i][j] / snx[i];
                        points[i][2][j] = i;
                    }
                }
                returnValue = new BasicPlotInfo(points, "x vs <i>N<sub>x</sub></i>/\u03a3<i>N<sub>x</sub></i> vs <i>t</i>", "Stage Index", "<i>N<sub>x</>/\u03a3<i>N<sub>x</sub></i>");
                returnValue.setZCaption("Generations");
                returnValue.setGraphType(BasicPlotInfo.k3D);
                returnValue.setLabelsT(false);
                returnValue.set3DIsDiscrete(true, true);
                returnValue.setIsFrequencies(true);
            }
            case SSPGPanel.kXNXT -> {
                points = new double[gens + 1][3][result[0].length];
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[0].length; j++) {
                        points[i][0][j] = j + 1;
                        points[i][1][j] = result[i][j];
                        points[i][2][j] = i;
                    }
                }
                returnValue = new BasicPlotInfo(points, "x vs <i>N<sub>x</sub></i> vs t", "Stage Index", "<i>N<sub>x</sub></i>");
                returnValue.setZCaption("Generations");
                returnValue.setGraphType(BasicPlotInfo.k3D);
                returnValue.setLabelsT(false);
                returnValue.set3DIsDiscrete(true, true);
            }
            case SSPGPanel.kEIGEN -> {
                Matrix m = Matrix.constructWithCopy(mat);
                EigenvalueDecomposition ed = m.eig();
                returnValue = new BasicPlotInfo(ed);
            }
            case SSPGPanel.kTABOUT -> returnValue = new BasicPlotInfo(this);
        }

        if (type != SSPGPanel.kTABOUT) {
            returnValue.setLineStyle(BasicPlotInfo.DASHED);
            returnValue.setLineColor(0, Color.black);
            returnValue.setSymbolStyle(0, BasicPlotInfo.DOTS);
            returnValue.setIsDiscrete(true);
        }
        return returnValue;
    }

    @Override
    public double[][] getMatrix() {
        double[][] newMatrix;
        double tempNum;
        int tempTrunked;

        newMatrix = new double[result.length][result[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                tempNum = result[i][j];
                if (tempNum < Math.pow(2, 32) / 2500) {
                    tempTrunked = (int) (tempNum * 1000);
                    tempNum = tempTrunked;
                    tempNum /= 1000;
                    if ((result[i][j] - tempNum) >= 0.0005) tempNum += 0.001;
                }
                newMatrix[i][j] = tempNum;
            }
        }

        return newMatrix;
    }

    @Override
    public JComponent[] getMatrixLabels() {
        final int i = getXEnd() - getXStart();
        JComponent[] temp = new JComponent[i];
        for (int j = 0; j < i; j++) {
            temp[j] = new HTMLLabel("N<sub>x</sub>(" + j + ") ");
        }
        return temp;
    }

    @Override
    public String[] getTitle() {
        return new String[]{"Tabular Projection"};
    }

    @Override
    public int getXStart() {
        return 0;
    }

    @Override
    public int getYStart() {
        return 0;
    }

    @Override
    public int getXEnd() {
        return getMatrix().length;
    }

    @Override
    public int getYEnd() {
        return getMatrix()[0].length;
    }
}

