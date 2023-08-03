import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main  {
	
	static int N;
	static int answer;
	static int[][] map;
	static int[][] row = {{0,1,0,1},
						{0,1,1,1}};
	
	static int[][] col = {{1,0,1,0},
						{1,0,1,1}};
	
	static int[][] diagonal = {{1,1,0,1},
								{1,1,1,0},
								{1,1,1,1}};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
	
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] pipe = {0, 0, 0, 1};
		movePipe(pipe);
		System.out.println(answer);
		
	}
	
	private static void movePipe(int[] pipe) {
		
		if(outMap(pipe)) return;
		if(checkWall(pipe)) return;
		if(pipe[0]!=pipe[2] && pipe[1]!=pipe[3] && checkDigonal(pipe)) return; 
		if(arrive(pipe)) {
			++answer;
			return;
		}
		
		//가로방향일 때
		if(pipe[0] == pipe[2]) {
			for(int i=0; i<2; i++) {
				int[] newPipe = new int[4];
				for(int d=0; d<4; d++) {
					newPipe[d] = pipe[d] + row[i][d];
				}
				movePipe(newPipe);
			}
		
		//세로방향일 때
		}else if(pipe[1] == pipe[3]) {
			for(int i=0; i<2; i++) {
				int[] newPipe = new int[4];
				for(int d=0; d<4; d++) {
					newPipe[d] = pipe[d] + col[i][d];
				}
				movePipe(newPipe);
			}
		
		//대각선일 때
		}else {
			for(int i=0; i<3; i++) {
				int[] newPipe = new int[4];
				for(int d=0; d<4; d++) {
					newPipe[d] = pipe[d] + diagonal[i][d];
					
				}
				movePipe(newPipe);
			}
		}
	}
	
	private static boolean checkDigonal(int[] newPipe) {
		return map[newPipe[2]-1][newPipe[3]] == 1 || map[newPipe[2]][newPipe[3]-1] == 1;
	}

	private static boolean arrive(int[] pipe) {
		return pipe[2] == N-1 && pipe[3] == N-1;
	}
	private static boolean outMap(int[] pipe) {
		
		return pipe[0] >= N || pipe[2] >= N || pipe[1] >= N || pipe[3] >= N;
	}
	private static boolean checkWall(int[] pipe) {
		return map[pipe[0]][pipe[1]] == 1 || map[pipe[2]][pipe[3]] == 1;
	}
}
