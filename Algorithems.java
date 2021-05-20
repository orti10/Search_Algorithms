import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Algorithems {

	static int cost;
	static String path ="";
	static int count = 1;
	static boolean with_open = false;
	static Hashtable<List<String>, Node> open = new Hashtable<>();
	static Hashtable<List<String>, Node> close = new Hashtable<>();

	public static void printmat(String[][] mat) {

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(mat[i][j] + ",");
			}
			System.out.println();
		}
	}

	public static List<String> convert_mat(String[][] g)
	{
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[i].length; j++) { 		
				list.add(g[i][j]); 
			}
		}
		return list;
	}
}