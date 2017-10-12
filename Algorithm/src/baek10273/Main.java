package baek10273;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static ArrayList<Edge>[] child;
	private static ArrayList<Integer>[] parents;
	private static int[] cave;
	private static Cave[] dp;
	private static int degree[];
	
	public static void main(String args[])throws IOException, CloneNotSupportedException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<tc; t++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			child = new ArrayList[n+1];
			parents = new ArrayList[n+1];
			for(int i = 0; i<n+1; i++){
				child[i] = new ArrayList<>();
				parents[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			
			cave = new int[n+1];
			degree = new int[n+1];
			
			for(int i = 1;i<n+1;i++){
				cave[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i<e; i++){
				st = new StringTokenizer(br.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				child[from].add(new Edge(to,cost));
				parents[to].add(from);
				
				degree[from]++;
			}
			
			dp = new Cave[n+1];
			/*Queue<Integer> readyQ = new LinkedList<>();
			
			for(int i =1; i<n+1;i++) {
				if(degree[i] == 0) readyQ.offer(i);
			}
			
			while(!readyQ.isEmpty()) {
				int tmp = readyQ.poll();
				degree[tmp]--;
				dfs(tmp);
				for(int x : parents[tmp]) {
					degree[x]--;
					if(degree[x] == 0) readyQ.offer(x);
				}
				
			}*/
			
			Cave resultCave = dfs(1);
			
			System.out.println(resultCave.cost + " " + resultCave.order.size());
			for(int i = resultCave.order.size()-1; i>=0; i--){
				System.out.print(resultCave.order.get(i)+ " ");
			}
			System.out.println();
			
		}
		
	}
	public static Cave dfs(int vertex) throws CloneNotSupportedException{
		
		if(dp[vertex] != null) return (Cave)dp[vertex].clone();
		
		ArrayList tmpArray = new ArrayList<>();
//		tmpArray.add(vertex);
		
		ArrayList<Cave> caves = new ArrayList<>();
		caves.add(new Cave(vertex,cave[vertex],tmpArray));
		
		for(Edge x : child[vertex]){
			Cave tmpCave = dfs(x.to);
			tmpCave.cost += cave[vertex] - x.cost;
			caves.add((Cave)tmpCave.clone());
		}
		
		Cave tmpCave = Collections.max(caves);
		tmpCave.order.add(vertex);
		
		return dp[vertex] = tmpCave;
		
	}
	
	static class Cave implements Comparable<Cave>,Cloneable{
		int vertex;
		int cost;
		ArrayList<Integer> order;
		public Cave(int vertex, int cost, ArrayList<Integer> order) {
			super();
			this.vertex = vertex;
			this.cost = cost;
			this.order = order;
		}
		@Override
		public int compareTo(Cave o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
		@Override
		protected Object clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			Cave clone = (Cave)super.clone();
			clone.cost = this.cost;
			clone.vertex = this.vertex;
			clone.order = (ArrayList<Integer>)this.order.clone();
			return clone;
		}
		
	}
	static class Edge{
		int to;
		int cost;
		public Edge(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}
	}
}
