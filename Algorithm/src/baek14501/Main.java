package baek14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] TiPi = new int[2][n+1];
		int[] dp = new int[n+10];
		
		for(int i= 0; i< n; i++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			TiPi[0][i+1] = Integer.parseInt(st.nextToken());
			TiPi[1][i+1] = Integer.parseInt(st.nextToken());
			
		}
		
		for(int i = 1; i<n+1; i++){
			
			if(dp[i] < dp[i-1]) dp[i]= dp[i-1];
			
			if(dp[i+TiPi[0][i]-1] < (dp[i-1]+TiPi[1][i])) {
				dp[i+TiPi[0][i]-1] = dp[i-1]+TiPi[1][i];
			}
		}
		
		System.out.println(dp[n]);
		
	}
}
