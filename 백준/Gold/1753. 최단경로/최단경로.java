import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		
		int vertex;
		int distance;
		
		public Node(int vertex, int distance) {
			this.vertex = vertex;
			this.distance = distance;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();
		
		for(int i=0; i<=V; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine()," ");
			
			int from = Integer.parseInt(st.nextToken());
			int vertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			Node node = new Node(vertex, weight);
			
			graph.get(from).add(node);
		}
		
		int[] d = new int[V+1];
		Arrays.fill(d, Integer.MAX_VALUE);
		d[K] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>( (o1, o2) -> {
			
			return o1.distance - o2.distance;
			
		});
		
		Node node = new Node(K, 0);
		pq.add(node);
		
		while(!pq.isEmpty()) {
			
			Node cur = pq.poll();
			int now = cur.vertex;
			int distance = cur.distance;
			
			if(d[now] < distance) continue;
			
			for(int i=0; i<graph.get(now).size(); i++) {
				
				Node next = graph.get(now).get(i);
				int cost = d[now] + next.distance;
				
				if( cost < d[next.vertex] ) {
					
					d[next.vertex] = cost;
                    pq.offer(new Node(next.vertex, cost));
                    
				}
			}
		}
		
		for(int i=1; i<=V; i++) {
            
			if(d[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}else {
				System.out.println(d[i]);
			}
			 
		}
		
	}
	
}
