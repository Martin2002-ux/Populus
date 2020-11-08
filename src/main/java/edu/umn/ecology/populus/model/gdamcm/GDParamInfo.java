/******************************************************************************
 Copyright (c) 2015 Regents of the University of Minnesota.

 This software is released under GNU General Public License 2.0
 http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */
package edu.umn.ecology.populus.model.gdamcm;

import edu.umn.ecology.populus.math.Routines;
import edu.umn.ecology.populus.plot.BasicPlot;
import edu.umn.ecology.populus.plot.BasicPlotInfo;
import edu.umn.ecology.populus.poproutines.Population;

import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * It is assumed to distribute the initial array in the p^2, 2pq, q^2
 * ratios.
 * Also, each locus is independent from the others.
 */

public class GDParamInfo implements BasicPlot {
    final String xCap = res.getString("Generations_b_i_t_");
    final String yCap = res.getString("Allelic_Frequency_b_i");
    final String mCap = res.getString("Genetic_Drift");
    final String xMCap = "Proportion of  Populations (<i>t</i> = ";
    final String yMCap = "Frequency of \"A\" Alleles";
    final String mMCap = "Genetic Drift (Markov Model)";
    static final ResourceBundle res = ResourceBundle.getBundle("edu.umn.ecology.populus.model.gdamcm.Res");
    private boolean isMarkov = false;
    private boolean is3D = false;
    private boolean selfing;
    private double[] lociFreqs;
    private int numAGenes, popSize;
    private int gens, N, loci;
    private Population[] populations;
    double[][] matrix;
    double[] freqs;
    double[] xValues;
    int generation = 0;
    String postMessage = null;

    private double fact(double n) {
        if (n == 0) {
            return 1;
        } else {
            return (int) n * fact(n - 1);
        }
    }

    public BasicPlotInfo getMonteCarloData() {
        BasicPlotInfo bpi;
        boolean fixed = true;
        int fixTime = 0;
        for (int i = 0; i < loci; i++)
            populations[i] = new Population(N, lociFreqs[i], selfing);

        double[][][] points = new double[loci][2][];
        double[] xArray = new double[gens + 1];
        for (int j = 0; j <= gens; j++) {
            xArray[j] = j;
        }
        for (int i = 0; i < loci; i++) {
            points[i][0] = xArray;
            points[i][1] = new double[xArray.length];
        }

        int genNo = 0;
        for (int j = 0; j < loci; j++) {
            genNo = 1;
            points[j][1][0] = populations[j].getPFreq();
            while (genNo <= gens) {
                populations[j] = populations[j].breed();
                points[j][1][genNo] = populations[j].getPFreq();
                if (populations[j].isFixed() != -1) {
                    break;
                }
                genNo++;
            }

            //change size if necessary
            if (populations[j].isFixed() != -1) {
                points[j][0] = Routines.shrinkDoubleArray(points[j][0], genNo + 1);
                points[j][1] = Routines.shrinkDoubleArray(points[j][1], genNo + 1);
                fixTime = Math.max(fixTime, genNo);
            } else {
                fixed = false;
            }
        }

        bpi = new BasicPlotInfo(points, mCap, xCap, yCap);
        if (fixed) {
            bpi.setPostMessage("All loci have fixed by t = " + (fixTime + 1));
        }
        bpi.setDefaultAxis(false);
        bpi.setIsFrequencies(true);
        bpi.vsTimeChars = new String[]{
                res.getString("Loci_1"), res.getString("Loci_2"),
                res.getString("Loci_3"), res.getString("Loci_4"),
                res.getString("Loci_5"), res.getString("Loci_6"),
                res.getString("Loci_7"), res.getString("Loci_8"),
                res.getString("Loci_9"), res.getString("Loci_10")
        };
        bpi.setStartGridded(true);
        return bpi;
    }

    @Override
    public BasicPlotInfo getBasicPlotInfo() {
        postMessage = null;
        if (isMarkov)
            return getMarkovData();
        else
            return getMonteCarloData();
    }

    public void incGenerations() {
        gens++;
    }

    public BasicPlotInfo getMarkovData() {
        BasicPlotInfo bpi = null;
        double[][][] points;
        if (!is3D) {
            if (generation != 0)
                freqs = Routines.multiplyMatricies(freqs, matrix);

            points = new double[1][2][];
            points[0][0] = xValues;
            points[0][1] = freqs;
            bpi = new BasicPlotInfo(points, mMCap, yMCap, xMCap + generation + ")  ");
            bpi.setIsFrequencies(true);
            generation++;
            bpi.isBarChart = true;
        } else {
            points = new double[gens + 1][3][];
            double[] zValues;
            freqs = new double[2 * popSize + 1];
            freqs[numAGenes % (2 * popSize + 1)] = 1.0;
            for (int i = 0; i < points.length; i++) {
                if (i != 0)
                    freqs = Routines.multiplyMatricies(freqs, matrix);
                zValues = new double[xValues.length];
                Arrays.fill(zValues, i);
                points[i][0] = xValues;
                points[i][1] = freqs;
                points[i][2] = zValues;
            }

            bpi = new BasicPlotInfo(points, "Markov Model 3D", "Frequency State", "Frequency", "Generation");
            bpi.setLabelsT(false);
            bpi.set3DIsDiscrete(true, false);
            bpi.setXMax(1.0);
            bpi.setYMax(1.0);
        }
        bpi.setStartGridded(true);
        return bpi;
    }

    public GDParamInfo(int gens, boolean selfing, int N, double[] lociFreqs) {
        this.gens = gens;
        this.loci = lociFreqs.length;
        this.populations = new Population[loci];
        this.selfing = selfing;
        this.N = N;
        this.lociFreqs = lociFreqs;
    }

    public GDParamInfo(int numAGenes, int popSize, boolean view3D, int gensToView) {
        double a, b, c;
        matrix = new double[2 * popSize + 1][2 * popSize + 1];
        freqs = new double[2 * popSize + 1];
        isMarkov = true;
        is3D = view3D;
        gens = gensToView;
        this.numAGenes = numAGenes;
        this.popSize = popSize;

        //initialize the frequency array
        freqs[numAGenes % (2 * popSize + 1)] = 1.0;

        //transition matrix for Markov model
        for (double i = 0; i < matrix.length; i++) {
            for (double j = 0; j < matrix[0].length; j++) {
                a = fact(2d * popSize) / (fact(2d * popSize - j) * fact(j));
                b = Math.pow(i / (2d * popSize), j);
                c = Math.pow(1d - i / (2d * popSize), 2d * popSize - j);
                matrix[(int) i][(int) j] = a * b * c;
            }
        }

        //initialize the values on the x-axis
        double[] xlist = new double[2 * popSize + 1];
        for (double i = 0; i < xlist.length; i++)
            xlist[(int) i] = i / (2.0 * popSize);
        xValues = xlist;
    }
}
