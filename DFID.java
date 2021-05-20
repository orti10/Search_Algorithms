import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class DFID {
	/*
	 * Is a transition algorithm for finding a route in 
	 * Unweighed trees that is asymptotically efficient
	 * 
	 * Recursive DFID with loop avoidance
	 * Combines the best features of bfs and dfs
	 * done limited dfs gradually
	 */
	static int cost;
	static String path ="";
	static int count = 1;
	static boolean with_open = false;
	static Hashtable<List<String>, Node> open = new Hashtable<>();
	static Hashtable<List<String>, Node> close = new Hashtable<>();

	public static String dfid(Node start, String[][] goal) {
		count=1;
		String result="";
		for(int depth=1; depth<Integer.MAX_VALUE; depth++) {
			//h is to save the vertices that are on the path
			HashMap<String,Node> h = new HashMap<>();
			//result can be one of 3 options:path OR fail OR cutoff
			result=limited_dfs(start,goal,depth,h);
			if(!result.equals("cutoff")) {
				return result;
			}
		}
		return result; 
	}
	
	public static String limited_dfs(Node start , String [][] goal, int limit, HashMap <String,Node> h) {
		String result="no path NULL";
		//to check if we reached to cutoff 
		boolean isCutoff=true; 
		List<Node> allowedOperator = null;

		//use the back pointers or the recursion tail
		if(Node.equal(start, new Node (goal))) {
			result=start.path;
			result+="\nNum:"+count;
			int cost=start.cost;
			result+="\nCost: "+cost;
			return result;
		}
		//when cutoff=0
		else if(limit==0) { 
			return "cutoff";
		}
		else {
			h.put(start.toString(),start);
			//-----------------//
			if(with_open)System.out.println(open.toString());
			//-----------------//
			isCutoff=false;
			allowedOperator = operator.getOperators(start,goal);
			for (Node g : allowedOperator) {
				//updates
				count=count+1;
				g.parent=start;                   
				g.isOut=true;

				result=limited_dfs(g,goal,limit-1,h);

				if(result.equals("cutoff")) {
					isCutoff=true;
				}
				else if(!result.equals("fail")){
					return result;
				}
			}
			h.remove(start.toString());
			if(isCutoff==true) {
				return "cutoff";
			}
			else {
				return "fail";
			}
		}
	}
}