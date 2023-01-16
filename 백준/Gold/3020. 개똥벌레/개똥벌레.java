import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] odd = new int[H+1];
        int[] even = new int[H+1];

        for(int i=1; i<=N; i++) {
            int height = Integer.parseInt(br.readLine());
            if(i%2!=0){                                     //홀수면
                odd[height]++;                              //홀수배열 해당 높이 장애물 수 +1
            }else{                                          //짝수면
                even[height]++;                             //짝수배열 해당 높이 장애물 수 +1
            }
        }


            for(int i=H-1; i>=1; i--){
                odd[i] += odd[i+1];
                even[i] += even[i+1];
            }


        int min = Integer.MAX_VALUE;

        for(int i=1, j=H; i<=H; i++, j--){
            int tmp = odd[i] + even[j];
            min = Math.min(min, tmp);
        }

        int cnt = 0;
        for(int i=1, j=H; i<=H; i++, j--){
            int tmp = odd[i] + even[j];
            if(tmp == min) cnt++;
        }

//        for(int i=1; i<=H-1; i++){
//            System.out.print(odd[i]+" ");
//        }
//        System.out.println();
//
//        for(int i=H-1; i>=1; i--){
//            System.out.print(even[i]+" ");
//        }

        System.out.println(min+" "+cnt);


    }
}
