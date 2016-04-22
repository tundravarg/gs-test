package tuman.gs_test.ep.gl;



import com.jogamp.opengl.GL2;



/**
 * Вспомогательные функции для работы с OpenGL.
 * @author Sergei Tumanov
 */
public final class GLUtils {

	/**
	 * Create new instance.
	 */
	private GLUtils() {}



	/**
	 * Установить цвет.
	 * @param gl Контекст OpenGL.
	 * @param argb Цвет (ARGB).
	 */
	public static void glColor1i(GL2 gl, int argb) {
		gl.glColor4d(((argb >>> 16) & 0xFF) / 255.0, ((argb >>> 8) & 0xFF) / 255.0, (argb & 0xFF) / 255.0, ((argb >>> 24) & 0xFF) / 255.0);
	}

}
