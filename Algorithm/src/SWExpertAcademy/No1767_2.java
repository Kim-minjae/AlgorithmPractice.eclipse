package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class No1767_2 {
	
	private static int allCores;
	private static ArrayList<Location> cores; 
	private static int[][] map;
	private static int n;
	private static int[] di = {0,1,0,-1};
	private static int[] dj = {1,0,-1,0};
	
	private static int maxCoreCount;
	private static int minLineLength;
	
	
	public static void main(String args[]) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<tc ;t++){
			
			n = Integer.parseInt(br.readLine());
			
			map = new int[n][n];
			cores = new ArrayList<>();
			maxCoreCount = 0;
			minLineLength = Integer.MAX_VALUE;
			
			for(int i = 0; i<n; i++){
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j<n; j++){
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1)  cores.add(new Location(i,j));
				}
			}
			
			allCores = cores.size();
			
			dfs(0,0,new HashSet<Location>());
			
			System.out.println(minLineLength);
			
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
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Location other = (Location) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
	}
	public static void dfs(int coreIndex, int coreCount, HashSet<Location> set){
		
		if(coreIndex == allCores) {
			
			if(maxCoreCount < coreCount){
				maxCoreCount = coreCount;
				minLineLength = set.size();
				return;
			}
			
			if(maxCoreCount == coreCount){
				if(minLineLength>set.size()){
					minLineLength = set.size();
				}
				return;
			}	
			return;
		}
			
		
		Location tmpLocation = cores.get(coreIndex);
		int x = tmpLocation.x;
		int y = tmpLocation.y;
		
		if(x == 0 || x == n-1 || y == 0 || y == n-1){
			dfs(coreIndex+1, coreCount+1, (HashSet<Location>) set.clone());
			return;
		}
		
		Loop1 : for(int i = 0; i<4; i++){
			
			int x1 = tmpLocation.x;
			int y1 = tmpLocation.y;
			HashSet<Location> tmpSet = (HashSet<Location>)set.clone();
			
			while(true){
				
				x1 += di[i];
				y1 += dj[i];
				
				
				if(x1 == -1 || x1 == n || y1 == -1 || y1 == n){
					dfs(coreIndex+1,coreCount+1,tmpSet);
					continue Loop1;
				}
				if(tmpSet.contains(new Location(x1,y1))) continue Loop1; 
				if(map[x1][y1] ==1)continue Loop1;
				
				tmpSet.add(new Location(x1, y1));
				
			}
			
		}
		
		dfs(coreIndex+1, coreCount, (HashSet<Location>) set.clone());
		
	}
}
