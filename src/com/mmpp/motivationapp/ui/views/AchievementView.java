package com.mmpp.motivationapp.ui.views;

import com.mmpp.motivationapp.backend.Achievement;
import com.mmpp.motivationapp.controllers.AchieverController;
import com.mmpp.motivationapp.ui.SceneManager;
import com.mmpp.motivationapp.ui.SceneView;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class AchievementView extends SceneView {
	private AchieverController achieverController;
	
	public AchievementView(SceneManager sceneManager, AchieverController achieverController) {
		super("Achievement", sceneManager);
		this.achieverController = achieverController;
	}
	
	public Pane createAchievement(Achievement achievement) {
		VBox root = new VBox();
		root.setMaxWidth(200);
		root.setSpacing(10);
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.CENTER);
		root.getStyleClass().add("achievement");
		
		Label nameLbl = new Label(achievement.getName());
		nameLbl.setWrapText(true);
		nameLbl.getStyleClass().add("achievement-name");
		nameLbl.setStyle("-fx-border-color: " + achievement.getColour() + ";");
		nameLbl.setTextAlignment(TextAlignment.CENTER);
		root.getChildren().add(nameLbl);
		
		Image image = new Image(getClass().getResourceAsStream("/trophy.png"), 32, 32, true, true);		
		ImageView imageView = new ImageView(image);
		imageView.setCache(true);
		imageView.setSmooth(true);
		root.getChildren().add(imageView);
		
		Label descLbl = new Label(achievement.getDescription());
		descLbl.setWrapText(true);
		descLbl.setTextAlignment(TextAlignment.CENTER);
		root.getChildren().add(descLbl);
		
		return root;
	}

	@Override
	public Scene getScene() {
		BorderPane root = new BorderPane();
		root.getStyleClass().add("body");
		
		Label titleLbl = new Label("Achievements");
		titleLbl.setMinWidth(640);
		titleLbl.setMinHeight(40);
		titleLbl.setPadding(new Insets(10, 20, 0, 20));
		titleLbl.getStyleClass().add("title");
		titleLbl.setTextAlignment(TextAlignment.CENTER);
		root.setTop(titleLbl);
		
		ScrollPane centerPane = new ScrollPane();
		centerPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
		VBox achievementPane = new VBox();
		achievementPane.prefWidthProperty().bind(centerPane.widthProperty());
		achievementPane.setAlignment(Pos.CENTER);
		achievementPane.setSpacing(10);
		
		for (Achievement achievement : achieverController.getAchiever().getMyAchievements()) {
			Pane pane = createAchievement(achievement);
			achievementPane.getChildren().add(pane);
		}
		
		centerPane.setContent(achievementPane);
		root.setCenter(centerPane);
		BorderPane.setMargin(centerPane, new Insets(20));
		
		// Bottom pane
		HBox bottomPane = new HBox();
		
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
		scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
		return scene;
	}
}
