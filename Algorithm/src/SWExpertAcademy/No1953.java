package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class No1953 {
	
	private static int[] dx = {0,1,0,-1};
	private static int[] dy = {1,0,-1,0};
	private static int[][] map;
	private static int l;
	private static boolean[][] visit;
	private static HashSet<Integer>[] conditions;
	
	public static void main(String args[])throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<tc; t++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			
			map = new int[n+2][m+2];
			visit = new boolean[n+2][m+2];
			
			for(int i = 0 ; i<n; i++){
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<m; j++){
					map[i+1][j+1] = Integer.parseInt(st.nextToken());
				}
			}
			
			conditions = new HashSet[4];
			for(int i = 0; i<4; i++){
				conditions[i] = new HashSet<>();
			}
			
			conditions[0].add(1);
			conditions[0].add(3);
			conditions[0].add(6);
			conditions[0].add(7);
			
			conditions[1].add(1);
			conditions[1].add(2);
			conditions[1].add(4);
			conditions[1].add(7);
			
			conditions[2].add(1);
			conditions[2].add(3);
			conditions[2].add(4);
			conditions[2].add(5);
			
			conditions[3].add(1);
			conditions[3].add(2);
			conditions[3].add(5);
			conditions[3].add(6);
			
			//dfs로  시작점에서 출발
			System.out.println(dfs(r+1,c+1,1));
			
		}
		
	}
	
	public static int dfs(int x,int y,int l2){
		
		visit[x][y] = true;
		if(l+1 == l2) return 1;
		
		int info = map[x][y];
		int result = 1;
		
		switch(info){
		case 1 :
			for(int i = 0; i<4; i++){
				if(!visit[x+dx[i]][y+dy[i]] && conditions[i].contains(map[x+dx[i]][y+dy[i]])){
					result += dfs(x+dx[i],y+dy[i],l2+1);
				}
			}
			break;
		case 2 :
			for(int i = 1; i<4; i+=2){
				if(!visit[x+dx[i]][y+dy[i]] && conditions[i].contains(map[x+dx[i]][y+dy[i]])){
					result += dfs(x+dx[i],y+dy[i],l2+1);
				}
			}
			break;
		case 3 :
			for(int i = 0; i<4; i+=2){
				if(!visit[x+dx[i]][y+dy[i]] && conditions[i].contains(map[x+dx[i]][y+dy[i]])){	
					result += dfs(x+dx[i],y+dy[i],l2+1);
				}
			}
			break;
		case 4 :
			
			for(int i = 0; i<4; i+=3){
				if(!visit[x+dx[i]][y+dy[i]] && conditions[i].contains(map[x+dx[i]][y+dy[i]])){
					result += dfs(x+dx[i],y+dy[i],l2+1);
				}
			}
			
			break;
		case 5 :
			for(int i = 0; i<2; i++){
				if(!visit[x+dx[i]][y+dy[i]] && conditions[i].contains(map[x+dx[i]][y+dy[i]])){
					result += dfs(x+dx[i],y+dy[i],l2+1);
				}
			}
			break;
		case 6 :
			
			for(int i = 1; i<3; i++){
				if(!visit[x+dx[i]][y+dy[i]] && conditions[i].contains(map[x+dx[i]][y+dy[i]])){
					result += dfs(x+dx[i],y+dy[i],l2+1);
				}
			}
			break;
		case 7 :
			for(int i = 2; i<4; i++){
				if(!visit[x+dx[i]][y+dy[i]] && conditions[i].contains(map[x+dx[i]][y+dy[i]])){
					result += dfs(x+dx[i],y+dy[i],l2+1);
				}
			}
			break;
			default : break;
		}
		
		return result;
	}
}
