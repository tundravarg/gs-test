package tuman.gs_test;



import java.util.Random;



/**
 * Hello world!
 */
public class App {
	
	/**
	 * Генератор чисел.
	 */
	private static Random random = new Random(System.currentTimeMillis());
	
	
	
	/**
	 * Главная функция.
	 * @param args Аргументы командной строки.
	 */
	public static void main(String[] args) {
		Acuario acuario = randomAquario();
		System.out.println(acuario);
	}
	
	
	
	/**
	 * Создать случайный аквариум.
	 * @return аквариум.
	 */
	private static Acuario randomAquario() {
		int[] bottom = new int[random.nextInt(50) + 1];
		for (int i = 0, n = bottom.length; i < n; i++) {
			bottom[i] = random.nextInt(10);
		}
		return new Acuario(bottom);
	}
	
}
