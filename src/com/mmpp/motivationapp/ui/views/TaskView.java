package com.mmpp.motivationapp.ui.views;

import com.mmpp.motivationapp.backend.Task;
import com.mmpp.motivationapp.ui.SceneManager;
import com.mmpp.motivationapp.ui.SceneView;

import javafx.scene.Scene;

public class TaskView extends SceneView {
	private Task task;
	
	public TaskView(SceneManager sceneManager) {
		super("Task", sceneManager);
	}

	@Override
	public Scene getScene() {
		return null;
	}
	
	public void setTask(Task task) {
		this.task = task;
	}
}
