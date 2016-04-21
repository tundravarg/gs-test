package tuman.gs_test.ep.gl;



import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLContext;



/**
 * Инициализирующий объект.
 * @author Sergei Tumanov
 */
public class GLInit implements IGLObject {

	@Override
	public void paint(GLContext glContext) {
		GL2 gl = glContext.getGL().getGL2();

		gl.glClearColor(1f, 1f, 1f, 1f);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		gl.glEnable(GL2.GL_DEPTH_TEST);
	}

}
