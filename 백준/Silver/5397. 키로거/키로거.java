import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception {
		
		//좌측 화살표 왼쪽 마지막 문자열 빼서 오른쪽 첫번째 문자열에 넣음
		//우측 화살표 오른쪽 첫번째 문자열 빼서 왼쪽 마지막 문자열에 넣음
		//백스페이스면 좌측 문자열 마지막 문자를 버림
		//문자가 들어오면 왼쪽 마지막에 넣음
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			
			String str = br.readLine();
			Stack<Character> left = new Stack<>();
			Stack<Character> right = new Stack<>();
			
			for(int i=0; i<str.length(); i++) {
				
				char input = str.charAt(i);
				
				switch(input) {
				
				case '<':
					if(!left.isEmpty()) {
						right.add(left.pop());
					}
					break;
				case '>':
					if(!right.isEmpty()) {
						left.push(right.pop());
					}
					break;
				case '-':
					if(!left.isEmpty()) {
						left.pop();
					}
					break;
				default:
					left.push(input);
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for(char c: left) {
				sb.append(c);
			}
			while(!right.isEmpty()) {
				sb.append(right.pop());
			}
			
			System.out.println(sb);
		}
	}
}
