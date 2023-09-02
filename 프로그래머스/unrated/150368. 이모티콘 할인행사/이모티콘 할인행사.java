import java.util.*;

class Solution {
    
    static int[] discountRate;
    static int answerSale, answerJoinCnt;
    public int[] solution(int[][] users, int[] emoticons) {
        
        discountRate = new int[emoticons.length];
        
        //dfs로 각 이모티콘의 할인율 설정
        //설정된 할인율에 따라 각 유저의 구매 정보를 추출
        //이모티콘 플러스 가입 or 구매 비용 계산
        //이모티콘 플러스 가입자 수가 기존 저장 수보다 더 많은 할인율일 때 가입자수와 구매 비용 저장
        //만약 기존 값과 같다면 구매 비용이 더 크다면 저장
        
        dfs(0, emoticons.length, emoticons, users);
        int[] answer = {answerJoinCnt, answerSale};
        return answer;
    }
    
    public static void dfs(int cnt, int end, int[] emoticons, int[][] users){
        
        if(cnt == end){
            
            //가입자수 및 구매 비용 계산
            
            int joinCnt = 0;
            int totalSale = 0;
            
            for(int i=0; i<users.length; i++){
                int rate = users[i][0];
                int costLimit = users[i][1];
                int cost = 0;
                
                for(int j=0; j<emoticons.length; j++){
                    if(discountRate[j] >= rate) cost += (emoticons[j] * (100-discountRate[j])) / 100;          
                }
                if(cost >= costLimit){
                    ++joinCnt;
                }else{
                    totalSale += cost;
                }
                
            }
            
            if(joinCnt > answerJoinCnt){
                answerJoinCnt = joinCnt;
                answerSale = totalSale;
            }else if(joinCnt == answerJoinCnt && totalSale > answerSale){
                answerSale = totalSale;
            }
            
            return;
        }
        
        for(int i=10; i<=40; i+=10){
            
            discountRate[cnt] = i;
            dfs(cnt+1, end, emoticons, users);
            
        }
        
    }
}