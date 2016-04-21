package tuman.gs_test.ep.gl;



import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GLContext;



/**
 * Сцена OpenGL.
 * @author Sergei Tumanov
 */
public class GLScene implements IGLGroup {

	/** Камера. */
	private GLCamera camera;
	/** Элементы сцены. */
	private final List<IGLObject> children = new ArrayList<>();



	/**
	 * Create new instance.
	 */
	public GLScene() {}



	/**
	 * Получить камеру.
	 * @return Камера.
	 */
	public GLCamera getCamera() {
		return camera;
	}

	/**
	 * Задать камеру.
	 * @param camera Камера.
	 */
	public void setCamera(GLCamera camera) {
		this.camera = camera;
	}

	@Override
	public List<IGLObject> getChildren() {
		return children;
	}



	@Override
	public void paint(GLContext glContext) {
		if (camera != null)
			camera.paint(glContext);
		IGLGroup.super.paint(glContext);
	}

}
