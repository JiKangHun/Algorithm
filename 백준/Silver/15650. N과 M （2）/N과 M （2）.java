import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] num = new int[M];

        combi(1, 0, N, M, num);
    }

    public static void combi(int start, int cnt, int N, int M, int[] num){

        if(cnt == M){
            for(int pick : num){
                System.out.print(pick+" ");
            }
            System.out.println();
            return;
        }

        for(int i=start; i<=N; i++){
            num[cnt] = i;
            combi(i+1, cnt+1, N, M, num);
        }
    }
}