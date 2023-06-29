import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<Integer> unionPeople;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		int answer = 0;
		
		while(true) {
			
			int[][] visited = new int[N][N];
			int unionCnt = 0;
			unionPeople = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					
					if(visited[i][j] == 0) {
						++unionCnt;
						bfs(map, visited, i, j, N, L, R, unionCnt);
					}
					
				}
			}
			
			if(unionCnt == N*N) break;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					
					map[i][j] = unionPeople.get(visited[i][j]-1);
				}
			}
			
			++answer;
			
		}
		
		System.out.println(answer);
		
	}

	private static void bfs(int[][] map, int[][] visited, int r, int c, int N, int L, int R, int unionCnt) {
		
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, -1, 0, 1};
		
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {r,c});
		visited[r][c] = unionCnt;
		int townCnt = 0;
		int sumPeople = 0;
		
		while(!q.isEmpty()) {
			
			int[] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			
			++townCnt;
			sumPeople += map[curR][curC];
			
			for(int d=0; d<4; d++) {
				
				int nr = curR + dr[d];
				int nc = curC + dc[d];
				
				if( nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if( Math.abs(map[curR][curC] - map[nr][nc]) < L || Math.abs(map[curR][curC] - map[nr][nc]) > R) continue;
				if( visited[nr][nc] != 0) continue;
				
				q.add(new int[] {nr, nc} );
				visited[nr][nc] = unionCnt;
				
			}
		}
		 unionPeople.add(sumPeople / townCnt);
	}
}
