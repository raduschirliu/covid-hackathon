package com.mmpp.motivationapp;

import com.mmpp.motivationapp.ui.SceneManager;
import com.mmpp.motivationapp.ui.views.MainView;
import com.mmpp.motivationapp.ui.views.NewTaskView;

import javafx.application.Application;
import javafx.stage.Stage;

public class MotivationApp extends Application {
	SceneManager sceneManager;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		sceneManager = new SceneManager(stage);
		sceneManager.registerScene("Main", new MainView(sceneManager));
		sceneManager.registerScene("NewTask", new NewTaskView(sceneManager));
		
		sceneManager.changeScene("Main");
		
		stage.setTitle("COVID Hackathon Motivator");
		stage.setResizable(false);
		stage.show();
	}
}
