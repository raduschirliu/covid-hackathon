package com.mmpp.motivationapp.ui;

import javafx.scene.Scene;

public abstract class SceneView {
	protected String sceneName;
	protected SceneManager sceneManager;
	
	public SceneView(String sceneName, SceneManager sceneManager) {
		this.sceneName = sceneName;
		this.sceneManager = sceneManager;
	}
	
	public abstract Scene getScene();
	
	public String getSceneName() {
		return sceneName;
	}
}
