package org.academiadecodigo.variachis.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.academiadecodigo.variachis.Alpha;
import org.academiadecodigo.variachis.Test;
import org.academiadecodigo.variachis.screen.MainMenuScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Freddie Santa";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new Test(), config);
	}
}
