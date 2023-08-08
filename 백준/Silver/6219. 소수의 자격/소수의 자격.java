import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		String D = st.nextToken();
		
		int[] primeNumber = new int[B+1];
		Arrays.fill(primeNumber, 1);
		primeNumber[1] = 0;
		
		for(int i=2; i<=Math.sqrt(B); i++) {
			if(primeNumber[i] == 0) continue;
			for(int j=2*i; j<=B; j+=i) {
				primeNumber[j] = 0;
			}
		}
		
		int answer = 0;
		for(int i=A; i<=B; i++) {
			if(primeNumber[i] != 0) {
				String num = String.valueOf(i);
				if(num.contains(D)) {
					++answer;
				}
			}
		}
		
		System.out.println(answer);
	}
}
