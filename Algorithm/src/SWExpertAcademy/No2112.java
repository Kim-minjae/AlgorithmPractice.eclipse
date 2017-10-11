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
			starter.setMin_serial();
			
			if(starter.min_serial >= k) {
				System.out.println("#"+(t+1)+" " +0);
				continue;
			}
			
			mapQ.offer(starter);
			
			Loop1 : while(!mapQ.isEmpty()){
			
				MapInfo tmpMap = mapQ.poll();
				
				if(tmpMap.depth == k) continue;
				
				for(int i = tmpMap.last; i<d; i++){
					if(tmpMap.visit[i] == 0){
						MapInfo tmpMapA = new MapInfo(tmpMap.map.clone(), tmpMap.depth+1, tmpMap.visit.clone(),i+1);
						tmpMapA.colorlingA(i);
						tmpMapA.setMin_serial();
						
						if( tmpMapA.min_serial == k){
							System.out.println("#"+(t+1)+" " +tmpMapA.depth);
							break Loop1;
						}if(tmpMapA.min_serial >= tmpMap.min_serial) {
							tmpMapA.visitA(i);
							mapQ.offer(tmpMapA);
						}
											
						MapInfo tmpMapB = new MapInfo(tmpMap.map.clone(), tmpMap.depth+1,tmpMap.visit.clone(),i+1);
						tmpMapB.colorlingB(i);
						tmpMapB.setMin_serial();
						
						if( tmpMapB.min_serial == k){
							System.out.println("#"+(t+1)+" " +tmpMapB.depth);
							break Loop1;
						}
						
						if(tmpMapB.min_serial >= tmpMap.min_serial) {
							tmpMapB.visitB(i);
							mapQ.offer(tmpMapB);
						}
					}
				}
			}	
		}	
	}
	public static int allChecker(int[][] arr){
		int result = d;
		for(int j=0; j<w; j++){
			result = Math.min(checker(arr,j), result);
		}
		return result;
	}
	
	public static int checker(int[][] arr, int j){
		int result = 0;
		
		int a = 0;
		int maxA = 0;
		
		int b = 0;
		int maxB = 0;
		
		for(int i =0; i<d; i++){
			
			if(arr[i][j] == 0){
				a++;
				b=0;
				maxA = Math.max(a, maxA);
			}
			if(arr[i][j] == 1){
				b++;
				a=0;
				maxB = Math.max(b, maxB);

			}
		}
		
		return result = Math.max(maxA, maxB);
	}
	
	static class MapInfo{
		int[][] map;
		int depth;
		int[] visit;
		int last;
		int min_serial;
		
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
		public void setMin_serial() {
			min_serial = allChecker(this.map);
		}
		
	}
}
