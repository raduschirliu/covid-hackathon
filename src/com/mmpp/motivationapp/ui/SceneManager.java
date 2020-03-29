package com.mmpp.motivationapp.ui;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
	private Stage stage;
	private String currentScene;
	private Map<String, SceneView> views;
	
	public SceneManager(Stage stage) {
		this.stage = stage;
		views = new HashMap<String, SceneView>();
	}
	
	public void changeScene(String name) {
		Scene scene = getScene(name);
		
		if (scene == null) {
			System.err.println("Error, scene does not exist: " + name);
			return;
		}
		
		stage.setScene(scene);
		currentScene = name;
	}
	
	public void reloadScene() {
		changeScene(currentScene);
	}
	
	public void registerScene(SceneView view) {
		if (views.containsKey(view.getSceneName())) {
			System.err.println("Error, cannot have duplicate scene name: " + view.getSceneName());
			return;
		}
		
		views.put(view.getSceneName(), view);
	}
	
	public Scene getScene(String name) {
		return views.get(name).getScene();
	}
	
	public SceneView getView(String name) {
		return views.get(name);
	}
}
