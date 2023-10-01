import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static class Node{
		
		int vertex;
		int distance;
		
		public Node(int vertex, int weight) {
			
			this.vertex = vertex;
			this.distance = weight;
		}
		
	}
		
	static ArrayList<ArrayList<Node>> graph;
	static int N, E;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, weight));
			graph.get(to).add(new Node(from, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		
		//1 -> v1 -> v2 -> N
		//1 -> v2 -> v1 -> N
		
		int[] a = dijkstra(1);
		int[] b = dijkstra(v1);
		int[] c = dijkstra(v2);
		
		int dist1 = Integer.MAX_VALUE;
		if(a[v1] != Integer.MAX_VALUE && b[v2] !=Integer.MAX_VALUE && c[N] !=Integer.MAX_VALUE) {
			dist1 = a[v1] + b[v2] + c[N];
		}
		
		
		int dist2 = Integer.MAX_VALUE;
		if(a[v2] != Integer.MAX_VALUE && c[v1] !=Integer.MAX_VALUE && b[N] !=Integer.MAX_VALUE) {
			dist2 = a[v2] + c[v1] + b[N];
		}
		
		answer = dist1 > dist2 ? dist2 : dist1;
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

	}

	private static int[] dijkstra(int from) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>( (o1, o2) -> o1.distance - o2.distance );
		int[] d = new int[N+1];
		Arrays.fill(d, Integer.MAX_VALUE);
		pq.add(new Node(from, 0));
		d[from] = 0;
		
		while(!pq.isEmpty()) {
			
			Node cur = pq.poll();
			int dist = cur.distance;
			if(d[cur.vertex] < dist) continue;
			
			for(int i=0; i<graph.get(cur.vertex).size(); i++) {
				Node next = graph.get(cur.vertex).get(i);
				int cost = d[cur.vertex] + next.distance;
				if(d[next.vertex] > cost) {
					d[next.vertex] = cost;
					pq.add(new Node(next.vertex, cost));
				}
				
			}
			
		}
		
		return d;
		
	}
	
}
