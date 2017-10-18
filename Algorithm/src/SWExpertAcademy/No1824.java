package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No1824 {
	
	private static int[] di = {0,1,0,-1}; // 동 남 서 북. 시계방향
	private static int[] dj = {1,0,-1,0};
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		Loop1 : for(int t = 0; t<tc; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int goalX = 0;
			int goalY = 0;
			
			char[][] map = new char[r][c];
			boolean[][][][] visit = new boolean[r][c][16][4]; // 루프를 막기위한 체크
			
			//map 입력받기
			for(int i =0; i<r; i++) {
				String tmpStr = br.readLine();
				for(int j = 0; j<c; j++) {
					map[i][j] = tmpStr.charAt(j);
				}
			}
			
			Queue<Process> processQ = new LinkedList<>();
			
			Process starter = new Process(0, 0, 0, 0);
			processQ.offer(starter);
			
			while(!processQ.isEmpty()) {
				
				Process tmpProcess = processQ.poll();
				int x = tmpProcess.x;
				int y = tmpProcess.y;
				int memory = tmpProcess.memory;
				int direction = tmpProcess.direction;
				
				if(x>r-1) x= 0; if(x<0) x=r-1; if(y>c-1) y =0; if(y<0) y = c-1; if(memory> 15) memory =0; if(memory<0) memory = 15;
			
				
				//칸이 갔던곳인지 묻는 판별
				if(visit[x][y][memory][direction])continue;
				
				//칸이 수인지 문자인지 판별. 
				
				char command = map[x][y];
				
				if(command-'0' >= 0 && command-'0' <=9) {
					memory = command-'0';
					visit[x][y][memory][direction] = true;
					processQ.offer(new Process(x+di[direction], y+dj[direction], memory, direction));
					continue;
				}
				
				switch(command) {
				case '<' :
					
					visit[x][y][memory][direction] = true;
					direction = 2;
					processQ.offer(new Process(x+di[direction], y+dj[direction], memory, direction));
					
					break;
				case '>' :
					
					visit[x][y][memory][direction] = true;
					direction = 0;
					processQ.offer(new Process(x+di[direction], y+dj[direction], memory, direction));
					
					break;
				case '^' :
					
					visit[x][y][memory][direction] = true;
					direction = 3;
					processQ.offer(new Process(x+di[direction], y+dj[direction], memory, direction));
					
					break;
				case 'v' :
					
					visit[x][y][memory][direction] = true;
					direction = 1;
					processQ.offer(new Process(x+di[direction], y+dj[direction], memory, direction));
					
					break;
				case '_' :
					
					if(memory == 0) {
						visit[x][y][memory][direction] = true;
						direction = 0;
						processQ.offer(new Process(x+di[direction], y+dj[direction], memory, direction));
					}else {
						visit[x][y][memory][direction] = true;
						direction = 2;
						processQ.offer(new Process(x+di[direction], y+dj[direction], memory, direction));
					}
					
					break;
				case '|' :
					
					if(memory == 0) {
						visit[x][y][memory][direction] = true;
						direction = 1;
						processQ.offer(new Process(x+di[direction], y+dj[direction], memory, direction));
					}else {
						visit[x][y][memory][direction] = true;
						direction = 3;
						processQ.offer(new Process(x+di[direction], y+dj[direction], memory, direction));
					}
					
					break;
				case '?' :
					visit[x][y][memory][direction] = true;
					for(int i = 0; i<4; i++) {
						processQ.offer(new Process(x+di[i], y+dj[i], memory, i));
					}
					break;
				case '.' :
					visit[x][y][memory][direction] = true;
					processQ.offer(new Process(x+di[direction], y+dj[direction], memory, direction));
					break;
				case '+' :
					visit[x][y][memory][direction] = true;
					memory++;
					processQ.offer(new Process(x+di[direction], y+dj[direction], memory, direction));
					break;
				case '-' :
					visit[x][y][memory][direction] = true;
					memory--;
					processQ.offer(new Process(x+di[direction], y+dj[direction], memory, direction));
					break;
					
				case '@' : 
					System.out.println("#"+(t+1) + " " + "YES");
					continue Loop1;
				default : 
					System.out.println(command);
					System.out.println("안걸렸다...");
				break;
					
				}
				
			}
			
			System.out.println("#"+(t+1) + " " + "NO");
			
		}
		
	}
	static class Process{
		int x;
		int y;
		int memory;
		int direction;
		public Process(int x, int y, int memory, int direction) {
			super();
			this.x = x;
			this.y = y;
			this.memory = memory;
			this.direction = direction;
		}
	}
}
