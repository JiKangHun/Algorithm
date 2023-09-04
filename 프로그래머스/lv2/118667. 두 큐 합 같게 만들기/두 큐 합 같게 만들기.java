import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = Integer.MAX_VALUE;
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        int n = queue1.length;
        
        long q1Sum = 0L;
        long q2Sum = 0L;
                
        for(int i=0; i<n; i++){            
            q1Sum += queue1[i];
            q2Sum += queue2[i];
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }
        
        if((q1Sum + q2Sum) % 2 != 0) return -1;
        long half = (q1Sum + q2Sum) / 2;
        int work = 0;
        for(int i=0; i<n*3-2; i++){
            if(q1Sum > half && !q1.isEmpty()){
                int next = q1.poll();
                q2.add(next);
                q1Sum -= next;
                ++work;                
            }else if(q1Sum < half && !q2.isEmpty()){
                int next = q2.poll();
                q1.add(next);
                q1Sum += next;
                ++work;
            }else{
                answer = work;
                break;
            }
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}