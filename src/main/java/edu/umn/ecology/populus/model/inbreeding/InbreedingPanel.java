/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.model.inbreeding;

import java.awt.*;
import edu.umn.ecology.populus.plot.*;
import edu.umn.ecology.populus.visual.ppfield.*;

public class InbreedingPanel extends BasicPlotInputPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = -9153822050325863983L;
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	PopulusParameterField populationPPF = new PopulusParameterField();
	PopulusParameterField frequencyPPF = new PopulusParameterField();
	PopulusParameterField gensPPF = new PopulusParameterField();

	public InbreedingPanel() {
		try {
			jbInit();
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateLabels() {

	}

	@Override
	public BasicPlot getPlotParamInfo() {
		return new InbreedingParamInfo(populationPPF.getInt(),frequencyPPF.getDouble(), gensPPF.getInt());
	}

	private void jbInit() throws Exception {
		this.setLayout( gridBagLayout1 );
		populationPPF.setIncrementAmount(5.0);
		populationPPF.setIntegersOnly(true);
		populationPPF.setMaxValue(500.0);
		populationPPF.setMinValue(1.0);
		populationPPF.setCurrentValue(100.0);
		populationPPF.setDefaultValue(100.0);
		populationPPF.setParameterName("Population");
		frequencyPPF.setParameterName("Initial F");
		frequencyPPF.setMaxValue(1.0);
		frequencyPPF.setIntegersOnly(true);
		frequencyPPF.setHelpText("Initial inbreeding coefficient");
		frequencyPPF.setIncrementAmount(0.1);
		gensPPF.setParameterName("Generations");
		gensPPF.setMinValue(1.0);
		gensPPF.setMaxValue(500.0);
		gensPPF.setDefaultValue(50.0);
		gensPPF.setHelpText("");
		gensPPF.setCurrentValue(50.0);
		gensPPF.setIntegersOnly(true);
		gensPPF.setIncrementAmount(5.0);
		this.add(frequencyPPF,           new GridBagConstraints(1, 0, 1, 2, 1.0, 1.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(gensPPF,            new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 10, 10), 0, 0));
		this.add(populationPPF,      new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 5, 10), 0, 0));
		registerChildren( this );
	}
}
