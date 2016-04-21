package tuman.gs_test.ep.gl;



import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.glu.GLU;



/**
 * Камера.
 * @author Sergei Tumanov
 */
public class GLCamera {

	/** Растояние до цели. */
	private double distance;
	/** Рыскание (град.). */
	private double yaw;
	/** Тангаж (град.). */
	private double pitch;
	/** Ширина вида (px). */
	private int viewWidth;
	/** Высота вида (px). */
	private int viewHeight;



	/**
	 * Create new instance.
	 */
	public GLCamera() {}

	/**
	 * Create new instance.
	 * @param distance Растояние до цели.
	 * @param yaw Рыскание (град.).
	 * @param pitch Тангаж (град.).
	 * @param viewWidth Ширина вида (px).
	 * @param viewHeight Высота вида (px).
	 */
	public GLCamera(double distance, double yaw, double pitch, int viewWidth, int viewHeight) {
		this.distance = distance;
		this.yaw = yaw;
		this.pitch = pitch;
		this.viewWidth = viewWidth;
		this.viewHeight = viewHeight;
	}



	/**
	 * Применить параметры камеры.
	 * @param glContext Контекст OpenGL.
	 */
	public void paint(GLContext glContext) {
		GL2 gl = glContext.getGL().getGL2();

		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
//		gl.glOrtho(-viewWidth / 2, viewWidth / 2, -viewHeight / 2, viewHeight / 2, -1000, 1000);
		GLU glu = new GLU();
		glu.gluPerspective(60.0, (double)viewWidth / viewHeight, 1.0, 10000.0);

		gl.glTranslated(0.0, 0.0, -distance);
		gl.glRotated(pitch, 1.0, 0.0, 0.0);
		gl.glRotated(yaw, 0.0, 0.0, -1.0);
	}



	/**
	 * Получить расстояние до цели.
	 * @return Расстояние до цели.
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Задать расстояние до цели.
	 * @param distance Расстояние до цели.
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * Получить рыскание.
	 * @return Рыскание.
	 */
	public double getYaw() {
		return yaw;
	}

	/**
	 * Задать рыскание.
	 * @param yaw Рыскание.
	 */
	public void setYaw(double yaw) {
		this.yaw = yaw;
	}

	/**
	 * Получить тангаж.
	 * @return Тангаж.
	 */
	public double getPitch() {
		return pitch;
	}

	/**
	 * Задать тангаж.
	 * @param pitch Тангаж.
	 */
	public void setPitch(double pitch) {
		this.pitch = pitch;
	}

	/**
	 * Получить ширину вида.
	 * @return Ширина вида.
	 */
	public int getViewWidth() {
		return viewWidth;
	}

	/**
	 * Задать ширину вида.
	 * @param viewWidth Ширина вида.
	 */
	public void setViewWidth(int viewWidth) {
		this.viewWidth = viewWidth;
	}

	/**
	 * Получить высоту вида.
	 * @return Высота вида.
	 */
	public int getViewHeight() {
		return viewHeight;
	}

	/**
	 * Задать высота вида.
	 * @param viewHeight Высота вида.
	 */
	public void setViewHeight(int viewHeight) {
		this.viewHeight = viewHeight;
	}

	/**
	 * Задать размер вида.
	 * @param viewWidth Ширина вида.
	 * @param viewHeight Высота вида.
	 */
	public void setViewSize(int viewWidth, int viewHeight) {
		this.viewWidth = viewWidth;
		this.viewHeight = viewHeight;
	}



	/**
	 * Приблизить/отдалить камеру.
	 * @param dt Смещение вдоль оси наблюдения.
	 * @return Эта камера.
	 */
	public GLCamera translate(double dt) {
		distance += dt;
		return this;
	}

	/**
	 * Повернуть камеру относительно цели.
	 * @param dYaw Приращение рыскания.
	 * @param dPitch Приращение тангажа.
	 * @return Эта камера.
	 */
	public GLCamera rotate(double dYaw, double dPitch) {
		yaw += dYaw;
		pitch += dPitch;
		return this;
	}

}
