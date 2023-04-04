import java.util.*;

/*
  완탐으로 푼다면?
  모든 사람에 대해 다른 사람과
  무게 * 거리 곱 3가지에 대해 다른 사람의 3가지 총 9가지를 비교해서
  그 중 하나라도 같으면
  시소 짝꿍
  
  A B
  2A 2B
  3A 3B
  4A 4B
*/

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        int[] weightList = new int[1001];
        for(int i=0; i<weights.length; i++){
            int idx = weights[i];
            weightList[idx]++;
        }
        
        for(int i=100; i<=1000; i++){
            if(weightList[i] == 0 ) continue;
            // int weight = i;
            // System.out.println("answer :"+answer);
            // System.out.println("original Weight :" +weight);
            int weight2 = 2*i;
            int weight3 = 3*i;
            int weight4 = 4*i;
            
            
            // System.out.println(weight2+" "+weight3+" "+weight4);
            
            //같은 무게가 여러명일 가능성 체크해야 함
            //양쪽 모두 계산되는 거 체크해야 함
            
            for(int j=i; j<=1000; j++){
                if(weightList[j] == 0 ) continue;
                if(i==j && weightList[i] <= 1) continue;
                if(i==j && weightList[i] >= 2) {
                    answer += (long)weightList[i] * (weightList[i] -1) / 2L; 
                    continue;
                }
                int compare2 = 2 * j;
                int compare3 = 3 * j;
                int compare4 = 4 * j;
                // System.out.println("compare Weight "+j);
                // System.out.println(compare2+" "+compare3+" "+compare4);
                
                if(weight2 == compare3 || weight2 == compare4
                  || weight3 == compare2 || weight3 == compare4
                   || weight4 == compare2 || weight4 == compare3 ){
                    
                  answer += (long)weightList[i] * weightList[j];
                }
            }
            // System.out.println("-----------------");
        }
        return answer;
    }
}
