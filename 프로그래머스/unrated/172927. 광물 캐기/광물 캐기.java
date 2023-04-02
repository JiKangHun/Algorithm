import java.util.*;

class Solution {
    static int[] num;
    static int ans = Integer.MAX_VALUE;
    static int[][] map = {{1,1,1},
                          {5,1,1},
                          {25,5,1}};
    static int[] newMinerals;
    public int solution(int[] picks, String[] minerals) {
        
        int pickCnt = 0;
        for(int i=0; i<picks.length; i++){
            pickCnt += picks[i];
        }
        int maxCnt = (int)Math.ceil(minerals.length/5.0);
        int limit = maxCnt >= pickCnt ? pickCnt : maxCnt;
        num = new int[limit];
                
        newMinerals = new int[minerals.length];
        
        for(int i=0; i<minerals.length; i++){
            if(minerals[i].equals("diamond")){
                newMinerals[i] = 0;
            }else if(minerals[i].equals("iron")){
                newMinerals[i] = 1;
            }else{
                newMinerals[i] = 2;
            }
        }
        
        dfs(picks, limit, 0, newMinerals);
        
        return ans;
    }
    
    public void dfs(int[] picks, int limit, int cnt, int[] newMinerals){
        
        if(cnt == limit){
            int fatigue = calFatigue(limit, newMinerals);
            ans = ans >= fatigue ? fatigue : ans;
            return;
        }
        
        for(int i=0; i<picks.length; i++){
            if(picks[i] > 0){
                num[cnt] = i;    
                picks[i] -= 1;
                dfs(picks, limit, cnt+1, newMinerals);
                picks[i] += 1;
            }else{
                continue;
            }
        }
    }
    
    public int calFatigue(int limit, int[] newMinerals){
        int sum = 0;
        int idx = 0;
        int useCnt = 0;
        for(int i=0; i<newMinerals.length; i++){
            ++useCnt;            
            int pickKind = num[idx];
            int mineralKind = newMinerals[i];
            sum += map[pickKind][mineralKind];
            if(useCnt >= 5){
                ++idx;
                if(idx>=num.length) break;
                useCnt = 0;
            }         
        }
        return sum;
    }
}