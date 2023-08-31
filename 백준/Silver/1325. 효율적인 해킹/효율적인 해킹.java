import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Integer>> arrList = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			arrList.add(new ArrayList<Integer>());
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arrList.get(from).add(to);
		}
		
		int answer = Integer.MIN_VALUE;
		int[] connectCnt = new int[N+1];
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=1; i<=N; i++) {
			
			if(arrList.get(i).size()==0) continue;
			ArrayDeque<Integer> q = new ArrayDeque<>();
			boolean[] visited = new boolean[N+1];
			q.add(i);
			visited[i] = true;
			
			while(!q.isEmpty()) {
				
				int qSize = q.size();
				
				for(int t=0; t<qSize; t++) {
					
					int cur = q.poll();
					
					for(int j=0; j<arrList.get(cur).size(); j++) {
						
						int next = arrList.get(cur).get(j);
						if(visited[next]) continue;
						++connectCnt[next];
						visited[next]= true;
						q.add(next);
					}
				}
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		int max = Integer.MIN_VALUE;
		for(int i=1; i<=N; i++) {
			if(connectCnt[i] > max) max = connectCnt[i];
		}
		for(int i=1; i<=N; i++) {
			if(connectCnt[i] == max) sb.append(i).append(" ");
		}
		
		System.out.println(sb);
	}
}
