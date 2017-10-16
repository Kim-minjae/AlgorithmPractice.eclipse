package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No1767 { 
	private static int allCoreCount;
	private static ArrayList<Location> coreLocation_xy;
	private static int[] di = {0,1,0,-1};
	private static int[] dj = {1,0,-1,0};
	public static void main(String args[]) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<tc; t++){
			
			int n = Integer.parseInt(br.readLine());
			
			int[][] map = new int[n+2][n+2];
			coreLocation_xy = new ArrayList<>();
			
			for(int i = 1; i<n+1; i++){
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 1; j<n+1; j++){
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1){
						coreLocation_xy.add(new Location(i, j));
					}
				}
			}
			
			Arrays.fill(map[0], -2);
			Arrays.fill(map[n+1], -2);
			for(int i = 0; i<n+2; i++){
				map[i][0] = -2;
				map[i][n+1] = -2;
			}
			
			allCoreCount = coreLocation_xy.size(); // 총 코어의 갯수.
			
			Queue<Situation> situations = new LinkedList<>();
			
			situations.offer(new Situation(0, 0, 0, cloneMap(map)));
			
			Situation MaxSituation = new Situation(0, -1, Integer.MAX_VALUE, map);
			
			while(!situations.isEmpty()){
				
				Situation tmpSituation = situations.poll();
				
				int tmpCoreIndex = tmpSituation.coreIndex;
				int tmpCoreCount = tmpSituation.coreCount;
				int tmpLinelength = tmpSituation.lineLength;
				int[][] tmpMap = cloneMap(tmpSituation.map);
				
//				System.out.println("tmpSitu : " + tmpSituation.coreIndex + " " + tmpSituation.coreCount+ " " + tmpSituation.lineLength);
//				System.out.println("MaxSitu : " + MaxSituation.coreIndex + " " + MaxSituation.coreCount+ " " + MaxSituation.lineLength);
				
				
				
				if(MaxSituation.coreCount<tmpCoreCount){
					MaxSituation = tmpSituation;
				}
				if(MaxSituation.coreCount == tmpCoreCount){
					if(MaxSituation.lineLength>tmpLinelength){
						MaxSituation.lineLength = tmpSituation.lineLength;
					}
				}
				if(tmpCoreIndex > allCoreCount-1) continue;
				
				//4방향 및 안긋기 하고 넘기기 , 만일 벽에 붙은놈이라면 안긋고 coreCount만 올리고 넘긴다.
				Location coreLocation = coreLocation_xy.get(tmpCoreIndex);
				if(coreLocation.x == 1 || coreLocation.x == n || coreLocation.y ==1 || coreLocation.y == n){
					tmpSituation.coreCount++;
					tmpSituation.coreIndex++;
					situations.offer(tmpSituation);
					continue;
				}
				
				//이걸로 다섯개 다넣는다.
				queuing(situations, tmpCoreIndex, tmpCoreCount, tmpLinelength, tmpMap);
				
			}
			
			System.out.println("#" + (t+1)+ " " + MaxSituation.lineLength);
			
		}
		
	}
	static class Situation{
		int coreIndex;
		int coreCount;
		int lineLength;
		int[][] map;
		public Situation(int coreIndex, int coreCount, int lineLength, int[][] map) {
			super();
			this.coreIndex = coreIndex;
			this.coreCount = coreCount;
			this.lineLength = lineLength;
			this.map = map;
		}
	}
	static class Location{
		int x;
		int y;
		public Location(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static int[][] cloneMap(int[][] map){
		int[][] clonedMap = new int[map.length][map[0].length];
		
		for(int i = 0; i<map.length; i++){
			clonedMap[i] = map[i].clone();
		}
		
		return clonedMap;
	}
	public static void queuing(Queue<Situation> tmpQ , int tmpCoreIndex, int tmpCoreCount, int tmpLinelength, int[][] map){
		
		//모든코어탐색했으면 리턴
		if(tmpCoreIndex > allCoreCount) return;
		
		//현재 탐방할 코어의 위치
		Location coreLocation = coreLocation_xy.get(tmpCoreIndex);
		
		
		//4방향으로 확인하고 되면 큐에집어넣기
		
		Loop2 : for(int i =0; i<4; i++){
			
			int newTmpLineLength = tmpLinelength;
			
			int x = coreLocation.x;
			int y = coreLocation.y;
			
			int[][] tmpMap = cloneMap(map);
			
			while(true){
				x += di[i];
				y += dj[i];
				
				
				if(tmpMap[x][y] == -1 || tmpMap[x][y] ==1) continue Loop2;
				if(tmpMap[x][y] == -2) break;
				tmpMap[x][y] = -1;
				newTmpLineLength++;
				
			}
			
			tmpQ.offer(new Situation(tmpCoreIndex+1, tmpCoreCount+1, newTmpLineLength, tmpMap));
			
		}
		
		tmpQ.offer(new Situation(tmpCoreIndex+1, tmpCoreCount, tmpLinelength, cloneMap(map)));
		
	}
}
