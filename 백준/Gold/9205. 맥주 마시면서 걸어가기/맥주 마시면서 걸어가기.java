import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<int[]> storeList;
	static String answer;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			
			storeList = new ArrayList<>();
			answer = "sad";
			
			int N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				storeList.add(new int[] {r,c});
			}
			
			st = new StringTokenizer(br.readLine()," ");
			int er = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());
			
			dfs(sr, sc, er, ec, new boolean[N]);
			
			System.out.println(answer);
		}

	}

	private static void dfs(int sr, int sc, int er, int ec, boolean[] visited) {
		
		if(isReachable(sr, sc, er, ec)) {
			answer = "happy";
			return;
		}else {
			if(answer.equals("happy")) {
				return;
			}else {
								
				for(int i=0; i<storeList.size(); i++) {
					
					int[] locStore = storeList.get(i);
					int r = locStore[0];
					int c = locStore[1];
					if(isReachable(sr, sc, r, c) && !visited[i]) {
						
						visited[i] = true;
						dfs(r, c ,er, ec, visited);
	
					}
				}
				
			}
		}
	}

	private static boolean isReachable(int sr, int sc, int er, int ec) {
		
		return Math.abs(sr-er) + Math.abs(sc-ec) <= 1000 ? true : false;
	}
}
