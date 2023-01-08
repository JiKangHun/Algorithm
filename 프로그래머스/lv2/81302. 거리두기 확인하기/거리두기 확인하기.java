import java.util.*;

class Solution {
    
    public int[] solution(String[][] places) {
        
        int[] answer = new int[5];        
        
        int idx = 0;
        outer :
        for(String[] arr: places){
            char[][] room = new char[5][5];
            for(int i=0; i<5; i++){
                room[i] = arr[i].toCharArray();            
            }                        
            
            for(int j=0; j<5; j++){
               for(int k=0; k<5; k++){
                  if(room[j][k] == 'P' && !bfs(j,k,room)) { 
                    answer[idx++] = 0;
                    continue outer;
                  }
               }
            }
            
            answer[idx++] = 1;
        }        
        return answer;
    }
    
    public boolean bfs(int r, int c, char[][] room) {
        
        int[] dr = {-1,0,1,0};
        int[] dc = {0,-1,0,1};
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        
        q.offer(new int[] {r,c});
        visited[r][c] = true;
        
        int depth = 0;
        while(depth<2){       
            
            int qSize = q.size();
            
            for(int i=0; i<qSize; i++){
                int[] cur = q.poll();
                                                
                for(int d=0; d<4; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];

                    if(nr < 0 || nr > 4 || nc < 0 || nc > 4) continue;
                    if(visited[nr][nc]) continue;
                    if(room[nr][nc] == 'X') continue;
                    
                    if(room[nr][nc] == 'P') return false;

                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                }            
            }
            
            ++depth;
        }
        
        return true;
    }

    
    
}