import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new LinkedList<>();
		int[] visited = new int[100001];
		visited[N] = -1;
		q.add(N);
		
		while(!q.isEmpty()) {
			
			int cur = q.poll();
			
			if(cur == K) {
				break;
			}
			
			int up = cur + 1;
			int down = cur - 1;
			int move = cur * 2;
			
			if(move<=100000 && visited[move]==0) {
				visited[move] = 3;
				q.add(move);
			}
			
			if(up<=100000 && visited[up]==0) {
				visited[up] = 2;
				q.add(up);
			}
			
			if(0<= down && visited[down]==0) {
				visited[down] = 1;
				q.add(down);
			}
						
		}
		
		Stack<Integer> stack = new Stack<>();
		stack.push(K);
		int cur = K;
		int time = 0;		
		
		while(cur != N) {
			
			if(visited[cur] == 3) {
				cur /= 2;
				stack.push(cur);
				++time;
			}
			
			if(visited[cur] == 2) {
				cur -= 1;
				stack.push(cur);
				++time;
			}
			
			if(visited[cur] == 1) {
				cur += 1;
				stack.push(cur);
				++time;
			}
			
		}
		
		System.out.println(time);
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}
	}
}
