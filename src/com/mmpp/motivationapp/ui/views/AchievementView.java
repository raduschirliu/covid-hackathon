package com.mmpp.motivationapp.ui.views;

import com.mmpp.motivationapp.backend.Achievement;
import com.mmpp.motivationapp.controllers.AchieverController;
import com.mmpp.motivationapp.ui.SceneManager;
import com.mmpp.motivationapp.ui.SceneView;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AchievementView extends SceneView {
	private AchieverController achieverController;
	
	public AchievementView(SceneManager sceneManager, AchieverController achieverController) {
		super("Achievement", sceneManager);
		this.achieverController = achieverController;
	}
	
	public Pane createAchievement(Achievement achievement) {
		StackPane pane = new StackPane();
		
		return pane;
	}

	@Override
	public Scene getScene() {
		BorderPane root = new BorderPane();
		achieverController.getAchiever().setLifetimeCompleted(1250);
		
		VBox centerPane = new VBox();
		
		for (Achievement achievement : achieverController.getAchiever().getAllAchievements()) {
			Pane pane = createAchievement(achievement);
			centerPane.getChildren().add(centerPane);
		}
		
		root.setCenter(centerPane);
		
		// Bottom pane
		HBox bottomPane = new HBox();
		bottomPane.setSpacing(20);
		
		Button backBtn = new Button("Back");
		backBtn.setPrefSize(640, 40);
		backBtn.setOnAction((ActionEvent event) -> {
			sceneManager.changeScene("Main");
		});
		bottomPane.getChildren().add(backBtn);
		
		root.setBottom(bottomPane);
		BorderPane.setMargin(bottomPane, new Insets(0, 20, 20, 20));
		
		Scene scene = new Scene(root, 640, 480);
		scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Ubuntu&display=swap");
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		return scene;
	}
}
