import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] num = new int[M];

        perm(0, N, M, num);
        System.out.println(sb);

    }

    public static void perm(int cnt, int N, int M, int[] num){

        if(cnt == M){
            for(int pick : num){
                sb.append(pick+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=1; i<=N; i++){
            num[cnt] = i;
            perm(cnt+1, N, M, num);
        }
    }
}