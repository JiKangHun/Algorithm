import java.util.*;

class Solution {
    
    public class File {
        
        String head;
        String number;
        String tail;
        
        public File(String head, String number, String tail){
            this.head = head;
            this.number = number;
            this.tail = tail;            
        }        
    }
    
    public ArrayList<String> solution(String[] files) {
        
        ArrayList<File> fileList = new ArrayList<>();
        
        for(String fileName : files) {
            StringBuilder head = new StringBuilder();
            StringBuilder number = new StringBuilder();
            StringBuilder tail = new StringBuilder();
            
            int numStart = 0;
            for(int i=0; i<fileName.length(); i++) {
                char idx = fileName.charAt(i);
                if(idx >= '0' && idx <= '9'){
                    numStart = i;
                    break;
                }
                head.append(idx);
            }
            
            for(int i=numStart; i<fileName.length(); i++){
                char idx = fileName.charAt(i);
                if(idx < '0' || idx > '9'){
                  tail.append(fileName.substring(i, fileName.length()));
                  break;
                }
                number.append(idx);
            } 
            
            fileList.add(new File(head.toString(), number.toString(), tail.toString()));
        }
        
        fileList.sort((o1, o2) -> {
           int step1 = o1.head.toUpperCase().compareTo(o2.head.toUpperCase());
           if(step1 == 0){
                return Integer.parseInt(o1.number) - Integer.parseInt(o2.number);
            }
             
            return step1;           
        });
        
        ArrayList<String> answer = new ArrayList<>();
        for(File file : fileList){
            answer.add(file.head+file.number+file.tail);
        }
        
            
        return answer;
    }
}