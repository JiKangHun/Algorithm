import java.util.*;

class Solution {
    public String solution(int n) {
        
        StringBuilder answer = new StringBuilder();
        
        int sum = 1;
        int idx = 1;
        
        while(true){
            int next = sum + (int)Math.pow(3, idx);
            if(n < next) break;
            sum = next;
            ++idx;
        }
        
        make(n, 0, idx, sum, sum + (int)Math.pow(3,idx)-1, idx, answer);
        
        return answer.toString();
    }
    
    public void make(int n, int cnt, int idx, int from, int to, int total, StringBuilder answer) {
        
        if(cnt == total){
            return;
        }
        
        int group = 1;
        int newFrom = 0;
        int newTo= 0;
        
        for(int i=from; i<=to; i += Math.pow(3,idx-1)){
            newFrom = i;
            newTo = i + (int)Math.pow(3, idx-1)-1;
            if( n <= newTo) break;
            group*=2;
        }
        answer.append(String.valueOf(group));
        

        make(n, cnt+1, idx-1, newFrom, newTo, total, answer);
        
    }
}