package de.ks.unigradecalculator;
import java.io.File;
import java.util.HashMap;

import de.ks.unigradecalculator.degrees.Degree;
import de.ks.unigradecalculator.degrees.InformatikBachelor2010;
import de.ks.unigradecalculator.degrees.InformatikBachelor2018;
import de.ks.unigradecalculator.degrees.WirtschaftswissenschaftenBachelor2012;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	private BorderPane borderPane;
	private DegreeHeader header;
	private File degreeFile;
	private Degree degree;
	private ModuleTable table;
	private FileReaderAndWriter fileManager;

	String filePath = "./degree.json";
	private HashMap<String, Degree> degreeMap = new HashMap<String, Degree>();

	public static void main(String[] args) {	
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		startUp();
		borderPane = new BorderPane();
		borderPane.setPadding(new Insets(16));
		header = new DegreeHeader(this);
		borderPane.setTop(header);	
		BorderPane.setMargin(header, new Insets(0, 0, 16, 0));
		
		table = new ModuleTable(degreeFile, this);
		borderPane.setCenter(table);
		BorderPane.setMargin(table, new Insets(0, 0, 16, 0));
		
		Button buttonNewModuleEntry = new Button("Neues Modul hinzufügen");
		buttonNewModuleEntry.setOnAction(e -> {
			table.addNewModule();
			header.update(this);
			fileManager.savetoFile();
		});		
		
		Button buttonDeleteModuleEntry = new Button("Entfernen");
		buttonDeleteModuleEntry.setOnAction(e -> {
			table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
			header.update(this);
			fileManager.savetoFile();
		});
		
		HBox hBox = new HBox(16);
		hBox.getChildren().addAll(buttonNewModuleEntry, buttonDeleteModuleEntry);
		
		borderPane.setBottom(hBox);
		
		Scene scene = new Scene(borderPane);
		scene.getStylesheets().add("./stylesheet.css");
		
		window.setScene(scene);		
		window.setTitle("Uni Schnitt Kalkulator");	
		window.show();	
	}

	private void startUp() {	
		initDegreeMap();
		fileManager = new FileReaderAndWriter(this);
		if (!checkForDegreeFile()) {
			createNewDegree();
			fileManager.savetoFile();
		} else {
			fileManager.loadFromFile(new File(filePath));
		}
	}

	private void initDegreeMap() {
		Degree degree = new InformatikBachelor2010("", 0);
		degreeMap.put(degree.getName(), degree);
		
		degree = new InformatikBachelor2018("", 0);
		degreeMap.put(degree.getName(), degree);
		
		degree = new WirtschaftswissenschaftenBachelor2012("", 0);
		degreeMap.put(degree.getName(), degree);
	}

	private void createNewDegree() {
		setDegree(new NewDegreeBox().display("", null, this));
	}

	private boolean checkForDegreeFile() {
		degreeFile = new File(filePath);		
		return degreeFile.isFile();		
	}

	public ModuleTable getTable() {
		return table;
	}

	public void setTable(ModuleTable table) {
		this.table = table;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public HashMap<String, Degree> getDegreeMap() {
		return degreeMap;
	}
	
	public FileReaderAndWriter getFileManager() {
		return fileManager;
	}

}
