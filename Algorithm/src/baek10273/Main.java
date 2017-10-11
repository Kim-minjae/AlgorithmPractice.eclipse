package baek10273;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	private static ArrayList<Integer>[] adjacent;
	private static int[][] adjacentCost;
	private static int[] cave;
	
	public static void main(String args[])throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<tc; t++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			adjacent = new ArrayList[n+1];
			for(int i = 0; i<n+1; i++){
				adjacent[i] = new ArrayList<>();
			}
			adjacentCost = new int[n+1][n+1];
			
			st = new StringTokenizer(br.readLine());
			
			cave = new int[n+1];
			
			for(int i = 1;i<n+1;i++){
				cave[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i<e; i++){
				st = new StringTokenizer(br.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				adjacent[from].add(to);
				adjacentCost[from][to] = cost;
				
			}
			Cave resultCave = dfs(1);
			
			System.out.println(resultCave.cost + " " + resultCave.order.size());
			for(int i = resultCave.order.size()-1; i>=0; i--){
				System.out.print(resultCave.order.get(i)+ " ");
			}
			System.out.println();
			
		}
		
	}
	public static Cave dfs(int vertex){
		
		if(adjacent[vertex].size() == 0){
			ArrayList tmpArray = new ArrayList<>();
			tmpArray.add(vertex);
			return new Cave(vertex,cave[vertex],tmpArray);
		}
		
		ArrayList<Cave> caves = new ArrayList<>();

		for(int x : adjacent[vertex]){
			Cave tmpCave = dfs(x);
			tmpCave.cost += cave[vertex] - adjacentCost[vertex][x];
			caves.add(tmpCave);
		}
		
		Cave tmpCave = Collections.max(caves);
		tmpCave.order.add(vertex);
		
		return tmpCave;
		
	}
	
	static class Cave implements Comparable<Cave>{
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
		
	}
}
