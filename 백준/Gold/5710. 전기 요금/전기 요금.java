import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if(A==0 && B==0) break;
			
			int usageTotal = 0;
			if(A<=200) {
				usageTotal += A / 2;
			}else if(A<=200 + 29700) {
				usageTotal += 100 + (A-200)/3;
			}else if(A<=200 + 29700 + 4950000) {
				usageTotal += 10000 + (A-200-29700)/5;
			}else {
				usageTotal += 1000000 + (A-200-29700-4950000)/7;
			}
						
			int from = 0;
			int to = usageTotal;
			int mid = 0;
			
			while(true) {
				
				mid = (from + to)/2;
				
				long sangFee = calFee(mid);
				long neighborFee = calFee(usageTotal - mid);
				long gap = Math.abs(sangFee - neighborFee);
				
				if(gap>B) {
					from = mid;
				}else if(gap<B){
					to = mid;
				}else {
					System.out.println(sangFee);
					break;
				}
			}
			
		}
	}
	
	public static long calFee(int usage) {
		
		if(usage <= 100) {
			return usage * 2;
		}else if(usage <= 10000) {
			return 100 * 2L + (usage-100) * 3;
		}else if(usage <= 1000000) {
			return 100 * 2L + 9900 * 3 + (usage - 10000) * 5;
		}else {
			return 100 * 2L + 9900 * 3 + 990000 * 5 + (usage-1000000) * 7;
		}
	}
}
