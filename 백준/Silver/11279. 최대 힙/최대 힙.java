import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception {
		PriorityQueue<Integer> pq = new PriorityQueue();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			int a = -Integer.parseInt(br.readLine());
			if(a!=0) {
				pq.offer(a);
			}else {
				if(!pq.isEmpty()) {
					System.out.println(-pq.poll());
				}else {
					System.out.println(0);
				}
			}
		}
		
	}
}
