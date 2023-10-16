import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		// F:높이   S:강호층  G:스타트링크층  U:U만큼 위로 이동  D:D만큼 아래로 이동
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int F = Integer.parseInt(st.nextToken()); 
		int S = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int U = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		Queue<int[]> q = new LinkedList<>();
		
		boolean[] visited = new boolean[F+1];
		visited[S] = true;
		q.add(new int[] {S,0});
		
		int answer = -1;
		while(!q.isEmpty()) {
			
			int[] cur = q.poll();
			int loc = cur[0];
			int cnt = cur[1];
			if(loc == G) {
				answer = cnt;
				break;
			}
			
			int up = loc + U;
			int down = loc - D;
			
			if(up <= F && !visited[up]) {
				visited[up] = true;
				q.add(new int[] {up,cnt+1});
			}
			
			if(down >= 1 && !visited[down]) {
				visited[down] = true;
				q.add(new int[] {down, cnt+1});
			}
		}
		
		System.out.println(answer == -1 ? "use the stairs" : answer);
		
	}
}
