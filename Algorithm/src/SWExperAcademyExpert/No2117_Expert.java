package SWExperAcademyExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class No2117_Expert {
    static int N;
    static int[][] map;
    static int[][] cache;
     
    static int find(int k){
        int max = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                for(int c=0;c<k-1;c++){
                    if(i-k+1+c<N&&i-k+1+c>=0&&j-c>=0){
                        cache[i][j]+=map[i-k+1+c][j-c];
                    }
                    if(i+c<N&&j-k+1+c>=0&&j-k+1+c<N){
                        cache[i][j]+=map[i+c][j-k+1+c];
                    }
                    if(i+k-1-c<N&&i+k-1-c>=0&&j+c<N){
                        cache[i][j] += map[i+k-1-c][j+c];
                    }
                    if(i-c>=0&&j+k-1-c>=0&&j+k-1-c<N){
                        cache[i][j] += map[i-c][j+k-1-c];
                    }
                }
                max = Math.max(max, cache[i][j]);
            }
        }
        return max;
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=testcase; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken()); // 마을 한 변 길이
            int M = Integer.parseInt(st.nextToken()); // 한 집당 내는 돈
            map = new int[N][N];
            cache = new int[N][N];
            int sum = 0;
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(st.nextToken())*M;
                    cache[i][j] = map[i][j];
                    sum += map[i][j];
                }
            }//map 초기화
            int result = 0;
            int tmp = 0;
            int x=0;
            for(int k=1;k<=N+1;k++){
                int val = k*k + (k-1)*(k-1);
                if(val>sum) break;
                if(k==1) tmp = M;
                else tmp = find(k);
                 
                if(tmp>=val) result = Math.max(result,tmp)/M;
            }
            System.out.println("#"+tc+" "+result);
        }
    }
}
