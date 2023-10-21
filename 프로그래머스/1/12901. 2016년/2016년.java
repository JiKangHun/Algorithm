
class Solution {
    public String solution(int a, int b) {
        String answer = "";
        
        int[] month = {31,29,31,30,31,30,31,31,30,31,30,31};
        int[] date = new int[366];
        date[1] = 4;
        
        for(int i=1; i<365; i++){
            date[i+1] = (date[i]+1)%7;
        }
        
        int day = 0;
        for(int i=0; i<a-1; i++){
            day += month[i];
        }
        day += b;
        
        System.out.print(day);
        
        switch(date[day]){
                
            case 0: answer = "MON";
            break;                
            case 1: answer = "TUE";
            break;
            case 2: answer = "WED";
            break;
            case 3: answer = "THU";
            break;
            case 4: answer = "FRI";
            break;
            case 5: answer = "SAT";
            break;
            case 6: answer = "SUN";
            break;
                
        }
                
        return answer;
    }
}