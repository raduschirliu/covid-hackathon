package com.mmpp.motivationapp.ui.views;

import java.util.ArrayList;

import com.mmpp.motivationapp.backend.Task;
import com.mmpp.motivationapp.controllers.AchieverController;
import com.mmpp.motivationapp.controllers.MotivationController;
import com.mmpp.motivationapp.controllers.TaskListManager;
import com.mmpp.motivationapp.ui.SceneManager;
import com.mmpp.motivationapp.ui.SceneView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class MainView extends SceneView {
	private TaskListManager taskManager;
	private MotivationController motivationController;
	private AchieverController achieverController;
	private StringProperty motivateText;
	
	public MainView(
		SceneManager sceneManager,
		TaskListManager taskManager,
		MotivationController motivationController,
		AchieverController achieverController
	) {
		super("Main", sceneManager);
		this.taskManager = taskManager;
		this.motivationController = motivationController;
		this.setAchieverController(achieverController);
		motivateText = new SimpleStringProperty("");
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
		if (darkBg) check.getStyleClass().add("white-bg");
		check.setSelected(task.getIsComplete());
		check.setMinHeight(25.0);
		check.setOnAction((ActionEvent e) -> {
			task.setComplete(!task.getIsComplete());
			motivateText.set(motivationController.getRandomMessage());
			
			if (task.getIsComplete()) {
				root.getStyleClass().add("completed");
				getAchieverController().getAchiever().setLifetimeCompleted(getAchieverController().getAchiever().getLifetimeCompleted() + 1);
			} else {
				getAchieverController().getAchiever().setLifetimeCompleted(getAchieverController().getAchiever().getLifetimeCompleted() - 1);
				root.getStyleClass().remove("completed");
			}
			
			getAchieverController().update();
		});
		root.getChildren().add(check);
		AnchorPane.setLeftAnchor(check, 0.0);
		
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
		
		Label nameLbl = new Label(task.getName());
		nameLbl.setPrefWidth(250);
		nameLbl.setMinHeight(25);
		nameLbl.setPadding(new Insets(0, 20, 0, 20));
		nameLbl.setPadding(new Insets(0));
		root.getChildren().add(nameLbl);
		AnchorPane.setLeftAnchor(nameLbl, 30.0);
		AnchorPane.setRightAnchor(nameLbl, editBtn.getWidth() + 90);
		
		return root;
	}

	@Override
	public Scene getScene() {
		BorderPane root = new BorderPane();
		root.getStyleClass().add("body");
		
		// Top pane
		AnchorPane topPane = new AnchorPane();
		
		ImageView logoV = new ImageView();
		Image logo = new Image(getClass().getResourceAsStream("/logo.png"), 350, 131, true, true);
		logoV.setImage(logo);
		logoV.setCache(true);
		logoV.setSmooth(true);
		topPane.getChildren().add(logoV);
		AnchorPane.setLeftAnchor(logoV, 25.0);
		
		Image trophyImage = new Image(getClass().getResourceAsStream("/trophy.png"), 24, 24, true, true);
		ImageView trophyImageView = new ImageView();
		trophyImageView.setImage(trophyImage);
		trophyImageView.setCache(true);
		trophyImageView.setSmooth(true);
		
		Button achievementBtn = new Button("Achievements", trophyImageView);
		achievementBtn.getStyleClass().add("small");
		achievementBtn.setOnAction((ActionEvent event) -> {
			sceneManager.changeScene("Achievement");
		});
		topPane.getChildren().add(achievementBtn);
		AnchorPane.setRightAnchor(achievementBtn, 40.0);
		AnchorPane.setTopAnchor(achievementBtn, 10.0);
		
		root.setTop(topPane);
		
		// Center pane
		ScrollPane centerPane = new ScrollPane();
		centerPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
		VBox taskList = new VBox();
		taskList.prefWidthProperty().bind(centerPane.widthProperty());
		ArrayList<Task> tasks = taskManager.getTodaysTasks();
		
		if (tasks.size() != 0) {
			for (int i = 0; i < tasks.size(); i++) {
				Pane taskPane = createTask(tasks.get(i), i % 2 == 1);
				taskList.getChildren().add(taskPane);
			}
		} else {
			Label emptyLbl = new Label("Create a Task to get started!");
			emptyLbl.getStyleClass().add("title");
			emptyLbl.setTextAlignment(TextAlignment.CENTER);
			taskList.getChildren().add(emptyLbl);
		}
		
		centerPane.setContent(taskList);
		root.setCenter(centerPane);
		BorderPane.setMargin(centerPane, new Insets(20));
		
		// Right pane
		VBox rightPane = new VBox();
		Label motivateLbl = new Label("");
		motivateLbl.setPrefWidth(100);
		motivateLbl.setWrapText(true);
		motivateLbl.textProperty().bind(motivateText);
		motivateLbl.setPadding(new Insets(20, 0, 0, 0));
		
		Button motivateBtn = new Button("Motivate");
		motivateBtn.setOnAction((ActionEvent event) -> {
			motivateText.set(motivationController.getRandomMessage());
		});
		
		rightPane.getChildren().add(motivateBtn);
		rightPane.getChildren().add(motivateLbl);
		
		root.setRight(rightPane);
		BorderPane.setMargin(rightPane, new Insets(20, 20, 0, 0));
		
		// Bottom pane
		HBox bottomPane = new HBox();
		
		Button newTaskBtn = new Button("New Task");
		newTaskBtn.setPrefWidth(600);
		newTaskBtn.setMinHeight(40);
		newTaskBtn.setOnAction((ActionEvent event) -> {
			motivateText.set(motivationController.getRandomMessage());
			((TaskView)sceneManager.getView("Task")).setTask(null);
			sceneManager.changeScene("Task");
		});
		bottomPane.getChildren().add(newTaskBtn);
		
		root.setBottom(bottomPane);
		BorderPane.setMargin(bottomPane, new Insets(0, 20, 20, 20));
		
		Scene scene = new Scene(root, 640, 480);
		scene.setOnKeyPressed((KeyEvent event) -> {
			if (event.getCode() == KeyCode.BACK_QUOTE) {
				achieverController.getAchiever().setLifetimeCompleted(1250);
				achieverController.update();
				System.out.println("Unlocked all achievements");
			}
		});
		scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Ubuntu&display=swap");
		scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
		return scene;
	}

	public AchieverController getAchieverController() {
		return achieverController;
	}

	public void setAchieverController(AchieverController achieverController) {
		this.achieverController = achieverController;
	}
}
