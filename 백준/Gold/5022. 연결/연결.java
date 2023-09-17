import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static class Point{
		
		int r1;
		int c1;
		int r2;
		int c2;
		
		public Point(int r1, int c1, int r2, int c2) {
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
		}
		
	}
	
	static int[] dr = {-3,0,3,0};
	static int[] dc = {0,3,0,-3};

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N  = Integer.parseInt(st.nextToken());
		int M  = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[3*(N+1)][3*(M+1)];
		
		st = new StringTokenizer(br.readLine()," ");
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine()," ");
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		Point point1 = new Point(r1*3+1,c1*3+1,r2*3+1,c2*3+1);
			
		st = new StringTokenizer(br.readLine()," ");
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine()," ");
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());
		Point point2 = new Point(r1*3+1,c1*3+1,r2*3+1,c2*3+1);
		
		
		int answer = Integer.MAX_VALUE;
		//A연결 후 B연결
		int length = connect(point1, point2, N, M);
		length = Math.min(length, connect(point2, point1, N, M));
		answer = Math.min(answer, length);
		
		//B연결 후 A연결
		length = connect(point2, point1, N, M);
		length = Math.min(length, connect(point1, point2, N, M));
		answer = Math.min(answer, length);
		
		
		System.out.println(answer == Integer.MAX_VALUE ? "IMPOSSIBLE" : answer);
		
														
	}
	
	public static int connect(Point point1, Point point2, int N, int M) {
		
		int[][] visitOther = new int[3*(N+1)][3*(M+1)];
		int visitCntA = bfs(point1, point2, N, M, visitOther);
		if(visitCntA !=0) {
			int visitCntB = bfs(point2, point1, N, M, visitOther);
			if(visitCntB != 0) {
				return visitCntA + visitCntB;
			}
		}
		
		return Integer.MAX_VALUE;
	}
	

	
	public static int bfs(Point first, Point second, int N, int M, int[][] visitOther) {
		
		int sr = first.r1;
		int sc = first.c1;
		int er = first.r2;
		int ec = first.c2;
		
		boolean[][] visited = new boolean[3*(N+1)][3*(M+1)];
		visited[sr][sc] = true;
		Queue<ArrayList<int[]>> q = new ArrayDeque<>();
		ArrayList<int[]> route = new ArrayList<>();
		route.add(new int[] {sr, sc});
		q.add(route);
		
		while(!q.isEmpty()) {
			
			ArrayList<int[]> cur = q.poll();
			
			int curR = cur.get(cur.size()-1)[0];
			int curC = cur.get(cur.size()-1)[1];
			
			
			if(curR == er && curC == ec) {	
				
				for(int i=0; i<cur.size(); i++) {
					int[] node = cur.get(i);
					visitOther[node[0]][node[1]] = 1;
				}
				return cur.size()-1;
			}
			
			for(int d=0; d<4; d++) {
				
				int nr = curR + dr[d];
				int nc = curC + dc[d];
				
				if(nr < 0 || nc < 0 || nr >= 3*(N+1) || nc >= 3*(M+1)) continue;
				if(visited[nr][nc]) continue;
				if(visitOther[nr][nc]==1) continue;
				if( (nr == second.r1 && nc == second.c1) || (nr == second.r2 && nc == second.c2) ) continue;
				
				ArrayList<int[]> newRoute = new ArrayList<>();
				for(int i=0; i<cur.size(); i++) {
					newRoute.add(cur.get(i));
				}
				newRoute.add(new int[] {nr,nc});
				q.add(newRoute);
				visited[nr][nc] = true;
			}
			
		}
	
		return 0;
	}
}
