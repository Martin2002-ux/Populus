/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.model.sgfac;

import edu.umn.ecology.populus.plot.BasicPlot;
import edu.umn.ecology.populus.plot.BasicPlotInputPanel;
import edu.umn.ecology.populus.visual.ppfield.PopulusParameterField;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class SGFACPanel extends BasicPlotInputPanel {
    /**
     *
     */
    private static final long serialVersionUID = 3113318822291660775L;
    private final JPanel fitnessesP = new JPanel();
    private final JPanel pP = new JPanel();
    private final PopulusParameterField sPPF = new PopulusParameterField();
    private final PopulusParameterField h2PPF = new PopulusParameterField();
    private final PopulusParameterField h1PPF = new PopulusParameterField();
    private final PopulusParameterField gPPF = new PopulusParameterField();
    private final GridBagLayout gridBagLayout1 = new GridBagLayout();
    private final GridBagLayout gridBagLayout2 = new GridBagLayout();
    private final GridBagLayout gridBagLayout3 = new GridBagLayout();
    private TitledBorder titledBorder1;
    private TitledBorder titledBorder2;
    private final PopulusParameterField gensPPF = new PopulusParameterField();
    private final JRadioButton freqRB = new JRadioButton();
    private final JRadioButton locHetRB = new JRadioButton();
    private final JRadioButton hetAdRB = new JRadioButton();
    private final JRadioButton gradRB = new JRadioButton();
    private final ButtonGroup plotTypeBG = new ButtonGroup();
    SGFACParamInfo pi = null;
    boolean isIterate = false;

    @Override
    public BasicPlot getPlotParamInfo() {
        if (pi == null || !isIterate) {
            int plotType;

            if (gradRB.isSelected())
                plotType = SGFACParamInfo.GRAD;
            else if (hetAdRB.isSelected())
                plotType = SGFACParamInfo.HETA;
            else if (locHetRB.isSelected())
                plotType = SGFACParamInfo.LOCH;
            else
                plotType = SGFACParamInfo.FREQ;

            pi = new SGFACParamInfo(sPPF.getDouble(), gPPF.getDouble(), h1PPF.getDouble(), h2PPF.getDouble(), plotType);
        } else {
            for (int i = 0; i < gensPPF.getInt(); i++)
                pi.doGeneration();
        }

        isIterate = false;
        return pi;
    }

    @Override
    public void willIterate(boolean isIterate) {
        this.isIterate = isIterate;
    }

    public SGFACPanel() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {//this is needed because w/o the title doesn't fit
        titledBorder1 = new TitledBorder("");
        titledBorder2 = new TitledBorder("");
        sPPF.setParameterName("<i>s</i>");
        sPPF.setMaxValue(1.0);
        sPPF.setIncrementAmount(0.05);
        sPPF.setDefaultValue(0.9);
        sPPF.setCurrentValue(0.9);
        sPPF.setHelpText("Intensity of selection changes across the gradient");
        h2PPF.setCurrentValue(0.1);
        h2PPF.setDefaultValue(0.1);
        h2PPF.setEnabled(false);
        h2PPF.setIncrementAmount(0.05);
        h2PPF.setMaxValue(1.0);
        h2PPF.setParameterName("<i>h<sub>2</>");
        h2PPF.setHelpText("Constant advantage held by heterozygotes over whichever homozygote is locally most fit");
        h1PPF.setCurrentValue(0.1);
        h1PPF.setDefaultValue(0.1);
        h1PPF.setEnabled(false);
        h1PPF.setIncrementAmount(0.05);
        h1PPF.setMaxValue(1.0);
        h1PPF.setParameterName("<i>h<sub>1</>");
        h1PPF.setHelpText("Minimum selective advantage of heterozygotes over homozygotes");
        gPPF.setCurrentValue(0.1);
        gPPF.setDefaultValue(0.1);
        gPPF.setIncrementAmount(0.05);
        gPPF.setMaxValue(1.0);
        gPPF.setParameterName("<i>g</i>");
        gPPF.setHelpText("Proportion of individuals that are derived as migrants from adjacent demes in each generation");
        fitnessesP.setLayout(gridBagLayout2);
        this.setLayout(gridBagLayout1);
        pP.setLayout(gridBagLayout3);
        fitnessesP.setBorder(titledBorder1);
        pP.setBorder(titledBorder2);
        titledBorder1.setTitle("Parameters");
        titledBorder2.setTitle("Selection Regimes");
        gensPPF.setCurrentValue(10.0);
        gensPPF.setDefaultValue(8.0);
        gensPPF.setIncrementAmount(5.0);
        gensPPF.setIntegersOnly(true);
        gensPPF.setMaxValue(100.0);
        gensPPF.setParameterName("Generations");
        freqRB.setText("Frequency Dependent");
        locHetRB.setText("Local Heterozygote");
        locHetRB.addChangeListener(e -> locHetRB_stateChanged(e));
        hetAdRB.setText("Heterozygote Advantage");
        hetAdRB.addChangeListener(e -> hetAdRB_stateChanged(e));
        gradRB.setSelected(true);
        gradRB.setText("Gradient");
        this.add(fitnessesP, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        fitnessesP.add(gensPPF, new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
        fitnessesP.add(sPPF, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
        fitnessesP.add(gPPF, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
        fitnessesP.add(h1PPF, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
        fitnessesP.add(h2PPF, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
        this.add(pP, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pP.add(freqRB, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0
                , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
        pP.add(locHetRB, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
                , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
        pP.add(hetAdRB, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
                , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
        pP.add(gradRB, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
        this.registerChildren(this);
        plotTypeBG.add(gradRB);
        plotTypeBG.add(hetAdRB);
        plotTypeBG.add(locHetRB);
        plotTypeBG.add(freqRB);
    }

    void hetAdRB_stateChanged(ChangeEvent e) {
        h1PPF.setEnabled(hetAdRB.isSelected());
    }

    void locHetRB_stateChanged(ChangeEvent e) {
        h2PPF.setEnabled(locHetRB.isSelected());

    }
}


