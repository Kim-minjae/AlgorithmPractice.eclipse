package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class No2115 {
	
	//static 영역에 dfs에 들어가면서 매 테스트케이스마다 초기화되는것들 넣어둠
	private static int map[][];
	private static int dpMap[][];
	private static int M;
	private static int C;
	private static int N;
	
	public static void main(String args[])throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<tc; t++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			dpMap = new int[N][N];
			
			//맵 초기화
			for(int i = 0; i<N; i++){
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++){
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int max = 0;
			//0부터 N제곱-1 까지 index를 키워가며 i와 j를 대체함
			for(int i =0; i<N*N; i++){
				if((i%N)>(N-M)) continue;
				max = Math.max(max, dfs(i,1));
			}
			System.out.println("#"+(t+1) + " " + max);
		}
		
	}
	public static int dfs(int index, int depth){
		if(depth == 2){
			return honeyPrice(index);
		}
		int result = honeyPrice(index);
		int secondMax = 0;
		for(int i = index+M; i<N*N ; i++){
			if((i%N)>(N-M)) continue;
			secondMax = Math.max(secondMax, dfs(i,2));
		}
		return result + secondMax;	
	}
	public static int honeyPrice(int index){
		if(dpMap[index/N][index%N] != 0){
			return  dpMap[index/N][index%N];
		}
		int priceSum1 = 0;
		
		//아까여기 시작점도 index i써야하는란에도 index라고 썼었어서 치명적인 실수를 범했었지. 그럼 시작점이 index인 경우만 선택되어서 몇개의 케이스는 맞게 나옴
		//그래서 더 햇깔릴 수가 있어.
		for(int i = index; i<index+M; i++){
			priceSum1 = Math.max(priceSum1, pick(index,i,C));
		}
		
		return dpMap[index/N][index%N] =priceSum1;
	}
	public static int pick(int start, int index, int capacity){
		
		if(index == (start+M-1)) return map[index/N][index%N]*map[index/N][index%N];
		
		int resultSum = map[index/N][index%N]*map[index/N][index%N];
		int max = 0;
		for(int i = index+1; i<start+M; i++){
			if((capacity - map[index/N][index%N]) >= map[i/N][i%N]){
				max = Math.max(max, pick(start,i,capacity-map[index/N][index%N]));
			}
		}
		return resultSum + max;
	}
}
