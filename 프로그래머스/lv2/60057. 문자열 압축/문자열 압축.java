import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        
        
        for(int i=1; i<=s.length()/2; i++) { //잘라 볼 단위, 최대 글자 길이의 절반 내림한 것
            
            int cutLength = 0;            
            StringBuilder compress = new StringBuilder();   //반복인지 확인할 기준 문자열
            int cnt = 0;                                    // 반복횟수
            
            for(int j=0; j< s.length()/i * i; j+=i) {       //단위 만큼 idx 건너 뛰면서 확인 // 나머지 나오는 길이 전까지만 탐색
                
                StringBuilder tmp = new StringBuilder();
                
                for(int k=0; k<i; k++) {                    // 단위만큼 읽기
                    tmp.append(s.charAt(j+k));                                   
                }
              
                
                if(compress.toString().equals(tmp.toString())){ //이미 들어있는 문자열과 단위만큼 읽은 문자열이 같다면
                    ++cnt;                                      //반복횟수를 늘려준다.
              
                }else{                                          // 다르다면 반복이 끊긴것이므로
                    
                    if(cnt>0){                                  // 반복한 적이 있다면 줄어든 길이 계산
                       
                        int length = String.valueOf(cnt+1).length(); 
                        cutLength += ((i*(cnt+1)) - (i+length));  // 줄어든 길이: (자른 단위 * 반복된 횟수) - (반복 회수 + 단위)             
                        cnt = 0;                                // 반복횟수를 리셋                                                
                      
                    }
                        compress.setLength(0);                  // 반복하는지 확인할 기존 문자열을 없애주고
                        compress.append(tmp.toString());        // 이번 턴에 읽은 문자열로 세팅해준다.
                        tmp.setLength(0);                       // 다음 턴에 읽을 것을 담는 변수는 초기화해준다.                            
                }
                
                if(compress.length()==0){                       //첫번째 턴에서는 들어가있는 게 없어서 length가 0이므로              
                    compress.append(tmp.toString());            // 이때는 무조건 넣어준다                    
                }                                
            }    
            
            if(cnt > 0){                                        //마지막 문자에서 반복되고 있는 중이었는데 처리 안 된 것들 처리
                int length = String.valueOf(cnt+1).length(); 
                cutLength += ((i*(cnt+1)) - (i+length));                
            }
            answer = Math.min(answer, s.length()-cutLength);    //단위 별로 줄어든 길이와 저장된 길이 중 최소값 정답으로 할당          
        }
        return answer != Integer.MAX_VALUE ? answer : 1;
    }
}