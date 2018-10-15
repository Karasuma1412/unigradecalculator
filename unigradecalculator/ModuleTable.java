package de.ks.unigradecalculator;
import java.io.File;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ModuleTable extends TableView<Module> {
	
	private ObservableList<Module> moduleList;

	public ModuleTable(File degreeFile, Main main) {
		moduleList = main.getDegree().getModuleList();
		
		TableColumn<Module, String> moduleNameColumn = new TableColumn<Module, String>("Modulname");
		TableColumn<Module, Integer> moduleCreditsColumn = new TableColumn<Module, Integer>("Credits");
		TableColumn<Module, Double> moduleGradeColumn = new TableColumn<Module, Double>("Note");
		TableColumn<Module, Boolean> moduleIsMainModuleColumn = new TableColumn<Module, Boolean>("Hauptmodul");
		TableColumn<Module, Boolean> moduleIsBachelorModuleColumn = new TableColumn<Module, Boolean>("Bachelorarbeit");
		TableColumn<Module, Boolean> moduleIsGradedColumn = new TableColumn<Module, Boolean>("Benotet: ");
		
		moduleNameColumn.setCellValueFactory(new PropertyValueFactory<Module, String>("name"));
		moduleCreditsColumn.setCellValueFactory(new PropertyValueFactory<Module, Integer>("credits"));
		moduleGradeColumn.setCellValueFactory(new PropertyValueFactory<Module, Double>("grade"));
		moduleIsMainModuleColumn.setCellValueFactory(new PropertyValueFactory<Module, Boolean>("mainModule"));
		moduleIsBachelorModuleColumn.setCellValueFactory(new PropertyValueFactory<Module, Boolean>("bachelorModule"));
		moduleIsGradedColumn.setCellValueFactory(new PropertyValueFactory<Module, Boolean>("graded"));
		
		getColumns().add(moduleNameColumn);
		getColumns().add(moduleCreditsColumn);
		getColumns().add(moduleGradeColumn);
		getColumns().add(moduleIsMainModuleColumn);
		getColumns().add(moduleIsBachelorModuleColumn);
		getColumns().add(moduleIsGradedColumn);
		setItems(moduleList);
	}

	public void addNewModule() {
		Module module = new NewModuleBox().display();
		if (module != null) {
			moduleList.add(module);
		}		
	}

}
