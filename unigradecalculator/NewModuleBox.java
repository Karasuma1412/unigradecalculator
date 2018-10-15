package de.ks.unigradecalculator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewModuleBox {

	public Module display() {
		Stage window = new Stage();
		window.setTitle("Neues Modul");
		
		VBox vbox = new VBox(16);		
		GridPane gridPane = new GridPane();
		
		Label moduleNameLabel = new Label("Modulname: ");		
		TextField moduleNameTextField = new TextField();
		moduleNameTextField.setPromptText("Name des Moduls");
		
		Label moduleCreditsLabel = new Label("Credits: ");
		TextField moduleCreditsTextField = new TextField();
		moduleCreditsTextField.setPromptText("Credits");
		
		Label moduleGradeLabel = new Label("Note: ");
		TextField moduleGradeTextField = new TextField();
		moduleGradeTextField.setPromptText("Note");
		
		Button buttonConfirm = new Button("OK");
		buttonConfirm.setOnAction(e -> window.close());
		
		CheckBox checkBoxMainModule = new CheckBox("Hauptstudium");
		CheckBox checkBoxBachelorModule = new CheckBox("Bachelorarbeit");
		
		gridPane.add(moduleNameLabel, 0, 0);
		gridPane.add(moduleCreditsLabel, 0, 1);
		gridPane.add(moduleGradeLabel, 0, 2);
		gridPane.add(moduleNameTextField, 1, 0);
		gridPane.add(moduleCreditsTextField, 1, 1);		
		gridPane.add(moduleGradeTextField, 1, 2);
		gridPane.setVgap(16);
		gridPane.setHgap(16);
		
		HBox buttonBox = new HBox(16);
		buttonBox.getChildren().addAll(buttonConfirm, checkBoxMainModule, checkBoxBachelorModule);
		vbox.getChildren().addAll(gridPane, buttonBox);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(16));
		
		Scene scene = new Scene(vbox);
		scene.getStylesheets().add("./stylesheet.css");
		
		window.setScene(scene);	
		window.showAndWait();
		
		String moduleName;
		if (moduleNameTextField.getText() != null && moduleNameTextField.getText().trim().isEmpty()) {
			moduleName = "";
		} else {
			moduleName = moduleNameTextField.getText();
		}
		
		int moduleCredits;
		try {
			moduleCredits = Integer.valueOf(moduleCreditsTextField.getText());
		} catch (NumberFormatException e) {
			moduleCredits = 0;
		}
		
		double moduleGrade;
		try {
			moduleGrade = Double.valueOf(moduleGradeTextField.getText());
		} catch (NumberFormatException e) {
			moduleGrade = 0;
		}
		
		boolean mainModule = checkBoxMainModule.isSelected();
		boolean bachelorModule = checkBoxBachelorModule.isSelected();
		
		Module module = new Module(moduleName, moduleCredits, moduleGrade, mainModule, bachelorModule);
		if (module.getName().trim() != "" && module.getName() != null) {
			return module;			
		} else {
			return null;
		}
		
	}

}
