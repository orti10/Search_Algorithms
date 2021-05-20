import java.util.ArrayList;
import java.util.List;

public class operator {

	/*all this function gets matrix, row, col that it is null and replace that 
	 * location with up/down/left/right position 
	 * 
	 */
	
	public static List<Node> getOperators(Node n, String[][] goal) {
		List<Node> moves = new ArrayList<Node>();
		
		for (int i=0;i<n.puzzle.length;i++) {
			for (int j=0;j<n.puzzle[i].length;j++) {
				if(n.puzzle[i][j].equals("_")) {

					//two lefts
					if((i != n.puzzle.length-1) && n.puzzle[i+1][j].equals("_"))
					{
						Node m = null;
						if(j==n.puzzle[i].length-1) {m=null;}
						else {
							m = new Node(n.puzzle, n.path, n.level, n.cost, n.parent);
							m.puzzle[i][j] = m.puzzle[i][j+1];
							m.puzzle[i+1][j] = m.puzzle[i+1][j+1];
							m.puzzle[i][j+1] = "_";
							m.puzzle[i+1][j+1] = "_";
							m.path = m.path + m.puzzle[i][j] + "&" + m.puzzle[i+1][j]  + "L-";
							m.parent = n;
							m.level = m.level+1;
							m.cost = n.cost + 6;
							m.g = m.cost;
							m.move = "l";
						}
						
						if(m != null)
						{
							m.fn = m.fn(goal, n.fn);
							moves.add(m);
						}
					}

					//two ups
					if((j != n.puzzle[i].length-1) && n.puzzle[i][j+1].equals("_")) {
						Node m = null;
						if(i == n.puzzle.length-1) {m=null;}
						else {
							m = new Node(n.puzzle, n.path, n.level, n.cost, n.parent);
							m.puzzle[i][j] = m.puzzle[i+1][j];
							m.puzzle[i][j+1] = m.puzzle[i+1][j+1];
							m.puzzle[i+1][j] = "_";
							m.puzzle[i+1][j+1] = "_";
							m.path = m.path + m.puzzle[i][j] + "&" + m.puzzle[i][j+1] + "U-";
							m.parent = n;
							m.level = m.level+1;
							m.cost = n.cost + 7;
							m.g = m.cost;
							m.move = "u";
						}
						if(m != null)
						{
							m.fn = m.fn(goal, n.fn);
							moves.add(m);
						}
					}

					//two rights
					if((i != n.puzzle.length-1) && n.puzzle[i+1][j].equals("_"))
					{
						Node m = null;
						if(j==0) m=null;
						else {
							m = new Node(n.puzzle, n.path, n.level, n.cost, n.parent);
							m.puzzle[i][j] = m.puzzle[i][j-1];
							m.puzzle[i+1][j] = m.puzzle[i+1][j-1];
							m.puzzle[i][j-1] = "_";
							m.puzzle[i+1][j-1] = "_";
							m.path = m.path + m.puzzle[i][j]  + "&" + m.puzzle[i+1][j] + "R-";
							m.parent = n;
							m.level = m.level+1;
							m.cost = n.cost + 6;
							m.g = m.cost;
							m.move = "r";
						}
						if(m != null)
						{
							m.fn = m.fn(goal, n.fn);
							moves.add(m);
						}
					}

					//two downs
					if((j != n.puzzle[i].length-1) && n.puzzle[i][j+1].equals("_")) 
					{
						Node m = null;
						if(i==0) {m=null;}
						else {
							m = new Node(n.puzzle, n.path, n.level, n.cost, n.parent);
							m.puzzle[i][j] = m.puzzle[i-1][j];
							m.puzzle[i][j+1] = m.puzzle[i-1][j+1];
							m.puzzle[i-1][j] = "_";
							m.puzzle[i-1][j+1] = "_";
							m.level = m.level+1;
							m.path =n.path + m.puzzle[i][j]  + "&" + m.puzzle[i][j+1] + "D-";
							m.parent = n;
							m.cost = n.cost + 7;
							m.g = m.cost;
							m.move = "d";
						}
						if(m != null)
						{
							m.fn = m.fn(goal, n.fn);
							moves.add(m);
						}
					}
					if(j!=n.puzzle[0].length) 
					{
						Node m = null;
						if(j==n.puzzle[i].length-1) {m=null;}
						else {
							m = new Node(n.puzzle, n.path, n.level, n.cost, n.parent);
							m.puzzle[i][j] = m.puzzle[i][j+1];
							m.path = n.path + m.puzzle[i][j+1] + "L-";
							m.parent = n;
							m.puzzle[i][j+1] = "_";
							m.level = m.level+1;
							m.cost = n.cost + 5;
							m.g =m.cost;;
							m.move = "l";
						}
						if(m!=null)
						{
							m.fn = m.fn(goal, n.fn);
							moves.add(m);
						}
					}

					//one up 
					if(i!=n.puzzle.length) {
						Node m = null;
						if(i == n.puzzle.length-1) 
						{m=null;}
						else {			
							m = new Node(n.puzzle, n.path, n.level, n.cost, n.parent);
							m.puzzle[i][j] = m.puzzle[i+1][j];
							m.path = n.path + m.puzzle[i][j]  + "U-";
							m.parent = n;
							m.puzzle[i+1][j] = "_";
							m.level = m.level+1;
							m.cost = n.cost + 5;
							m.g =m.cost;
							m.move = "u";	
						}
						if(m != null)
						{
							m.fn = m.fn(goal, n.fn);
							moves.add(m);
						}
					}
					
					//one right
					if(j!=0 && !n.puzzle[i][j-1].equals("_")) {
						Node m = null;
						if(j==0) {m=null;}
						else {
							m = new Node(n.puzzle, n.path, n.level, n.cost, n.parent);
							m.puzzle[i][j] = m.puzzle[i][j-1];
							m.puzzle[i][j-1] = "_";
							m.path = n.path + m.puzzle[i][j]+ "R-";
							m.parent = n;
							m.level = m.level+1;
							m.cost = n.cost + 5;
							m.g =m.cost;
							m.move = "r";		
						}
						if(m != null)
						{
							m.fn = m.fn(goal, n.fn);
							moves.add(m);
						}
					}

					// 1 down
					if(i!=0 && !n.puzzle[i-1][j].equals("_")) {
						Node m = null;
						if(i==0) {m=null;}
						else {
							m = new Node(n.puzzle, n.path, n.level, n.cost, n.parent);
							m.puzzle[i][j] = m.puzzle[i-1][j];
							m.puzzle[i-1][j] = "_";
							m.path =n.path + m.puzzle[i][j] + "D-";
							m.parent = n;
							m.level = m.level+1;
							m.cost = n.cost + 5;
							m.g =m.cost;
							m.move = "d";
						}
						if(m!=null)
						{
							m.fn = m.fn(goal, n.fn);
							moves.add(m);
						}
					}
				}
			}
		}
		return moves;
	}
}