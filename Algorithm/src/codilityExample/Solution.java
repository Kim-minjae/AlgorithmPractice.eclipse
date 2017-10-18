package codilityExample;

import java.io.IOException;
import java.util.Arrays;

public class Solution {
	public static void main(String args[]) throws IOException{
		
		int A[] = {-1,-3};
		
		Solution a = new Solution();
		System.out.println(a.solution(A));
		
	}
	public int solution(int[] A) {
		
		Arrays.sort(A);
		int result =1;
		
		for(int i = 0; i<A.length; i++) {
			
			if(A[i] > 0) {
				if(A[i] == result) {
					result++;
					continue;
				}
				
				if(A[i] > result) {
					return result;
				}
			}
			
		}
		return result;
	}
}

