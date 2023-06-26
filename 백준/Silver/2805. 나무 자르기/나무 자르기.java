import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] tree = new int[N];
		
		st = new StringTokenizer(br.readLine()," ");
		
		int start = 0;
		int end = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			if( tree[i] > end ) end = tree[i];
		}
		
		int answer = 0;
		while(start <= end) {
			
			int mid = (start + end)/2;
			long sum = 0;
			for(int i=0; i<N; i++) {
				if(tree[i] > mid) sum += (tree[i] - mid);
			}
			if(sum >= M) {
				answer = mid;
				start = mid+1;
			}else {
				end = mid-1;
			}
		}
		
		System.out.println(answer);
		
	}
}
