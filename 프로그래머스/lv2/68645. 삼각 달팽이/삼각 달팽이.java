import java.util.*;

class Solution {
    public int[] solution(int n) {
                
        int[] answer = new int[n*(n+1)/2];
        int[] dr = {1,0,-1};
        int[] dc = {0,1,-1};
        int[][] arr = new int[n][n];
        
        int num = 1;
        int d = 0;
        int r = -1;
        int c = 0;
     
        for(int i=0; i<n; i++){
            d = i % 3;
            for(int j=i; j<n; j++){
                r = r + dr[d];
                c = c + dc[d];
                arr[r][c] = num++;
            }
        }
        
        int idx = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<=i; j++){
                answer[idx++] = arr[i][j];
            }
        }
        return answer;
    }
}