package de.ks.unigradecalculator;
import de.ks.unigradecalculator.degrees.Degree;
import de.ks.unigradecalculator.degrees.InformatikBachelor2010;
import de.ks.unigradecalculator.degrees.InformatikBachelor2018;
import de.ks.unigradecalculator.degrees.WirtschaftswissenschaftenBachelor2012;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewDegreeBox {
	
	public Degree display(String degreeName, Long credits, Main main) {
		Stage window = new Stage();
		window.setTitle("Neuer Studiengang");
		
		VBox vbox = new VBox(16);		
		GridPane gridPane = new GridPane();
		
		Label degreeNameLabel = new Label("Studiengang: ");		
		
		ChoiceBox<Degree> degreeChoiceBox = new ChoiceBox<Degree>();
		ObservableList<Degree> degreeList = FXCollections.observableArrayList();
		degreeList.addAll(new InformatikBachelor2010("Informatik", 210),
				new InformatikBachelor2018("Informatik", 180),
				new WirtschaftswissenschaftenBachelor2012("Wirtschaftswissenschaften", 210));
		degreeChoiceBox.setItems(degreeList);
		
		Button buttonConfirm = new Button("OK");
		buttonConfirm.setOnAction(e -> {
			if (degreeChoiceBox.getSelectionModel().getSelectedItem() != null) {
				window.close();
			}			
		});
		
		gridPane.add(degreeNameLabel, 0, 0);
		gridPane.add(degreeChoiceBox, 1, 0);
		gridPane.setVgap(16);
		gridPane.setHgap(16);
		
		vbox.getChildren().addAll(gridPane, buttonConfirm);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(16));
		
		Scene scene = new Scene(vbox);
		scene.getStylesheets().add("./stylesheet.css");
		
		window.setScene(scene);	
		window.showAndWait();
		
		Degree newDegree = degreeChoiceBox.getSelectionModel().getSelectedItem();
		Degree oldDegree = main.getDegree();
		
		if (oldDegree != null && newDegree != null) {
			newDegree.setModuleList(oldDegree.getModuleList());
		}
		
		return newDegree;
	}

}
