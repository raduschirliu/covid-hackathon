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

public class TaskView extends SceneView {
	private String name;
	private int priority;
	private Task task;
	private TaskListManager taskManager;
	
	public TaskView(SceneManager sceneManager, TaskListManager taskManager) {
		super("Task", sceneManager);
		this.taskManager = taskManager;
		this.task = null;
	}
	
	public void setTask(Task task) {
		this.task = task;
		priority = 1;
		name = "";
		
		if (task != null) {
			priority = task.getPriority();
			name = task.getName();
		}
	}

	@Override
	public Scene getScene() {
		BorderPane root = new BorderPane();
		root.getStyleClass().add("body");
		
		// Center pane
		VBox centerPane = new VBox();
		
		Label nameLbl = new Label("Name:");
		centerPane.getChildren().add(nameLbl);
		
		TextField nameField = new TextField();
		nameField.setText(name);
		centerPane.getChildren().add(nameField);
		
		Label priorityLabel = new Label("Priority:");
		priorityLabel.setPadding(new Insets(20, 0, 0, 0));
		centerPane.getChildren().add(priorityLabel);
		
		Slider prioritySlider = new Slider(1, BackendConstants.MAX_PRIORTIY, 1);
		prioritySlider.setValue(priority);
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
		
		Button saveBtn = new Button("Save");
		if (task == null) saveBtn.setText("Create");		
		saveBtn.setPrefSize(640, 40);
		saveBtn.setOnAction((ActionEvent event) -> {
			name = nameField.getText();
			priority = (int)prioritySlider.getValue();
			
			if (task == null) {
				createTask();
			} else {
				saveTask();
			}
		});
		bottomPane.getChildren().add(saveBtn);
		
		root.setBottom(bottomPane);
		BorderPane.setMargin(bottomPane, new Insets(0, 20, 20, 20));
		
		Scene scene = new Scene(root, 640, 480);
		scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Ubuntu&display=swap");
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		return scene;
	}
	
	// TODO: Make it create new task
	private void createTask() {
		Task newTask = new Task(name, priority);
		taskManager.addTaskToToday(newTask);
		System.out.println("Created new task: " + newTask);
		
		sceneManager.changeScene("Main");
	}
	
	// TODO: make it save
	private void saveTask() {
		task.setName(name);
		task.setPriority(priority);
		System.out.println("Saved task: " + task);
		taskManager.getTodaysList().sortTasks(); //Reorder the tasks now that we have possibly changed the priority
		sceneManager.changeScene("Main");
	}
}
