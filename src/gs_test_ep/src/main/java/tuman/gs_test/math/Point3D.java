package tuman.gs_test.math;



/**
 * Точка в пространстве.
 * @author Sergei Tumanov
 */
public class Point3D implements IPoint3D {

	/** Координата X. */
	private double x;
	/** Координата Y. */
	private double y;
	/** Координата Z. */
	private double z;



	/**
	 * Create new instance (0, 0, 0).
	 */
	public Point3D() {}

	/**
	 * Create new instance.
	 * @param x Координата X.
	 * @param y Координата Y.
	 * @param z Координата Z.
	 */
	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Create new instance.
	 * @param point Другая точка.
	 */
	public Point3D(IPoint3D point) {
		this.x = point.getX();
		this.y = point.getY();
		this.z = point.getZ();
	}



	/**
	 * Инициализировать точку.
	 * @return Эта точка.
	 */
	public Point3D init() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
		return this;
	}

	/**
	 * Инициализировать точку.
	 * @param x Координата X.
	 * @param y Координата Y.
	 * @param z Координата Z.
	 * @return Эта точка.
	 */
	public Point3D init(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	/**
	 * Инициализировать точку.
	 * @param point Другая точка.
	 * @return Эта точка.
	 */
	public Point3D init(IPoint3D point) {
		this.x = point.getX();
		this.y = point.getY();
		this.z = point.getZ();
		return this;
	}



	@Override
	public double getX() {
		return x;
	}

	/**
	 * Задать координату X.
	 * @param x Координата X.
	 * @return Эта точка.
	 */
	public Point3D setX(double x) {
		this.x = x;
		return this;
	}

	@Override
	public double getY() {
		return y;
	}

	/**
	 * Задать координату Y.
	 * @param y Координата Y.
	 * @return Эта точка.
	 */
	public Point3D setY(double y) {
		this.y = y;
		return this;
	}

	@Override
	public double getZ() {
		return z;
	}

	/**
	 * Задать координату Z.
	 * @param z Координата Z.
	 * @return Эта точка.
	 */
	public Point3D setZ(double z) {
		this.z = z;
		return this;
	}

}
