import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		while(true) {
			if(isPelindrom(N) && isPrimeNumber(N)) {
				System.out.println(N);
				break;
			}
			
			++N;
		}
	}

	private static boolean isPrimeNumber(int n) {
		
		if(n==1) return false;
		
		for(int i=2; i<=Math.sqrt(n); i++) {
			if(n%i == 0) return false;
		}
		
		return true;
	}

	private static boolean isPelindrom(int n) {
		
		String num = String.valueOf(n);
		for(int i=0; i<num.length(); i++) {
			char front = num.charAt(i);
			char back = num.charAt(num.length()-1-i);
			if(front != back) return false;
		}
		return true;
	}
}
