import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	
	static char[] str;
	static char[] picked;
	static boolean[] visited;
	static HashMap<Character,Integer> map = new HashMap<>();
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().toCharArray();
		
		picked = new char[str.length];
		visited = new boolean[str.length];
		perm(0, str.length);
		for(int i=0; i<str.length; i++) {
			map.put(str[i], map.getOrDefault(str[i], 0) + 1);
		}
		
		int num = 1;
		for(Character ch : map.keySet()) {
			int same = 1;
			for(int i=map.get(ch); i>=1; i--) {
				same *= i;
			}
			num *= same;
		}
		
		System.out.println(answer / num);
	}
	private static void perm(int cnt, int length) {
		
		if(cnt == length) {
			
			++answer;
			return;
		}
		
		
		for(int i=0; i<str.length; i++) {
			
			if(visited[i]) continue;
			if(cnt > 0 && picked[cnt-1] == str[i]) continue;
			visited[i] = true;
			picked[cnt] = str[i];
			perm(cnt+1, length);
			visited[i] = false;
			
		}
		
	}
}
