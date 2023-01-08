import java.util.*;

class Solution {
    
    public int[] solution(String[][] places) {
        
        int[] answer = new int[5];               //정답배열 초기화, 대기실 5개의 거리두기 성공 여부
        
        int idx = 0;
        outer :
        for(String[] arr: places){              // 대기실 정보 하나씩 가져오기
            char[][] room = new char[5][5];     // 1차원 배열 2차원으로 변환하여 담을 변수 초기화
            for(int i=0; i<5; i++){
                room[i] = arr[i].toCharArray(); // 2차원 배열로 변환     
            }                        
            
            for(int j=0; j<5; j++){
               for(int k=0; k<5; k++){                        //대기실 정보 쭉 돌면서 사람을 찾아서
                  if(room[j][k] == 'P' && !bfs(j,k,room)) {   //만약 사람이 앉아있고, bfs 결과가 false면 거리두기 실패
                    answer[idx++] = 0;                        //정답을 0으로 초기화 후 다음 대기실로 넘어가기 위해 idx값 +1
                    continue outer;                           //다음 대기실 조사를 위해 바깥 for문 다음으로 넘기기
                  }
               }
            }
            
            answer[idx++] = 1;                                //2차원 배열 모두 탐색하면서 'P'가 BFS 모두 통과하면 1로 초기화
        }        
        return answer;
    }
    
    public boolean bfs(int r, int c, char[][] room) { 
        // 사방 탐색하면서 거리두기가 실패할 가능성이 있는 노드는 Q에 담으면서 확인해 나간다
        // 확인하려는 P 입장에서 거리두기 성공이면 TRUE 리턴
        // P 입장에서 사방탐색하면서 탐색 위치에 P나 O가 있을 때만 Q에 담으며 탐색을 이어간다.
        // 'X'일 경우 만약 대각방향에 사람이 앉아있어서 거리두기에 실패한다고 해도, 적어도 그 X때문에 실패하는 경우는 아니다.
        // 다른 방향에 파티션이 없기 때문에 실패할 것이고, 이 경우는 그 방향을 탐색하면서 알 수 있기 때문에
        // 'X'는 Q에 담을 필요가 없다.
        
        int[] dr = {-1,0,1,0};
        int[] dc = {0,-1,0,1};
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        
        q.offer(new int[] {r,c});  // 현재 탐색할 'P'의 위치 삽입
        visited[r][c] = true;      // 방문처리
        
        int depth = 0;
        while(depth<2){            // 너비 탐색으로 2번째 깊이까지만 가면 맨하튼거리 2인 위치 모두 확인 가능
            
            int qSize = q.size();  // 한 깊이에 대해 한 WHILE 문에서 한 번에 탐색하기 위해 사이즈 알아내서 초기화
            
            for(int i=0; i<qSize; i++){  // 사이즈만큼 돌면서 같은 뎁스의 노드 모두 다 꺼냄
                int[] cur = q.poll();
                                                
                for(int d=0; d<4; d++) {    //사방탐색하면서
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];

                    if(nr < 0 || nr > 4 || nc < 0 || nc > 4) continue;  // 배열 범위 벗어나면 넘기기
                    if(visited[nr][nc]) continue;                       // 이미 방문한 위치면 넘기기
                    if(room[nr][nc] == 'X') continue;                   // 'X'면 파티션, 파티션이라면 적어도 'P'에서
                                                                        // 이 파티션을 타고 거리두기가 실패하는 경우는 없음, 넘김
                    if(room[nr][nc] == 'P') return false;               // 바로 옆이 사람이면 실패, FALSE 리턴

                    visited[nr][nc] = true;                             // Q에 담은 것 방문처리
                    q.offer(new int[] {nr, nc});
                }            
            }
            
            ++depth;
        }
        
        return true;
    }

    
    
}