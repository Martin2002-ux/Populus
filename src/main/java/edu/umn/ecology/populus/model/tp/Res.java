/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.model.tp;
import java.io.Serializable;
import edu.umn.ecology.populus.constants.ColorScheme;

public class Res extends java.util.ListResourceBundle implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 7450474430134898360L;
	static final Object[][] contents = new String[][] {
		{
			"Bacterial", "Temperate Phage"
		},  {
			"One", "<b><i>" +
					ColorScheme.getColorString(0) + "L </font></i></b>, <b><i>" +
					ColorScheme.getColorString(1) + "T </font></i></b>"},  {
						"Two", "<b><i>" +
								ColorScheme.getColorString(0) + "L </font></i></b>, <b><i>" +
								ColorScheme.getColorString(1) + "T </font></i></b>, <b><i>" +
								ColorScheme.getColorString(2) + "S </font></i></b>"},  {
									"Three", "<b><i>" +
											ColorScheme.getColorString(0) + "L </font></i></b>, <b><i>" +
											ColorScheme.getColorString(1) + "T </font></i></b>, <b><i>" +
											ColorScheme.getColorString(2) + "S </font></i></b>, <b><i>" +
											ColorScheme.getColorString(3) + "R </font></i></b>"},  {
												"Four", "<b><i>" +
														ColorScheme.getColorString(0) + "L </font></i></b>, <b><i>" +
														ColorScheme.getColorString(1) + "T </font></i></b>, <b><i>" +
														ColorScheme.getColorString(2) + "S </font></i></b>, <b><i>" +
														ColorScheme.getColorString(3) + "V </font></i></b>"},  {
															"Five", "<b><i>" +
																	ColorScheme.getColorString(0) + "L </font></i></b>, <b><i>" +
																	ColorScheme.getColorString(1) + "T </font></i></b>, <b><i>" +
																	ColorScheme.getColorString(2) + "S </font></i></b>, <b><i>" +
																	ColorScheme.getColorString(3) + "R </font></i></b>, <b><i>" +
																	ColorScheme.getColorString(4) + "V </font></i></b>"},{
																		"Time_b_i_t_", "Time ( <b><i>t</> )"
																	},  {
																		"Host_Population", "Host Population Density (   "
																	},  {
																		"totHostDens", "Total Host Density ( <b><i>"+ColorScheme.getColorString(0)+"N</> )"
																	},

	};

	@Override
	public Object[][] getContents() {
		return contents;
	}
}