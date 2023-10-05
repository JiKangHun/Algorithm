import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static class Spot implements Comparable<Spot>{
		
		int r;
		int c;
		int breakCnt;
		
		public Spot(int r, int c, int breakCnt) {
			this.r = r;
			this.c = c;
			this.breakCnt = breakCnt;
		}
		
		public int compareTo(Spot o1) {
			
			if(this.breakCnt == o1.breakCnt) {
				return o1.r + o1.c - this.r + this.c; 
			}else {
				return this.breakCnt - o1.breakCnt;
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[][] cnt = new int[N][M];
		int[][] visited = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j) - '0';		
			}
		}
		
		//벽을 가장 적게 부순 것
		//N- 행 + M - 열이 가장 큰 것
		//맵에 벽을 부순 갯수 저장하여 그보다 작은 건 CONTINUE 때리기
		
		int[] dr = {-1,0,1,0};
		int[] dc = {0,1,0,-1};
		
		int answer = 0;
		PriorityQueue<Spot> pq = new PriorityQueue<>();
		pq.add(new Spot(0,0,0));
		
		while(!pq.isEmpty()) {
			
			Spot cur = pq.poll();
//			System.out.println("r:"+cur.r +" c:"+cur.c+" cnt:"+cur.breakCnt);
			if(cur.r == N-1 && cur.c == M-1) {
				answer = cur.breakCnt;
				break;
			}
			
			for(int d=0; d<4; d++) {
				
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
//				System.out.println("nr:"+nr +" nc:"+nc);
				if(visited[nr][nc] != 0 && cnt[nr][nc] <= cur.breakCnt + map[nr][nc]) continue;
				visited[nr][nc] = 1;
				cnt[nr][nc] = cur.breakCnt + map[nr][nc];
				pq.add(new Spot(nr, nc, cnt[nr][nc]));
				
			}
			
		}
		
		System.out.println(answer);
		
	}
}
