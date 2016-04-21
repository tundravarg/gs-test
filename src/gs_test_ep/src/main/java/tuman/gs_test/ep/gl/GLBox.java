package tuman.gs_test.ep.gl;



import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLContext;

import tuman.gs_test.math.Box3D;
import tuman.gs_test.math.IBox3D;



/**
 * Параллелепипед.
 * @author Sergei Tumanov
 */
public class GLBox implements IGLObject {

	/** Конфигурация. */
	private Box3D box = new Box3D();
	/** Цвет (ARGB). */
	private int color = 0xFF000000;



	/**
	 * Create new instance.
	 */
	public GLBox() {}

	/**
	 * Create new instance.
	 * @param box Конфигурация.
	 * @param color Цвет (ARGB).
	 */
	public GLBox(IBox3D box, int color) {
		this.box.init(box);
		this.color = color;
	}



	/**
	 * Получить конфигурацию.
	 * @return Конфигурация.
	 */
	public Box3D getBox() {
		return box;
	}

	/**
	 * Получить цвет (ARGB).
	 * @return Цвет (ARGB).
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Задать цвет (ARGB).
	 * @param color Цвет (ARGB).
	 */
	public void setColor(int color) {
		this.color = color;
	}



	@Override
	public void paint(GLContext glContext) {
		GL2 gl = glContext.getGL().getGL2();

		gl.glEnable(GL2.GL_LIGHTING);

		double px = box.getPosition().getX();
		double py = box.getPosition().getY();
		double pz = box.getPosition().getZ();
		double sx = box.getSize().getX();
		double sy = box.getSize().getY();
		double sz = box.getSize().getZ();

		GLUtils.glColor1i(gl, color);

		gl.glBegin(GL2.GL_TRIANGLE_FAN);
			gl.glNormal3d(0.0, 0.0, -1.0);
			gl.glVertex3d(px,      py,      pz);
			gl.glVertex3d(px + sx, py,      pz);
			gl.glVertex3d(px + sx, py + sy, pz);
			gl.glVertex3d(px,      py + sy, pz);
		gl.glEnd();
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
			gl.glNormal3d(0.0, -1.0, 0.0);
			gl.glVertex3d(px,      py,      pz);
			gl.glVertex3d(px,      py,      pz + sz);
			gl.glVertex3d(px + sx, py,      pz + sz);
			gl.glVertex3d(px + sx, py,      pz);
		gl.glEnd();
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
			gl.glNormal3d(-1.0, 0.0, 0.0);
			gl.glVertex3d(px,      py,      pz);
			gl.glVertex3d(px,      py + sy, pz);
			gl.glVertex3d(px,      py + sy, pz + sz);
			gl.glVertex3d(px,      py,      pz + sz);
		gl.glEnd();

		gl.glBegin(GL2.GL_TRIANGLE_FAN);
			gl.glNormal3d(0.0, 0.0, 1.0);
			gl.glVertex3d(px + sx, py + sy, pz + sz);
			gl.glVertex3d(px,      py + sy, pz + sz);
			gl.glVertex3d(px,      py,      pz + sz);
			gl.glVertex3d(px + sx, py,      pz + sz);
		gl.glEnd();
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
			gl.glNormal3d(0.0, 1.0, 0.0);
			gl.glVertex3d(px + sx, py + sy, pz + sz);
			gl.glVertex3d(px + sx, py + sy, pz);
			gl.glVertex3d(px,      py + sy, pz);
			gl.glVertex3d(px,      py + sy, pz + sz);
		gl.glEnd();
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
			gl.glNormal3d(1.0, 0.0, 0.0);
			gl.glVertex3d(px + sx, py + sy, pz + sz);
			gl.glVertex3d(px + sx, py,      pz + sz);
			gl.glVertex3d(px + sz, py,      pz);
			gl.glVertex3d(px + sx, py + sy, pz);
		gl.glEnd();
	}

}
