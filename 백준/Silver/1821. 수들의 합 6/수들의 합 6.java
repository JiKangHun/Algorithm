import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N,F;
	static int[][] d;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		d = new int[N][N];
		
		d[0][0] = 1;
		for(int i=1; i<N; i++) {
			for(int j=0; j<=i; j++) {
				if(j==0 || j==i) {
					d[i][j] = 1;
				}else {
					d[i][j] = d[i-1][j-1] + d[i-1][j];
				}
			}
		}
		
		int[] input = new int[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = i+1;
		}
		
		while(np(input));

	}
	
	private static boolean np(int[] numbers) {
		
		int sum = 0;
		for(int m=0; m<numbers.length; m++) {
			sum += numbers[m] * d[N-1][m];
		}
		if(sum == F) {
			for(int num: numbers) {
				System.out.print(num+" ");
			}
			return false;
		}
		
		int N = numbers.length;
		int i = N-1;
		while(i>0 && numbers[i-1]>=numbers[i]) --i; 
		if(i==0) return false; 
		
		int j = N-1;
		while(numbers[i-1]>=numbers[j]) --j;
		
		swap(numbers, i-1, j);
		
		int k = N-1;
		
		while(i<k) swap(numbers, i++, k--);
		
		return true;
		
		
	}

	private static void swap(int[] numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
		
	}
}
