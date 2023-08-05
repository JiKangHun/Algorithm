import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] primeNumber = new int[M+1];
		primeNumber[1] = 1;
		for(int i=2; i<=Math.sqrt(M); i++) {
			if(primeNumber[i] == 1) continue;
			for(int j=2*i; j<=M; j+=i) {
				primeNumber[j] = 1;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=N; i<=M; i++) {
			if(primeNumber[i] == 0) sb.append(i+"\n");
		}
		System.out.println(sb);
	}
}
