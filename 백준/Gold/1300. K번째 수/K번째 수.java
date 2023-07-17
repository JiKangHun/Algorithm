import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		
		long left = 1;
		long right = (long)N*N+1;
		long answer = 1;
		
		while(left+1 < right) {
			
			long mid = (left+right) / 2;
			long smaller = 0;
			
			for(int i=1; i<=N; i++) {
				// 나누어 떨어진다. 같은 게 있다.
				if(mid % i == 0) {
					// 내가 배열에 포함된다.
					if( mid / i <= N) {
						smaller += mid/i - 1;
					// 내가 배열에 포함되지 않는다.
					}else {
						smaller += N;
					}
				// 나누어 떨어지지 않는다. 내가 없다.
				}else {
					if( mid / i <= N) {
						smaller += mid/i;
					// 내가 배열에 포함되지 않는다.
					}else {
						smaller += N;
					}
				}
			}
			
			if(k <= smaller) {
				right = mid;
			}else {
				answer = mid;
				left = mid;
			}
		}
		
		System.out.println(answer);
	}
}
