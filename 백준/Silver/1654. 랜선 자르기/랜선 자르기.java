import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] lan = new int[K];
		long answer = 0;
		long left = 0;
		long right = 0;
		
		for(int i=0; i<K; i++) {
			lan[i] = Integer.parseInt(br.readLine());
			right = Math.max(right, lan[i]);
		}
		right += 1;
		
		while(left+1 < right) {
			
			long mid = (left + right) / 2;
			long cnt = 0;
			for(int i=0; i<K; i++) {
				cnt += lan[i] / mid;
			}
			
			if( cnt >= N ) {
				answer = mid;
				left = mid;
			}else {
				right = mid;
			}
			
		}
		
		System.out.println(answer);
		
		
	}
}
