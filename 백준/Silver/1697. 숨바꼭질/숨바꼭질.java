import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static class Node{
		int cnt;
		int num;
		public Node(int num, int cnt) {			
			this.num = num;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		boolean[] visited= new boolean[100001];
		
		Node start = new Node(N,0);
		
		Queue<Node> q = new LinkedList<>();
		q.offer(start);
		
		int result = 0;
		
		
		while(!q.isEmpty()) {			
			Node n = q.poll();
			
			if(n.num==K) {
				result = n.cnt;
				break;
			}
			
			int n1 = n.num-1;
			int n2 = n.num+1;
			int n3 = n.num*2;
			if(0<=n1 && !visited[n1]) {
				q.offer(new Node(n1,n.cnt+1));
				visited[n1] = true;
			}
			if(n2 <=100000 && !visited[n2]) {
				q.offer(new Node(n2,n.cnt+1));
				visited[n2] = true;
			}
			if(n3 <=100000 && !visited[n3]) {
				q.offer(new Node(n3,n.cnt+1));
				visited[n3] = true;
			}													
		}		
		System.out.println(result);
		
		
		
	}
}
