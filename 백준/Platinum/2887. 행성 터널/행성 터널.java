import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] p;
	static class Node implements Comparable<Node> {
		
		int vertex;
		int loc;
		
		public Node(int vertex, int loc) {
			this.vertex = vertex;
			this.loc = loc;
		}
		
		@Override
		public int compareTo(Node node) {
			return this.loc - node.loc;
		}
		
	}
	
	static class Edge {
		
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		p = new int[N];
		
		ArrayList<Node> xList = new ArrayList<>();
		ArrayList<Node> yList = new ArrayList<>();
		ArrayList<Node> zList = new ArrayList<>();
		
		PriorityQueue<Edge> pq = new PriorityQueue<>( (o1, o2) -> {
			return o1.weight - o2.weight; 
		});
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			xList.add(new Node(i, x));
			yList.add(new Node(i, y));
			zList.add(new Node(i, z));
			
		}
		
		
		makeEdge(xList,pq);
		makeEdge(yList,pq);
		makeEdge(zList,pq);
		make();
		
		long answer = 0;
		int count = 0;
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(union(edge.from, edge.to)) {
				answer += edge.weight;
				if(++count == N-1) break;
			}
		}
		
		System.out.println(answer);
		
	}
	
	public static void makeEdge(ArrayList<Node> arrList, PriorityQueue<Edge> pq) {
		
		Collections.sort(arrList);
		
		for(int i=0; i<arrList.size()-1; i++) {
			
			int from = arrList.get(i).vertex;
			int to = arrList.get(i+1).vertex;
			int weight = Math.abs(arrList.get(i).loc - arrList.get(i+1).loc); 
			Edge edge = new Edge(from, to, weight);
			pq.add(edge);
		}
	}
	
	public static void make() {
		
		for(int i=0; i<N; i++) {
			p[i] = i;
		}
	}
	
	public static int find(int x) {
		
		if(p[x] == x) return x;
		return p[x] = find(p[x]);
	}
	
	public static boolean union(int a, int b) {
		
		a = find(a);
		b = find(b);
		if(a==b) return false;
		p[a] = b;
		return true;
	}
	
}
