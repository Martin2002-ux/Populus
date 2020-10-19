/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.model.cp;
import java.io.Serializable;

public class Res extends java.util.ListResourceBundle implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 4362408338276150015L;
	static final Object[][] contents = new String[][]{
		{ "Discrete_Predator", "Discrete Predator-Prey (Competing Predators)" },
		{ "Predator_2_Q_", "Predator 2 (<i>Q</i>)" },
		{ "Prey_N_", "Prey (<i>N</i>)" },
		{ "Predator_1_P_", "Predator 1 (<i>P</i>)" },
		{ "Competing_Predators", "Competing Predators" },
		{ "Model_Type", "Model Type" },
		{ "Model_Parameters", "Model Parameters" },
		{ "Initial_Conditions", "Initial Conditions" },
		{ "Plot_Type", "Plot Type" },
		{ "Initial_Population", "Initial Population" },
		{ "Predator_1_Size_i_P_i", "Predator 1 Size (<i>P</i><sub>0</sub>)" },
		{ "Predator_2_Size_i_Q_i", "Predator 2 Size (<i>Q</i><sub>0</sub>)" },
		{ "Number_of_generations", "Number of generations to plot" },
		{ "Generations_", "Generations:" },
		{ "Prey_Size_i_N_i_sub_0", "Prey  Size (<i>N</i><sub>0</sub>)" },
		{ "Time_i_t_i_", "Time (<i> t </i>)" }};

		@Override
		public Object[][] getContents() {
			return contents;
		}
}
