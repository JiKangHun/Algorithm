import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] num;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		num = new int[M];
		perm(0, N, M);
		System.out.println(sb);
	}


	private static void perm(int cnt, int N, int M) {

		if(cnt==M) {
			
			for(int i=0; i<M; i++) {
				sb.append(num[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			num[cnt] = i;
			perm(cnt+1, N, M);
		}
	}
}
