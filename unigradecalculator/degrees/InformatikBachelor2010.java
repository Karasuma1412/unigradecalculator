package de.ks.unigradecalculator.degrees;

import de.ks.unigradecalculator.degrees.calculators.DegreeCalculatorInformatikBachelor2010;

public class InformatikBachelor2010 extends Degree{
	
	public InformatikBachelor2010(String degreeName, long degreeCredits) {
		super(degreeName, degreeCredits);
		this.name = "Informatik Bachelor PO 2010";
		this.credits = 210;
		setCalculator(new DegreeCalculatorInformatikBachelor2010());
	}
	
	

}
