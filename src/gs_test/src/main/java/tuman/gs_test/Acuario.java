package tuman.gs_test;



import java.util.Arrays;
import java.util.Random;



/**
 * Аквариум.
 * @author Sergei Tumanov.
 */
public class Acuario {

	/**
	 * Колонка аквариума.
	 * @author Sergei Tumanov.
	 */
	public static class Column {

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



	/**
	 * Генератор чисел.
	 */
	private static Random random = new Random(System.currentTimeMillis());



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
	 * Получить содержимое аквариума.
	 * @return Содержимое аквариума.
	 */
	public Column[] getContents() {
		return contents;
	}

	/**
	 * Получить глубину аквариума.
	 * @return Глубина аквариума.
	 */
	public int getDepth() {
		return depth;
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
	 * Задать основание.
	 * @param str высоты основания.
	 * <p>В формате str ::= [ &lt;int&gt; { { ',' | ';' | &lt;space&gt; } &lt;int&gt; } ].
	 */
	public void setBottom(String str) {
		String[] elements = str.split("[,;\\s]+");
		try {
			int[] bottom = Arrays.stream(elements)
					.filter(e -> !e.isEmpty())
					.mapToInt(Integer::parseInt)
					.toArray();
			setBottom(bottom);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid format. Must be [ <int> { { ',' | ';' | <space> } <int> } ]. (123, 456;,  ; 789)");
		}
	}

	/**
	 * Получить строковое преставление высот основания.
	 * @return Строковое преставление высот основания.
	 */
	public String getBottomString() {
		StringBuilder sb = new StringBuilder();
		Arrays.stream(contents).forEach(c -> sb.append(c.ground).append(", "));
		if (sb.length() != 0)
			sb.setLength(sb.length() - 2);
		return sb.toString();
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



	/**
	 * Создать случайный аквариум.
	 * @return аквариум.
	 */
	public static Acuario randomAquario() {
		int[] bottom = new int[random.nextInt(50) + 1];
		for (int i = 0, n = bottom.length; i < n; i++) {
			bottom[i] = random.nextInt(10);
		}
		return new Acuario(bottom);
	}

}
