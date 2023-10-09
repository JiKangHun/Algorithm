import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][5];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i<n; i++) {
			HashSet<Integer> set = new HashSet<>();
			for(int k=0; k<5; k++) {
				
				int num = arr[i][k];
				
				for(int j=0; j<n; j++) {
					if(i==j) continue;
					if(arr[j][k] == num) {
						set.add(j);
					}
					
				}
			}
			
			if(set.size() > max) {
				max = set.size();
				answer = i;
			}
		}
		
		System.out.println(answer+1);
	}
	
}
