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
		
		int idx = 0;
		int sum = 0;
		boolean isPossible = true;
		
		while(true) {
			
			if(idx == N) {
				isPossible = false;
				break;
			}
			sum += sequence[idx];
			if(sum >= S) break;
			++idx;
		}
		
		if(!isPossible) {
			System.out.println(0);
		}else {
			
			int start = 0;
			int end = idx;
			int answer = idx+1;
			int length = idx+1;
			
			while( end != N ) {

				if(sum >= S) {
					answer = Math.min(answer, length);
					sum -= sequence[start];
					++start;
					--length;
				}else {
					++end;
					if(end == N) break;
					sum += sequence[end];
					++length;
				}
			}
			
			System.out.println(answer);
		}
		
		
	}
}
