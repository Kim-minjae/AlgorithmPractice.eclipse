package baek10273;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	
	public static void main(String arsg[]) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t= 0; t<tc; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			int[] cave = new int[n+1];
			
			for(int i = 1; i<n+1; i++) {
				cave[i] = Integer.parseInt(st.nextToken());
			}
			
			ArrayList<Edge>[] adjacent = new ArrayList[n+1];
			
			for(int i = 1; i<n+1; i++) {
				adjacent[i] = new ArrayList<>();
			}
			
			
			
			for(int i = 0; i<e; i++) {
				st = new StringTokenizer(br.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				adjacent[from].add(new Edge(to,cost));
				
			}
			
			Queue<Cave> readyQ = new LinkedList<>();
			Queue<Cave> resultQ = new LinkedList<>();
			
			ArrayList<Integer> tmpArray = new ArrayList<>();
			tmpArray.add(1);
			Cave first = new Cave(1, cave[1], tmpArray);
			readyQ.offer(first);
			
			Cave[] dp = new Cave[n+1];
			
			while(!readyQ.isEmpty()) {
				
				Cave tmpCave = readyQ.poll();
				
				if(dp[tmpCave.vertex] == null) {
					dp[tmpCave.vertex] = new Cave(tmpCave.vertex, tmpCave.cost, (ArrayList<Integer>) tmpCave.visit.clone());
					resultQ.offer(dp[tmpCave.vertex]);
				}
				
				for(Edge x : adjacent[tmpCave.vertex]) {
					if(dp[x.to] == null || (dp[x.to].cost < tmpCave.cost+cave[x.to]-x.cost)) {
						tmpArray = (ArrayList<Integer>)tmpCave.visit.clone();
						tmpArray.add(x.to);
						dp[x.to] = new Cave(x.to, tmpCave.cost+cave[x.to]-x.cost,tmpArray);
						
						readyQ.offer(dp[x.to]);
						resultQ.offer(dp[x.to]);
					}
				}
				
			}
			
			Cave result = Collections.max(resultQ);
			
			System.out.println(result.cost + " " + result.visit.size());
			for(int x : result.visit) {
				System.out.print(x + " ");
			}
			System.out.println();
		}
		
	}
	static class Cave implements Comparable<Cave>{
		int vertex;
		int cost;
		ArrayList<Integer> visit;
		public Cave(int vertex, int cost, ArrayList<Integer> visit) {
			super();
			this.vertex = vertex;
			this.cost = cost;
			this.visit = visit;
		}
		@Override
		public int compareTo(Cave o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
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
