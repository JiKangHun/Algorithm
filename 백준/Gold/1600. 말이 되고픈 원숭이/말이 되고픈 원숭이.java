import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[H][W];
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0,0,K});
		int[][] visited = new int[H][W];
		int[][] kRemain = new int[H][W];
		visited[0][0] = 1;
		
		int[] dr = {1,0,-1,0,2,1,2,1,-1,-2,-1,-2};
		int[] dc = {0,1,0,-1,1,2,-1,-2,2,1,-2,-1};
		int answer = -1;
		int depth = -1;
		
		loop:
		while(!q.isEmpty()) {
			
			++depth;
			int qSize = q.size();
			for(int i=0; i<qSize; i++) {
				
				int[] cur = q.poll();
				
				int r = cur[0];
				int c = cur[1];
				int k = cur[2];
				
				if(r==H-1 && c==W-1) {
					answer = depth;
					break loop;
				}
				
				for(int d=0; d<12; d++) {
					
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr<0 || nc<0 || nr>=H || nc>=W) continue;
					if(map[nr][nc] != 0) continue;
					
					if(d<4 && (visited[nr][nc]==0 || kRemain[nr][nc] < k)) {
						q.add(new int[] {nr,nc,k});
						visited[nr][nc] = 1;
						kRemain[nr][nc] = k;
					}
					if(k>0 && d>=4 && (visited[nr][nc] ==0 || kRemain[nr][nc] < k-1)) {
						q.add(new int[] {nr,nc,k-1});
						visited[nr][nc] = 1;
						kRemain[nr][nc] = k-1;
					}
					
				}
			}
						
		}
		
		System.out.println(answer);
	}
}
