import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] soldiers = new int[N];

        for(int i=N-1; i>=0; i--){
            soldiers[i] = Integer.parseInt(st.nextToken());
        }

        int[] LIS = new int[N];
        int max = 0;
        for(int i=0; i<N; i++){
            LIS[i] = 1;
            for(int j=0; j<i; j++){
                if(soldiers[j] < soldiers[i] && LIS[i] < LIS[j]+1){
                    LIS[i] = LIS[j]+1;
                    max = Math.max(max, LIS[i]);
                }
            }
        }
        System.out.println(max==0? N-1 : N-max);
    }

}