import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] light = new int[M];
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<M; i++) {
			light[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		if(M==1) {
			answer = N - light[0] >= light[0] ? N - light[0] : light[0];  
		}else if(M==N) {
			answer = 1;
		}else {
			
			int left = 0;
			int right = N;
			
			while(left+1 < right) {
				
				int mid = (left+right) / 2;
				boolean isPossible = true;
				
				for(int i=0; i<M; i++) {
					
					if(i==0) {
						if(light[i] - mid > 0 || light[i] + mid < light[i+1] - mid) {
							isPossible = false;
							break;
						}
					}else if(i==M-1) {
						if(light[i-1] + mid < light[i] - mid || light[i] + mid < N) {
							isPossible = false;
							break;
						}
					}else {
						if(light[i-1] + mid < light[i] - mid || light[i] + mid < light[i+1] - mid) {
							isPossible = false;
							break;
						}
					}
				}
				
				if(isPossible) {
					answer = mid;
					right = mid;
				}else {
					left = mid;
				}
			}
		}	
		System.out.println(answer);
	}
}
