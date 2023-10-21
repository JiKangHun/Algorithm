import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		int horizon = Math.abs(w-x) < Math.abs(x-0) ? Math.abs(w-x) : Math.abs(x-0);
		int vertical = Math.abs(h-y) < Math.abs(y-0) ? Math.abs(h-y) : Math.abs(y-0);
		System.out.println(horizon > vertical ? vertical : horizon);
		
	}
}
