package baek1937;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] di = {0,1,0,-1};
	private static int[] dj = {1,0,-1,0};
	private static int[][] map;
	private static int[][] dp;
	
	
	public static void main(String args[]) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());		
		
		map = new int[n+2][n+2];
		dp = new int[n+2][n+2];
		
		for(int i = 1; i<n+1; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j<n+1; j++){
				
				map[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		
		int MaxYear = 0;
		
		for(int i =1; i<n+1; i++){
			for(int j = 1; j<n+1; j++){
				MaxYear = Math.max(MaxYear, dfs(i,j));
			}
		}
		
		System.out.println(MaxYear);
	}
	public static int dfs(int x, int y){
		
		if(map[x][y] == 0) return 0;
		if(dp[x][y] != 0) return dp[x][y];
		
		int result = 1;
		int max = 0;
		
		
		for(int i = 0; i<4; i++){
			if(map[x+di[i]][y+dj[i]] > map[x][y]){
				max = Math.max(max, dfs(x+di[i], y+dj[i]));
			}
		}
		
		return dp[x][y] =  result+max;
		
	}
}
