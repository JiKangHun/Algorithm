import java.util.*;

class Solution {
    public int solution(String s) {
                
        HashMap<String, Integer> map = new HashMap<>();
        map.put("zero", 0); map.put("one", 1);
        map.put("two", 2); map.put("three", 3);
        map.put("four", 4); map.put("five", 5);
        map.put("six", 6); map.put("seven", 7);
        map.put("eight", 8); map.put("nine", 9);
        
        StringBuilder sb = new StringBuilder();
        String tmp = "";
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if( ch -'0' >= 0 && ch - '0' <= 9){
                sb.append(ch+"");            
            }else{
                tmp += ch;
            }
        
            if(map.get(tmp) != null){                
                sb.append(String.valueOf(map.get(tmp)));
                tmp = "";
            }
        }
        return Integer.parseInt(sb.toString());
    }
}