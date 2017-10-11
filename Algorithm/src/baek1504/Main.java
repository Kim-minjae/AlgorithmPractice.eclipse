package baek1504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static ArrayList<Integer>[] adjacent;
	private static int[][] adjacentCost;
	
	public static void main(String args[]) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		
		adjacentCost = new int[n+1][n+1];
		adjacent = new ArrayList[n+1];
		
		for(int i = 0; i<n+1; i++) {
			adjacent[i] = new ArrayList<>();
		}
		
		
		//인접리스트와 cost인접행렬을 둘 다 적는것이 편할것같다.
		
		
		for(int i = 0; i<e; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			adjacent[from].add(to);
			adjacent[to].add(from);
			
			adjacentCost[from][to] = adjacentCost[to][from] = cost;
			
		}
		
		st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		//dp만들기
		
		long[] dpA = new long[n+1]; // 중간지점 a로부터의 각 vertex까지의 최단거리
		long[] dpB = new long[n+1]; // 중간지점 b로부터의 각 vertex까지의 최단거리
		Arrays.fill(dpA, Integer.MAX_VALUE);
		Arrays.fill(dpB, Integer.MAX_VALUE);
		
		PriorityQueue<Man> priorityQueueA = new PriorityQueue<>();
		PriorityQueue<Man> priorityQueueB = new PriorityQueue<>();
		
		priorityQueueA.offer(new Man(a,0));
		dpA[a] = 0;
		priorityQueueB.offer(new Man(b,0));
		dpB[b] = 0;
		//첫번째 두개의 중간지점이 MaxValue 일 경우 while문 계속 돌리고 아닐경우 다음으로 넘어감.
		//만일 두번째 중간지점을 가기위해 무조건 첫번째 중간지점을 지나가야 한다고 한다면 ? 혹시모르니 
	
		while(!priorityQueueA.isEmpty()) {
			Man tmpManA = priorityQueueA.poll();
			
			int tmpAvertex = tmpManA.vertex;
			long tmpAcost = tmpManA.cost;
			
			for(int x : adjacent[tmpAvertex]) {
				if((dpA[x] > (tmpManA.cost+adjacentCost[tmpAvertex][x]))) {
					
					dpA[x] = tmpManA.cost+adjacentCost[tmpAvertex][x];
					priorityQueueA.offer(new Man(x,dpA[x]));
					
				}
			}
		}
		
		while(!priorityQueueB.isEmpty()) {
			Man tmpManB = priorityQueueB.poll();
			
			int tmpBvertex = tmpManB.vertex;
			long tmpBcost = tmpManB.cost;
			
			for(int x : adjacent[tmpBvertex]) {
				if((dpB[x] > (tmpManB.cost+adjacentCost[tmpBvertex][x]))) {
					
					dpB[x] = tmpManB.cost+adjacentCost[tmpBvertex][x];
					priorityQueueB.offer(new Man(x,dpB[x]));
					
				}
			}
		}
		
		if(dpA[1] == Integer.MAX_VALUE || dpA[b] == Integer.MAX_VALUE || dpA[n] == Integer.MAX_VALUE || dpB[1] == Integer.MAX_VALUE || dpB[a] == Integer.MAX_VALUE || dpB[n] == Integer.MAX_VALUE ) {
			System.out.println(-1);
			return;
		}
		
		long courseA = dpA[1]+dpA[b]+dpB[n];
		long courseB = dpB[1]+dpB[a]+dpA[n];
		
		System.out.println(Math.min(courseA, courseB));
		
	}
	static class Man implements Comparable<Man>{
		int vertex;
		long cost;
		
		public Man(int vertex, long cost) {
			super();
			this.vertex = vertex;
			this.cost = cost;
		}
		@Override
		public int compareTo(Man o) {
			// TODO Auto-generated method stub
			return this.cost<o.cost?-1:1;
		}
	}
}
