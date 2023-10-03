import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws Exception {
		
		//알파벳 배열 생성
		//알파벳 수 카운트, 위치 리스트에 저장
		//배열 돌면서 K보다 크거나 같은 알파벳이면
		//문자열 돌면서 위치 슬라이딩 윈도우
		//리스트 idx 0을 from으로, K-1을 to로 하여 길이 계산 -> 리스트 to 값 - 리스트 from 값 + 1
		//from 1 늘리고 to 1늘리면서 to가 last idx면 종료
		//이렇게 해가면서 가장 짧은 것과 가장 긴 것 계속 갱신
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			
			String W = br.readLine();
			int K = Integer.parseInt(br.readLine());
			
			HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
			
			for(int i=0; i<W.length(); i++) {
				char ch = W.charAt(i);
				if(map.get(ch) == null) map.put(ch, new ArrayList<Integer>());
				map.get(ch).add(i);				
			}
			
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			
			for(char ch: map.keySet()) {
				
				ArrayList<Integer> idxList = map.get(ch);
				if(idxList.size() >= K) {
					
					int from = 0;
					int to = K-1;
					while(to <= idxList.size()-1) {
						
						int length = idxList.get(to) - idxList.get(from) + 1;
						min = Math.min(min,  length);
						max = Math.max(max, length);
						
						++from;
						++to;
						
					}
				}
			}
			
			if(min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
				System.out.println(-1);
			}else {
				System.out.println(min+" "+max);
			}
			
		}
		
	}
}
