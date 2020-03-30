package com.mmpp.motivationapp;

import com.mmpp.motivationapp.controllers.AchieverController;
import com.mmpp.motivationapp.controllers.MotivationController;
import com.mmpp.motivationapp.controllers.TaskListManager;
import com.mmpp.motivationapp.ui.SceneManager;
import com.mmpp.motivationapp.ui.views.AchievementView;
import com.mmpp.motivationapp.ui.views.MainView;
import com.mmpp.motivationapp.ui.views.TaskView;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 * The main front-end of the COVID Hackathon Task List and Motivation Application
 * @author Radu Schirliu
 *
 */
public class MotivationApp extends Application {
	SceneManager sceneManager;
	TaskListManager taskManager;
	MotivationController motivationController;
	AchieverController achieverController;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		motivationController = new MotivationController();
		taskManager = new TaskListManager(motivationController);
		achieverController = new AchieverController();
		
		sceneManager = new SceneManager(stage);
		sceneManager.registerScene(new MainView(sceneManager, taskManager, motivationController));
		sceneManager.registerScene(new TaskView(sceneManager, taskManager));
		sceneManager.registerScene(new AchievementView(sceneManager, achieverController));

		sceneManager.changeScene("Main");
		
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
		stage.setTitle("MotivateMate");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	
	@Override
	public void stop() {
		taskManager.saveListsToFile();
		achieverController.saveAchievementsToFile();
	}
}
