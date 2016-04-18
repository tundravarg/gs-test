package tuman.gs_test;



import java.util.Arrays;



/**
 * Аквариум.
 * @author Sergei Tumanov.
 */
public class Acuario {

	/** Глубина аквариума. */
	private int depth;
	/** Содержимое. */
	private int[][] contents;
	
	
	
	/**
	 * Создание кземпляра.
	 * @param bottom высоты дна.
	 */
	public Acuario(int[] bottom) {
		depth = Arrays.stream(bottom).reduce(0, (max, d) -> d > max ? d : max);
		contents = Arrays.stream(bottom)
				.mapToObj(b -> new int[] {b >= 0 ? b : 0, depth - (b >= 0 ? b : 0)})
				.toArray(int[][]::new);
		System.out.println("Depth: " + depth);
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			int h = depth - i - 1;
			for (int j = 0, n = contents.length; j < n; j++) {
				int b = contents[j][0];
				int a = contents[j][1];
				System.err.printf("SA: %d, %d, %d\n", h, b, a);
				sb.append(h >= b + a ? '.' : h >= b ? '~' : '#');
			}
			sb.append('\n');
		}
		return sb.toString();
	}
	
}
