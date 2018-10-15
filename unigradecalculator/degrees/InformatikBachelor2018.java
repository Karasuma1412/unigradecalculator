package de.ks.unigradecalculator.degrees;

import de.ks.unigradecalculator.degrees.calculators.DegreeCalculatorInformatikBachelor2018;

public class InformatikBachelor2018 extends Degree{
	
	public InformatikBachelor2018(String degreeName, long degreeCredits) {
		super(degreeName, degreeCredits);
		this.name = "Informatik Bachelor PO 2018";
		this.credits = 180;
		setCalculator(new DegreeCalculatorInformatikBachelor2018());
	}	

}
