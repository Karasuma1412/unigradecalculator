package de.ks.unigradecalculator.degrees;
import de.ks.unigradecalculator.Module;
import de.ks.unigradecalculator.degrees.calculators.DegreeCalculator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Degree {
	
	protected long credits;
	protected long creditsEarnedBasicModules;
	protected long creditsEarnedMainModules;
	protected long creditsEarnedBachelorModule;
	protected long creditsEarnedWithoutGrade;
	
	protected long creditsEarned;
	protected long creditsLeft;
	
	protected double grade;
	protected double gradeBasicModules;
	protected double gradeMainModules;
	protected double gradeBachelorModule;
	protected String name;
	
	private DegreeCalculator calculator;
	
	private ObservableList<Module> moduleList = FXCollections.observableArrayList();
	
	public Degree(String degreeName, long degreeCredits) {
		setName(degreeName);
		setCredits(degreeCredits);
		creditsEarnedBasicModules = 0;
		creditsEarnedMainModules = 0;
		creditsEarnedBachelorModule = 0;
		creditsEarnedWithoutGrade = 0;
		creditsEarned = 0;
		creditsLeft = credits;
		
		grade = 0;
		gradeBasicModules = 0;
		gradeMainModules = 0;
		gradeBachelorModule = 0;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public long getCredits() {
		return credits;
	}

	public void setCredits(long degreeCredits) {
		this.credits = degreeCredits;
	}

	public long getCreditsLeft() {
		return creditsLeft;
	}

	public void setCreditsLeft(long l) {
		this.creditsLeft = l;
	}

	public long getCreditsEarnedBasicModules() {
		return creditsEarnedBasicModules;
	}

	public void setCreditsEarnedBasicModules(long creditsEarned) {
		this.creditsEarnedBasicModules = creditsEarned;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public long getCreditsEarnedMainModules() {
		return creditsEarnedMainModules;
	}

	public void setCreditsEarnedMainModules(long creditsEarnedMainModules) {
		this.creditsEarnedMainModules = creditsEarnedMainModules;
	}

	public long getCreditsEarnedBachelorModule() {
		return creditsEarnedBachelorModule;
	}

	public void setCreditsEarnedBachelorModule(long creditsEarnedBachelorModule) {
		this.creditsEarnedBachelorModule = creditsEarnedBachelorModule;
	}

	public long getCreditsEarnedWithoutGrade() {
		return creditsEarnedWithoutGrade;
	}

	public void setCreditsEarnedWithoutGrade(long creditsEarnedWithoutGrade) {
		this.creditsEarnedWithoutGrade = creditsEarnedWithoutGrade;
	}

	public long getCreditsEarned() {
		return creditsEarned;
	}

	public void setCreditsEarned(long creditsEarned) {
		this.creditsEarned = creditsEarned;
	}

	public double getGradeBasicModules() {
		return gradeBasicModules;
	}

	public void setGradeBasicModules(double gradeBasicModules) {
		this.gradeBasicModules = gradeBasicModules;
	}

	public double getGradeMainModules() {
		return gradeMainModules;
	}

	public void setGradeMainModules(double gradeMainModules) {
		this.gradeMainModules = gradeMainModules;
	}

	public double getGradeBachelorModule() {
		return gradeBachelorModule;
	}

	public void setGradeBachelorModule(double gradeBachelorModule) {
		this.gradeBachelorModule = gradeBachelorModule;
	}

	public ObservableList<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(ObservableList<Module> moduleList) {
		this.moduleList = moduleList;
	}

	public DegreeCalculator getCalculator() {
		return calculator;
	}

	public void setCalculator(DegreeCalculator calculator) {
		this.calculator = calculator;
	}
}
