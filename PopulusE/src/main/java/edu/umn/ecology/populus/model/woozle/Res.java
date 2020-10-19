/*******************************************************************************
 * Copyright (c) 2015 Regents of the University of Minnesota.
 *
 * This software is released under GNU General Public License 2.0
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 *******************************************************************************/
package edu.umn.ecology.populus.model.woozle;
import java.io.*;

public class Res extends java.util.ListResourceBundle implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -9184615047198726431L;
	static final Object[][] contents = new String[][] {
		{
			"Agreements_", "Agreements:"
		},  {
			"Generation_", "Generation:"
		},  {
			"Woozleology", "Woozleology"
		},  {
			"Target_Phrase", "Target Phrase"
		},  {
			"METHINKS_IT_IS_LIKE_A", "METHINKS IT IS LIKE A WOOZLE"
		},  {
			"Brood_Size", "Brood Size"
		},  {
			"Mutation_Rate", "Mutation Rate"
		},  {
			"Model_a_diploid", "Model a diploid, sexual process"
		},  {
			"Show_phrase_evolving", "Show phrase evolving"
		},  {
			"Same_seed_for_random", "Same seed for random generator"
		},  {
			"Crossover_Rate", "Crossover Rate"
		},  {
			"Resume", "Resume"
		},  {
			"Pause", "Pause"
		},  {
			"Number_of_parents", "Number of parents must be greater than brood size"
		},  {
			"Run_Again", "Run Again"
		},  {
			"Generations_", "Generations:"
		},  {
			"Current_Phrase_", "Current Phrase:"
		},  {
			"methinks_it_is_like_a", "methinks it is like a woozle"
		},  {
			"Target_Phrase_", "Target Phrase:"
		}
	};

	@Override
	public Object[][] getContents() {
		return contents;
	}
}
