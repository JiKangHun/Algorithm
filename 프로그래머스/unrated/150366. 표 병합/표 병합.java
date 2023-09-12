import java.util.*;

class Solution {
    
    static String[] table;
    static int[] parent;
    
    public ArrayList<String> solution(String[] commands) {
        
        table = new String[2501];
        parent = new int[2501];
        
        ArrayList<String> arrList = new ArrayList<>();
        make();
        
        for(int i=0; i<commands.length; i++){
            
            String[] command = commands[i].split("\\s");
            String action = command[0];
            
            if(action.equals("UPDATE")){
                update(command);
            }else if(action.equals("MERGE")){
                merge(command);
            }else if(action.equals("UNMERGE")){
                unmerge(command);
            }else{
                int cellNum = convert(command[1], command[2]);
                if(table[cellNum] != null){
                    arrList.add(table[cellNum]);
                }else{
                    arrList.add("EMPTY");
                }
            }
        }
        
        return arrList;
    }
    
    public void unmerge(String[] command){
        
        int cellNum = convert(command[1],command[2]);
        int parentNum = find(cellNum);
        String value = table[parentNum];
        
        boolean[] chk = new boolean[2501];
        
        for(int i=1; i<=2500; i++){
            if(find(parent[i]) == parentNum){
                chk[i] = true;
                table[i] = null;
            }
        }
        
        for(int i=1; i<=2500; i++){
            if(chk[i]) parent[i] = i;
        }
        
        if(value!=null){
            table[cellNum] = value;
        }
        
    }
    
    public void merge(String[] command){
        
        int x = convert(command[1], command[2]);
        int y = convert(command[3], command[4]);
        
        if(union(x,y)){
            
            int parentNum = find(x);
            
            String value = null;
            if(table[x] != null){
                value = table[x];
            }else if(table[y] != null){
                value = table[y];
            }
            
            if(value != null){
                for(int i=1; i<=2500; i++){
                    if(find(parent[i]) == parentNum){
                        table[i] = value;
                    }
                }
            } 
        }
    }
    
    public void update(String[] command){
        
        if(command.length > 3){
            
            int cellNum = convert(command[1], command[2]);
            int mergeNum = find(cellNum);
            
            for(int i=1; i<=2500; i++){
                    
                if(find(parent[i]) == mergeNum){
                    table[i] = command[3];
                }       
            }
            
        }else{
            for(int i=1; i<=2500; i++){
                if(table[i] != null && table[i].equals(command[1])){
                    table[i] = command[2];
                }
            }
        }
    }
    
    public void make() {
        
        for(int i=1; i<=2500; i++){
            parent[i] = i;
        }
        
    }
    
    public int find(int x) {
        
        if(parent[x] == x) return x;
        
        return parent[x] = find(parent[x]);
        
    }
    
    public boolean union(int x, int y) {
        
        x = find(x);
        y = find(y);
        if(x==y) return false;
        
        parent[x] = y;
        return true;
        
    }
    
    public int convert(String r, String c){
        int row = Integer.parseInt(r);
        int col = Integer.parseInt(c);
        return (row-1) * 50 + col;
    }
    
}