import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int[] budget = new int[N];		
		int max = Integer.MIN_VALUE;
		int sum = 0;
		
		for(int i=0; i<N; i++) {
			budget[i] = Integer.parseInt(st.nextToken());
			sum += budget[i];
			if(max < budget[i]) max = budget[i];
		}
		
		int total = Integer.parseInt(br.readLine());
		
		if(sum<=total) {
			System.out.println(max);
		}else {
			
			int low = 0;
			int high = max;
			int answer = 0;
			
			while(low+1 < high) {
				
				int mid = (low + high) / 2;
				sum = 0;
				for(int i=0; i<N; i++) {
					sum += budget[i] <= mid ? budget[i] : mid; 
				}
				
				if(sum <= total) {
					answer = mid;
					low = mid;
				}else {
					high = mid;
				}
				
			}
			
			System.out.println(answer);
		}
	}
}
