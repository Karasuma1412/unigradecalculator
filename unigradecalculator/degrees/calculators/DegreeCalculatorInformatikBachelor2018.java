package de.ks.unigradecalculator.degrees.calculators;

public class DegreeCalculatorInformatikBachelor2018 extends DegreeCalculator {
	
	int basicModuleCreditsWeight = 1;
	int mainModuleCreditsWeight = 2;
	int bachelorModuleCreditsWeight = 2;

	@Override
	protected double calculateFinalGrade(double gradeMainModules, double gradeBachelorModule, double gradeBasicModules,
			int creditsMainModule, int creditsBachelorModule, int creditsBasicModules) {
		
		if (creditsBasicModules != 0 && creditsMainModule != 0 && creditsBachelorModule != 0) {
			long basicModulesWeightedCredits = creditsBasicModules * basicModuleCreditsWeight;
			long mainModulesWeightedCredits = creditsMainModule * mainModuleCreditsWeight;
			long bachelorModuleWeightedCredits = creditsBachelorModule * bachelorModuleCreditsWeight;
			
			return ((gradeBasicModules * basicModulesWeightedCredits 
					+ gradeMainModules * mainModulesWeightedCredits
					+ gradeBachelorModule * bachelorModuleWeightedCredits)
						/ (basicModulesWeightedCredits + mainModulesWeightedCredits
								+ bachelorModuleWeightedCredits));
		} else {
			return 0;
		}
		
	}

	
	

	


}
