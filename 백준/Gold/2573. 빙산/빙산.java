import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		
		
		while(true) {
		
			// 1) 시간 경과
			++time;
			
			int remainIceberg = melt(N,M);
			if(remainIceberg <= 1) {
				time = 0; 
				break;
			}
			
			boolean isTwo = bfs(remainIceberg, N, M);
			
			if(isTwo) break;
			
			
		}
		
		System.out.println(time);
	}
	private static boolean bfs(int remainIceberg, int N, int M) {
		
		boolean[][] visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++){
				
				if(map[i][j] != 0) {
					
					visited[i][j] = true;
					Queue<int[]> q = new ArrayDeque<>();
					q.add(new int[] {i,j});
					int cnt = 1;
					
					while(!q.isEmpty()) {
						
						int[] cur = q.poll();
						int curR = cur[0];
						int curC = cur[1];
						
						for(int d=0; d<4; d++) {
							
							int nr = curR + dr[d];
							int nc = curC + dc[d];
							
							if(!visited[nr][nc] && map[nr][nc] > 0) {
								visited[nr][nc] = true;
								++cnt;
								q.add(new int[] {nr,nc});
							}
						}
					}
					
					if(cnt == remainIceberg) return false;
						
					return true;
				}
			}
		}
		
		return true;
	}
	
	private static int melt(int N, int M) {
		
		
		int[][] tmpMap = new int[N][M];
		
		// 빙산이 있는 칸의 사방 체크: 바다인 칸이 몇 개인지 체크 후 현재 빙산 높이에서 바다 갯수만큼 뺀 값을 임시맵에 저장
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] !=0) {
					
					int seaCnt = 0;
					for(int d=0; d<4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if(map[nr][nc] == 0) ++seaCnt;
					}
					int meltDown = map[i][j] - seaCnt;
					tmpMap[i][j] = meltDown > 0 ? meltDown : 0; 
				}
			}
		}
		
		int icebergCnt = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				
				if(map[i][j] != 0) {
					map[i][j] = tmpMap[i][j];
					if(map[i][j] > 0) ++icebergCnt;
				}
			}
		}
		
		return icebergCnt;
	}
}
