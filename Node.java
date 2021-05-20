public class Node implements Comparable<Node>{

	String[][] puzzle;
	String path ="";
	String move = "";
	Node parent = null;
	int level ;
	int index = 0;
	int cost = 0;
	int fn = 0;
	int g = 0;
	int h = 0;
	boolean isOut = false;

	public Node(String[][] puzz) {
		this.puzzle = new String[puzz.length][puzz[0].length];
		for (int i = 0; i < puzz.length; i++) {
			for (int j = 0; j < puzz[0].length; j++) {
				this.puzzle[i][j] = puzz[i][j];
			}
		}
		this.level = index++;
	}

	public Node(String[][] puzz, String pat, int l, int cost, Node p) {

		this.puzzle = new String[puzz.length][puzz[0].length];
		for (int i = 0; i < puzz.length; i++) {
			for (int j = 0; j < puzz[0].length; j++) {
				this.puzzle[i][j] = puzz[i][j];
			}
		}
		this.path = pat;
		this.level = index++;
		this.cost = cost;
		this.parent = p;
	}
	//Contractor
	public Node(Node n) {
		Node temp = new Node(n.puzzle,n.path, n.level, n.cost, n.parent);
		this.puzzle = temp.puzzle;
		this.path = temp.path;
		this.level = index++;
		this.cost = n.cost;
		this.g = n.g;
		this.h = n.h;
		this.fn = n.fn;
	}

	/// f(n) = h + g
	// h = hurstec
	// g = cose
	public int fn(String[][] goal, int puzz)
	{
		this.fn = puzz + heuristic(this.puzzle, goal);
		return this.fn;
	}

	//this is a Comparable, checking the nodes by values
	@Override
	public int compareTo(Node puzz) {
		if (this.fn < puzz.fn) {
			return -1;
		}
		if (this.fn > puzz.fn) {
			return 1;
		}
		else if(this.fn==puzz.fn) {

			if(this.level<puzz.level) 
			{
				return -1;
			}
			else if(this.level>puzz.level) 
			{
				return 1;
			}
			else
			{
				String n_n = this.move;
				String g_n = puzz.move;
				if(n_n.equals("u") || g_n.equals("r")) return -1;
				else if(g_n.equals("l")) return 1;
				else if(n_n.equals("u") && !g_n.equals("l")) return -1;
				else if(n_n.equals("r")  && !g_n.equals("l") && !g_n.equals("u")) return -1;
				return 1;
			}
		}
		return 0;
	}
	
	//checking if 2 objects have the same values 
	public static boolean equal(Object obj, Object goal)
	{
		String s = "";
		if(((Node) obj).path.length() > 1)
		{
			s = ((Node) obj).path.substring(0,((Node) obj).path.length()-1);
		}
		for (int i = 0; i < ((Node) obj).puzzle.length; i++) {
			for (int j = 0; j < ((Node) obj).puzzle[0].length; j++) {
				if(!((Node) obj).puzzle[i][j].equals(((Node) goal).puzzle[i][j])) 
				{
					return false;
				}
			}
		}
		((Node) obj).path = s; 
		return true;
	}

	/**
	 * Calculates sum of Manhattan distances for this board
	 * The distance between two points measured along axes at right angles.
	 * In a plane with p1 at (x1, y1) and p2 at (x2, y2), it is |x1 - x2| + |y1 - y2|
	 * The full heuristic proof is in doc file
	 */
	public static int heuristic(String [][] n, String[][] goal) {
		int manhattanDis = 0;
		for (int k = 0; k < goal.length; k++) {
			for (int l = 0; l < goal[0].length; l++) {
				for (int i = 0; i < n.length; i++) {
					for (int j = 0; j < n[0].length; j++) {
						if (n[i][j].equals(goal[k][l]) && n[i][j]!="_") {
							manhattanDis=manhattanDis+(Math.abs(i-k)+Math.abs(j-l));	
						}
					}
				}
			}
		}
		return manhattanDis;  
	} 
}