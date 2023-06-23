import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static class Shark{
		int r;
		int c;
		int speed;
		int dir;
		int size;
		
		public Shark(int r, int c, int speed, int dir, int size) {
			super();
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Shark[][] map = new Shark[R][C];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) -1;
			int c = Integer.parseInt(st.nextToken()) -1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) -1;
			int z = Integer.parseInt(st.nextToken());
			map[r][c] = new Shark(r,c,s,d,z);
		}
		
		int loc = -1;
		int times = C;
		while(times>0) {
			times--;
			loc++;
			fishing(loc, map, R);
			move(map,R,C);
			
		}
		
		System.out.println(size);
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static int size = 0;
	private static void move(Shark[][] map, int R, int C) {
		
		ArrayList<Shark> sharkList = new ArrayList<>();
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] != null) {
					Shark cur = map[i][j];
					int r = cur.r;
					int c = cur.c;
					int d = cur.dir;
					
					//상하로 움직일 때
					if(d<2) {
						if(d==1) {
							r = r + cur.speed;
							while( !(0 <= r && r <= R-1) ) {
								d = (d+1)%2;
								if(r > R-1) {
									r = (R-1) - (r - (R-1));
								}else {
									r = Math.abs(r);
								}
							}
							
						}else {
							r = r - cur.speed;
							while( !(0 <= r && r <= R-1) ) {
								d = (d+1)%2;
								if(r > R-1) {
									r = (R-1) - (r - (R-1));
								}else {
									r = Math.abs(r);
								}
							}
						}
					//좌우로 움직일 때
					}else {
						if(d == 2) {
							c = c + cur.speed;
							while( !(0 <= c && c <= C-1) ) {
								d = (d+1)%2+2;
								if(c > C-1) {
									c = (C-1) - (c - (C-1));
								}else {
									c = Math.abs(c);
								}
							}
						}else {
							c = c - cur.speed;
							while( !(0 <= c && c <= C-1) ) {
								d = (d+1)%2+2;
								if(c > C-1) {
									c = (C-1) - (c - (C-1));
								}else {
									c = Math.abs(c);
								}
							}
						}
					}
					
					sharkList.add(new Shark(r,c,cur.speed,d,cur.size));
					map[i][j] = null;
				} // if
			} // inner
		} // outer
		
		for(Shark shark : sharkList) {
			int r = shark.r;
			int c = shark.c;
			if(map[r][c] == null) {
				map[r][c] = shark;
			}else {
				if(map[r][c].size < shark.size) {
					map[r][c] = shark;
				}
			}
		}
	}

	private static void fishing(int loc, Shark[][] map, int R) {
		for(int i=0; i<R; i++) {
			if(map[i][loc] != null) {
				size = size + map[i][loc].size;
				map[i][loc] = null;
				break;
			}
		}
	}
}
