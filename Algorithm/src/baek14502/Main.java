package baek14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int[][] map;
	private static int minGermNumber;
	private static int n;
	private static int m;
	private static int[] di={0,1,0,-1};
	private static int[] dj={1,0,-1,0};
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		minGermNumber = Integer.MAX_VALUE;
		int wallcount =0;
		
		ArrayList<Location> emptyZone = new ArrayList<>();
		HashSet<Location> germSet = new HashSet<>();
		
		for(int i = 0; i<n ; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<m; j++){
				
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 0) emptyZone.add(new Location(i, j));
				if(map[i][j] == 2) germSet.add(new Location(i,j));
				if(map[i][j] == 1) wallcount++;
				
			}
		}
		
		for(int i= 0; i<emptyZone.size()-2; i++){
			for(int j = i+1; j<emptyZone.size()-1; j++){
				for(int k = j+1; k<emptyZone.size(); k++){
					
					int x1 = emptyZone.get(i).x;
					int y1 = emptyZone.get(i).y;
					
					int x2 = emptyZone.get(j).x;
					int y2 = emptyZone.get(j).y;
					
					int x3 = emptyZone.get(k).x;
					int y3 = emptyZone.get(k).y;
					
					map[x1][y1] = map[x2][y2] = map[x3][y3] = 1;
					
					/*System.out.printf("%d , %d  / %d , %d  / %d , %d / ÀÏ¶§ ", x1,y1,x2,y2,x3,y3);
					System.out.println();*/
					minGermNumber = Math.min(splashGerm((HashSet<Location>) germSet.clone()), minGermNumber);
					map[x1][y1] = map[x2][y2] = map[x3][y3] = 0;
					
				}
			}
		}
		
		System.out.println(n*m-wallcount-minGermNumber-3);
		
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
	public static int splashGerm(HashSet<Location> germset){
		
		Iterator<Location> iterator = germset.iterator();

		Queue<Location> readyQ = new LinkedList<>();
		while(iterator.hasNext()){
			Location tmp = iterator.next();
			
			readyQ.offer(tmp);
		}
		
		while(!readyQ.isEmpty()){
			if(germset.size()>minGermNumber) return Integer.MAX_VALUE;
			Location tmpLocation = readyQ.poll();
			
				for(int i = 0; i<4; i++){
					
					int x = tmpLocation.x + di[i]; if(x>n-1)continue; if(x<0) continue;
					int y = tmpLocation.y + dj[i]; if(y>m-1)continue; if(y<0) continue;
				
					Location tmpLocation2 = new Location(x, y);
					
					if(map[x][y] ==1) continue;
					
				if(!germset.contains(tmpLocation2)){
					germset.add(tmpLocation2);
					readyQ.offer(tmpLocation2);
				}
			}
		}
		
		return germset.size();
	}
}
