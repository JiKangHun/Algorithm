import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	  public static void main(String[] args) throws Exception {
		  
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	    int N = Integer.parseInt(st.nextToken()); // 접시 수
	    int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
	    int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
	    int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호(이벤트 초밥 종류)

	    int[] kind = new int[d+1];
	    int[] belt = new int[N];
	    
	    for(int i=0; i<N; i++) {
	    	belt[i] = Integer.parseInt(br.readLine());
	    }
	    
	    int answer = 0;
	    int sum = 0;
	    
	    for(int i=0; i<k; i++) {
	    	int sushi = belt[i];
	    	if(kind[sushi] == 0) ++sum;
	    	++kind[sushi];
	    }
	    
	    answer = kind[c] == 0 ? sum + 1 : sum;
	    
	    int start = 0;
	    int end = k-1;
	    
	    for(int i=0; i<N; i++) {
	    	
	    	--kind[belt[start]];
	    	if(kind[belt[start]] == 0) --sum;
	    	++start;
	    	
	    	end = (end+1) % N;
	    	if(kind[belt[end]] == 0) ++sum;
	    	++kind[belt[end]];
	    	
	    	answer = kind[c] == 0 ? Math.max(sum + 1, answer) : Math.max(sum, answer); 
	    	
	    }
	    
	    System.out.println(answer);
	    
	 }
}
