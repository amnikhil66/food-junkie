package com.foodjunkie.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.foodjunkie.game.FoodJunkie;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Food Junkie";
		config.height = 800;
		config.width = 400;

		new LwjglApplication(new FoodJunkie(), config);
	}
}
