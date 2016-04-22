package tuman.gs_test.ep.gl;



import java.util.ArrayList;
import java.util.List;

import tuman.gs_test.Acuario;
import tuman.gs_test.math.Box3D;



/**
 * Аквариум.
 * @author Sergei Tumanov
 */
public class GLAcuario implements IGLGroup {

	/** Аквариум. */
	private Acuario acuario;
	/** Дочерние объекты. */
	private final List<IGLObject> children = new ArrayList<>();



	/**
	 * Create new instance.
	 */
	public GLAcuario() {}

	/**
	 * Create new instance.
	 * @param acuario Аквариум.
	 */
	public GLAcuario(Acuario acuario) {
		setAcuario(acuario);
	}



	/**
	 * Получить аквариум.
	 * @return Аквариум.
	 */
	public Acuario getAcuario() {
		return acuario;
	}

	/**
	 * Задать аквариум.
	 * <p>Обновление будет произведено автоматически.
	 * @param acuario Аквариум.
	 */
	public void setAcuario(Acuario acuario) {
		this.acuario = acuario;
		refresh();
	}

	@Override
	public List<IGLObject> getChildren() {
		return children;
	}



	/**
	 * Обновить объект.
	 */
	public void refresh() {
		children.clear();

		if (acuario == null) return;

		Acuario.Column[] contents = acuario.getContents();
		int numberOfColumns = contents.length;
//		int depth = acuario.getDepth();

		final double BOX_SIZE = 10.0;
		double acuarioLength = BOX_SIZE * numberOfColumns;

		double x0 = acuarioLength * -0.5;
		double y0 = BOX_SIZE * -0.5;
		double z0 = 0.0;

		for (int i = 0; i < numberOfColumns; i++) {
			Acuario.Column column = contents[i];
			double x = x0 + BOX_SIZE * i;

			int j = 0;
			for (int n = column.ground; j < n; j++) {
				children.add(new GLBox(new Box3D(x, y0, z0 + BOX_SIZE * j, BOX_SIZE, BOX_SIZE, BOX_SIZE), 0xFF808080));
			}
			for (int n = column.ground + column.water; j < n; j++) {
				children.add(new GLBox(new Box3D(x, y0, z0 + BOX_SIZE * j, BOX_SIZE, BOX_SIZE, BOX_SIZE), 0xFF0080FF));
			}
		}
	}

}
