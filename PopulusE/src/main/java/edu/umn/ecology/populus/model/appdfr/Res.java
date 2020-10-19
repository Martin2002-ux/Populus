/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.model.appdfr;
import java.io.Serializable;

public class Res extends java.util.ListResourceBundle implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1013893664028647666L;
	static final Object[][] contents = new String[][]{
		{ "Functional_Responses", "Functional Responses" },
		{ "Discrete_Predator", "Discrete Predator-Prey (Functional Responses)" },
		{ "Model_Type", "Model Type" },
		{ "Model_Parameters", "Model Parameters" },
		{ "Initial_Conditions", "Initial Conditions" },
		{ "Axes", "Axes" },
		{ "Initial_Population", "Initial Population" },
		{ "Prey_Size_i_N_i_sub_0", "Prey Size (<i>N</i><sub>0</sub>)" },
		{ "Type_III", "Type III" },
		{ "continuous", "continuous" },
		{ "Type_I", "Type I" },
		{ "discrete", "discrete" },
		{ "Predator_Size_i_P_i", "Predator Size (<i>P</i><sub>0</sub>)" },
		{ "Number_of_generations", "Number of generations to plot" },
		{ "Generations_", "Generations:" },
		{ "Type_II", "Type II" },
		{ "Pvs_N", "<i>P</i>  vs <i>N</i>" },
		{ "NP_vs_t", "<i>N</i>, <i>P</i>  vs <i>t</i>" },
		{ "Plot_Type", "Plot Type" },
		{ "Time_i_t_i_", "Time ( <i>t</i> )" },
		{ "_i_N_i_", "<i> N </i>" },
		{ "N", " N</>, " },
		{ "_i_P_", "<i>P</>" }};

		@Override
		public Object[][] getContents() {
			return contents;
		}
}
