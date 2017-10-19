package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class No2819 {
	
	private static int[][] map;
	private static HashSet<String>[][] hashcodeMap;
	private static HashSet<String> resultSet;
	
	private static int[] di = {0,1,0,-1};
	private static int[] dj = {1,0,-1,0};
	
	public static void main(String args[]) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t =0 ; t<tc; t++){
			
			map = new int[4][4];
			hashcodeMap = new HashSet[4][4];
			resultSet = new HashSet<>();
			
			for(int i = 0 ; i<4; i++){
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j<4; j++){
					map[i][j] = Integer.parseInt(st.nextToken());
					hashcodeMap[i][j] = new HashSet<>();
				}
			}
			
			for(int i = 0; i<4; i++){
				for(int j = 0; j<4; j++){
					dfs(i,j,6,null);
				}
			}
			System.out.println("#" + (t+1) + " " + resultSet.size());
		}
		
	}
	public static void dfs(int x, int y, int count, String tmp){
		
		if(hashcodeMap[x][y].contains(tmp)) return;
		hashcodeMap[x][y].add(tmp);
		
		if(count == -1){	
			if(!resultSet.contains(tmp)) resultSet.add(tmp);
			return;
		}
	
		String tmpMapNum = map[x][y] +"";
		
		if(tmp == null){
			tmp = new String(tmpMapNum);
		}else{
			tmp = tmp+tmpMapNum;
		}	
		for(int i = 0; i<4; i++){
			if(x+di[i] >=0 && x+di[i] <4 && y+dj[i] >=0 && y+dj[i]<4){
				dfs(x+di[i], y+dj[i],count-1,tmp);
			}
		}
	}
}
