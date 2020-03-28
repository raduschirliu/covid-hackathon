package com.mmpp.motivationapp.ui;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
	private Stage stage;
	private Map<String, Scene> scenes;
	
	public SceneManager(Stage stage) {
		this.stage = stage;
		scenes = new HashMap<String, Scene>();
	}
	
	public void changeScene(String name) {
		stage.setScene(getScene(name));
	}
	
	public void registerScene(String name, SceneView view) {
		scenes.put(name, view.getScene());
	}
	
	public Scene getScene(String name) {
		return scenes.get(name);
	}
}
