package baek2669;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String args[])throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] degree = new int[n+1];
		int[] child = new int[n+1];
		boolean[] result = new boolean[n+1];
		
		for(int i = 0; i<n; i++){
			child[i+1] = Integer.parseInt(br.readLine());
			degree[i+1]++;
			degree[child[i+1]]++;
		}
		
		Queue<Integer> readyQ = new LinkedList<>();
		
		for(int i =1 ; i<n+1; i++){
			if(degree[i] == 1) readyQ.offer(i);
		}
		int count = 0;
		
		while(!readyQ.isEmpty()){
			
			int tmp = readyQ.poll();
			
			degree[tmp]--;
			degree[child[tmp]]--;
			result[tmp] = true;
			count++;
			if(degree[child[tmp]] == 1){
				readyQ.offer(child[tmp]);
				
			}
		}
		System.out.println(n-count);
		for(int i =1; i<n+1;i++){
			if(!result[i])System.out.println(i);
		}
		
	}
}
