package com.mmpp.motivationapp.ui.views;

import com.mmpp.motivationapp.controllers.MotivationController;
import com.mmpp.motivationapp.controllers.TaskListManager;
import com.mmpp.motivationapp.ui.SceneManager;
import com.mmpp.motivationapp.ui.SceneView;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainView extends SceneView {
	private TaskListManager taskManager;
	private MotivationController motivationController;
	
	public MainView(SceneManager sceneManager, TaskListManager taskManager, MotivationController motivationController) {
		super("Main", sceneManager);
		this.taskManager = taskManager;
		this.motivationController = motivationController;
	}

	@Override
	public Scene getScene() {
		BorderPane root = new BorderPane();
		
		// Center pane
		VBox centerPane = new VBox();
		ScrollPane scrollPane = new ScrollPane();
		
		centerPane.getChildren().add(new Text("Tasks:"));
		centerPane.getChildren().add(scrollPane);
		
		VBox taskList = new VBox();
		
		for (int i = 0; i < 50; i++) {
			Button btn = new Button("Test task");
			btn.setPrefWidth(300);
			
			taskList.getChildren().add(btn);
		}
		
		scrollPane.setContent(taskList);
		root.setCenter(centerPane);
		BorderPane.setMargin(centerPane, new Insets(20));
		
		// Right pane
		VBox rightPane = new VBox();
		Text motivateText = new Text("");
		
		Button motivateBtn = new Button("Motivate");
		motivateBtn.setOnAction((ActionEvent event) -> {
			motivateText.setText(motivationController.getRandomMessage());
		});
		
		rightPane.getChildren().add(motivateText);
		rightPane.getChildren().add(motivateBtn);
		
		root.setRight(rightPane);
		BorderPane.setMargin(rightPane, new Insets(20, 20, 0, 0));
		
		// Bottom pane
		StackPane bottomPane = new StackPane();
		
		Button newTaskBtn = new Button("New Task");
		newTaskBtn.setPrefWidth(640);
		newTaskBtn.setMinHeight(40);
		newTaskBtn.setOnAction((ActionEvent event) -> {
			sceneManager.changeScene("NewTask");
		});
		bottomPane.getChildren().add(newTaskBtn);
		
		root.setBottom(bottomPane);
		BorderPane.setMargin(bottomPane, new Insets(0, 20, 20, 20));
		
		return new Scene(root, 640, 480);
	}
}
