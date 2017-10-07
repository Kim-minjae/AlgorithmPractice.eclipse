package baek9466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main2 {
	
	
	private static int[] vertexIndex;
	private static boolean[] globalvisit;
	private static int count;
	private static int[] child;
	
	public static void main(String args[])throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<tc; t++){
			
			int n = Integer.parseInt(br.readLine());
			child = new int[n+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i = 1; i< n+1; i++){
				child[i] = Integer.parseInt(st.nextToken());
			}
			
			count = 0;
			
			vertexIndex = new int[n+1];
			globalvisit = new boolean[n+1];
			
			for(int i = 1; i<n+1; i++){
				boolean[] visit = new boolean[n+1];
				if(!globalvisit[i]){
					dfs(i,visit,1);
				}
			}
			
			System.out.println(n - count);
		}
		
	}
	public static void dfs(int vertex,boolean[] visit, int turn){
		if(globalvisit[vertex]) return;
		if(vertex == child[vertex]){
			globalvisit[vertex] = true;
			count++;
			return;
		}
		
		visit[vertex] = true;
		globalvisit[vertex] = true;
		
		
		if(visit[child[vertex]]){
			count+= turn - vertexIndex[vertex];
		}
		vertexIndex[vertex] = turn;
		dfs(child[vertex],visit,turn+1);
	}
	
}
