package baek2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[])throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] degree = new int[n+1];
		ArrayList<Integer>[] children = new ArrayList[n+1];
		
		for(int i =1; i<n+1; i++){
			children[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<m; i++){
			
			st = new StringTokenizer(br.readLine());
			
			//입력이 A B 순으로 들어오면 
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			degree[B]++;
			
			children[A].add(B);
			
		}
		
		Queue<Integer> readyQ = new LinkedList<>(); 
		Queue<Integer> resultQ = new LinkedList<>(); 
		
		for(int i = 1; i<n+1; i++){
			if(degree[i] == 0){
				readyQ.offer(i);
				degree[i]--;
			}
		}
		
		while(!readyQ.isEmpty()){
			
			int tmp = readyQ.poll();
			resultQ.offer(tmp);
			
			for(int k : children[tmp]){
				degree[k]--;
				if(degree[k] == 0) readyQ.offer(k);
			}
			
		}
		
		while(!resultQ.isEmpty()){
			System.out.print(resultQ.poll() + " ");
		}
		
	}
}
