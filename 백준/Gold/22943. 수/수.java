import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] num = {0,1,2,3,4,5,6,7,8,9};
	static int[] picked, sum, multiple;
	static int answer;
	static boolean[] visited = new boolean[10];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		picked = new int[K];
		int[] primeNumber = new int[98766];
		Arrays.fill(primeNumber, 1);
		primeNumber[1] = 0;
		for(int i=2; i<Math.sqrt(98765); i++) {
			if(primeNumber[i]==0) continue;
			for(int j=i*2; j<=98765; j+=i) {
				primeNumber[j] = 0;
			}
		}
		
		sum = new int[98766];
		multiple = new int[98766];
		
		for(int i=2; i<=98765; i++) {
			if(primeNumber[i] == 1) {
				for(int j=i; i+j<=98765; j++) {
					 if(i!=j && primeNumber[j] == 1) {
						sum[i+j] = 1;
					}
				}
			}
		}
		
		for(int i=2; i<=98765; i++) {
			if(primeNumber[i] == 1) {
				for(int j=2; j<=98765; j++) {
					if(i*j > 98765) break;
					if(primeNumber[j] == 1) {
						multiple[i*j] = 1;
					}
				}
			}
		}
		
		perm(0, K, M);
		
		System.out.println(answer);
		
	}
	
	
	private static void perm(int cnt, int K, int M) {
		
		if(cnt == K) {
		 
			String num = "";
			for(int i=0; i<K; i++) {
				num += picked[i];
			}
			int isAnswer = Integer.parseInt(num);
			if(sum[isAnswer] == 1) {
				int getNum = getNumDividedByM(isAnswer, M);
				if(multiple[getNum] == 1) {
					++answer;
				}
			}
			return;
			
		}
		
		for(int i=0; i<10; i++) {
			
			if(cnt==0 && i==0) continue;
			if(visited[i]) continue;
			
			visited[i] = true;
			picked[cnt] = i;
			perm(cnt+1, K, M);
			visited[i] = false;
			
		}
		
	}


	private static int getNumDividedByM(int num, int M) {
		
		if(num % M != 0) return num;
		return getNumDividedByM(num / M, M);
	}
}
