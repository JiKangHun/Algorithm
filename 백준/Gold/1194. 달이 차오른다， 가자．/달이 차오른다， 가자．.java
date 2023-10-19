import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Minsik{
		
		int r;
		int c;
		int key;
		
		public Minsik(int r, int c, int key) {
			this.r = r;
			this.c = c;
			this.key = key;
		}
	
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[H][W];
		int[][][] visited = new int[H][W][64]; //0:열쇠x, 1:a, 2:b .... 6:f
			
		Queue<Minsik> q = new LinkedList<>();
		
		for(int i=0; i<H; i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=0; j<W; j++) {
				map[i][j] = ch[j];
				if(map[i][j] == '0') {
					q.add(new Minsik(i,j,0));
					visited[i][j][0] = 1;
				}
			}
		}
		
		int[] dr = {-1,0,1,0};
		int[] dc = {0,1,0,-1};
		
		int depth = -1;
		int answer = -1;
		
		outer:
		while(!q.isEmpty()) {
			
			++depth;
			int qSize = q.size();
			
			for(int i=0; i<qSize; i++) {
				
				Minsik cur = q.poll();
				int r = cur.r;
				int c = cur.c;
				if(map[r][c] == '1') {
					answer = depth;
					break outer;
				}
				
				for(int d=0; d<4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					//범위를 벗어남
					if(nr<0 || nc<0 || nr>=H || nc>=W) continue;
					//벽
					if(map[nr][nc] == '#') continue;
					//문인데 열쇠가 없음
					if('A' <= map[nr][nc] && 'F' >= map[nr][nc] && 
							( cur.key & (1 << map[nr][nc]-'A') ) != (int)Math.pow(2, map[nr][nc]-'A')) continue;
					//현재 열쇠 상태로 방문한 적 있음
					if(visited[nr][nc][cur.key] == 1) continue;
					
					//열쇠가 있는 곳이면 열쇠 get
					if('a' <= map[nr][nc] && 'f' >= map[nr][nc]) {
						int nextKey = cur.key | 1 << (map[nr][nc] -'a');
						visited[nr][nc][nextKey] = 1;
						q.add(new Minsik(nr, nc, nextKey));						
					}else {
						visited[nr][nc][cur.key] = 1;
						q.add(new Minsik(nr, nc, cur.key));
					}
				}
			}
		}
		
		System.out.println(answer);
		
		
	}
}
