import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PollardRho {

	private static final int CERTAINTY = 10;
	private static final int FACTORIZATIONS = 100;
	private static final int TIME_LIMIT = 15 * 1000 + 3000;
	private long finalDeadline;
	private int factorizationsLeft;

	public PollardRho() {
		factorizationsLeft = FACTORIZATIONS;
		finalDeadline = System.currentTimeMillis() + TIME_LIMIT;
	}

	public List<BigInteger> factorize(BigInteger n) {
		long deadline = System.currentTimeMillis() + (finalDeadline - System.currentTimeMillis()) / factorizationsLeft;
		factorizationsLeft--;

		List<BigInteger> factors = new ArrayList<BigInteger>();

		if (n.isProbablePrime(CERTAINTY)) {
			factors.add(n);
			return factors;
		}
		Queue<BigInteger> queue = new LinkedList<BigInteger>();

		queue.add(n);
		while (!queue.isEmpty()) {
			BigInteger current = queue.poll();
			BigInteger factor = pollardRho(current, deadline);
			if (factor == null) {

				return null;
			}

			factors.add(factor);

			if (current.divide(factor).isProbablePrime(CERTAINTY)) {
				factors.add(current.divide(factor));
			} else {
				queue.add(current.divide(factor));
			}
		}
		return factors;
	}

	private BigInteger pollardRho(BigInteger n, long deadline) {
		BigInteger res = trialDivision(n);
		if (res != null) {
			return res;
		}

		BigInteger x = BigInteger.valueOf(2);
		BigInteger y = BigInteger.valueOf(2);
		BigInteger d = BigInteger.ONE;
		while (d.equals(BigInteger.ONE)) {
			if (System.currentTimeMillis() >= deadline) {
				return null;
			}
			x = f(x, n);
			y = f(f(y, n), n);
			d = n.gcd(x.subtract(y));
		}
		if (d.equals(n)) {
			return null;
		} else {
			return d;
		}
	}

	private BigInteger trialDivision(BigInteger n) {
		for (int p : Constants.PRIMES) {
			if (n.mod(BigInteger.valueOf(p)).equals(BigInteger.ZERO)) {
				return (BigInteger.valueOf(p));
			}
		}
		return null;
	}

	private BigInteger f(BigInteger x, BigInteger n) {
		return x.pow(2).subtract(BigInteger.ONE).mod(n);
	}

	public static void main(String[] args) {
		System.out.println(new PollardRho().pollardRho(BigInteger.valueOf(9), System.currentTimeMillis() + 100000000));
	}
}
