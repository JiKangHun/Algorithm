import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	//동남서북
	static int[][][] cctv = 
			{
			{{0},{1},{2},{3}}, //1번
			{{0,2},{1,3}}, //2번
			{{0,1},{1,2},{2,3},{3,0}}, //3번
			{{0,1,2},{1,2,3},{2,3,0},{3,0,1}}, //4번
			{{0,1,2,3}} //5번
			};
	
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static int N,M;
	static ArrayList<int[]> cctvList;
	static int[] p;
	static int[][] map;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		cctvList = new ArrayList<>();
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] >= 1 && map[i][j] <= 5) cctvList.add(new int[] {i,j,map[i][j]-1});
			}
		}
		p = new int[cctvList.size()];
		
		perm(0, cctvList.size());
		
		System.out.println(answer);
	}

	private static void perm(int cnt, int total) {
		
		if(cnt == total) {
			
			answer = Math.min(answer, count());
			return;
		}
		
		for(int i=0; i<cctv[cctvList.get(cnt)[2]].length; i++) {
			
			p[cnt] = i;
			perm(cnt+1, total);
			
		}
		
	}

	private static int count() {
		
		int[][] tmp = new int[N][M];
		for(int i=0; i<N; i++) {
			tmp[i] = map[i].clone();
		}
		
		for(int i=0; i<cctvList.size(); i++) {
			
			int[] loc = cctvList.get(i);
			
			int type = loc[2];
			int dir = p[i];
			
			for(int j=0; j<cctv[type][dir].length; j++) {
				
				int r = loc[0];
				int c = loc[1];
				int d = cctv[type][dir][j];
				
				while(true) {
					
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr < 0 || nc < 0 || nr >= N || nc >= M) break;
					if(map[nr][nc] == 6) break;
					
					tmp[nr][nc] = -1;
					r = nr;
					c = nc;
					
				}
				
			}
		}
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(tmp[i][j] == 0) ++cnt;
			}
		}
		
		return cnt;
	}
}
