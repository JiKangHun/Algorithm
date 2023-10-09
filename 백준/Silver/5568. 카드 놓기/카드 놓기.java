import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	
	static int[] num, p, visited;
	static Set<Integer> set = new HashSet<>();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		num = new int[n];
		visited = new int[n];
		p = new int[k];
		
		for(int i=0; i<n; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		perm(0, n, k);
		System.out.println(set.size());
		
	}

	private static void perm(int cnt, int n, int k) {
		
		if(cnt == k) {
			String pick = "";
			for(int i=0; i<k; i++) {
				pick += p[i];
			}
			set.add(Integer.parseInt(pick));
			return;
		}
		
		
		for(int i=0; i<n; i++) {
			if(visited[i]==1) continue;
			p[cnt] = num[i];
			visited[i] = 1;
			perm(cnt+1, n, k);
			visited[i] = 0;
		}
		
	}
}
