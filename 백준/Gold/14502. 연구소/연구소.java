import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int answer;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		ArrayList<int[]> wallList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) wallList.add(new int[] {i, j});
			}
		}
		
		combi(3, 0, 0, wallList, new ArrayList<int[]>(), map, N, M);
		
		System.out.println(answer);
	}

	private static void combi(int total, int start, int cnt, ArrayList<int[]> wallList, ArrayList<int[]> selected, int[][] map, int N, int M) {
		
		if(cnt == total) {
			
			for(int i=0; i<selected.size(); i++) {
				
				int[] loc = selected.get(i);
				int r = loc[0];
				int c = loc[1];
				map[r][c] = 1;
				
			}
			
			answer = Math.max(answer, countSafeArea(N, M, map));
			
			for(int i=0; i<selected.size(); i++) {
				
				int[] loc = selected.get(i);
				int r = loc[0];
				int c = loc[1];
				map[r][c] = 0;
				
			}
			
			
			return;
		}
		
		for(int i=start; i<wallList.size(); i++) {
			
			selected.add(wallList.get(i));
			combi(total, i+1, cnt+1, wallList, selected, map, N, M);
			selected.remove(selected.size()-1);
			
		}
	}

	private static int countSafeArea(int N, int M, int[][] map) {
		
		int[][] tmpMap = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				
				if(map[i][j] == 2) {
					
					dfs(i, j, tmpMap, N, M);
				}
			}
		}
		
		int cnt = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				
				if(tmpMap[i][j] == 0) ++cnt;
				
			}
		}
		
		return cnt;
	}

	private static void dfs(int r, int c, int[][] tmpMap, int N, int M) {
		
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		
		for(int d=0; d<4; d++) {
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if( nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
			if(tmpMap[nr][nc] != 0) continue;
			
			tmpMap[nr][nc] = 2;
			dfs(nr, nc, tmpMap, N, M);
		}
		
	}
}
