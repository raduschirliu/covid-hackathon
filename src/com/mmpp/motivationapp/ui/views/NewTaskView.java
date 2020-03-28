package com.mmpp.motivationapp.ui.views;

import com.mmpp.motivationapp.backend.BackendConstants;
import com.mmpp.motivationapp.backend.Task;
import com.mmpp.motivationapp.controllers.TaskListManager;
import com.mmpp.motivationapp.ui.SceneManager;
import com.mmpp.motivationapp.ui.SceneView;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NewTaskView extends SceneView {
	private String name;
	private int priority;
	private TaskListManager taskManager;
	
	public NewTaskView(SceneManager sceneManager, TaskListManager taskManager) {
		super("NewTask", sceneManager);
		this.taskManager = taskManager;
	}

	@Override
	public Scene getScene() {
		BorderPane root = new BorderPane();
		
		// Center pane
		VBox centerPane = new VBox();
		
		Label nameLbl = new Label("Name:");
		centerPane.getChildren().add(nameLbl);
		
		TextField nameField = new TextField();
		centerPane.getChildren().add(nameField);
		
		Label priorityLabel = new Label("Priority:");
		priorityLabel.setPadding(new Insets(20, 0, 0, 0));
		centerPane.getChildren().add(priorityLabel);
		
		Slider prioritySlider = new Slider(1, BackendConstants.MAX_PRIORTIY, 1);
		prioritySlider.setMinorTickCount(0);
		prioritySlider.setMajorTickUnit(1);
		prioritySlider.setSnapToTicks(true);
		prioritySlider.setShowTickLabels(true);
		prioritySlider.setShowTickMarks(true);
		centerPane.getChildren().add(prioritySlider);
		
		root.setCenter(centerPane);
		BorderPane.setMargin(centerPane, new Insets(20));
		
		// Bottom pane
		HBox bottomPane = new HBox();
		bottomPane.setSpacing(20);
		
		Button backBtn = new Button("Back");
		backBtn.setPrefSize(640, 40);
		backBtn.setOnAction((ActionEvent event) -> {
			sceneManager.changeScene("Main");
		});
		bottomPane.getChildren().add(backBtn);
		
		Button createBtn = new Button("Create");
		createBtn.setPrefSize(640, 40);
		createBtn.setOnAction((ActionEvent event) -> {
			name = nameField.getText();
			priority = (int)prioritySlider.getValue();
			createTask();
		});
		bottomPane.getChildren().add(createBtn);
		
		root.setBottom(bottomPane);
		BorderPane.setMargin(bottomPane, new Insets(0, 20, 20, 20));
		
		return new Scene(root, 640, 480);
	}
	
	// TODO: make it do things
	private void createTask() {
		Task task = new Task(name, priority);
		System.out.println("Created new task: " + task);
		sceneManager.changeScene("Main");
	}
}
