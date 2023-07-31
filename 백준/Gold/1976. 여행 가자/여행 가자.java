import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] p;
	static int[] r;
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		p = new int[N+1];
		r = new int[N+1];
		
		makeSet();
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=1; j<=N; j++) {
				int connect = Integer.parseInt(st.nextToken());
				if(i == j) continue;
				if(connect == 1) {
					union(i,j);
				}
				
			}
		}
		
		st = new StringTokenizer(br.readLine()," ");
		int parent = find(Integer.parseInt(st.nextToken()));
		String answer = "YES";
		while(st.hasMoreTokens()) {
			if(parent != find(Integer.parseInt(st.nextToken()))){
				answer = "NO";
				break;
			}
		}
		
		System.out.println(answer);
	}
	
	private static void union(int from, int to) {
		
		int a = find(from);
		int b = find(to);
		if(a==b) return;
		if(r[a] < r[b]) {
			p[a] = b;
			r[a] += 1;
		}else {
			p[b] = a;
			r[b] += 1;
		}
		
	}

	private static int find(int a) {
		
		if(p[a] == a) return a;
		return p[a] = find(p[a]);
	}

	private static void makeSet() {
		
		for(int i=1; i<=N; i++) {
			p[i] = i;
			r[i] = 1;
		}
		
	}
	
	
}
