import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	static int N, M;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		
		int[] pos = new int[4];
		
		for(int i=0; i<N; i++) {
			char[] row = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				map[i][j] = row[j];
				if(map[i][j] == 'R') {
					pos[0] = i; pos[1] = j;
					map[i][j] = '.';
				}
				if(map[i][j] == 'B') {
					pos[2] = i; pos[3] = j;
					map[i][j] = '.';
				}
			}
		}
		
		bfs(map, pos);
		answer = answer == Integer.MAX_VALUE ? -1 : answer;
		System.out.println(answer);
		
	}

	private static void bfs(char[][] map, int[] pos) {
		
		Queue<int[]> q = new ArrayDeque<>();
		boolean[] visited = new boolean[11111];
		visited[visitCal(pos[0], pos[1], pos[2], pos[3])] = true;
		int moveCnt = 0;
		
		q.add(pos);
		
		loop:
		while(!q.isEmpty()) {
			
			++moveCnt;
			int size = q.size();
			
			for(int i=0; i<size; i++) {
				
				int[] cur = q.poll();
				
				for(int d=0; d<4; d++) {
					
					int[] fixCur = {cur[0], cur[1], cur[2], cur[3]};
					int[] red = {cur[0], cur[1]};
					int[] blue = {cur[2], cur[3]};
					
					boolean isRedGoal = move('R', map, red, fixCur, d);
					boolean isBlueGoal =  move('B', map, blue, fixCur, d);
					
					if(visited[visitCal(red[0], blue[0], red[1], blue[1])]) continue;
					
					if(!isBlueGoal && isRedGoal ) {
						answer = moveCnt;
						break loop;
					}else if(isBlueGoal) {
						continue;
					}
					
					q.add(new int[] {red[0], red[1], blue[0], blue[1]});
					visited[visitCal(red[0], blue[0], red[1], blue[1])] = true;
					
				}
			}
		}
	}

	private static boolean move(char marble, char[][] map, int[] moveLoc, int[] fixCur, int d) {

		boolean isGoal = false;
		boolean aheadMarble = false;
		int r = moveLoc[0]; int c = moveLoc[1];
		
		
		while(true) {
			
			int nr = r + dr[d]; 
			int nc = c + dc[d];
			
			if(map[nr][nc] == '#') break;
			if(map[nr][nc] == 'O') isGoal = true;
				
			if(marble == 'B' && nr == fixCur[0] && nc == fixCur[1]) aheadMarble = true;
			if(marble == 'R' && nr == fixCur[2] && nc == fixCur[3]) aheadMarble = true;
			
			r = nr; c = nc;
			
			if(isGoal) break;
		}
		
		if( aheadMarble && !isGoal ) {
			
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
		
		moveLoc[0] = r; moveLoc[1] = c;
		return isGoal;
	}
	
	private static int visitCal(int x1, int y1, int x2, int y2) {
		return 1000 * x1 + 100 * y1 + 10 * x2 + y2;
	}
}
