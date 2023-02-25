import java.util.*;

class Solution {
    
    public String solution(String[] participant, String[] completion) {
     
        HashMap<String, Integer> all = new HashMap();
        for(String name : participant){
            all.put(name, all.getOrDefault(name, 0) + 1);
        }
        
        for(String name : completion){
            if(all.get(name) > 1){
              all.put(name, all.get(name)-1);  
            }else{
              all.remove(name);  
            }            
        }
        
        String answer = "";
        for(String name: all.keySet()){
            answer = name;
        }
        
        return answer;
    }
}