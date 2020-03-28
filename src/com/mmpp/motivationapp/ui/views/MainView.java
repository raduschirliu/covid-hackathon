package com.mmpp.motivationapp.ui.views;

import com.mmpp.motivationapp.ui.SceneManager;
import com.mmpp.motivationapp.ui.SceneView;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainView extends SceneView {
	public MainView(SceneManager sceneManager) {
		super(sceneManager);
	}

	@Override
	public Scene getScene() {
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(20));
		
		VBox taskList = new VBox();
		taskList.getChildren().add(new Text("Tasks:"));
		for (int i = 0; i < 10; i++) {
			Button btn = new Button("Test task");
			btn.setPrefWidth(300);
			
			taskList.getChildren().add(btn);
		}
		root.setCenter(taskList);
		
		StackPane buttonPane = new StackPane();
		
		Button newTaskBtn = new Button("New Task");
		newTaskBtn.setPrefWidth(600);
		newTaskBtn.setMinHeight(40);
		newTaskBtn.setOnAction((ActionEvent event) -> {
			sceneManager.changeScene("NewTask");
		});
		buttonPane.getChildren().add(newTaskBtn);
		
		root.setBottom(buttonPane);
		
		return new Scene(root, 640, 480);
	}
}
