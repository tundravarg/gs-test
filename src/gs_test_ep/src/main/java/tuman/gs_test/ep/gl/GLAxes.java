package tuman.gs_test.ep.gl;



import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLContext;



/**
 * Оси координат.
 * @author Sergei Tumanov
 */
public class GLAxes implements IGLObject {

	/**
	 * Create new instance.
	 */
	public GLAxes() {}



	@Override
	public void paint(GLContext glContext) {
		GL2 gl = glContext.getGL().getGL2();

		gl.glDisable(GL2.GL_LIGHTING);

		gl.glLineWidth(2.0f);

		gl.glBegin(GL2.GL_LINES);
			gl.glColor3f(1f, 0f, 0f);
			gl.glVertex3f(-10.0F,  0.0F, 0.0F);
			gl.glVertex3f( 100.0F, 0.0F, 0.0F);
		gl.glEnd();

		gl.glBegin(GL2.GL_LINES);
			gl.glColor3f(0f, 1f, 0f);
			gl.glVertex3f(0.0F, -10.0F,  0.0F);
			gl.glVertex3f(0.0F,  100.0F, 0.0F);
		gl.glEnd();

		gl.glBegin(GL2.GL_LINES);
			gl.glColor3f(0f, 0f, 1f);
			gl.glVertex3f(0.0F, 0.0F, -10.0F);
			gl.glVertex3f(0.0F, 0.0F,  100.0F);
		gl.glEnd();
	}

}
