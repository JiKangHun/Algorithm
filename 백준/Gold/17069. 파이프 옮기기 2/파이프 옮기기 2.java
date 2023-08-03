import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
	
		int[][] map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		long[][][] d = new long[N+1][N+1][3];
		if(map[1][3] != 1) {
			d[1][3][0] = 1;
			if(map[2][2] != 1 && map[2][3] != 1) {
				d[2][3][2] = 1;
			}
		}
		
		//그 방향으로 온 거는 그 방향 칸에 플러스 해주어야 함
		//마지막 정답은 전 방향으로 온 거 총합
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				
				// 빈칸인 경우
				if(map[i][j] != 1) {
					//왼쪽 칸은 가로나 대각으로 온 것만 더한다
					d[i][j][0] += d[i][j-1][0] + d[i][j-1][2];
					//위쪽 칸은 세로나 대각으로 온 것만 더한다
					d[i][j][1] += d[i-1][j][1] + d[i-1][j][2];
					//대각 방향은 다 더한다.(왼쪽과 위가 벽이 아닌 경우)
					if(map[i][j-1] != 1 && map[i-1][j] != 1) {
						d[i][j][2] += d[i-1][j-1][0] + d[i-1][j-1][1] + d[i-1][j-1][2];
					}
				}
			}
		}
		
		long answer = 0;
		for(int i=0; i<3; i++) {
			answer += d[N][N][i];
		}
		
		System.out.println(answer);
		
	}
}
