import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

public class IDA_Star {

	static int cost,numOpen=1,numClose=0;;
	static String path ="";
	static int count = 1;
	static boolean with_open = false;
	static Hashtable<List<String>, Node> open = new Hashtable<>();
	static Hashtable<List<String>, Node> close = new Hashtable<>();

	public static String ida_star(Node start, String[][] goal) {
		int count=1;
		String result="no path NULL";
		Stack<Node> L = new Stack<Node>();
		int t=start.fn(goal, 0);
		//the cost of the t (threshold) constitutes a lower limit for the best solution
		while(t!=Integer.MAX_VALUE) {

			int minF=Integer.MAX_VALUE;

			start.isOut=false;
			L.add(start);
			open.put(Algorithems.convert_mat(start.puzzle),start);
			while(!L.isEmpty()) 
			{
				//-----------------//
				if(with_open)System.out.println(open.toString());
				//-----------------//
				Node n= L.pop();
				count++;
				if(n.isOut) 
				{//if mark as out
					open.remove(Algorithems.convert_mat(n.puzzle));
				}
				else 
				{
					n.isOut = true;//mark as out
					L.add(n);
					List<Node> allowedOperator = operator.getOperators(n, goal);

					for (Node g : allowedOperator) 
					{
						g.parent = n;         
						g.fn=g.fn(goal, g.fn);
						if(g.fn > t) 
						{
							minF=Math.min(minF,g.fn);
							continue;
						}
						Node take=open.get(Algorithems.convert_mat(g.puzzle));

						//If H contains g_tag = g and g_tag marked out
						if(open.containsKey(Algorithems.convert_mat(g.puzzle)) && take.isOut)
						{
							continue;
						}
						//If H contains g_tag = g and g_tag not marked out
						if(open.containsKey(Algorithems.convert_mat(g.puzzle)) && take.isOut==false) 
						{
							if(take.fn > g.fn)
							{
								L.remove(take);
								open.remove(Algorithems.convert_mat(take.puzzle));
							}
							else
							{
								continue;
							}
						}
						if(Node.equal(g, new Node (goal))) {
							result=g.path;
							result+="\nNum:" +count;
							int cost=g.cost;
							result+="\nCost: " +cost;
							return result;
						}
						L.push(g);
						open.put(Algorithems.convert_mat(g.puzzle),g);
					}
				}
			}
			t=minF;
		}
		result+="\nNum:"+count;
		return result;
	}
}