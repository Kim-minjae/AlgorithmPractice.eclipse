package samsungPro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int[][] dp;
	private static Node[] nodeArray;
	
	public static void main(String args[])throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<tc; t++){
			
			int n = Integer.parseInt(br.readLine());
			
			int[] degree = new int[n];
			int[] parents = new int[n];

			dp = new int[n][4];
			nodeArray = new Node[n];
			
			for(int i = 0; i<n; i++){
				nodeArray[i] = new Node(i);
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			
			//노드별 차수 그리고 자식입력 셋팅완료
			for(int i = 0; i<n; i++){
				
				int tmpValue = Integer.parseInt(st.nextToken());
				
				nodeArray[tmpValue].child.add(nodeArray[i]);
				parents[i] = tmpValue;
				degree[i]++;
				degree[tmpValue]++;
				
			}
			
			Queue<Integer> readyQ = new LinkedList<>();
			Queue<Integer> resultQ = new LinkedList<>();
			
			for(int i = 0; i<n; i++){
				if(degree[i] == 1) {
					readyQ.offer(i);
					degree[parents[i]]--;
					degree[i]--;
				}
			}
			
			while(!readyQ.isEmpty()){
				
				while(!readyQ.isEmpty()){
					
					int tmp = readyQ.poll();
					
					if(--degree[parents[tmp]] == 0) readyQ.offer(parents[tmp]);
					
					resultQ.offer(tmp);
					
				}
				
				while(!resultQ.isEmpty()){
					
					int tmp2 = resultQ.poll();
					//bottom up 보단 top다운해야한다 왜냐하면 childe의 최솟값들을 더해서 현 노드의 index의 최솟값을 최신화 해줘야하기때문
				}
			}
			
			
			
		}
			
	}
	  
	static class Node{
		int index;
		int max;
		ArrayList<Node> child;
		
		public Node(int index) {
			super();
			this.index = index;
			this.max = max;
			child = new ArrayList<Node>();
		}
		public int getMax() {
			return max;
		}
		public void setMax(int max) {
			this.max = max;
		}	
	}
}
