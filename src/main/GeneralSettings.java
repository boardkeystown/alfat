package main;

import org.lwjgl.util.vector.Vector3f;
import utils.MyFile;

//import utils.MyFile;

/**
 * Just some configs. File locations mostly.
 * 
 * @author Karl
 *
 */
public class GeneralSettings {
	//**************Icons*******************
	public static final MyFile ICON_LOCATION = new MyFile("/res/icon/");

	//*********************Shaders****************************
	public static final MyFile GUI_VERTEX = new MyFile("/rendering/guis/guiVertexShader.glsl");
	public static final MyFile GUI_FRAGMENT = new MyFile("/rendering/guis/guiFragmentShader.glsl");
	public static final MyFile FONT_VERTEX = new MyFile("/rendering/text/fontVertex.glsl");
	public static final MyFile FONT_FRAGMENT = new MyFile("/rendering/text/fontFragment.glsl");
	public static final MyFile TEXT_BOX_VERTEX = new MyFile("/rendering/textBox/boxVertex.glsl");
	public static final MyFile TEXT_BOX_FRAGMENT = new MyFile("/rendering/textBox/boxFragment.glsl");
	public static final MyFile CURSOR_VERTEX = new MyFile("/rendering/cursor/cursorVertex.glsl");
	public static final MyFile CURSOR_FRAGMENT = new MyFile("/rendering/cursor/cursorFragment.glsl");
	public static final MyFile SELECTION_VERTEX = new MyFile("/rendering/selection/vertex.glsl");
	public static final MyFile SELECTION_FRAGMENT = new MyFile("/rendering/selection/fragment.glsl");


    public static final int DISPLAY_WIDTH = 1280;
	public static final int DISPLAY_HEIGHT = 720;


    public static float delta;
	public static long lastFrameTime = System.currentTimeMillis();

	public static void update() {
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime) / 1000f;
		lastFrameTime = currentFrameTime;
	}

	private static long getCurrentTime() {
		return System.currentTimeMillis();
	}

	public static float getFrameTime(){
		return delta;
	}

	public static final float RAPID_MODE_DURATION = 1.0f;
	public static final int RAPID_MODE_FREQUENCY = 3;
	public static final int DEFAULT_TAB_WIDTH = 4;
	public static final Vector3f HIGHLIGHT_COLOR = new Vector3f(0, 0.7f, 0.93f);
	public static final float TEXT_BOX_BORDER_WIDTH = 0.003f;

}