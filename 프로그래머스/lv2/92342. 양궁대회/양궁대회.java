import java.util.*;

class Solution {
    
    static int[] point;
    static int gap = Integer.MIN_VALUE;
    static int[] answer;
    public int[] solution(int n, int[] info) {
        
        //1,1,1,2 == 1,1,2,1
        
        //1,2,3,4
        //1,2,3  1,2,4  1,3,4  2,3,4
        //1,1,1  1,1,2  1,1,3  1,1,4  1,2,2  1,2,3  1,2,4  1,3,3  1,3,4  1,4,4  2,2,2
        
        
        point = new int[n];
        combi(0, 10, n, info);
        
        
        return gap == Integer.MIN_VALUE ? new int[] {-1} : answer;
    }
    
    public static void combi(int cnt, int start, int total, int[] info){
        
        if(cnt == total){
            
            // for(int i=0; i<total; i++){
            //     System.out.print(point[i]);
            // }
            // System.out.println();
            
            //점수 계산
            int[] arrowRion = new int[11];
            for(int i=0; i<total; i++){
                ++arrowRion[point[i]];
            }
            
            int pointGap = 0;
            for(int i=0; i<11; i++){
                
                if(arrowRion[i] > info[i]){
                    pointGap += 10-i;
                }else if(info[i]!=0 && arrowRion[i] <= info[i]){
                    pointGap -= 10-i;
                }
            }
            
            //기존 저장된 점수 차와 비교 
            //점수 차 더 크다면 새로운 배열 저장, 10을 0점 뽑은 거로 취급, 낮은 점수 더 많은 게 먼저 구해짐
            //점수 차 저장
            
            if(pointGap > 0 && pointGap > gap){
                gap = pointGap;
                answer = arrowRion;                
            }
    
            return;
        }
        
        for(int i=start; i>=0; i--){
            
            point[cnt] = i;
            combi(cnt+1, i, total, info);
            
        }
    }
}