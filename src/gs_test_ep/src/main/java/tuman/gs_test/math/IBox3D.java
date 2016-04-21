package tuman.gs_test.math;



/**
 * Параллелепипед в пространстве.
 * @author Sergei Tumanov
 */
public interface IBox3D {

	/**
	 * Получить положение параллелепипеда.
	 * @return Положение параллелепипеда.
	 */
	IPoint3D getPosition();

	/**
	 * Получить размер параллелепипеда.
	 * @return Размер параллелепипеда.
	 */
	IPoint3D getSize();

}
