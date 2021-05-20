import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class DFBnB {

	static int cost;
	static String path ="";
	static int max_steps =0;
	static boolean with_open = false;

	public static String dfbnb(Node start, String[][] goal) {
		//create stack for nodes
		Stack<Node> stack = new Stack<Node>();
		//create hash set for checking loop avoidance
		HashMap<List<String>, Node> h=new HashMap<>();
		String result="no path NULL";	

		stack.add(start);//L
		h.put(Algorithems.convert_mat(start.puzzle), start);//H	
		int count=1;
		
		//the cost of the t (threshold) starting with an upper limit
		int t = Integer.MAX_VALUE;
		while(!stack.isEmpty()) {
			//remove_front()
			Node n =stack.pop();
			//-----------------//
			if(with_open) System.out.println(h.toString());
			//-----------------//
			//n is marked as out
			if (n.isOut) { 

				h.remove(Algorithems.convert_mat(n.puzzle));

			} else {
				n.isOut=true;
				stack.add(n);
				//create all nodes
				List<Node> allowedOperator = operator.getOperators(n, goal);
				Node[] N = new Node[allowedOperator.size()];

				//apply all of the allowed operators on n
				int i=0;
				for(Node g : allowedOperator) {
					count++;
					g.parent=n;
					N[i] = g;
					i++;
				}
				Arrays.asList(N);
				n.compareTo(N[0]);
				Arrays.sort(N);

				for(Node g: allowedOperator) {
					Node g_tag=h.get(Algorithems.convert_mat(g.puzzle));
					if(g.cost >= t) {
						//found the location of the index that needs to be remove
						int index=0;
						for (int j = 0; j < N.length; j++) {
							if(Node.equal(g, N[j])){
								index=j;
							}
						}
						//remove g and all the nodes after it from N
						allowedOperator.subList(0, index);
					}
					else if(h.containsKey(g_tag) && (g.isOut))
					{
						int index=0;
						for (int j = 0; j < N.length; j++) {
							if(Node.equal(g, N[j])){
								index=j;
							}
						}
						//remove g from N
						allowedOperator.remove(index);
					}
					else if(h.containsKey(Algorithems.convert_mat(g.puzzle)) && !(g.isOut)) {
						if(g_tag.cost <= g.cost) {

							//remove Node g from N
							Node[] temp = new Node[N.length-1];
							int index=0;
							for (int j = 0; j < N.length; j++) {
								if(!Node.equal(N[j], g)) {
									temp[index] = N[j];
									index++;
								}
							}
							N=temp;
						}
						else {
							//remove g_tag from L and from H
							stack.remove(g_tag);
							h.remove(Algorithems.convert_mat(g_tag.puzzle));
						}
					}
					else if(Node.equal(g, new Node(goal))) {
						t=g.fn(goal,g.fn);
						if(g.cost < t)
						{
							result = "";
							result+="\n" + g.path;
							result+="\nNum:"+count;
							result+="\nCost: "+g.cost;
							return result;
						}

						//remove g and all the nodes
						int index=0;
						for (int j = 0; j < N.length; j++) {
							if(Node.equal(g, N[j])){
								index=j;
							}
						}
						//remove g and all the nodes after it from N
						allowedOperator.subList(0, index);
					}
				}
				// insert N in a reverse order to L and H
				for (int j = N.length-1; j >=0; j--) {
					stack.add(N[j]);//L
					if(N[j] != null) h.put(Algorithems.convert_mat(N[j].puzzle), N[j]);//H
				}
			}
		}
		return result;
	}
}