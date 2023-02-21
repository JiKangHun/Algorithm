import java.util.*;


class Solution {
    
    static boolean[][] visited;
    static char[][] map;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};
    
    public ArrayList<Integer> solution(String[] maps) {
        
        ArrayList<Integer> answer = new ArrayList();
        
        int row = maps.length;
        int col = maps[0].length();
        
        map = new char[row][col];
        
        visited = new boolean[row][col];
        
        for(int i=0; i<row; i++) {
            char[] info = maps[i].toCharArray();
            for(int j=0; j<col; j++) {
                map[i][j] = info[j];
            }
        }
        
        boolean isPossible = false; //안 되는 경우 처리 어떻게?
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
               
                if(!visited[i][j] && map[i][j]!='X'){
                    isPossible = true;
                    answer.add( bfs(row, col, i, j) );
                }
                
            }
        }
        
        if(!isPossible) answer.add(-1);
        
        Collections.sort(answer);
        return answer;
    }
    
    public int bfs(int r, int c, int curX, int curY){
        
        int food = map[curX][curY] - '0';
        visited[curX][curY] = true;
        Queue<int[]> q = new ArrayDeque();
        q.add(new int[] {curX, curY});
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];
            
            for(int d=0; d<4; d++){
                
                int nr = cr+dr[d];
                int nc = cc+dc[d];
                
                if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc] == 'X') continue;
                
                food += map[nr][nc] - '0';
                visited[nr][nc] = true;
                q.add(new int[] {nr,nc});
                
            }
            
        }
        
        return food;
    }
}