import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
	
	static Queue<Node> queue = new LinkedList<>();
	static int cost;
	static String path ="";
	static int count = 1;
	static boolean with_open = false;
	static Hashtable<List<String>, Node> open = new Hashtable<>();
	static Hashtable<List<String>, Node> close = new Hashtable<>();
	
	//Breadth-First-Search
	public static String bfs(Node start, String[][] goal) {
		String result="no path NULL";
		queue.add(start);
		Algorithems.printmat(start.puzzle);
		open.put(Algorithems.convert_mat(start.puzzle),start);
		{
			while(!queue.isEmpty()) {
				//checking if the file expects to print the open list
				if(with_open) System.out.println(open.toString());
				//Remove the vertex at the top of the queue
				Node n = queue.remove();
				open.remove(Algorithems.convert_mat(n.puzzle),n);
				close.put(Algorithems.convert_mat(n.puzzle), n);
				//-----------------//
				if(with_open) System.out.println(open.toString());
				//-----------------//
				List<Node> allowedOperator = operator.getOperators(n, goal);
				//For each operator allowed to run on n
				for(Node g: allowedOperator) {
					//run the operator on n and get some vertex g
					g.parent=n;
					g.isOut = true;
					if(!open.containsKey(Algorithems.convert_mat(g.puzzle)) 
							&& !close.containsKey(Algorithems.convert_mat(g.puzzle))) 
					{
						count++;
						//If reached the goal, return the route to g
						if(Node.equal(g, new Node(goal))) {
							result = "";
							result+=g.path;
							result+="\nNum:" + count;
							result+="\nCost:" + g.cost;
							return result;
						}
						//Else, return g to the queue
						queue.add(g);
						open.put(Algorithems.convert_mat(g.puzzle),g);
					}
				}
			}
			return result;
		}
	}
}