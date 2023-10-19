import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int answer, N, S;
	static int[] num;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		num = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		if(S==0) --answer;
		System.out.println(answer);
		
	}

	private static void dfs(int cnt, int sum) {
		
		if(cnt == N) {
			answer = sum == S ? answer+1: answer;
			return;
		}
		
		dfs(cnt+1, sum + num[cnt]);
		dfs(cnt+1, sum);
	}
}
