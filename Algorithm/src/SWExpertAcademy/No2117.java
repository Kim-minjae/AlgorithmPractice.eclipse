package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class No2117 {	
	
	public static void main(String args[])throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0 ; t<tc; t++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());			
			int max = 0;
			int houseCount = 0;
			ArrayList<House> houseArrayList = new ArrayList<>();
			
			
			
			for(int i = 0; i<n; i++){
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<n; j++){
					int tmp = Integer.parseInt(st.nextToken());
					if(tmp == 1){
						houseCount++;
						houseArrayList.add(new House(i,j));
					}
					
				}
			}
			
			//resultQ의 사이즈가 houseCount가 될때까지 넣으면서 이중포문으로 전 구간을 탐색한다.
			
			for(int i = 0; i<n; i++){
				
				for(int j = 0; j<n; j++){
					Stack<House> resultQ = new Stack<>();
					Comp comparator = new Comp(i,j);
					Collections.sort(houseArrayList,comparator);
					
					while(resultQ.size()< houseCount){
						
						for(int x = 0; x<houseArrayList.size(); x++){
							
							//거리에 해당되는 값 - Q.size()*m 이것이 0보다 크면 max갱신해야함
							//우선 resultQ에다가 순서에 다가온 house를 집어넣는다. 그리고 peek하면서 거리 차이를 확인하고 거리에따른 가격차이를 확인한다. 괜춘하면 max갱신
							
							resultQ.push(houseArrayList.get(x));
							
							
							
							int k = 1+(Math.abs(resultQ.peek().x -i)+Math.abs(resultQ.peek().y -j));
							
							int cost = k*k + (k-1)*(k-1);
							int earn = m*resultQ.size();
							
							int tmpQpeekx = resultQ.peek().x;
							int tmpQpeeky = resultQ.peek().y;
							
							if((earn - cost ) >=0){
								max = Math.max(max, resultQ.size());
							}
							/*System.out.printf("%d,%d 에 %d,%d로 비교함 k는 %d임 그랬더니 /cost는 %d이고 /earn은 %d임 / max값이 %d로 나옴 ",i,j,tmpQpeekx,tmpQpeeky,k,cost,earn,max);
							System.out.println();*/
							
						}
						
					}
					
				}
			}
			
			System.out.println(max);
			
		}
		
	}
	static class House{
		int x;
		int y;
		public House(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	} 
	static class Comp implements Comparator<House>{
		int x1;
		int y1;
		
		public Comp(int x1, int y1) {
			super();
			this.x1 = x1;
			this.y1 = y1;
		}

		@Override
		public int compare(House o1, House o2) {
			// TODO Auto-generated method stub
			return (Math.abs(o1.x-x1)+Math.abs(o1.y-y1)) - (Math.abs(o2.x-x1)+Math.abs(o2.y-y1));
		}
		
	}
}
