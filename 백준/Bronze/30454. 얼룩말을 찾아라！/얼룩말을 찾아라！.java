import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int max = Integer.MIN_VALUE;
		int answer = 0;
		
		for(int i=0; i<N; i++) {
			
			char[] zebra = br.readLine().toCharArray();
			int cnt = 0;
			boolean isZero = true;
			
			for(int j=0; j<L; j++) {
				if(zebra[j] - '0' == 1 && isZero) {
					++cnt;
					isZero = false;
				}else if(zebra[j] - '0' == 0){
					isZero = true;
				}
				
			}
			
			if(max < cnt) {
				max = cnt;
				answer = 1;
			}else if(max == cnt) {
				++answer;
			}
			
		}
		
		System.out.println(max+" "+answer);
		
	}
}
