package com.mmpp.motivationapp.ui;

import javafx.scene.Scene;

public abstract class SceneView {
	protected SceneManager sceneManager;
	
	public SceneView(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	public abstract Scene getScene();
}
