package tuman.gs_test;



import java.util.Arrays;



/**
 * Аквариум.
 * @author Sergei Tumanov.
 */
public class Acuario {

	/**
	 * Колонка аквариума.
	 * @author Sergei Tumanov.
	 */
	private static class Column {

		/** Количество клеток основания. */
		public int ground;
		/** Количество клеток воды. */
		public int water;

		/**
		 * Создание экземпляра.
		 * @param ground Количество клеток основания.
		 * @param water Количество клеток воды.
		 */
		public Column(int ground, int water) {
			this.ground = ground;
			this.water = water;
		}

	}



	/** Глубина аквариума. */
	private int depth;
	/** Содержимое. */
	private Column[] contents;



	/**
	 * Создание кземпляра.
	 */
	public Acuario() {}

	/**
	 * Создание кземпляра.
	 * @param bottom высоты дна.
	 */
	public Acuario(int[] bottom) {
		setBottom(bottom);
		fill();
	}



	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			int h = depth - i - 1;
			for (Column column: contents)
				sb.append(h >= column.ground + column.water ? '.' : h >= column.ground ? '~' : '#');
			sb.append('\n');
		}
		return sb.toString();
	}



	/**
	 * Задать основание.
	 * @param bottom высоты основания.
	 */
	public void setBottom(int[] bottom) {
		depth = Arrays.stream(bottom).max().orElse(0);
		contents = Arrays.stream(bottom)
				.mapToObj(b -> new Column(b >= 0 ? b: 0, 0))
				.toArray(Column[]::new);
	}

	/**
	 * Заполнить аквариум водой до верху.
	 */
	public void fill() {
		for (Column column: contents)
			column.water = depth - column.ground;
	}

}
