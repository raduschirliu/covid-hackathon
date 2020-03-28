package com.mmpp.motivationapp;

import com.mmpp.motivationapp.controllers.MotivationController;
import com.mmpp.motivationapp.controllers.TaskListManager;
import com.mmpp.motivationapp.ui.SceneManager;
import com.mmpp.motivationapp.ui.views.MainView;
import com.mmpp.motivationapp.ui.views.NewTaskView;

import javafx.application.Application;
import javafx.stage.Stage;

public class MotivationApp extends Application {
	SceneManager sceneManager;
	TaskListManager taskManager;
	MotivationController motivationController;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		taskManager = new TaskListManager();
		motivationController = new MotivationController();
		
		sceneManager = new SceneManager(stage);
		sceneManager.registerScene(new MainView(sceneManager, taskManager, motivationController));
		sceneManager.registerScene(new NewTaskView(sceneManager, taskManager));
		
		sceneManager.changeScene("Main");
		
		stage.setTitle("COVID Hackathon Motivator");
		stage.setResizable(false);
		stage.show();
	}
	
	@Override
	public void stop() {
		// TODO: Save everything when app stops
	}
}
