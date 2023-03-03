import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        p = new int[N];
        for(int i=0; i<N; i++){
            p[i] = i;
        }

        int answer = 0;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(union(x,y)){
                answer = i+1;
                break;
            }
        }
        System.out.println(answer != 0 ? answer : 0);
    }

    public static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if(x==y){
            return true;
        }else{
            p[x] = y;
            return false;
        }
    }

    public static int find(int x){
        if(p[x] == x) return x;
        else return p[x] = find(p[x]);
    }
}
