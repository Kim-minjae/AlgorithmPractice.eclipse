package baek9466;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, S[], cnt; //학생들의 수, 선택된 학생들의 번호 , 싸이클에 속하는 정점의 개수 
	static boolean visited[], finished[];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++){
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			S = new int[n+1];
			for(int j = 1; j<=n; j++){
				S[j] = Integer.parseInt(st.nextToken());
			}			
			visited = new boolean[n+1];
			finished = new boolean[n+1];
			//각 컴포넌트에 대해 DFS시작
			cnt = 0;
			for(int k=1; k<=n; k++){
				if(!visited[k]) dfs(k);
			}
			System.out.println(n-cnt);	
		}
	}
	private static void dfs(int curr) {
		visited[curr] = true;
		int next = S[curr];	
		if(visited[next]){
			if(!finished[next]){
				for(int temp = next; temp!=curr; temp=S[temp])	cnt++;
				cnt++;//자기 자신 
			}
		}else	dfs(next);
		finished[curr] = true;
	}
}