package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No2112 {
	
	private static int[][] map1;
	private static int d;
	private static int w;
	private static int k;
	
	public static void main(String args[])throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		 for(int t = 0; t< tc; t++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			Queue<MapInfo> mapQ = new LinkedList<>();
			
			map1 = new int[d][w];
			
			for(int i = 0; i<d; i++){
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<w; j++){
					map1[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			MapInfo starter = new MapInfo(map1.clone(),0,new int[d],0);
			mapQ.offer(starter);
			
			
			int count = 0;
			while(!mapQ.isEmpty()){
			
				MapInfo tmpMap = mapQ.poll();
				
				
				
				boolean allchekerpoint = allChecker(tmpMap.map);
				
				if(allchekerpoint){
					System.out.println("#"+(t+1)+" " +tmpMap.depth);
					break;
				}
				if(tmpMap.depth == k) continue;
				
				
				for(int i = tmpMap.last; i<d; i++){
					if(tmpMap.visit[i] == 0){
						MapInfo tmpMapA = new MapInfo(tmpMap.map.clone(), tmpMap.depth+1, tmpMap.visit.clone(),i+1);
						tmpMapA.colorlingA(i);
						tmpMapA.visitA(i);
						mapQ.offer(tmpMapA);
											
						MapInfo tmpMapB = new MapInfo(tmpMap.map.clone(), tmpMap.depth+1,tmpMap.visit.clone(),i+1);
						tmpMapB.colorlingB(i);
						tmpMapB.visitB(i);
						mapQ.offer(tmpMapB);
					}
				}
			}
		}
	}
	public static boolean allChecker(int[][] arr){
		boolean result = true;
		for(int j=0; j<w; j++){
			if(!checker(arr,j))return false;
		}
		return result;
	}
	
	public static boolean checker(int[][] arr, int j){
		boolean result = false;
		
		int a = 0;
		int b = 0;
		
		for(int i =0; i<d; i++){
			
			if(arr[i][j] == 0){
				a++;
				b=0;
			}
			if(arr[i][j] == 1){
				b++;
				a=0;
			}
			if((a==k)||(b==k)) return true;
		}
		
		return result;
	}
	
	static class MapInfo{
		int[][] map;
		int depth;
		int[] visit;
		int last;
		public MapInfo(int[][] cloneMap, int depth, int[] visit, int last) {
			super();
			this.map = new int[cloneMap.length][cloneMap[0].length];
			for(int i = 0; i<d; i++){
				this.map[i] = cloneMap[i].clone();
			}
			this.depth = depth;
			this.visit = visit;
			this.last = last;
		}
		public void colorlingA(int i){
			for(int j = 0; j<map[i].length;j++){
				map[i][j] = 0;
			}
		}
		public void colorlingB(int i){
			for(int j = 0; j<map[i].length;j++){
				map[i][j] = 1;
			}
		}
		public void visitA(int i){
			this.visit[i] = 1;
		}
		public void visitB(int i){
			this.visit[i] = 2;
		}
		
	}
}
