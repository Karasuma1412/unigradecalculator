package de.ks.unigradecalculator.degrees;

import de.ks.unigradecalculator.degrees.calculators.DegreeCalculatorWirtschaftswissenschaftenBachelor2012;

public class WirtschaftswissenschaftenBachelor2012 extends Degree{
	
	

	public WirtschaftswissenschaftenBachelor2012(String degreeName, long degreeCredits) {
		super(degreeName, degreeCredits);
		name = "Wirtschaftswissenschaften Bachelor PO 2012";
		credits = 210;
		setCalculator(new DegreeCalculatorWirtschaftswissenschaftenBachelor2012());
	}

}
