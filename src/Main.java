import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;

public class Main {

	private BufferedReader in;
	private PollardRho pollardRho;

	public Main() {
		in = new BufferedReader(new InputStreamReader(System.in));
		pollardRho = new PollardRho();
	}

	public static void main(String[] args) {
		Main main = new Main();
		String line = main.read();
		StringBuilder sb = new StringBuilder();
		while (line != null) {
			getFactors(main.factorize(new BigInteger(line)), sb);
			line = main.read();
		}
		System.out.println(sb.toString());
	}

	private List<BigInteger> factorize(BigInteger n) {
		return pollardRho.factorize(n);
	}

	private static void getFactors(List<BigInteger> factors, StringBuilder sb) {
		if (factors == null) {
			sb.append("fail\n");
		} else {
			for (BigInteger factor : factors) {
				sb.append(factor).append('\n');
			}
		}
		sb.append('\n');
	}

	private String read() {
		String line = null;
		try {
			if (in.ready())
				line = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
}
