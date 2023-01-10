import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine(), " ");
    int[] arr = new int[N];

    for(int i=0; i<N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }


    int end = 0;
    int sum = 0;
    int cnt = 0;

    for(int start=0; start<N; start++) {
      while(sum < M && end < N) {
        sum += arr[end];
        ++end;
      }
      if(sum == M){
        ++cnt;
      }

      sum -= arr[start];      
    }
    
    System.out.print(cnt);
    
  }
}