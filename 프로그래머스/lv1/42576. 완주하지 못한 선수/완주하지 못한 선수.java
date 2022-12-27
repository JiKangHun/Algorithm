import java.util.*;

class Solution {
    
    public String solution(String[] participant, String[] completion) {
        
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<participant.length; i++){
            if(map.get(participant[i])!=null){
                Integer num = map.get(participant[i]);
                map.put(participant[i], ++num);
            }else{
                map.put(participant[i], 1);
            }
        }
        
        for(int i=0; i<completion.length; i++){
            Integer num = map.get(completion[i]);
            map.put(completion[i], --num);
        }
        
        Iterator<String> keys = map.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            if(map.get(key)>0){
                return key;
            } 
        }
        
        return null;
    }
}