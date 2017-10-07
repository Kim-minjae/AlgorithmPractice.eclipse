package nhnNO5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
/**
 * Created by pose2 on 2017-09-24.
 */
public class Main {
    public static void main(String args[]) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        
        
        int tc = Integer.parseInt(br.readLine());

        for(int t = 0; t<tc; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] dpMap = new int[N+1][M+1];
            long start = System.currentTimeMillis();
            for(int i = 1; i< N+1; i++){
                String tmp = br.readLine();
                for(int j = 1; j<M+1; j++){
                    dpMap[i][j] = tmp.charAt(j-1) - '0';
                }
            }

            int max = 0;
            int sum = 0;

            for(int i = 1; i< N+1; i++){
                for(int j =1; j<M+1; j++){
                    if(dpMap[i][j] != 0){

                        sum += dpMap[i][j] = 1 + Math.min(dpMap[i-1][j-1],Math.min(dpMap[i-1][j],dpMap[i][j-1]));
                        	
                        max = Math.max(max, dpMap[i][j]);
                        
                    }
                }
            }

//            System.out.println(max);
//            System.out.println(sum);
            
            for(int k = 1; k<= max; k++){
            	
            	int[][] dpMapLeft = new int[N+1][M+1];
                int[][] dpMapUp = new int[N+1][M+1];
                
            	for(int i = 1; i<N+1; i++){
            		for(int j = 1; j<M+1; j++){
            			
            			if(dpMap[i][j] >= k){
            				
            				if(dpMap[i][j-1] >= k){
            					sum += dpMapLeft[i][j] = dpMapLeft[i][j-1]+1;
            				}
            				
            				if(dpMap[i-1][j] >= k){
            					sum += dpMapUp[i][j] = dpMapUp[i-1][j] +1;
            				}
            				
            			}
            			
            		}
            	}
            	
            }
            System.out.println(sum);
            long end = System.currentTimeMillis();
            System.out.println((end-start));
        }
    }
}
