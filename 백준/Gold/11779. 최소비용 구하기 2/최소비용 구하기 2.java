import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSpinnerUI;

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
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();
		for(int i=0; i<=n; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		for(int i=0; i<m; i++) {
			
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Node(to, cost));
			
		}
		
		st = new StringTokenizer(br.readLine()," ");
		
		int departure = Integer.parseInt(st.nextToken());
		int destination = Integer.parseInt(st.nextToken()); 
		
		
		int[] d = new int[n+1];
		Arrays.fill(d, Integer.MAX_VALUE);
		ArrayList<ArrayList<Integer>> route = new ArrayList<>();
		for(int i=0; i<=n; i++) {
			route.add(new ArrayList<Integer>());
		}
		
		Node node = new Node(departure, 0);
		d[departure] = 0;
		route.get(departure).add(departure);
		
		
		PriorityQueue<Node> pq = new PriorityQueue<>( (o1, o2) -> {
			return o1.distance - o2.distance;
		});
		
		pq.add(node);
		
		while(!pq.isEmpty()) {
			
			Node cur = pq.poll();
			int vertex = cur.vertex;
			int distance = cur.distance;
			
			if(vertex == destination) break;
			if(d[vertex] < distance) continue;
			
			for(int i=0; i<graph.get(vertex).size(); i++) {
				
				Node next = graph.get(vertex).get(i);
				int cost = distance + next.distance;
				
				if(cost < d[next.vertex]) {
					
					d[next.vertex] = cost;
					pq.offer(new Node(next.vertex, cost));
					route.get(next.vertex).clear();
					
					for(int j=0; j<route.get(vertex).size(); j++) {
						route.get(next.vertex).add(route.get(vertex).get(j));
					}
					
					route.get(next.vertex).add(next.vertex);
					
				}
			}
			
		}
		
		System.out.println(d[destination]);
		System.out.println(route.get(destination).size());
		for(int i=0; i<route.get(destination).size(); i++) {
			System.out.print(route.get(destination).get(i)+" ");
		}
		
	}
	
}
