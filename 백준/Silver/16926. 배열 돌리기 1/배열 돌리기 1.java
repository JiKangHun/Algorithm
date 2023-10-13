import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int point = Math.min(N, M)/2;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < point; j++) {		
				go(j, j, N - j, M - j);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}

	}

	private static void go(int r, int c, int N, int M) {
		
		int j = r;
		int tmp = arr[r][c];
		
		int d = 0;
		
		while(d<4) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (check(j, nr, nc, N, M)) {
				arr[r][c] = arr[nr][nc];
				r = nr;
				c = nc;
			}else {
				d += 1;
			}					
		}
		arr[j+1][j] = tmp;
		
	}

	private static boolean check(int k, int nr, int nc, int N, int M) {
		return nr >= k && nr < N && nc >= k && nc < M;
	}
}
