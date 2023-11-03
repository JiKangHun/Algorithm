import java.util.Scanner;

public class Main {
	
	static int[][] map;
	static int cntB;
	static int cntW;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		divide(0,0,N);
		System.out.println(cntW);
		System.out.println(cntB);
	}

	private static void divide(int S1, int S2, int N) {
		
		int startColor = map[S1][S2];
		for(int i=S1; i<S1+N; i++) {
			for(int j=S2; j<S2+N; j++) {
				if(startColor != map[i][j]) {
					divide(S1,S2,N/2);
					divide(S1,S2+N/2,N/2);
					divide(S1+N/2,S2,N/2);
					divide(S1+N/2,S2+N/2,N/2);
					return;
				}
			}
		}
		if(startColor==0) {
			cntW++;
		}else {
			cntB++;
		}
	}
}
