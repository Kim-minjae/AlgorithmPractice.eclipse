package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No2383 {
	
	private static ArrayList<Comparator<Man>> comp;
	private static ArrayList<Man> mans;
	private static ArrayList<CompTmp> compTmp;
	
	public static void main(String args[])throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<tc; t++){
			
			int n = Integer.parseInt(br.readLine());
			int[] aPoint = new int[2];
			int[] bPoint = new int[2];
			comp = new ArrayList<Comparator<Man>>();
			mans = new ArrayList<>();
			compTmp = new ArrayList<>();
			ArrayList<Man> readyQA = new ArrayList<>();
			ArrayList<Man> readyQB = new ArrayList<>();
			
			for(int i = 0; i<n; i++){
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++){
					int tmp = Integer.parseInt(st.nextToken());
					
					if(tmp == 1){
						mans.add(new Man(i,j));
					}
					if(tmp >= 2){
						comp.add(new Comp(i,j,tmp));
						compTmp.add(new CompTmp(i, j,tmp));
					}
				}
			}
			
			System.out.println("#" +(t+1)+" " + dfs(readyQA,readyQB,0));
			
		}
		
	}
	static class Man{
		int x;
		int y;
		public Man(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static class CompTmp {
		int x;
		int y;
		int tmp;
		public CompTmp(int x, int y,int tmp) {
			super();
			this.x = x;
			this.y = y;
			this.tmp = tmp;
		}
		
	}
	
	static class Comp implements Comparator<Man>{
		int aX;
		int aY;
		int capacity;
		
		public int getaX() {
			return aX;
		}

		public void setaX(int aX) {
			this.aX = aX;
		}

		public int getaY() {
			return aY;
		}

		public void setaY(int aY) {
			this.aY = aY;
		}

		public int getCapacity() {
			return capacity;
		}

		public void setCapacity(int capacity) {
			this.capacity = capacity;
		}

		public Comp(int aX, int aY, int capacity) {
			super();
			this.capacity = capacity;
			this.aX = aX;
			this.aY = aY;
		}

		@Override
		public int compare(Man o1, Man o2) {
			// TODO Auto-generated method stub
			return (Math.abs(o1.x - aX)+Math.abs(o1.y-aY)) - ((Math.abs(o2.x-aX) +Math.abs(o2.y-aY)));
		}
	}
	@SuppressWarnings("unchecked")
	public static int dfs(ArrayList<Man> readyA,ArrayList<Man> readyB,int i){
		
		if(readyA.size() + readyB.size() == mans.size()){
			return counter(readyA,readyB);
		}
		ArrayList<Man> tmpArrayA = (ArrayList<Man>) readyA.clone();
		ArrayList<Man> tmpArrayB = (ArrayList<Man>) readyB.clone();
		tmpArrayA.add(mans.get(i));
		tmpArrayB.add(mans.get(i));
		return Math.min(dfs(tmpArrayA,readyB,i+1), dfs(readyA,tmpArrayB,i+1));
	}
	public static int counter(ArrayList<Man> readyA, ArrayList<Man> readyB){
		int max = 0;
		
		Collections.sort(readyA,comp.get(0));
		Collections.sort(readyB,comp.get(1));
		
		int aCost = 0;
		int bCost = 0;
		
		Queue<Integer> tmpQ = new LinkedList<>();
		
		for(int i =0; i<readyA.size(); i++){
			if(i>=3){
				if(tmpQ.peek()+ compTmp.get(0).tmp  < (Math.abs(readyA.get(i).x - compTmp.get(0).x)+Math.abs(readyA.get(i).y-compTmp.get(0).y))+1){
					tmpQ.poll();
					tmpQ.offer((Math.abs(readyA.get(i).x - compTmp.get(0).x)+Math.abs(readyA.get(i).y-compTmp.get(0).y))+1);
				}else{
					int x = tmpQ.poll();
					tmpQ.offer(x + compTmp.get(0).tmp);
				}
				continue;
			}
			tmpQ.offer(Math.abs(readyA.get(i).x - compTmp.get(0).x)+Math.abs(readyA.get(i).y-compTmp.get(0).y)+1);
		}
		while(!tmpQ.isEmpty()){
			 aCost = tmpQ.poll()+ compTmp.get(0).tmp;
		}
		
		for(int i =0; i<readyB.size(); i++){
			if(i>=3){
				if(tmpQ.peek()+ compTmp.get(1).tmp<(Math.abs(readyB.get(i).x - compTmp.get(1).x)+Math.abs(readyB.get(i).y-compTmp.get(1).y))+1){
					tmpQ.poll();
					tmpQ.offer((Math.abs(readyB.get(i).x - compTmp.get(1).x)+Math.abs(readyB.get(i).y-compTmp.get(1).y))+1);
				}else{
					int x = tmpQ.poll();
					tmpQ.offer(x+ compTmp.get(1).tmp);
				}
				continue;
			}
			tmpQ.offer(Math.abs(readyB.get(i).x - compTmp.get(1).x)+Math.abs(readyB.get(i).y-compTmp.get(1).y)+1);
		}
		while(!tmpQ.isEmpty()){
			 bCost = tmpQ.poll() + compTmp.get(1).tmp;
		}
		
		return Math.max(aCost, bCost);
	}
}
