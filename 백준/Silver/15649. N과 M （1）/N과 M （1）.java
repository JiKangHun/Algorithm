import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int N, M;
	static int[] num;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		num = new int[M];
		visited = new boolean[N];
		
		perm(0);
	}


	private static void perm(int cnt) {

		if(cnt==M) {
			for(int i=0; i<M; i++) {
				System.out.print(num[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(visited[i-1]) continue;
			num[cnt] = i;
			visited[i-1] = true;
			perm(cnt+1);
			visited[i-1] = false;
			num[cnt] = 0;
		}
	}
}
