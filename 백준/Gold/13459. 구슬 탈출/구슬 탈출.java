import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int N;
	static int M;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		
		int[] redLoc = new int[2];
		int[] blueLoc = new int[2];
		
		for(int i=0; i<N; i++) {
			char[] row = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				map[i][j] = row[j];
				if(map[i][j] == 'R') {
					redLoc[0] = i; redLoc[1] = j;
				}
				if(map[i][j] == 'B') {
					blueLoc[0] = i; blueLoc[1] = j;
				}
			}
		}		
		
		for(int d=0; d<4; d++) {
			boolean[] visited = new boolean[11111];
			int chk = visitCal(redLoc[0], redLoc[1], blueLoc[0], blueLoc[1]);
			visited[chk] = true;
			dfs(0, d, map, redLoc, blueLoc, visited);
		}
		
		answer = answer == Integer.MAX_VALUE ? 0 : 1;
		System.out.println(answer);
		
	}

	private static void dfs(int cnt, int d, char[][] map, int[] redLoc, int[] blueLoc, boolean[] visited) {
		
		if(cnt >= answer) return;
		if(cnt == 10) return;

		int[] newRedLoc = {redLoc[0], redLoc[1]};
		boolean isRedGoal = move(map, newRedLoc, d);
		
		int[] newBlueLoc = {blueLoc[0], blueLoc[1]};
		boolean isBlueGoal =  move(map, newBlueLoc, d);
		
		
		if(!isBlueGoal && isRedGoal ) {
			answer = Math.min(answer, cnt+1);
			return;
		}else if(isBlueGoal) {
			return;
		}
		
		int newChk = visitCal(newRedLoc[0], newRedLoc[1], newBlueLoc[0], newBlueLoc[1]);
		if(visited[newChk])return;
		
		// 다음 이동으로 보내기
		map[redLoc[0]][redLoc[1]] = '.';
		map[blueLoc[0]][blueLoc[1]] = '.';
		
		map[newBlueLoc[0]][newBlueLoc[1]] = 'B';
		map[newRedLoc[0]][newRedLoc[1]] = 'R';
		
		visited[newChk] = true;
		
		for(int i=0; i<=3; i++) {
			
			if(d<=1 && i<=1) continue;
			if(d>1 && i>1) continue;
			dfs(cnt+1, i, map, newRedLoc, newBlueLoc, visited);
		}
		
		visited[newChk] = false;
		
		map[newRedLoc[0]][newRedLoc[1]] = '.';
		map[newBlueLoc[0]][newBlueLoc[1]] = '.';
		
		map[redLoc[0]][redLoc[1]] = 'R';
		map[blueLoc[0]][blueLoc[1]] = 'B';
		
	}

	private static int visitCal(int x1, int y1, int x2, int y2) {
		return 1000 * x1 + 100 * y1 + 10 * x2 + y2;
	}

	private static boolean move(char[][] map, int[] newLoc, int d) {

		boolean isGoal = false;
		boolean aheadMarble = false;
		
		int r = newLoc[0];
		int c = newLoc[1];
		
		while(true) {
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(map[nr][nc] == '#') break;
			if(map[nr][nc] == 'O') {
				isGoal = true;
				break;
			}
			if(map[nr][nc] == 'B' || map[nr][nc] == 'R' ) {
				aheadMarble = true;
			}
			
			r = nr;
			c = nc;
		}
		
		if( aheadMarble ) {
			
			if(d == 0) {
				r += 1;
			}else if(d == 1) {
				r -= 1;
			}else if(d == 2) {
				c += 1;
			}else {
				c -= 1;
			}
		}
		
		newLoc[0] = r;
		newLoc[1] = c;
		
		return isGoal;
	}
}
