import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
						// 동, 남, 서, 북
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static int N, M, score;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		
		int[][] dice = {{0,2,0},
						{4,1,3},
						{0,5,0},
						{0,6,0}};
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int r = 1; int c = 1;
		int dir = 0;
		int ans = 0;
		
		while(K-->0) {
			
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(!check(nr,nc)) {
				if(dir<2) {
					dir+=2;
				}else {
					dir-=2;
				}
			}
			
			r = r+dr[dir];
			c = c+dc[dir];
			
			change(dice, dir);
			
			score = 0;
			dfs(r,c,map[r][c], new boolean[N+1][M+1]);
			
			ans += score * map[r][c];
			
			if(dice[3][1] > map[r][c]) {
				dir = (dir+1) % 4;
			}else if(dice[3][1] < map[r][c]) {
				if(dir==0) dir = 3; 
                else dir = dir-1;
			}
		}
		System.out.println(ans);
	}
	
	private static void dfs(int r, int c, int num, boolean[][] visited) {
		
		score++;
		visited[r][c] = true;
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!check(nr,nc)) continue;
			if(visited[nr][nc]) continue;
			if(map[nr][nc] != num) continue;
			
			visited[nr][nc] = true;
			dfs(nr,nc,num, visited);
		}
	}
	
	private static void change(int[][] dice, int dir) {
		
		switch(dir) {
		case 0: //동
			int tmp = dice[3][1];
			dice[3][1] = dice[1][2];
			dice[1][2] = dice[1][1];
			dice[1][1] = dice[1][0];
			dice[1][0] = tmp;
			break;
		case 1: //남
			tmp = dice[3][1];
			dice[3][1] = dice[2][1];
			dice[2][1] = dice[1][1];
			dice[1][1] = dice[0][1];
			dice[0][1] = tmp;
			break;
		case 2: //서
			tmp = dice[3][1];
			dice[3][1] = dice[1][0];
			dice[1][0] = dice[1][1];
			dice[1][1] = dice[1][2];
			dice[1][2] = tmp;
			break;
		case 3: //북
			tmp = dice[0][1];
			dice[0][1] = dice[1][1];
			dice[1][1] = dice[2][1];
			dice[2][1] = dice[3][1];
			dice[3][1] = tmp;
			break;
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >=1 && nr <=N && nc>=1 && nc<=M;
	}
}
