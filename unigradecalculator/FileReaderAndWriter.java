package de.ks.unigradecalculator;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.ks.unigradecalculator.degrees.Degree;

public class FileReaderAndWriter {
	
	Main main;
	
	String degreeInfo = "Degree Info";
	String degreeCredits = "Credits";
	String degreeCreditsEarned = "Credits Earned";
	String degreeCreditsLeft = "Credits Left";
	String degreeCreditsBasicModules = "Credits Basic Modules";
	String degreeCreditsMainModules = "Credits Main Modules";
	String degreeCreditsBachelorModule = "Credits Bachelor Module";
	String degreeCreditsNotGradedModules = "Credits Not Graded Modules";
		
	String degreeName = "Name";
	String degreeGradeBachelorModule = "Degree Grade Bachelor Module";
	String degreeGradeBasicModule = "Degree Grade Basic Modules";
	String degreeGradeMainModule = "Degree Grade Main Modules";
	String degreegrade = "Degree Grade";
	
	String moduleMainModule = "Module Is Main Module";
	String moduleBachelorModule = "Module Is Bachelor Module";
	String moduleCredits = "Module Credits";
	String moduleName = "Name";
	String moduleIsGraded = "Module Is Graded";
	String moduleGrade = "Module Grade";
	
	
	String modules = "Modules";
	

	public FileReaderAndWriter(Main main) {
		this.main = main;
	}

	public void loadFromFile(File file) {
		JSONObject jsonObject = createJSONObject(file);
		handleJSONObject(jsonObject);
	}

	private void handleJSONObject(JSONObject jsonObject) {
		JSONObject info = (JSONObject) jsonObject.get(degreeInfo);		
		String name = (String) info.get(degreeName);
		
		long creditsEarned = (long) info.get(degreeCreditsEarned);
		long creditsLeft = (long) info.get(degreeCreditsLeft);
		long creditsBasicModules = (long) info.get(degreeCreditsBasicModules);
		long creditsMainModules = (long) info.get(degreeCreditsMainModules);
		long creditsBachelorModule = (long) info.get(degreeCreditsBachelorModule);
		long creditsNotGradedModules = (long) info.get(degreeCreditsNotGradedModules);
		
		double gradeBasicModules = (double) info.get(degreeGradeBasicModule);
		double gradeMainModules = (double) info.get(degreeGradeMainModule);
		double gradeBachelorModule = (double) info.get(degreeGradeBachelorModule);
		double grade = (double) info.get(degreegrade);
		
		Degree degree = main.getDegreeMap().get(name);
		
		degree.setCreditsEarned(creditsEarned);
		degree.setCreditsEarnedBachelorModule(creditsBachelorModule);
		degree.setCreditsEarnedBasicModules(creditsBasicModules);
		degree.setCreditsEarnedMainModules(creditsMainModules);
		degree.setCreditsEarnedWithoutGrade(creditsNotGradedModules);
		degree.setCreditsLeft(creditsLeft);
		
		degree.setGradeBasicModules(gradeBasicModules);
		degree.setGradeMainModules(gradeMainModules);
		degree.setGradeBachelorModule(gradeBachelorModule);
		degree.setGrade(grade);
		
		createModuleList(jsonObject, degree);
		main.setDegree(degree);
	}

	private void createModuleList(JSONObject jsonObject, Degree degree) {
		JSONArray moduleArray = (JSONArray) jsonObject.get(modules);
		
		for (int i = 0; i < moduleArray.size(); i++) {
			JSONObject moduleJSONObject = (JSONObject) moduleArray.get(i);
			String name = (String) moduleJSONObject.get(moduleName);
			long credits = (long) moduleJSONObject.get(moduleCredits);
			double grade = (double) moduleJSONObject.get(moduleGrade);
			boolean bachelor = (boolean) moduleJSONObject.get(moduleBachelorModule);
			boolean main = (boolean) moduleJSONObject.get(moduleMainModule);
			
			Module module = new Module(name, credits, grade, main, bachelor);
			degree.getModuleList().add(module);
		}
	}

	private JSONObject createJSONObject(File file) {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		
		try {
			obj = (JSONObject) parser.parse(new FileReader(file));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	@SuppressWarnings("unchecked")
	public void savetoFile() {
		Degree degree = main.getDegree();
		
		JSONObject obj = new JSONObject();
		JSONObject info = new JSONObject();	
		
		info.put(degreeGradeBachelorModule, degree.getGradeBachelorModule());
		info.put(degreeGradeMainModule, degree.getGradeMainModules());
		info.put(degreeGradeBasicModule, degree.getGradeBasicModules());
		info.put(degreegrade, degree.getGrade());
		
		info.put(degreeCreditsLeft, degree.getCreditsLeft());
		info.put(degreeCreditsNotGradedModules, degree.getCreditsEarnedWithoutGrade());
		info.put(degreeCreditsBachelorModule, degree.getCreditsEarnedBachelorModule());
		info.put(degreeCreditsMainModules, degree.getCreditsEarnedMainModules());
		info.put(degreeCreditsBasicModules, degree.getCreditsEarnedBasicModules());
		info.put(degreeCreditsEarned, degree.getCreditsEarned());
		info.put(degreeCredits, degree.getCredits());
		info.put(degreeName, degree.getName());				
		
		List<Module> moduleList = main.getDegree().getModuleList();
		JSONArray moduleListJSONArray = new JSONArray();
		
		for(Module module : moduleList) {
			JSONObject moduleObject = new JSONObject();		
			moduleObject.put(moduleIsGraded, module.isGraded());
			moduleObject.put(moduleBachelorModule, module.isBachelorModule());
			moduleObject.put(moduleMainModule, module.isMainModule());
			moduleObject.put(moduleGrade, module.getGrade());
			moduleObject.put(moduleCredits, module.getCredits());
			moduleObject.put(moduleName, module.getName());			
			moduleListJSONArray.add(moduleObject);
		}
		
		obj.put(degreeInfo, info);
		obj.put(modules, moduleListJSONArray);
		
		try {
			FileWriter file = new FileWriter("./degree.json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
