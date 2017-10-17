package baek1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] di = {0,0,1,-1};
	private static int[] dj = {1,-1,0,0};
	private static int[] hDi = {-2,-2,-1,1,2,2,1,-1};
	private static int[] hDj = {-1,1,2,2,1,-1,-2,-2};
	
	public static void main(String args[])throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int k = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[h+4][w+4];
		int[][][] dp = new int[210][210][33];
		
		
		//갈 수 있는곳 -1 못가는데 0 으로 매핑함. 그리고 2.2가 출발점.
		for(int i = 2; i<h+2; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 2; j<w+2; j++) {
				map[i][j] = Integer.parseInt(st.nextToken())-1;
			}
		}
		
		//만약 원숭이가 w+1,w+1에 도착했으면 끝냄
		
		//0만나면 죽음 -1은 상관무\
		
		
		PriorityQueue<Monkey> moPriorityQueue = new PriorityQueue<>();
		
		//startMonkey 집어넣는다.
		Monkey startMonkey = new Monkey(2, 2, k, 0, 0);
		moPriorityQueue.offer(startMonkey);
		
		while(!moPriorityQueue.isEmpty()) {
			
			Monkey tmpMonkey = moPriorityQueue.poll();
			
			int mx = tmpMonkey.x;
			int my = tmpMonkey.y;
			int mk = tmpMonkey.k;
			int mMove = tmpMonkey.move;
			int hMove = tmpMonkey.horseMove;
			
			if(tmpMonkey.x == h+1 && tmpMonkey.y == w+1) {
				System.out.println(tmpMonkey.horseMove+tmpMonkey.move);
				return;
			}
			
			for(int i = 0; i<4; i++) {
				
				if(map[mx+di[i]][my+dj[i]] != 0 && 
						((dp[mx+di[i]][my+dj[i]][hMove] == 0) || ((dp[mx+di[i]][my+dj[i]][hMove])>(mMove+hMove+1)))) {
					dp[mx+di[i]][my+dj[i]][hMove] = mMove+hMove+1;
					moPriorityQueue.offer(new Monkey(mx+di[i] , my+dj[i], mk, mMove+1, hMove));
				}
				
			}
			
			if(mk>0) {
				for(int i = 0; i<8; i++) {
				
					if(map[mx+hDi[i]][my+hDj[i]] != 0 && 
							((dp[mx+hDi[i]][my+hDj[i]][hMove+1] == 0) || ((dp[mx+hDi[i]][my+hDj[i]][hMove+1])>(mMove+hMove+1)))) {
						dp[mx+hDi[i]][my+hDj[i]][hMove+1] = mMove+hMove+1;
						moPriorityQueue.offer(new Monkey(mx+hDi[i] , my+hDj[i],mk-1, mMove, hMove+1));
					}
				}
			}
		}
		
		System.out.println(-1);
		
		
	}
	static class Monkey implements Comparable<Monkey>{
		int x;
		int y;
		int k;
		int move;
		int horseMove;
		public Monkey(int x, int y, int k, int move, int horseMove) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
			this.move = move;
			this.horseMove = horseMove;
		}
		@Override
		public int compareTo(Monkey arg0) {
			// TODO Auto-generated method stub
			return (this.horseMove+this.move) -(arg0.horseMove+arg0.move);
		}
		
	}
}
