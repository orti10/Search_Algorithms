import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ortal
 * 
 * This class represent a program that run on file and doing a 
 * search algorithm on it is Colored NxM-tile game
 * in the end of the algorithm we can get the goal puzzle and all the cost and path of the algorithm
 * The algorithms are : BFS, DFID, A, IDA and DFBnB
 * The program choose one of them by the input file and run it and make output text file of the results
 */
public class Ex1 {

	public static String ReadFile(String ex1File) {
		BufferedReader br = null;
		String line = "";
		String[] array = new String[4];
		String[][] currentMat = null;
		String[][] goalMat = null;
		boolean with_time = false;
		boolean with_open = false;
		boolean flag = false;
		boolean flag2 = false;

		try {
			br = new BufferedReader(new FileReader(ex1File)); 
			int count =0;

			while((line = br.readLine()) !=null) {

				if(count == 0) array[0]=line;
				else if(count == 1) {
					if(line.equals("with time")) {
						array[1]="with time";
						with_time=true;
					}
					else 
						array[1]="no time" ;
				}
				else if(count == 2){
					if(line.equals("with open")) {
						array[2].equals("with open");
						with_open=true;
					}
					else 
						array[2] ="no open";
				}
				else if(count == 3) {
					int n = Character.getNumericValue(line.charAt(0));
					int m = Character.getNumericValue(line.charAt(2));
					currentMat = new String[n][m];
					goalMat = new String[n][m];
					array[3] = String.valueOf(line.charAt(0));
					flag = true;
				}

				else if (flag && count < Integer.parseInt(array[3]) + 4){	
					String[] temp = line.split(",");
					for (int i = 0; i < temp.length; i++) {
						currentMat[count-4][i] = temp[i]; 
					}
				}
				else if(flag2){
					String[] temp = line.split(",");
					for (int i = 0; i < temp.length; i++) {
						goalMat[count-5-goalMat.length][i] = temp[i]; 
					}
				}

				if(line.contains("Goal")) {
					flag2=true;
				}
				count++;
			}

		} catch (IOException e) {

			e.printStackTrace();
		} 
		Node current = new Node(currentMat);
		//BFS
		if(array[0].equals("BFS")) {
			System.out.println("bfs");
			if(with_open) {BFS.with_open=true;}
			long start = System.currentTimeMillis();  
			String answer = BFS.bfs(current, goalMat);
			long end = System.currentTimeMillis();
			float sec = (end - start) / 1000F; 
			if (with_time==true) answer += "\n" + sec + " seconds";
			return answer;
		}
		//A_star
		if(array[0].equals("A*")) { 
			System.out.println("A star");
			if(with_open) {A_Star.with_open=true;}
			long start = System.currentTimeMillis();  
			String answer = A_Star.a_star(current, goalMat);
			long end = System.currentTimeMillis();
			float sec = (end - start) / 1000F; 
			if (with_time==true) answer += "\n" + sec + " seconds";
			return answer;		
		}
		//DFBnB
		if(array[0].equals("DFBnB")) {
			System.out.println("DFBnB");
			if(with_open) {DFBnB.with_open=true;}
			long start = System.currentTimeMillis();  
			String answer = DFBnB.dfbnb(current, goalMat);
			long end = System.currentTimeMillis();
			float sec = (end - start) / 1000F; 
			if (with_time==true) answer += "\n" + sec + " seconds";
			return answer;	
		}
		//IDA_star
		if(array[0].equals("IDA*")) {
			System.out.println("IDA star");
			if(with_open) {IDA_Star.with_open=true;}
			long start = System.currentTimeMillis();  
			String answer = IDA_Star.ida_star(current, goalMat);
			long end = System.currentTimeMillis();
			float sec = (end - start) / 1000F; 
			if (with_time==true) answer += "\n" + sec + " seconds";
			return answer;	
		}
		//DFID
		if(array[0].equals("DFID")) {
			System.out.println("DFID");
			if(with_open) {DFID.with_open=true;}
			long start = System.currentTimeMillis();  
			String answer = DFID.dfid(current, goalMat);
			long end = System.currentTimeMillis();
			float sec = (end - start) / 1000F; 
			if (with_time==true) answer += "\n" + sec + " seconds";		
			return answer;
		}
		return "";
	}

	public static void main(String[] args) {

		String s = "input.txt";
		String answer = ReadFile(s);
		System.out.println(answer);
		try {
			FileWriter file = new FileWriter("output.txt");
			file.write(answer);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}