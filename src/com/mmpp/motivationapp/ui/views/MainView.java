package com.mmpp.motivationapp.ui.views;

import java.util.ArrayList;

import com.mmpp.motivationapp.backend.Task;
import com.mmpp.motivationapp.controllers.MotivationController;
import com.mmpp.motivationapp.controllers.TaskListManager;
import com.mmpp.motivationapp.ui.SceneManager;
import com.mmpp.motivationapp.ui.SceneView;
import com.sun.javafx.css.Stylesheet;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MainView extends SceneView {
	private TaskListManager taskManager;
	private MotivationController motivationController;
	
	public MainView(SceneManager sceneManager, TaskListManager taskManager, MotivationController motivationController) {
		super("Main", sceneManager);
		this.taskManager = taskManager;
		this.motivationController = motivationController;
	}
	
	public Pane createTask(Task task, boolean darkBg) {
		AnchorPane root = new AnchorPane();
		root.getStyleClass().add("task");
		root.setMinSize(300, 25);
		root.setPadding(new Insets(0, 5, 0, 5));
		
		if (task.getIsComplete()) {
			root.getStyleClass().add("completed");
		}
		
		if (darkBg) {
			root.getStyleClass().add("dark");
		}
		
		CheckBox check = new CheckBox();
		check.setSelected(task.getIsComplete());
		check.setMinHeight(25.0);
		check.setOnAction((ActionEvent e) -> {
			task.setComplete(!task.getIsComplete());
			
			if (task.getIsComplete()) {
				root.getStyleClass().add("completed");
			} else {
				root.getStyleClass().remove("completed");
			}
		});
		root.getChildren().add(check);
		AnchorPane.setLeftAnchor(check, 0.0);
		
		Label priorityLbl = new Label("(" + task.getPriority() + ")");
		priorityLbl.setPadding(new Insets(0, 0, 0, 5));
		priorityLbl.setPrefWidth(40);
		priorityLbl.setMinHeight(25);
		root.getChildren().add(priorityLbl);
		AnchorPane.setLeftAnchor(priorityLbl, 30.0);
		
		Label nameLbl = new Label(task.getName());
		nameLbl.setPrefWidth(250);
		nameLbl.setMinHeight(25);
		nameLbl.setPadding(new Insets(0, 20, 0, 20));
		root.getChildren().add(nameLbl);
		AnchorPane.setLeftAnchor(nameLbl, 50.0);
		
		Button deleteBtn = new Button("X");
		deleteBtn.setPrefSize(30, check.getHeight());
		deleteBtn.getStyleClass().add("outline");
		deleteBtn.setOnAction((ActionEvent e) -> {
			System.out.println("Delete: " + task.getName());
			taskManager.removeTodaysTask(task);
			sceneManager.reloadScene();
		});
		root.getChildren().add(deleteBtn);
		AnchorPane.setRightAnchor(deleteBtn, 0.0);
		
		Button editBtn = new Button("Edit");
		editBtn.setPrefHeight(check.getHeight());
		editBtn.getStyleClass().add("outline");
		editBtn.setOnAction((ActionEvent e) -> {
			System.out.println("Edit: " + task.getName());
			((TaskView)sceneManager.getView("Task")).setTask(task);
			sceneManager.changeScene("Task");
		});
		root.getChildren().add(editBtn);
		AnchorPane.setRightAnchor(editBtn, editBtn.getWidth() + 40);
		
		return root;
	}

	@Override
	public Scene getScene() {
		BorderPane root = new BorderPane();
		root.getStyleClass().add("body");
		
		// Center pane
		VBox centerPane = new VBox();
		centerPane.setPrefWidth(400);
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.prefWidthProperty().bind(centerPane.prefWidthProperty());
		
//		centerPane.getChildren().add(new Text("Tasks:"));
		centerPane.getChildren().add(scrollPane);
		
		VBox taskList = new VBox();
		taskList.prefWidthProperty().bind(scrollPane.prefWidthProperty());
		ArrayList<Task> tasks = taskManager.getTodaysTasks();
		
		if (tasks.size() != 0) {
			for (int i = 0; i < tasks.size(); i++) {
				Pane taskPane = createTask(tasks.get(i), i % 2 == 1);
				taskPane.prefWidthProperty().bind(taskList.prefWidthProperty());
				taskList.getChildren().add(taskPane);
			}
			
		} else {
			Label emptyLbl = new Label("No tasks!");
			emptyLbl.getStyleClass().add("title");
			emptyLbl.setTextAlignment(TextAlignment.CENTER);
			taskList.getChildren().add(emptyLbl);
		}
		
		scrollPane.setContent(taskList);
		root.setCenter(centerPane);
		BorderPane.setMargin(centerPane, new Insets(20));
		
		// Right pane
		VBox rightPane = new VBox();
		Label motivateLbl = new Label("");
		motivateLbl.setPrefWidth(100);
		motivateLbl.setMaxWidth(100);
		motivateLbl.setWrapText(true);
		
		Button motivateBtn = new Button("Motivate");
		motivateBtn.setOnAction((ActionEvent event) -> {
			motivateLbl.setText(motivationController.getRandomMessage());
		});
		
		rightPane.getChildren().add(motivateBtn);
		rightPane.getChildren().add(motivateLbl);
		
		root.setRight(rightPane);
		BorderPane.setMargin(rightPane, new Insets(20, 20, 0, 0));
		
		// Bottom pane
		StackPane bottomPane = new StackPane();
		
		Button newTaskBtn = new Button("New Task");
		newTaskBtn.setPrefWidth(640);
		newTaskBtn.setMinHeight(40);
		newTaskBtn.setOnAction((ActionEvent event) -> {
			((TaskView)sceneManager.getView("Task")).setTask(null);
			sceneManager.changeScene("Task");
		});
		bottomPane.getChildren().add(newTaskBtn);
		
		root.setBottom(bottomPane);
		BorderPane.setMargin(bottomPane, new Insets(0, 20, 20, 20));
		
		Scene scene = new Scene(root, 640, 480);
		scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Ubuntu&display=swap");
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		return scene;
	}
}
