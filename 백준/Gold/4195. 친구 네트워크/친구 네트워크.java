import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			int F = Integer.parseInt(br.readLine());
			
			int[] p = new int[200001];
			Arrays.fill(p, -1);
			HashMap<String, Integer> map = new HashMap<>();
			StringTokenizer st;
			
			for(int j=0; j<F; j++) {
				st = new StringTokenizer(br.readLine()," ");
				String name1 = st.nextToken();
				String name2 = st.nextToken();
				int idx1 = 0;
				int idx2 = 0;
				
				idx1 = findIdx(map, name1);
				idx2 = findIdx(map, name2);
				
				union(idx1, idx2, p);
				int root = find(idx1, p);
				System.out.println(Math.abs(p[root]));
				
			}
		}
	}

	private static void union(int a, int b, int[] p) {
		
		a = find(a, p);
		b = find(b, p);
		if(a==b) return;
		p[b] += p[a];
		p[a] = b;
	}

	private static int find(int x, int[] p) {
		
		if(p[x] < 0) return x;
		return p[x] = find(p[x], p);
	}

	private static int findIdx(HashMap<String, Integer> map, String name) {
		
		if(!map.containsKey(name)) map.put(name, map.size()+1);
		return map.get(name);
		
	}

}
