import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static class Member implements Comparable<Member>{

		int age;
		int order;
		String name;
		
		public Member(int age, int order, String name) {
			this.age = age;
			this.order = order;
			this.name = name;
		}
		
		public int compareTo(Member o) {
		
			if(this.age == o.age) {
				return this.order - o.order;
			}else {
				return this.age - o.age;
			}
			
		}
		
	}
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Member> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			pq.add(new Member(age, i, name));
		}
		
		for(int i=0; i<N; i++) {
			Member mem = pq.poll();
			System.out.println(mem.age+" "+mem.name);
		}
		
		
	}
}
