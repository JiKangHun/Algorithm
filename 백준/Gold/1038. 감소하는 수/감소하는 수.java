import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		if(N<=9) {
			System.out.println(N);
		}else {
			
			int cnt = -1;
			
			outer:
			for(long i=0; i<=9876543210L; i++) {
				
				String str = i+"";
				if(str.length() == 1) {
					++cnt;
					continue;
				}
				
				int prev = str.charAt(0) -'0';
				int length = str.length();
				
				for(int j=1; j<str.length(); j++) {
					int digit = str.charAt(j) - '0';
					if(prev <= digit) {
						i += (int)Math.pow(10, length-j) - Integer.parseInt(str.substring(j, str.length()))-1;
						continue outer;
					}
					prev = digit;
				}
				++cnt;
				

				if(cnt == N) {
					System.out.println(i);
					return;
				}
			}
			
			System.out.println(-1);
			
			
		}
	}
}
