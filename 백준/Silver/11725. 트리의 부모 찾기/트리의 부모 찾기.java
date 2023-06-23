import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
		
		for(int i=0; i<=N; i++) {
			tree.add(new ArrayList<>());
		}
		
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			tree.get(node1).add(node2);
			tree.get(node2).add(node1);
		}
		
		int[] parent = new int[N+1];
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		
		while(!q.isEmpty()) {
			int curNode = q.poll();
			for(int i=0; i<tree.get(curNode).size(); i++) {
				int nextNode = tree.get(curNode).get(i);
				if(parent[nextNode] != 0 || nextNode == 1) continue; 
				parent[nextNode] = curNode;
				q.add(nextNode);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=2; i<=N; i++) {
			sb.append(parent[i]).append("\n");
		}
		System.out.print(sb);
		
	}
}
