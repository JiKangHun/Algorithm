import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        for(int i=n-1; i>=1; i--){
            deliveries[i-1] += deliveries[i];
            pickups[i-1] += pickups[i];
        }
        
        int moveCnt = 0;
        if(deliveries[0] >= pickups[0]){
            moveCnt = deliveries[0] / cap;
            if(deliveries[0] % cap != 0) ++moveCnt;            
        }else{
            moveCnt = pickups[0] / cap;
            if(pickups[0] % cap != 0) ++moveCnt;            
        }
        int[] distance;
        if(moveCnt>0){
             distance = new int[moveCnt];    
        }else{
            return 0;
        }
        
        
        int nextPoint = 0;
        for(int i=n-1; i>=0; i--){
            if(nextPoint > moveCnt-1) break;
            if(deliveries[i] > nextPoint * cap){                    
                int visitCnt = (deliveries[i] - nextPoint * cap) / cap;
                if((deliveries[i] - nextPoint * cap ) % cap != 0 ) ++visitCnt;
                for(int j=nextPoint; j<nextPoint+visitCnt; j++){                        
                    distance[j] = i; 
                }
                nextPoint += visitCnt;
            }
                
        }
        
        nextPoint = 0;
        for(int i=n-1; i>=0; i--){
            if(nextPoint > moveCnt-1) break;
            if(pickups[i] > nextPoint * cap){
                int visitCnt = (pickups[i] - nextPoint * cap) / cap;
                if((pickups[i] - nextPoint * cap ) % cap != 0) ++visitCnt;
                for(int j=nextPoint; j<nextPoint+visitCnt; j++){                        
                    if(distance[j] < i) distance[j] = i; 
                }
                nextPoint += visitCnt;
            }
        }
        
        for(int i=0; i<distance.length; i++){
            answer += (distance[i]+1) * 2;
        }

        return answer;
    }
}