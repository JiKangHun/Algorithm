import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
            Set<String> set = new HashSet<>();
        for(String s:phone_book){
            set.add(s);
        }
        
        for(String s: set){            
            String tmp = "";
            for(int i=0; i<s.length()-1; i++){
                tmp += s.charAt(i);
                if(set.contains(tmp)) return false;
            }
        }
        return answer;
    }
}