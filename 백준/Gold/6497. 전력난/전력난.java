import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] p;
	
	static class Edge {
		
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N==0 && M==0) break;
			
			p = new int[N+1];
			
			PriorityQueue<Edge> pq = new PriorityQueue<>( (o1, o2) -> {
				return o1.weight - o2.weight;
			});
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine()," ");
				Edge edge = new Edge(Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken()));
				pq.add(edge);
			}
			
			make();
			int count = 0;
			int max = 0;
			int answer = 0;
			
			while(!pq.isEmpty()) {
				Edge edge = pq.poll();
				if(union(edge.from, edge.to) && ++count <= N-1) {
					answer += edge.weight;
				}
				max += edge.weight;
			}
			
			System.out.println(max - answer);
		}
	}
	
	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		p[a] = b;
		return true;
	}
	
	public static int find(int x) {
		
		if(p[x] == x) return x;
		return p[x] = find(p[x]);
	}
	
	public static void make() {
		
		for(int i=0; i<=N; i++) {
			p[i] = i;
		}
	}
}
