package de.ks.unigradecalculator;
import de.ks.unigradecalculator.degrees.Degree;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DegreeHeader extends HBox {
	
	private Label degreeNameLabel = new Label();
	private Label degreeCreditsEarnedLabel = new Label();
	private Label degreeCreditsLeftLabel = new Label();
	private Label degreeGradeLabel = new Label();
	private Labeled degreeBasicModuleGradeLabel = new Label();
	private Labeled degreeMainModuleGradeLabel = new Label();
	private Labeled degreeBachelorModuleGradeLabel = new Label();
	
	public DegreeHeader(Main main) {
		
		Button editDegreeButton = new Button("Edit");
		editDegreeButton.setOnAction(e -> {
			Degree degree = new NewDegreeBox().display(main.getDegree().getName(), 
					main.getDegree().getCredits(), main);
			
			if (degree != null) {
				main.setDegree(degree);
				update(main);
				main.getFileManager().savetoFile();
			}
		});
		getChildren().add(editDegreeButton);
		
		Degree degree = main.getDegree();
		
		VBox degreeNameVBox = new VBox(16);
		degreeNameLabel.setText(degree.getName());
		degreeNameVBox.getChildren().addAll(new Label("Studiengang"), degreeNameLabel);
		
		VBox degreeCreditsEarnedVBox = new VBox(16);
		degreeCreditsEarnedLabel.setText(String.valueOf(degree.getCreditsEarnedBasicModules()));
		degreeCreditsEarnedVBox.getChildren().addAll(new Label("Credits Verdient"), degreeCreditsEarnedLabel);
		
		VBox degreeCreditsLeftVBox = new VBox(16);
		degreeCreditsLeftLabel.setText(String.valueOf(degree.getCreditsLeft()));
		degreeCreditsLeftVBox.getChildren().addAll(new Label("Offene Credits"), degreeCreditsLeftLabel);
		
		VBox degreeBasicModulesGradeVBox = new VBox(16);
		degreeBasicModuleGradeLabel.setText(String.valueOf(degree.getCalculator().round(degree.getGradeBasicModules(), 1)));
		degreeBasicModulesGradeVBox.getChildren().addAll(new Label("Grundstudium"), degreeBasicModuleGradeLabel);
		
		VBox degreeMainModulesGradeVBox = new VBox(16);
		degreeMainModuleGradeLabel.setText(String.valueOf(degree.getCalculator().round(degree.getGradeMainModules(), 1)));
		degreeMainModulesGradeVBox.getChildren().addAll(new Label("Hauptstudium"), degreeMainModuleGradeLabel);
		
		VBox degreeBachelorModuleGradeVBox = new VBox(16);
		degreeBachelorModuleGradeLabel.setText(String.valueOf(degree.getCalculator().round(degree.getGradeBachelorModule(), 1)));
		degreeBachelorModuleGradeVBox.getChildren().addAll(new Label("Bachelorarbeit"), degreeBachelorModuleGradeLabel);
		
		VBox degreeGradeVBox = new VBox(16);
		degreeGradeLabel.setText(String.valueOf(degree.getCalculator().round(degree.getGrade(), 1)));
		degreeGradeVBox.getChildren().addAll(new Label("Note"), degreeGradeLabel);
		
		getChildren().addAll(degreeNameVBox, degreeCreditsEarnedVBox, degreeCreditsLeftVBox, 
				degreeBasicModulesGradeVBox, degreeMainModulesGradeVBox, degreeBachelorModuleGradeVBox, degreeGradeVBox);
		setSpacing(16);
	}

	public void update(Main main) {
		Degree degree = main.getDegree();
		degree.getCalculator().caculcateCreditsAndGrades(main);

		degreeNameLabel.setText(degree.getName());
		degreeCreditsEarnedLabel.setText(String.valueOf(degree.getCreditsEarned()));
		degreeCreditsLeftLabel.setText(String.valueOf(degree.getCreditsLeft()));
		degreeGradeLabel.setText(String.valueOf(degree.getCalculator().round(degree.getGrade(), 1)));
		degreeBasicModuleGradeLabel.setText(String.valueOf(degree.getCalculator().round(degree.getGradeBasicModules(), 1)));
		degreeMainModuleGradeLabel.setText(String.valueOf(degree.getCalculator().round(degree.getGradeMainModules(), 1)));
		degreeBachelorModuleGradeLabel.setText(String.valueOf(degree.getCalculator().round(degree.getGradeBachelorModule(), 1)));
	}

}
