/*package baek13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] dx = {1,0,-1,0};
	private static int[] dy = {0,1,0,-1};
	private static char[][] map;
	
	public static void main(String args[])throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Queue<Balls> balls = new LinkedList<>();
		
		map = new char[n][m]; 
		
		for(int i = 0; i< n; i++){
			String tmp = br.readLine();
			for(int j = 0; j< m; j++){
				map[i][j] = tmp.charAt(j);
			}
		}
		
		while(!balls.isEmpty()){
			
			Balls tmp = balls.poll();
			
			for(int dir = 0; dir<4; dir++){
				Balls result = move(tmp,dir);
				if(result.blueX != 0){
					if(result.redX == 0 && result.redY == 0){
						System.out.println(result.count);
						return;
					}
				}
			}
			
		}
		
		
	}
	static class Balls implements Cloneable{
		
		int redX;
		int redY;
		boolean redMovable;
		
		int blueX;
		int blueY;
		boolean blueMovable;
		
		int count;

		public Balls(int redX, int redY, boolean redMovable, int blueX, int blueY, boolean blueMovable, int count) {
			super();
			this.redX = redX;
			this.redY = redY;
			this.redMovable = redMovable;
			this.blueX = blueX;
			this.blueY = blueY;
			this.blueMovable = blueMovable;
			this.count = count;
		}

		
	}
	//오 아래 왼 위 순서로 한다.
	public static Balls move(Balls balls, int dir){
		
		//dir의 방향에 맡게 움직이되, 도중에 모든 경우를 고려한다.
		
		//먼저해야할것을 찾는다.
		
		switch(dir){
		
		return balls;
	}
}
*/