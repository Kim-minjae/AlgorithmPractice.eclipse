package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No2382 {
	
	private static int[] di = {0,-1,1,0,0};
	private static int[] dj = {0,0,0,-1,1};
	
	public static void main(String args[]) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<tc; t++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 셀 한변
			int M = Integer.parseInt(st.nextToken()); // 격리시간
			int K = Integer.parseInt(st.nextToken()); // 군집 수
			
			Queue<Germ> germs = new LinkedList<>();
			Queue<Location> locations = new LinkedList<>();
			
			for(int i = 0; i<K; i++){
				
				st = new StringTokenizer(br.readLine());
				
				int tmpX = Integer.parseInt(st.nextToken());
				int tmpY = Integer.parseInt(st.nextToken());
				int tmpGermNum = Integer.parseInt(st.nextToken());
				int tmpDirection = Integer.parseInt(st.nextToken());
				
				germs.offer(new Germ(tmpX, tmpY, tmpGermNum, tmpDirection, tmpGermNum));
				
			}
			//M시간동안 돌림
			for(int i = 0; i<M; i++){
				
				//매번 새로운 맵을 만들어서 적용
				
				Germ[][] Gmap = new Germ[N][N];
				
				//Q에서 빌때까지 뽑으면서 프로세스 하고 맵에 박아둔다. 이 상황에서는 로케이션을 박아둔다.
				Loop1 : while(!germs.isEmpty()){
					
					Germ tmpGerm = germs.poll();
					
					tmpGerm.x = tmpGerm.x + di[tmpGerm.direction];
					tmpGerm.y = tmpGerm.y + dj[tmpGerm.direction];
					
					//비어있다면?
						if(Gmap[tmpGerm.x][tmpGerm.y] == null){
						//그곳이 최외곽인가 아닌가를 확인해본다.
						
						//최외곽이라면 방향바꿔주고 넣기
						if(tmpGerm.x == 0 || tmpGerm.x == N-1 || tmpGerm.y ==0 || tmpGerm.y == N-1){
							//방향 바꿔주기. else if 로 구현하면서 한번만 적용되도록 하기
							switch(tmpGerm.direction){
							case 1 : tmpGerm.direction = 2; break;
							case 2 : tmpGerm.direction = 1; break;
							case 3 : tmpGerm.direction = 4; break;
							case 4 : tmpGerm.direction = 3; break;
							default : System.out.println("no way"); break;
							}
							tmpGerm.germNum /= 2; // 수를 반으로 쪼깬다.
							if(tmpGerm.germNum == 0)continue Loop1;
						}
						//비어있고 최외곽이 아니라면 그냥 넣어주기만하자. 
							Gmap[tmpGerm.x][tmpGerm.y] = tmpGerm;
							locations.offer(new Location(tmpGerm.x, tmpGerm.y));
					}
					//비어있지않다면 locations에는 추가할 필요가 없고, 그 자리에 있는것과 앞으로들어올것들을 모아서 병합해준다,
						
					else{
						if(Gmap[tmpGerm.x][tmpGerm.y].leader > tmpGerm.leader){
							Gmap[tmpGerm.x][tmpGerm.y].germNum+= tmpGerm.germNum;
						}else{
							tmpGerm.germNum += Gmap[tmpGerm.x][tmpGerm.y].germNum;
							Gmap[tmpGerm.x][tmpGerm.y] = tmpGerm;
						}
					}
					
				}
				while(!locations.isEmpty()){
					Location tmpLocation = locations.poll();
					Gmap[tmpLocation.x][tmpLocation.y].leader = Gmap[tmpLocation.x][tmpLocation.y].germNum;
					germs.offer(Gmap[tmpLocation.x][tmpLocation.y]);
				}
				
			}		
			//다 돌린후 남아있는 세균수 구하기
			int sum = 0;
			while(!germs.isEmpty()){
				Germ tmpGerm = germs.poll();
				
				sum += tmpGerm.germNum;
			}	
			System.out.println(sum);
		}
	}
	
	static class Germ{
		int x;
		int y;
		int germNum;
		int direction;
		int leader;
		public Germ(int x, int y, int germNum, int direction, int leader) {
			super();
			this.x = x;
			this.y = y;
			this.germNum = germNum;
			this.direction = direction;
			this.leader = leader;
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
	}
}
