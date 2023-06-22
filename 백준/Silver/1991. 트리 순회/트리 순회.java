import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static char[][] tree;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		tree = new char[N][3];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			char root = st.nextToken().charAt(0);
			char leftChild = st.nextToken().charAt(0);
			char rightChild = st.nextToken().charAt(0);
			tree[i][0] = root;
			tree[i][1] = leftChild;
			tree[i][2] = rightChild;
		}
		
		// 'A'-'A'
		
		preOrder('A');
		System.out.println();
		inOrder('A');
		System.out.println();
		postOrder('A');
	}

	private static void postOrder(char node) {
		if(node == '.') return;
		int rowNum = findRowNum(node);
		postOrder(tree[rowNum][1]);
		postOrder(tree[rowNum][2]);
		System.out.print(node);
	}

	private static void inOrder(char node) {
		if(node == '.') return;
		int rowNum = findRowNum(node);
		inOrder(tree[rowNum][1]);
		System.out.print(node);
		inOrder(tree[rowNum][2]);
	}

	private static void preOrder(char node) {
		if(node == '.') return;
		int rowNum = findRowNum(node);
		System.out.print(node);
		preOrder(tree[rowNum][1]);
		preOrder(tree[rowNum][2]);
		
	}
	
	private static int findRowNum(char node) {
		
		int rowNum = 0;
		for(int i=0; i<tree.length; i++) {
			if(tree[i][0] == node) rowNum = i;
		}
		
		return rowNum;
	}
}
