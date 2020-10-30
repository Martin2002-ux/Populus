/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.model.das;

import edu.umn.ecology.populus.plot.*;

public class DASModel extends BasicPlotModel {

	@Override
	public Object getModelHelpText() {
		return "DASHELP";
	}

	public DASModel() {
		this.setModelInput( new DASPanel() );
	}

	public static String getModelName() {
		return "Drift and Selection";
	}

	@Override
	protected String getHelpId() {
		return "das.overview";
	}
}