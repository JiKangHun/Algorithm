import java.util.*;

class Solution {
    boolean solution(String s) {

        Stack<Character> left = new Stack();
        Stack<Character> right = new Stack();
        
        for(int i=0; i<s.length(); i++){
            char parenthesis = s.charAt(i);
            if(parenthesis == '('){
                left.push(parenthesis);
            }else{
                if(left.size()==0) return false;
                left.pop();
            }
        }
        
        if(left.size()!=0) return false;
        

        return true;
    }
}