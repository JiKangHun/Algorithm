import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] sequence = new int[N];
		
		st = new StringTokenizer(br.readLine()," ");
		
		for(int i=0; i<N; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int sum = sequence[0];
		int answer = Integer.MAX_VALUE;
		
		while( true ) {
			
			if(sum >= S) {
				answer = Math.min(answer, end-start+1);
				sum -= sequence[start++];
			}else {
				if(end == N-1) break;
				sum += sequence[++end];
			}
		}
		
		answer = answer == Integer.MAX_VALUE ? 0 : answer;
		System.out.println(answer);
		
	}
}
