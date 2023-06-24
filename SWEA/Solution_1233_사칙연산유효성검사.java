package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_1233_사칙연산유효성검사 {
	public static void main(String[] args) throws Exception {
		
		int T = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=T; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			
			int[][] tree = new int[N+1][3];
			
			for(int i=1; i<=N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				int node = Integer.parseInt(st.nextToken());
				int value = st.nextToken().charAt(0);
				if( value >= 49) value -= 48;
				tree[node][0] = value;

				if(st.hasMoreTokens()) {
					int left = Integer.parseInt(st.nextToken());
					tree[node][1] = left;
				}
				if(st.hasMoreTokens()) {
					int right = Integer.parseInt(st.nextToken());
					tree[node][2] = right;
				}
			}
			
			Stack<Integer> ops = new Stack<>();
			if(inOrder(tree, ops, 1) && ops.peek() < 10) {
				sb.append("#"+tc+" 1").append("\n");
			}else {
				sb.append("#"+tc+" 0").append("\n");
			}
			
		}
		
		System.out.println(sb);
		
	}

	private static boolean inOrder(int[][] tree, Stack<Integer> ops, int root) {
		
		int value = tree[root][0];
		
		if(tree[root][1] == 0 || inOrder(tree, ops, tree[root][1])) {
			
			if(ops.size()==0) {
				if(value >= 10) return false;
			}else if(ops.peek() < 10) {
				if(value < 10) return false;
			}else if(ops.peek() >= 10) {
				if(value >= 10) return false;
			}
			
			ops.push(value);
		}else{
			return false;
		};
		
		if(tree[root][2] == 0 || inOrder(tree, ops, tree[root][2])) return true;			
		
		return false;
	}
}
