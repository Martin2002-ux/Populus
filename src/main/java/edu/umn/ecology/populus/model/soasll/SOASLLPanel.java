/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.model.soasll;

import edu.umn.ecology.populus.plot.BasicPlot;
import edu.umn.ecology.populus.plot.BasicPlotInputPanel;
import edu.umn.ecology.populus.visual.ppfield.PopulusParameterField;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SOASLLPanel extends BasicPlotInputPanel {
    /**
     *
     */
    private static final long serialVersionUID = -5796264770398871761L;
    private final JPanel fitnessesP = new JPanel();
    private final JPanel pP = new JPanel();
    private final PopulusParameterField wxYPPF = new PopulusParameterField();
    private final PopulusParameterField wXYPPF = new PopulusParameterField();
    private final PopulusParameterField wxxPPF = new PopulusParameterField();
    private final PopulusParameterField wXxPPF = new PopulusParameterField();
    private final PopulusParameterField wXXPPF = new PopulusParameterField();
    private final PopulusParameterField pMalePPF = new PopulusParameterField();
    private final PopulusParameterField pFemalePPF = new PopulusParameterField();
    private final GridBagLayout gridBagLayout1 = new GridBagLayout();
    private final PopulusParameterField gensPPF = new PopulusParameterField();
    private final GridBagLayout gridBagLayout2 = new GridBagLayout();
    private final GridBagLayout gridBagLayout3 = new GridBagLayout();
    private TitledBorder titledBorder1;
    private TitledBorder titledBorder2;

    @Override
    public BasicPlot getPlotParamInfo() {
        return new SOASLLParamInfo(wXXPPF.getDouble(), wXxPPF.getDouble(), wxxPPF.getDouble(), wXYPPF.getDouble(),
                wxYPPF.getDouble(), pMalePPF.getDouble(), pFemalePPF.getDouble(), gensPPF.getInt());
    }

    public SOASLLPanel() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {//this is needed because w/o the title doesn't fit
        titledBorder1 = new TitledBorder("");
        titledBorder2 = new TitledBorder("");
        wxYPPF.setParameterName("<i>w<sub>xY</>");
        wxYPPF.setMaxValue(1.0);
        wxYPPF.setIncrementAmount(0.05);
        wxYPPF.setDefaultValue(1.0);
        wxYPPF.setCurrentValue(1.0);
        wxYPPF.setHelpText("Fitness of xY Genotypes (in Heterogametic Sex) ");
        wXYPPF.setCurrentValue(1.0);
        wXYPPF.setDefaultValue(1.0);
        wXYPPF.setIncrementAmount(0.05);
        wXYPPF.setMaxValue(1.0);
        wXYPPF.setParameterName("<i>w<sub>XY</>");
        wXYPPF.setHelpText("Fitness of XY Genotypes (in Heterogametic Sex) ");
        wxxPPF.setCurrentValue(1.0);
        wxxPPF.setDefaultValue(1.0);
        wxxPPF.setIncrementAmount(0.05);
        wxxPPF.setMaxValue(1.0);
        wxxPPF.setParameterName("<i>w<sub>xx</>");
        wxxPPF.setHelpText("Fitness of xx Genotypes (in Homogametic Sex) ");
        wXxPPF.setCurrentValue(1.0);
        wXxPPF.setDefaultValue(1.0);
        wXxPPF.setIncrementAmount(0.05);
        wXxPPF.setMaxValue(1.0);
        wXxPPF.setParameterName("<i>w<sub>Xx</>");
        wXxPPF.setHelpText("Fitness of Xx Genotypes (in Homogametic Sex) ");
        wXXPPF.setCurrentValue(1.0);
        wXXPPF.setDefaultValue(1.0);
        wXXPPF.setIncrementAmount(0.05);
        wXXPPF.setMaxValue(1.0);
        wXXPPF.setParameterName("<i>w<sub>XX</>");
        wXXPPF.setHelpText("Fitness of XX Genotypes (in Homogametic Sex) ");
        fitnessesP.setLayout(gridBagLayout2);
        pMalePPF.setIncrementAmount(0.05);
        pMalePPF.setMaxValue(1.0);
        pMalePPF.setParameterName("<i>p<sub>males</>");
        pMalePPF.setHelpText("Allelic Frequency ");
        pFemalePPF.setCurrentValue(1.0);
        pFemalePPF.setDefaultValue(1.0);
        pFemalePPF.setIncrementAmount(0.05);
        pFemalePPF.setMaxValue(1.0);
        pFemalePPF.setParameterName("<i>p<sub>females</>");
        pFemalePPF.setHelpText("Allelic Frequency");
        this.setLayout(gridBagLayout1);
        pP.setLayout(gridBagLayout3);
        gensPPF.setCurrentValue(8.0);
        gensPPF.setDefaultValue(8.0);
        gensPPF.setIncrementAmount(5.0);
        gensPPF.setIntegersOnly(true);
        gensPPF.setMaxValue(100.0);
        gensPPF.setParameterName("Generations");
        gensPPF.setHelpText("This is the number of generations to be simulated.");
        fitnessesP.setBorder(titledBorder1);
        pP.setBorder(titledBorder2);
        titledBorder1.setTitle("Fitnesses");
        titledBorder2.setTitle("Initial Frequencies");
        this.add(fitnessesP, new GridBagConstraints(0, 0, 1, 2, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        fitnessesP.add(wXXPPF, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
        fitnessesP.add(wXxPPF, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));
        fitnessesP.add(wxxPPF, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));
        fitnessesP.add(wXYPPF, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));
        fitnessesP.add(wxYPPF, new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
        this.add(pP, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pP.add(pFemalePPF, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        pP.add(pMalePPF, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
                , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(gensPPF, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.registerChildren(this);
    }
}
