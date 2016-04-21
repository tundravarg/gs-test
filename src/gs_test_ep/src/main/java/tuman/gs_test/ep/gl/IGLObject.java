package tuman.gs_test.ep.gl;



import com.jogamp.opengl.GLContext;



/**
 * Объект, который может взаимодействовать с OpenGL.
 * @author Sergei Tumanov
 */
public interface IGLObject {

	/**
	 * Отрисовать (применить) объект.
	 * @param glContext Контекст OpenGL.
	 */
	void paint(GLContext glContext);

}
