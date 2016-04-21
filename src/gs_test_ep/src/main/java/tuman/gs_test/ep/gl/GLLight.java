package tuman.gs_test.ep.gl;



import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLContext;



/**
 * Источник света.
 * @author Sergei Tumanov
 */
public class GLLight implements IGLObject {

	@Override
	public void paint(GLContext glContext) {
		GL2 gl = glContext.getGL().getGL2();

		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glColorMaterial(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE);

		// TODO Убрать хардкод.

		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, new float[] {0.2f, 0.2f, 0.2f, 0.2f}, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, new float[] {0.5f, 0.5f, 0.5f, 0.5f}, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, new float[] {300.0f, 500.0f, 1000.0f, 1.0f}, 0);
		gl.glEnable(GL2.GL_LIGHT0);
	}

}
