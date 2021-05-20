import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;

/*
 * https://stackabuse.com/graphs-in-java-a-star-algorithm/
 */
public class A_Star {

	static int cost;
	static String path ="";
	static PriorityQueue<Node> queue = new PriorityQueue<Node>();
	static int count = 1;
	static boolean with_open = false;
	static Hashtable<List<String>, Node> open = new Hashtable<>();
	static Hashtable<List<String>, Node> close = new Hashtable<>();

	public static String a_star(Node start, String[][] goal) {
		String result="no path NULL";
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		start.fn = start.fn(goal, 0);
		q.add(start);
		open.put(Algorithems.convert_mat(start.puzzle),start);
		int count = 0;

		while(!q.isEmpty()) {
			Node n = q.remove();
			open.remove(Algorithems.convert_mat(n.puzzle),n);

			if(Node.equal(n, new Node(goal))) 
			{
				count++;
				result = "";		
				result+="\n" + n.path;
				result+="\nNum:"+ count;
				result+="\nCost: "+n.cost;
				return result;

			}
			close.put(Algorithems.convert_mat(n.puzzle), n);
			//-----------------//
			if(with_open) System.out.println(open.toString());
			//-----------------//
			ArrayList<Node> allowedOperator = (ArrayList<Node>) operator.getOperators(n, goal);

			for (Node g : allowedOperator) {
				count++;
				g.parent = n;
				g.isOut = true;
				if(!close.containsKey(Algorithems.convert_mat(g.puzzle)) 
						&& !open.contains(Algorithems.convert_mat(g.puzzle)))
				{

					open.put(Algorithems.convert_mat(g.puzzle), g);
					q.add(g);
				}
				else{
					Node temp = open.get(Algorithems.convert_mat(g.puzzle));
					if((temp != null) && g.cost < temp.cost) {
						open.replace(Algorithems.convert_mat(g.puzzle),temp, g);
						q.remove(temp);
						q.add(g);
					}
				}
			}
		}
		return result;
	}
}