import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String text[] = br.readLine().split(" ");
			
			double A = Double.parseDouble(text[0]);
			double B = Double.parseDouble(text[1]);
			int decimalSeparator = Integer.parseInt(text[2]);
			
			double res = A%B;
			
			for(int i=1; i<decimalSeparator;i++) {
				res *= 10;
				res %= B;
			}

			System.out.println( (int)((res/B)*10) );
			
		}catch(IOException e) {
			System.out.println("error");
		}
	}
}