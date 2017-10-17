package baek14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int[] di = {-1,0,1,0};
	private static int[] dj = {0,1,0,-1};
	
	public static void main(String[] args)throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		
		st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken()); // d 가 0 이면 북, 1 이면 동 2이면 남 3이면 서
		
		for(int i = 0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<m; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		RobotCleaner robot = new RobotCleaner(r, c, d, 0);
		
		while(robot.d != -1){
			
			if(map[robot.x][robot.y] == 0){
				robot.clenSpace++;
				map[robot.x][robot.y] = -1;
			}
			
			int direction = robot.d;
			
			if(map[robot.x+1][robot.y] != 0 && map[robot.x-1][robot.y] != 0 && map[robot.x][robot.y+1] != 0 && map[robot.x][robot.y-1] != 0){
				
				int back = direction +2;
				if(back >3) back -=4;
				
				if(map[robot.x + di[back]][robot.y + dj[back]] ==1 ){
					robot.d = -1;
					continue;
				}
				
				robot.x += di[back];
				robot.y += dj[back];
				
				continue;
			}
			
			direction--;
			if(direction<0) direction+=4;
			while(map[robot.x + di[direction]][robot.y + dj[direction]] != 0){
				direction --;
				if(direction<0) direction+=4;
			}
			
			robot.d = direction;
			robot.x += di[direction];
			robot.y += dj[direction];
		}
		
		System.out.println(robot.clenSpace);
		
	}
	static class RobotCleaner{
		int x;
		int y;
		int d;
		int clenSpace;
		public RobotCleaner(int x, int y, int d,int cleanSpace) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.clenSpace = cleanSpace;
		}
	}
}
