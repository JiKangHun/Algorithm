import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static char[] alphabet;
	static char[] password;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		alphabet = new char[C];
		password = new char[L];
		
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<C; i++) {
			alphabet[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alphabet);
		
		combi(0, 0, L, 0);
				
	}

	private static void combi(int start, int cnt, int num, int vowel) {
		
		if(cnt == num) {
			
			if(vowel >= 1 && vowel < num-1) {
				
				for(int i=0; i<num; i++) {
					System.out.print(password[i]);
				}
				System.out.println();
			}
			return;
		}
		
		
		for(int i=start; i<alphabet.length; i++) {
			
			password[cnt] = alphabet[i];
			if( check(password[cnt]) ) {				
				combi(i+1, cnt+1, num, vowel+1);
			}else {
				combi(i+1, cnt+1, num, vowel);
			}
		}
	}

	private static boolean check(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
}
