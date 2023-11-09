import java.util.*;

class Solution {
    
    static HashMap<String, Integer> menu = new HashMap<>();
    
    public ArrayList<String> solution(String[] orders, int[] course) {
        String[] answer = {};
        
        for(int i=0; i<orders.length; i++){
            
            for(int j=0; j<course.length; j++){
                
                if(orders[i].length() >= course[j]){
                    
                    combi(orders[i], "", course[j], 0, 0);
                }
            }
        }
        
        ArrayList<String> arrList = new ArrayList<>();
        int[] max = new int[11];
        for(String key : menu.keySet()){
            for(int i=0; i<course.length; i++){
                if(key.length() == course[i] && menu.get(key) >= 2 && max[key.length()] < menu.get(key)){
                   max[key.length()] = menu.get(key);
                }    
            }
            
        }
        
        for(String key : menu.keySet()){
            if(max[key.length()] == menu.get(key)){
                arrList.add(key);
            }   
        }
        Collections.sort(arrList);
        
        return arrList;
    }
    
    public void combi(String order, String select, int total, int start, int cnt){
        
        if(cnt == total){
            char[] ch = select.toCharArray();
            Arrays.sort(ch);
            String key = "";
            for(int i=0; i<ch.length; i++){
                key += ch[i];
            }
            menu.put(key, menu.getOrDefault(key, 0) +1);
            return;
        }
        
        for(int i=start; i<order.length(); i++){
            
        
            combi(order, select + order.charAt(i), total, i+1, cnt+1);
        }
    }
}