import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		int[] cow = new int[11];
		Arrays.fill(cow, -1);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int loc = Integer.parseInt(st.nextToken());
			
			if(cow[num] != -1 && cow[num] != loc) {
				++cnt;
			}
			
			cow[num] = loc;
			
		}
		
		System.out.println(cnt);
	}

}
