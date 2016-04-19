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
		pourOut();
	}



	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			int h = depth - i - 1;
			for (Column column: contents)
				sb.append(h >= column.ground + column.water ? ' ' : h >= column.ground ? '~' : '@');
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

	/**
	 * Слить воду в дырки.
	 */
	public void pourOut() {
		int ph[] = new int[1];
		ph[0] = 0;
		for (int i = 0, n = contents.length; i < n; i++)
			pourOut(contents[i], ph);
		ph[0] = 0;
		for (int i = contents.length - 1; i >= 0; i--)
			pourOut(contents[i], ph);
	}

	/**
	 * Слить воду.
	 * @param column Колонка.
	 * @param ph Ссылка на текущий уровень.
	 */
	private void pourOut(Column column, int[] ph) {
		int h = ph[0];
		if (column.ground == 0) {
			column.water = 0;
			ph[0] = 0;
		} else if (column.ground >= h) {
			column.water = 0;
			ph[0] = column.ground;
		} else if (column.ground + column.water > h) {
			column.water = h - column.ground;
		}
	}

}
