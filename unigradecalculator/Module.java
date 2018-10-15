package de.ks.unigradecalculator;

public class Module {
	
	private String name;
	private long credits;
	private double grade;
	private boolean mainModule;
	private boolean bachelorModule;
	private boolean graded = true;
	
	public Module(String moduleName, long credits2, double moduleGrade, boolean mainModule, boolean bachelorModule) {
		name = moduleName;
		credits = credits2;
		grade = moduleGrade;
		this.mainModule = mainModule;
		this.bachelorModule= bachelorModule;
		if (moduleGrade == 0) {
			graded = false;
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCredits() {
		return credits;
	}
	public void setCredits(long credits) {
		this.credits = credits;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public boolean isMainModule() {
		return mainModule;
	}
	public void setMainModule(boolean mainModule) {
		this.mainModule = mainModule;
	}
	public boolean isBachelorModule() {
		return bachelorModule;
	}
	public void setBachelorModule(boolean bachelorModule) {
		this.bachelorModule = bachelorModule;
	}
	public boolean isGraded() {
		return graded;
	}
	public void setGraded(boolean graded) {
		this.graded = graded;
	}

}
