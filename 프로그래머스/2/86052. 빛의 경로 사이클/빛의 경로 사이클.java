import java.util.*;

class Solution {
    
    //남동북서
    static int[] dr = {1, 0,-1,0};
    static int[] dc = {0, 1,0,-1};
    static int n,m;
    static ArrayList<Integer> answer = new ArrayList<>();
    static int[][][] visited;
    
    public ArrayList<Integer> solution(String[] grid) {
        
        n = grid.length;
        m = grid[0].length();        
        visited = new int[n][m][4];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                for(int d=0; d<4; d++){
                    if(visited[i][j][d] != 0) continue;
                    int cycleLength = dfs(i,j,d,0,grid);
                    answer.add(cycleLength);
                }
            }
        }
        Collections.sort(answer);
  
        return answer;
    }
    
    public int dfs(int r, int c, int d, int length, String[] grid){
        
        
        while(true){
            
            if(visited[r][c][d] == 1){
                break;
            }
            visited[r][c][d] = 1;
            ++length;

            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr < 0){
                nr = n-1;
            }else if( nr >= n){
                nr = 0;
            }else if(nc < 0){
                nc = m-1;
            }else if(nc >= m){
                nc = 0;
            }
            
            if(grid[nr].charAt(nc) == 'R'){
                d = (d+7)%4;
            }else if(grid[nr].charAt(nc) == 'L'){
                d = (d+1)%4;
            }
            r = nr;
            c = nc;
    
        }
        
        return length;
    }
}