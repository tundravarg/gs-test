package tuman.gs_test.math;



/**
 * Параллелепипед в пространстве.
 * @author Sergei Tumanov
 */
public class Box3D implements IBox3D {

	/** Положение параллелепипеда. */
	private Point3D position = new Point3D();
	/** Размер параллелепипеда. */
	private Point3D size = new Point3D();



	/**
	 * Create new instance.
	 */
	public Box3D() {}

	/**
	 * Create new instance.
	 * @param positionX Координата X.
	 * @param positionY Координата Y.
	 * @param positionZ Координата Z.
	 * @param sizeX Размер по X.
	 * @param sizeY Размер по Y.
	 * @param sizeZ Размер по Z.
	 */
	public Box3D(double positionX, double positionY, double positionZ, double sizeX, double sizeY, double sizeZ) {
		this.position.init(positionX, positionY, positionZ);
		this.size.init(sizeX, sizeY, sizeZ);
	}

	/**
	 * Create new instance.
	 * @param position Положение.
	 * @param size Размер.
	 */
	public Box3D(IPoint3D position, IPoint3D size) {
		this.position.init(position);
		this.size.init(size);
	}

	/**
	 * Create new instance.
	 * @param box Другой параллелепипед.
	 */
	public Box3D(IBox3D box) {
		this.position.init(box.getPosition());
		this.size.init(box.getSize());
	}



	/**
	 * Инициализация.
	 * @return Этот параллелепипед.
	 */
	public Box3D init() {
		return init(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
	}

	/**
	 * Инициализация.
	 * @param positionX Координата X.
	 * @param positionY Координата Y.
	 * @param positionZ Координата Z.
	 * @param sizeX Размер по X.
	 * @param sizeY Размер по Y.
	 * @param sizeZ Размер по Z.
	 * @return Этот параллелепипед.
	 */
	public Box3D init(double positionX, double positionY, double positionZ, double sizeX, double sizeY, double sizeZ) {
		this.position.init(positionX, positionY, positionZ);
		this.size.init(sizeX, sizeY, sizeZ);
		return this;
	}

	/**
	 * Инициализация.
	 * @param position Положение.
	 * @param size Размер.
	 * @return Этот параллелепипед.
	 */
	public Box3D init(IPoint3D position, IPoint3D size) {
		this.position.init(position);
		this.size.init(size);
		return this;
	}

	/**
	 * Инициализация.
	 * @param box Другой параллелепипед.
	 * @return Этот параллелепипед.
	 */
	public Box3D init(IBox3D box) {
		this.position.init(box.getPosition());
		this.size.init(box.getSize());
		return this;
	}



	@Override
	public Point3D getPosition() {
		return position;
	}

	/**
	 * Задать положение.
	 * @param positionX Координата X.
	 * @param positionY Координата Y.
	 * @param positionZ Координата Z.
	 * @return Этот параллелепипед.
	 */
	public Box3D setPosition(double positionX, double positionY, double positionZ) {
		this.position.init(positionX, positionY, positionZ);
		return this;
	}

	/**
	 * Задать положение.
	 * @param position Положение.
	 * @return Этот параллелепипед.
	 */
	public Box3D setPosition(Point3D position) {
		this.position.init(position);
		return this;
	}

	@Override
	public Point3D getSize() {
		return size;
	}

	/**
	 * Задать размер.
	 * @param sizeX Размер по X.
	 * @param sizeY Размер по Y.
	 * @param sizeZ Размер по Z.
	 * @return Этот параллелепипед.
	 */
	public Box3D setSize(double sizeX, double sizeY, double sizeZ) {
		this.position.init(sizeX, sizeY, sizeZ);
		return this;
	}

	/**
	 * Задать размер.
	 * @param size Размер.
	 * @return Этот параллелепипед.
	 */
	public Box3D setSize(Point3D size) {
		this.size.init(size);
		return this;
	}

}
