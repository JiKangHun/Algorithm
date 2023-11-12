class Solution {
    public int[][] solution(int n) {
        int[][] answer = new int[n][n];
        
        
        int r = 0;
        int c = 0;
        int d = 0;
        int num = 1;
        int[] dr = {0,1,0,-1};
        int[] dc = {1,0,-1,0};
        
        while(num <= n*n){
            
            answer[r][c] = num;
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr < 0 || nc < 0 || nr >=n || nc >= n || answer[nr][nc] != 0){
                d = (d+1) % 4;
            }
            r = r + dr[d];
            c = c + dc[d];
            ++num;
        }
        
        return answer;
    }
}