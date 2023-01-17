import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 접시 수
        int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호(이벤트 초밥 종류)

        int[] belt = new int[N];
        int[] sushiNum = new int[d+1];

        for(int i=0; i<N; i++){
            belt[i] =Integer.parseInt(br.readLine()); //벨트에 놓인 초밥 종류 초기화
        }


        int cnt = 0; // k개씩 슬라이딩 윈도우 할 때 초밥 종류 수 저장

        for(int i=0; i<k; i++){ // 시작부터 K개를 먹었을 때 가지수 계산
            int kind = belt[i]; // 벨트에서 순서대로 꺼내서
            if(sushiNum[kind] ==0) cnt++; // 아직 담겨있는 초밥이 아니라면 종류수 +1
            ++sushiNum[kind];   // 그 종류의 초밥 먹은 개수 +1
        }

        int start = 0;
        int end = k;
        int result = cnt;

        for(int i=1; i<N; i++){ // 0~K-1번 까지는 이미 담았으므로 1번 빼고 N-1번 순회

            if(sushiNum[c]==0){
                result = Math.max(result, cnt+1);
            }else{
                result = Math.max(result, cnt);
            }

            if(--sushiNum[belt[start]]==0) --cnt; // start 위치의 초밥을 뺐을 때 접시 개수가 0이면 종류수 -1
            if(sushiNum[belt[end]]++==0) ++cnt;   // end 위치의 초밥을 넣는데 현재 개수가 0이었다면 종류수 +1

            ++start;                       // start를 한 칸 이동
            if(end+1 >= N){                // end는 끝 idx를 초과하면 나머지 계산으로 다시 0부터 시작하게 보냄
                end = (end+1) % N;
            }else{                         // 아니면 그냥 한 칸 이동
                ++end; 
            }
        }

        System.out.println(result);

    }
}
