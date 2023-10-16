import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static class Location{
		
		int loc;
		int target;
		ArrayList<Integer> route;
		
		public Location(int loc, int target, ArrayList<Integer> route) {
			
			this.loc = loc;
			this.target = target;
			this.route = route;
		}
		

	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(N > K) {
			System.out.println(N-K);
			for(int i=N; i>=K; i--) {
				System.out.print(i+" ");
			}
			return;
		}
		
		Queue<Location> q = new LinkedList<>();
		ArrayList<Integer> route = new ArrayList<>();
		route.add(N);
		q.add(new Location(N,K,route));
		
		int[] visited = new int[100001];
		Arrays.fill(visited, Integer.MAX_VALUE);
		visited[N] = 0;
		
		int answer = Integer.MAX_VALUE;
		ArrayList<Integer> answerRoute = new ArrayList<>();
		
		while(!q.isEmpty()) {
			
			Location cur = q.poll();
			if(cur.loc == K && cur.route.size() < answer) {			
				answer = cur.route.size();
				answerRoute = cur.route;
				break;
			}
			
			int down = cur.loc - 1;
			int up = cur.loc + 1;
			int move = cur.loc * 2;
			
			if(move <= 100000 && visited[move] > cur.route.size()+1) {
				ArrayList<Integer> next = new ArrayList<>();
				for(int num: cur.route) {
					next.add(num);
				}
				next.add(move);
				q.add(new Location(move,K, next));
				visited[move] = next.size();
			}
			
			if(down >= 0 && visited[down] > cur.route.size()+1) {
				ArrayList<Integer> next = new ArrayList<>();
				for(int num: cur.route) {
					next.add(num);
				}
				next.add(down);
				q.add(new Location(down,K, next));
				visited[down] = next.size();
			}
			
			if(up <= 100000 && visited[up] > cur.route.size()+1) {
				ArrayList<Integer> next = new ArrayList<>();
				for(int num: cur.route) {
					next.add(num);
				}
				next.add(up);
				q.add(new Location(up, K,next));
				visited[up] = next.size();
			}
			
		}
		
		System.out.println(answer-1);
		for(int num: answerRoute) {
			System.out.print(num+" ");
		}
	}
}
