import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		int[][] map = new int[30][30];
		
		for(int i=0; i<30; i++) {
			for(int j=0; j<=i; j++) {
				if(j==0 || j==i) {
					map[i][j] = 1;
					continue;
				}
				
				map[i][j] = map[i-1][j-1] + map[i-1][j];
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken())-1;
		int C = Integer.parseInt(st.nextToken())-1;
		int W = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		
		for(int i=R; i<R+W; i++) {
			for(int j=C; j<=C+i-R; j++) {
				answer += map[i][j];
			}
		}
		
		
		System.out.println(answer);
		
	}
}
