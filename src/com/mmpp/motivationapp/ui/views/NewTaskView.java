package com.mmpp.motivationapp.ui.views;

import com.mmpp.motivationapp.ui.SceneManager;
import com.mmpp.motivationapp.ui.SceneView;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class NewTaskView extends SceneView {
	public NewTaskView(SceneManager sceneManager) {
		super(sceneManager);
	}

	@Override
	public Scene getScene() {
		StackPane root = new StackPane();
		
		Button createBtn = new Button("Create");
		createBtn.setOnAction((ActionEvent event) -> {
			createTask();
		});
		root.getChildren().add(createBtn);
		
		Button backBtn = new Button("Back");
		backBtn.setOnAction((ActionEvent event) -> {
			sceneManager.changeScene("Main");
		});
		root.getChildren().add(backBtn);
		
		return new Scene(root, 640, 480);
	}
	
	// TODO: make it do things
	private void createTask() {
		
	}
}
