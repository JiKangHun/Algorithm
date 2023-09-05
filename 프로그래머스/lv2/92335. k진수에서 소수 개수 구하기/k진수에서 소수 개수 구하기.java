import java.util.*;

class Solution {
    
    static int answer;
    
    public int solution(int n, int k) {
        convert(n, k, "");
        return answer;
    }
    
    public static void convert(int n, int k, String num){
        
        
        if(n == 0){
            
            String part = "";
            
            for(int i=0; i<num.length(); i++){
                
                char digit = num.charAt(i);
                if(digit == '0') continue;
                part+=digit;
                
                
                if(i+1==num.length() || num.charAt(i+1) == '0'){
                    
                    long partToLong = Long.parseLong(part);
                    
               
                    boolean isPrimeNumber = true;
                    
                    for(int j=2; j<=Math.sqrt(partToLong); j++){
                        if(partToLong % j == 0){
                            isPrimeNumber = false;
                            break;
                        }
                    }
                    if(partToLong != 1 && isPrimeNumber) ++answer;
                    
                    part = "";
                    
                }
                
                
            }
            return;
            
        }else{
    
            convert(n/k, k, n%k + num);
            
        }
        
        
    }
}