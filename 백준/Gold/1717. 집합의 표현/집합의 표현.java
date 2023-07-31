import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[] p;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		p = new int[n+1];
		
		makeSet();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int type = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(type == 0) union(a,b);
			if(type == 1) {
				if(find(a) == find(b)) {
					sb.append("YES\n");
				}else {
					sb.append("NO\n");
				}
			}
		}
		
		System.out.println(sb);
	}

	private static int find(int x) {
		
		if(p[x] == x) return x;
		p[x] = find(p[x]);
		return p[x];
		
	}

	private static void union(int a, int b) {
		
		a = find(a);
		b = find(b);
		p[a] = b;
		
	}

	private static void makeSet() {
		
		for(int i=0; i<=n; i++) {
			p[i] = i;
		}
		
	}
}
