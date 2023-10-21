import java.time.*;

class Solution {
    public String solution(int a, int b) {
        String answer = "";
        
        int[] month = {31,29,31,30,31,30,31,31,30,31,30,31};
        String[] day = {"FRI", "SAT", "SUN", "MON","TUE","WED","THU"};
      
        int cnt = 0;
        for(int i=0; i<a-1; i++){
            cnt += month[i];
        }
        cnt += b -1;
                
        return LocalDate.of(2016, a, b).getDayOfWeek().toString().substring(0,3);
    }
}