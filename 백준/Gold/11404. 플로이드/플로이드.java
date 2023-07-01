import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 9999999;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int[][] D = new int[n+1][n+1];
		
		for(int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			if(D[a][b]!=0) {
				D[a][b] = Math.min(D[a][b], cost);
			}else {
				D[a][b] = cost;
			}
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(i==j) {
					continue;
				}else if(D[i][j] == 0) {
					D[i][j] = INF;
				}
			}
		}
		
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				if(i==k) continue;
				for(int j=1; j<=n; j++) {
					if(j==k || i==j) continue;
					D[i][j] = Math.min(D[i][k]+D[k][j], D[i][j]);
				}
			}
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(D[i][j] == INF) {
					System.out.print(0+" ");
					continue;
				}
				System.out.print(D[i][j]+" ");
			}
			System.out.println();
		}
	}
}
