class Solution {
    public int solution(String s) {
                        
        if(s.length() == 1) return 1;
        
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        
        char x = s.charAt(0);        
        sb.append(x);
        int idx = 1;
        int xCnt = 1;
        int yCnt = 0;
        
        while(idx < s.length()) {
            
            if(idx == s.length()-1){
                answer++;
                break;
            }
            
            if(sb.length()==0) {
                x = s.charAt(idx);
                sb.append(x);
                xCnt = 1;
                yCnt = 0;
                ++idx;
                continue;
            }
            
            if(s.charAt(idx) == x){
                ++xCnt;
                sb.append('x');
            }else{
                ++yCnt;
                sb.append('y');
            }
            
            if(xCnt == yCnt){
              ++answer;
              sb.setLength(0);  
            } 
            idx++;
        }
        
        return answer;
    }
}