package de.ks.unigradecalculator.degrees.calculators;

import de.ks.unigradecalculator.Main;
import de.ks.unigradecalculator.Module;
import de.ks.unigradecalculator.degrees.Degree;

public class DegreeCalculator {
	
	double basicModuleWeight = .25;
	double mainModuleWeight = .5;
	double bachelorModuleWeight = .25;
	
	int basicModuleCreditsWeight = 1;
	int mainModuleCreditsWeight = 1;
	int bachelorModuleCreditsWeight = 1;

	public void caculcateCreditsAndGrades(Main main) {
		Degree degree = main.getDegree();
		double grade = 0;
		double gradeBasicModules = 0;
		double gradeMainModules = 0;
		double gradeBachelorModule = 0;
				
		int creditsEarned = 0;
		int creditsBasicModules = 0;
		int creditsMainModule = 0;
		int creditsBachelorModule = 0;
		int creditsNotGradedModules = 0;
		
		for (Module module : main.getDegree().getModuleList()) {
			if (module.isMainModule()) {
				gradeMainModules += module.getGrade() * module.getCredits();
				creditsMainModule += module.getCredits();
			} else if (module.isBachelorModule()) {
				gradeBachelorModule += module.getGrade();
				creditsBachelorModule += module.getCredits();
			} else if (module.isGraded()) {
				gradeBasicModules += module.getGrade() * module.getCredits();
				creditsBasicModules += module.getCredits();
			} else {
				creditsNotGradedModules += module.getCredits();
			}
		}							
		
		gradeBasicModules = calculatebasicModuleGrade(gradeBasicModules, creditsBasicModules);
		gradeMainModules = calculateMainModuleGrade(gradeMainModules, creditsMainModule);
		gradeBachelorModule = calculateBachelorModuleGrade(gradeBachelorModule, creditsBachelorModule);
		
		setDegreeValues(degree, creditsEarned, creditsBasicModules, creditsMainModule, 
				creditsBachelorModule, creditsNotGradedModules, gradeBasicModules, 
				gradeMainModules, gradeBachelorModule, grade);	
	}
	
	private double calculateBachelorModuleGrade(double gradeBachelorModule, int creditsBachelorModule) {
		return gradeBachelorModule;
	}

	private double calculateMainModuleGrade(double gradeMainModules, int creditsMainModule) {
		if (creditsMainModule > 0) {
			gradeMainModules /= creditsMainModule;
		}		
		return gradeMainModules;
	}

	private double calculatebasicModuleGrade(double gradeBasicModules, int creditsBasicModules) {
		if (creditsBasicModules > 0) {
			gradeBasicModules /= creditsBasicModules;
		}	
		return gradeBasicModules;
	}

	private void setDegreeValues(Degree degree, int creditsEarned, int creditsBasicModules, 
			int creditsMainModule, int creditsBachelorModule, int creditsNotGradedModules, 
			double gradeBasicModules, double gradeMainModules, double gradeBachelorModule, double grade) {
		
		creditsEarned = creditsMainModule + creditsBachelorModule 
				+ creditsBasicModules + creditsNotGradedModules;
		
		grade = calculateFinalGrade(gradeMainModules, gradeBachelorModule, gradeBasicModules, 
				creditsMainModule, creditsBachelorModule, creditsBasicModules);
		
		degree.setCreditsEarned(creditsEarned);
		degree.setCreditsEarnedBasicModules(creditsBasicModules);
		degree.setCreditsEarnedMainModules(creditsMainModule);
		degree.setCreditsEarnedBachelorModule(creditsBachelorModule);
		degree.setCreditsEarnedWithoutGrade(creditsNotGradedModules);
		degree.setCreditsLeft(degree.getCredits() - creditsEarned);
		
		degree.setGradeBasicModules(gradeBasicModules);
		degree.setGradeMainModules(gradeMainModules);
		degree.setGradeBachelorModule(gradeBachelorModule);
		degree.setGrade(grade);
	}

	protected double calculateFinalGrade(double gradeMainModules, double gradeBachelorModule, double gradeBasicModules, 
			int creditsMainModule, int creditsBachelorModule, int creditsBasicModules) {
		
		return gradeMainModules * mainModuleWeight + gradeBachelorModule * bachelorModuleWeight 
				+ gradeBasicModules * basicModuleWeight;
	}

	public double round(double grade, int i) {
		int value = 1;
		for (int k = 0; k < i; k++) {
			value *= 10;
		}
		int tempValue = (int) (grade * value);
		double result = (double) tempValue / (double) value;
		
		return result;
	}

}
