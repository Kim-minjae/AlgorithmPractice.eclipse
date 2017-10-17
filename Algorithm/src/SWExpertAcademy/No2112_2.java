package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No2112_2 {
	
	private static int[][] map;
	private static int[] a;
	private static int[] b;
	private static int d;
	private static int k;
	
	public static void main(String args[])throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t =0; t<tc; t++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			map = new int[d][w];
			a = new int[w];
			b = new int[w];
			
			Arrays.fill(a, 0);
			Arrays.fill(b, 1);
			
			for(int i = 0; i<d; i++){
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<w; j++){
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int[] dummyMap = new int[w];
			Arrays.fill(dummyMap, -1);
			int result = Math.max(dfs(k,'x',0,new int[w],new int[w],new int[w],map[0].clone()), dfs(k-1,'a',0,new int[w],new int[w],new int[w],a.clone()));
			result = Math.max(result, dfs(k-1,'b',0,new int[w],new int[w],new int[w],b.clone()));
			
			System.out.println("#"+(t+1)+" " + (k-result));
		}
		
	}
	public static int dfs(int k2, char status ,int depth, int[] max, int[] aCount, int[] bCount, int[] pastMap){
		
		if(k2==0) return 0;
		if(depth == d) return 0;
		int[] presentMap = new int[pastMap.length];
		
		switch(status){
		
		case 'a' : 
			presentMap = a.clone();
			for(int i = 0; i<pastMap.length; i++){
				if(pastMap[i] == presentMap[i]){
					aCount[i]++;
					bCount[i] = 0;
				}else{
					aCount[i]=1;
					bCount[i]=0;
				}
				max[i] = Math.max(aCount[i], max[i]);
				
			}
			
			break;
			
		case 'b' :
			presentMap = b.clone();
			for(int i = 0; i<pastMap.length; i++){
				if(pastMap[i] == presentMap[i]){
					bCount[i]++;
					aCount[i] = 0;
				}else{
					bCount[i] = 1;
					aCount[i] = 0;
				}
				max[i] = Math.max(bCount[i], max[i]);
				
			}
			break;
		case 'x' :
			presentMap = map[depth].clone();
			for(int i = 0; i<pastMap.length; i++){
				if(pastMap[i] == presentMap[i]){
					if(pastMap[i] == 0){
						aCount[i]++;
						bCount[i] = 0;
					}
					if(pastMap[i] == 1){
						bCount[i]++;
						aCount[i] = 0;
					}
				}else{
					if(presentMap[i] == 0){
						aCount[i] =1;
						bCount[i] =0;
					}else{
						aCount[i] = 0;
						bCount[i] = 1;
					}
				}
				max[i] = Math.max(max[i],Math.max(bCount[i], aCount[i]));
			}
			break;
			default : break;
		}
		
		//max 판별 localmax 와 globalmax확인
		//k가 넘어가면 Integer.MAX_VALUE 반환
		int min = Integer.MAX_VALUE;
		for(int i = 0; i<max.length; i++){
			min = Math.min(min, max[i]);
		}
		
		if(min>=k) return k2;
		
		int a1 = dfs(k2,'x',depth+1,max.clone(),aCount.clone(),bCount.clone(),presentMap.clone());
		int a2 = dfs(k2-1,'a',depth+1,max.clone(),aCount.clone(),bCount.clone(),presentMap.clone());
		int a3 = dfs(k2-1,'b',depth+1,max.clone(),aCount.clone(),bCount.clone(),presentMap.clone());
		
		return Math.max(a1, Math.max(a2, a3));
	}
}
