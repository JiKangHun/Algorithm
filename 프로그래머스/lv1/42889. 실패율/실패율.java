import java.util.*;

class Solution {
    
    public class Stage implements Comparable<Stage>{
        int num;
        double failRate;
        
        public Stage(int num, double failRate){
            this.num = num;
            this.failRate = failRate;
        }
        
        @Override
        public int compareTo(Stage o1){
            
            if(this.failRate < o1.failRate){
                return 1;
            }else if(this.failRate > o1.failRate){
                return -1;
            }else{
                return this.num - o1.num;
            }
        }
        
    }
    
    public int[] solution(int N, int[] stages) {
        
        int[] tryingCnt = new int[N+2]; //각 스테이지 현재 시도 중인 유저수
        int[] tryHistoryCnt = new int[N+1]; //각 스테이지 시도한 전체 유저 수
        tryHistoryCnt[0] = stages.length;
        
        for(int i=0; i<stages.length; i++){
            ++tryingCnt[stages[i]];
        }
        
        
        PriorityQueue<Stage> pq = new PriorityQueue<>();
        
        for(int i=1; i<=N; i++){
            tryHistoryCnt[i] = tryHistoryCnt[i-1] - tryingCnt[i-1];
            double failRate = (double)tryingCnt[i] / tryHistoryCnt[i];
            Stage stage = new Stage(i, failRate);
            pq.add(stage);
        }
        
        int[] answer = new int[N];
        for(int i=0; i<N; i++){
            answer[i] = pq.poll().num;
        }
        
        return answer;
    }
}