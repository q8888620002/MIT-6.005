
public class MathUtils {
	public static int tripleMin(int x, int y, int z){
		int triMin;
		triMin = Math.min(Math.min(x, y), z);
		return triMin;
	}
}
