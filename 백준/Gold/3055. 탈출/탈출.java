import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

	static char[][] map;
	static int[][] visitDochi;
	static int[][] visitWater;
	static int R, C;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static Queue<Point> q;
	static boolean finish;
	
	static class Point {
		int x;
		int y;
		int time;

		public Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visitDochi = new int[R][C];
		visitWater = new int[R][C];
		q = new LinkedList<>();
		Point dochi = null;
		for (int i = 0; i < R; i++) {
			char c[] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = c[j];
				// 입력 받으면서 처음 물위치 먼저 q에 넣고
				if (map[i][j] == '*') {
					visitWater[i][j] = 1;
					q.offer(new Point(j, i, -1));
				}
				if (map[i][j] == 'S')
					dochi = new Point(j, i, 0);
			}
		}

		q.offer(dochi); // 도치 처음 위치 큐에 넣기
		visitDochi[dochi.y][dochi.x] = 1; // 도치 처음위치 방문처리
		
		while (!q.isEmpty()) {
			
			int qSize = q.size();
			
			for (int i = 0; i < qSize; i++) {
				Point cur = q.poll();
				if (cur.time == -1) {
					goWater(cur);
				} else {
					goDochi(cur);
					if(finish) return;
				}
			}
			
		}
		
		System.out.println("KAKTUS");
	}

	private static void goDochi(Point cur) {
		int y = cur.y;
		int x = cur.x;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if (!check(ny, nx)) continue;
			if (map[ny][nx] == 'X') continue;
			if (map[ny][nx] == '*') continue;
			if (visitDochi[ny][nx]==1) continue;
			
			if (map[ny][nx] == '.') {
				visitDochi[ny][nx] = 1;
				map[ny][nx] = 'S';
				q.offer(new Point(nx, ny, cur.time + 1));
			}else if(map[ny][nx]=='D') {
				System.out.println(cur.time+1);
				finish = true;
				return;
			}
		}
	}

	private static void goWater(Point cur) {
		int y = cur.y;
		int x = cur.x;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if (!check(ny, nx)) continue;
			if (map[ny][nx] == 'X') continue;
			if (map[ny][nx] == 'D') continue;
			if (visitWater[ny][nx]==1) continue;
			
			if (map[ny][nx] == '.') {
				map[ny][nx] = '*';
				visitWater[ny][nx] = 1;
				q.offer(new Point(nx, ny, -1));
			} else if(map[ny][nx]=='S') {
				map[ny][nx] = '*';
				q.offer(new Point(nx, ny, -1));
			}
		}
	}

	private static boolean check(int ny, int nx) {
		return ny >= 0 && ny < R && nx >= 0 && nx < C;
	}
}
