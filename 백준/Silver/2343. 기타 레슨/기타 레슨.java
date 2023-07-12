import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine()," ");
		int[] length = new int[N];
		int left = 0;
		int right = 0;
		for(int i=0; i<N; i++) {
			length[i] = Integer.parseInt(st.nextToken());
			right += length[i];
		}
		
		int answer = right;
		
		outer:
		while(left + 1 < right) {
			
			int mid = (left + right) / 2;
			int sum = 0;
			int group = 0;
			
			for(int i=0; i<N; i++) {
				if(length[i] > mid) {
					left = mid;
					continue outer;
				}
				sum += length[i];
				if(sum > mid) {
					++group;
					sum = length[i];
				}
			}
			++group;
			
			if(group <= M) {
				answer = mid;
				right = mid;
			}else {
				left = mid;
			}

		}
		
		System.out.println(answer);
	}
}
