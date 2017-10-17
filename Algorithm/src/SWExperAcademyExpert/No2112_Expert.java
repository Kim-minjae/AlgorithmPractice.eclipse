package SWExperAcademyExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No2112_Expert {
    static int d,w,k,ans;
    static int [][]map,store;
 public static void main(String[] args) throws Exception {
     BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        for(int t=1; t<=testcase; t++) {         
           ans = 10000000;
           st = new StringTokenizer(bf.readLine());
           d = Integer.parseInt(st.nextToken());
           w = Integer.parseInt(st.nextToken());
           k = Integer.parseInt(st.nextToken());
           map = new int[d][w];
           store = new int[d][w];
           for(int i=0; i<d; i++){
                  st = new StringTokenizer(bf.readLine());
                  for(int j=0; j<w; j++){
                      map[i][j] = Integer.parseInt(st.nextToken());
                      store[i][j]  = map[i][j];
                  }
           }
           func(0,0);
System.out.println("#"+t+" "+ans); 
    }       
    bf.close();
 }
 static void func(int cnt, int idx) {
     boolean finish = false;
     //가로로 한번 탐색
         for(int j=0; j<w; j++) {
             int num = map[0][j]; 
             int c=1; 
             boolean check = false;
             for(int i=1; i<d; i++) {
                 if(num == map[i][j]){
                     c++;
                 }
                 else if(num!=map[i][j]) {
                     c=1;
                     num = map[i][j];
                 }
                 if(c>=k){
                     check = true;
                    break; 
                 }
             }
             if(check == false){
                 finish = true;
                 break;
             }
         }
        if(finish== false) {
         ans = Math.min(ans, cnt);
         return;
        }
     if(ans <=cnt) return;
     if(idx>=d) return;
     if(cnt >=k){
         return;
     }
     func(cnt,idx+1);
     for(int a=0; a<2; a++) {
         for(int j=0; j<w; j++){
            map[idx][j] = a;
         }
         func(cnt+1,idx+1);
         for(int j=0; j<w; j++) {
                map[idx][j] = store[idx][j];
         }
     }
 }
}