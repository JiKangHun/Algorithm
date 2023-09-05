import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int[] rank = {6,6,5,4,3,2,1};
        
        int zeroCnt = 0;
        for(int i=0; i<6; i++) {
            if(lottos[i]==0) ++zeroCnt;
        }
        
        int matchCnt = 0;
        for(int i=0; i<6; i++){
            int num = lottos[i];
            for(int j=0; j<6; j++){
                int winNum = win_nums[j];
                if(num == winNum){
                   ++matchCnt;
                    break;
                }  
            }
        }
        
        answer[0] = rank[zeroCnt+matchCnt];
        answer[1] = rank[matchCnt];
        
        return answer;
    }
}