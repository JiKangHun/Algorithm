import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		int tc = 0;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			
			++tc;
			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			if(n==0) break;
			
			ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
			for(int i=0; i<=n; i++) {
				tree.add(new ArrayList<>());
			}
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());
				tree.get(node1).add(node2);
				tree.get(node2).add(node1);
			}
			
			boolean[] visited = new boolean[n+1];
			int treeCnt = 0;			
			
			for(int i=1; i<=n; i++) {
				
				if(!visited[i]) {
					
					boolean isTree = true;
					Queue<Integer> q = new ArrayDeque<>();
					q.add(i);
					visited[i] = true;
					
					int nodeCnt = 0;
					int edgeCnt = 0;
					
					while(!q.isEmpty()) {
						
						int curNode = q.poll();
						++nodeCnt;
						edgeCnt += tree.get(curNode).size();
						
						for(int j=0; j<tree.get(curNode).size(); j++) {
							int nextNode = tree.get(curNode).get(j);
							if(!visited[nextNode]) {
								visited[nextNode]= true;
								q.add(nextNode);
							}
						}
				
					}
					
					if(nodeCnt <= edgeCnt/2) isTree = false;
					if(isTree) ++treeCnt;
				}
				
			}
			
			switch(treeCnt) {
				
				case 0: sb.append("Case "+tc+": "+"No trees.").append("\n");
				break;
				case 1: sb.append("Case "+tc+": "+"There is one tree.").append("\n");
				break;
				default: sb.append("Case "+tc+": "+"A forest of "+treeCnt+" trees.").append("\n");
			}
			
		}
		
		System.out.println(sb);
	}
}
