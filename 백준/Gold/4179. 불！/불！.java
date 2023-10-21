import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R,C, answer = -1;
	static char[][] map;
	static int[][] visited;
	static int[] jihun;
	static ArrayList<int[]> fireList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new int[R][C];
		jihun = new int[3];
		
		for(int i=0; i<R; i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				map[i][j] = ch[j];
				if(map[i][j] == 'J') {
					jihun[0] = i;
					jihun[1] = j;
					jihun[2] = 1;
				}
				if(map[i][j] =='F') fireList.add(new int[] {i,j});
			}
		}
		
		//불 map에서 확산
		//불의 각 위치 도달 시간 저장
		//지훈이 bfs, 도달시간이 더 작거나 빈칸인 경우만 이동, 밖으로 빠져나가면 끝내기
		bfs(0);
		bfs(1);
		
		System.out.println(answer==-1? "IMPOSSIBLE" : answer);
	}

	//type: 0-불, 1-지훈
	private static void bfs(int type) {
		
		Queue<int[]> q = new LinkedList<>();
		if(type == 0) {
			for(int[] fire: fireList) {
				q.add(fire);
				visited[fire[0]][fire[1]] = 1;
			}
		}else {
			q.add(jihun);
			visited[jihun[0]][jihun[1]] = -1;
		}
		
		int[] dr = {-1,0,1,0};
		int[] dc = {0,1,0,-1};
		
		while(!q.isEmpty()) {
				
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(type == 0 && (nr < 0 || nc < 0 || nr >= R || nc >= C)) continue;
				if(type == 1 && (nr < 0 || nc < 0 || nr >= R || nc >= C)) {
					
					answer = cur[2];
					return;
				}
				
				if(map[nr][nc] == '#') continue;
				
				if(type == 0 && visited[nr][nc] !=0) continue;
				if(type ==0) visited[nr][nc] = visited[r][c] + 1;
				if(type== 0) q.add(new int[] {nr,nc});
				
				if(type ==1 && visited[nr][nc]!=0 && visited[nr][nc] <= cur[2]+1) continue;
				if(type == 1 && visited[nr][nc] == -1) continue;
				if(type ==1) visited[nr][nc] = -1;
				if(type ==1) q.add(new int[] {nr,nc,cur[2]+1});
				
			}
			
		}
		
	}
}
