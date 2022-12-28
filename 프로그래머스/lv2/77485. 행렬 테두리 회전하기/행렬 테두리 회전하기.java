import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        
        int[] answer = new int[queries.length];
        int[][] arr = new int[rows+1][columns+1];
        int num = 1;
        for(int i=1; i<rows+1; i++){
            for(int j=1; j<columns+1; j++){
                arr[i][j] = num++;
            }
        }
        
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};
        
        for(int i=0; i<queries.length; i++){
            int x1 = queries[i][0];
            int y1 = queries[i][1];
            int x2 = queries[i][2];
            int y2 = queries[i][3];
            
            int tmp = arr[x1][y1];
            int d = 0;
            
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] {x1,y1});
            int min = Integer.MAX_VALUE;
            
            while(true) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                
                if(arr[x][y] < min) {
                    min = arr[x][y];
                    answer[i] = min;
                }
                
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                
                if(nx>x2 || ny >y2 || nx < x1 || ny < y1) d++;                                            
                
                nx = x + dx[d];
                ny = y + dy[d];
                
                if(nx == x1 && ny == y1){
                    arr[x][y] = tmp;
                    break;
                }
                
                arr[x][y] = arr[nx][ny];
                q.offer(new int[] {nx, ny});
            }
        }
   
        
        return answer;
    }
}