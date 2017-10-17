package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class No2105 {
	
	private static int[][] map;
	private static int[] dx = {1,1,-1,-1};
	private static int[] dy = {-1,1,1,-1};
	
	public static void main(String args[])throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<tc; t++){
			
			int n = Integer.parseInt(br.readLine());
			
			map = new int[n+2][n+2];
			
			for(int i = 0; i<n; i++){
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j =0; j<n; j++){
					map[i+1][j+1] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max = -1;
			int[] abcdCount = new int[4];
			
			for(int i = 1; i < n+1; i++){
				for(int j = 1; j < n+1; j++){
					HashSet<Integer> tmpHash = new HashSet<>();	
					tmpHash.add(map[i][j]);
//					System.out.println(map[i][j]);
					max = Math.max(max, dfs(i+dx[0],j+dy[0],0,tmpHash,abcdCount.clone()));
				}
			}
			System.out.println(max);
		}
		
	}
	public static int dfs(int x, int y ,int direction, HashSet<Integer> visit, int[] abcdCount){
		
		if(map[x][y] == 0) return -1;
		
		HashSet<Integer> visittmp = (HashSet<Integer>) visit.clone();
		
		if(visittmp.contains(map[x][y])) return -1;
		visittmp.add(map[x][y]);
		
		if(direction == 0){
			abcdCount[2]++;			
		}
		if(direction == 1){
			abcdCount[3]++;
		}
		
		if(direction == 2){
			while(abcdCount[2]-1 !=0){
				x += dx[2];
				y += dy[2];
				if(map[x][y] == 0) return -1;
				abcdCount[2]--;
				if(visittmp.contains(map[x][y])) return -1;
				visittmp.add(map[x][y]);
			}
			
			while((abcdCount[3]-1) != 0){
				x += dx[3];
				y += dy[3];
				if(map[x][y] == 0) return -1;
				abcdCount[3]--;
				if(visittmp.contains(map[x][y])) return -1;
				visittmp.add(map[x][y]);
			}
			return visittmp.size();
		}
		
		
		//쭉간다와 꺾는다 두개중 큰거반환.
		return Math.max(dfs(x+dx[direction],y+dy[direction],direction,(HashSet<Integer>) visittmp.clone(),abcdCount.clone())
				, dfs(x+dx[direction+1],y+dy[direction+1],direction+1,(HashSet<Integer>) visittmp.clone(),abcdCount.clone()));
		
	}
}
	