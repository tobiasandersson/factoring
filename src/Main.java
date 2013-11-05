import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;


public class Main {
	
	private BufferedReader in;
	
	public Main() {
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		String line = main.read();
		while (line != null) {
			printFactors(factorize(new BigInteger(line)));
			line = main.read();
		}
	}
	
	private static List<BigInteger> factorize(BigInteger n) {
		return PollardRho.factorize(n);
	}

	private static void printFactors(List<BigInteger> factors) {
		if(factors == null) {
			System.out.println("fail");
		} else {
			for(BigInteger factor: factors) {
				System.out.println(factor);
			}
		}
		System.out.println();
	}

	private String read() {
		String line = null;
		try {
			if(in.ready()) 
				line = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
}
