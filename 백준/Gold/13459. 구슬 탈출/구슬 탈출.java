import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 3시간 10분

public class Main {
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
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
		
		dfs(0, 0, map, redLoc, blueLoc);
		dfs(0, 1, map, redLoc, blueLoc);
		dfs(0, 2, map, redLoc, blueLoc);
		dfs(0, 3, map, redLoc, blueLoc);
		
		answer = answer == Integer.MAX_VALUE ? 0 : 1;
		System.out.println(answer);
		
	}

	private static void dfs(int cnt, int d, char[][] map, int[] redLoc, int[] blueLoc) {
		
//		System.out.println("cnt: "+cnt);
//		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		
		if(cnt >= answer) return;
		if(cnt == 10) return;
		
		int rR = redLoc[0];
		int rC = redLoc[1];
//		System.out.print("curR: "+rR+" ");
//		System.out.println("curC: "+rC);
		
		boolean aheadB = false;
		boolean isReadGoal = false;
		
		while(true) {
			
			int nr = rR + dr[d];
			int nc = rC + dc[d];
			
//			System.out.print("nr: "+nr+" ");
//			System.out.println("nc: "+nc);
			if(map[nr][nc] == '#') break;
			if(map[nr][nc] == 'O') {
				isReadGoal = true;
				break;
			}
			if(map[nr][nc] == 'B') aheadB = true;
			
			rR = nr;
			rC = nc;
		}
		
		if( aheadB ) {
			if(d == 0 ) {
				rR += 1;
			}else if( d == 1) {
				rC += 1;
			}else if( d == 2) {
				rR -= 1;
			}else {
				rC -= 1;
			}
		}
		
		int bR = blueLoc[0];
		int bC = blueLoc[1];
		boolean aheadR = false;
		boolean isBlueGoal = false;
		
		while(true) {
			
			int nr = bR + dr[d];
			int nc = bC + dc[d];
			
			if(map[nr][nc] == '#') break;
			if(map[nr][nc] == 'O') {
				isBlueGoal = true;
				break;
			}
			if(map[nr][nc] == 'R') aheadR = true;
			
			bR = nr;
			bC = nc;
			
		}
		
		if( aheadR ) {
			if(d == 0 ) {
				bR += 1;
			}else if( d == 1) {
				bC += 1;
			}else if( d == 2) {
				bR -= 1;
			}else {
				bC -= 1;
			}
		}
		
		if(!isBlueGoal && isReadGoal ) {
			answer = Math.min(answer, cnt+1);
			return;
		}else if(isBlueGoal) {
			return;
		}
		
		int[] newRedLoc = {rR, rC};
		int[] newBlueLoc = {bR, bC};
		
		// 다음 이동으로 보내기
		map[redLoc[0]][redLoc[1]] = '.';
		map[blueLoc[0]][blueLoc[1]] = '.';
		
		map[rR][rC] = 'R';
		map[bR][bC] = 'B';
//		System.out.println("bR: "+bR+"bC: "+bC);
		
		for(int i=0; i<=3; i++) {
			
			dfs(cnt+1, i, map, newRedLoc, newBlueLoc);
		}
		
		map[rR][rC] = '.';
		map[bR][bC] = '.';
		
		map[redLoc[0]][redLoc[1]] = 'R';
		map[blueLoc[0]][blueLoc[1]] = 'B';
	}
}
