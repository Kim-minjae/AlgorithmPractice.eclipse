package baek2533;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static ArrayList<Integer>[] friends;
	private static int[][] dp;
	
	public static void main(String args[])throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		dp = new int[n+1][2];
		for(int i = 0; i<n+1; i++){
			dp[i][0] = dp[i][1] =-1;
		}
		friends = new ArrayList[n+1];
		
		for(int i = 1; i<n+1; i++){
			friends[i] = new ArrayList<>();
		}
			
		for(int i = 1; i< n; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			friends[a].add(b);
			friends[b].add(a);
		}
		
		int a1 = dfs(1,0,0);
		int a2 = dfs(1,1,0);
		System.out.println(Math.min(a1,a2));
	}
	public static int dfs(int vertex, int isEarlyAdoptor,int from){
		
		if(dp[vertex][isEarlyAdoptor] != -1) return dp[vertex][isEarlyAdoptor];
		
		
		int result = 0;
		
		if(isEarlyAdoptor == 0){
			result = 1;
			for(int x : friends[vertex]){
				if(x == from) continue;
				result += Math.min(dfs(x,0,vertex), dfs(x,1,vertex)); 
			}
			
		}else{
			for(int x : friends[vertex]){
				if(x == from) continue;
				result += dfs(x,0,vertex); 
			}
		}
		
		return dp[vertex][isEarlyAdoptor] = result;
	}
}
