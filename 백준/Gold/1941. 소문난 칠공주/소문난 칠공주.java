import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
	
	static char[] map;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[25];
		char[] selected = new char[7];
		
		for(int i=0; i<5; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j=0; j<5; j++) {
				map[i*5+j] = line[j];
			}
		}
		
		combi(0, 0, 7, selected, new ArrayList<Integer>());
		System.out.println(answer);
		
	}

	private static void combi(int start, int cnt, int total, char[] selected, ArrayList<Integer> arrayList) {
		
		if(cnt == total) {
			
			int sCnt = 0;
			for(int i=0; i<7; i++) {
				if(selected[i] == 'S') ++sCnt; 
			}
			
			if(sCnt >= 4 && check(arrayList)) {
				
				++answer;
				
			}
			
			return;
		}
		
		
		for(int i=start; i<map.length; i++) {
			
			selected[cnt] = map[i];
			arrayList.add(i);
			combi(i+1, cnt+1, total, selected, arrayList);
			arrayList.remove(arrayList.size()-1); 
			
		}
		
	}

	private static boolean check(ArrayList<Integer> arrayList) {
		
	
		int[][] classRoom = new int[5][5];
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, -1, 0, 1};
		
		for(int i=0; i<arrayList.size(); i++) {
			
			int num = arrayList.get(i);
			int row = num / 5;
			int col = num % 5;
			
			classRoom[row][col] = 1;
		}
		
		boolean isPossible = true;
		
		for(int i=0; i<arrayList.size(); i++) {
			
			int num = arrayList.get(i);
			int row = num / 5;
			int col = num % 5;
			
			Queue<int[]> q = new ArrayDeque<>();
			classRoom[row][col] = 2;
			q.add(new int[] {row, col});
			
			int cntBfs = 0;
			
			while(!q.isEmpty()) {
				
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				++cntBfs;
				
				for(int d=0; d<4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if( nr < 0 || nc < 0 || nr >= 5 || nc >= 5) continue;
					if(classRoom[nr][nc] == 2) continue;
					if(classRoom[nr][nc] == 0) continue;
					q.add(new int[] {nr, nc});
					classRoom[nr][nc] = 2;
					
				}
				
			}
			
			if (cntBfs == 7) {
				isPossible = true;
			}else {
				isPossible = false;
			}
			break;
			
		}
		
		return isPossible;
	}
}
