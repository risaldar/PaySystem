package app.code;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.math.BigInteger;

public class MathUtils extends BigInteger {


	public MathUtils(byte[] val) {
		super(val);
		// TODO Auto-generated constructor stub
	}

	private static final RoundingMode DEFAULT_ROUNDINGMODE = RoundingMode.HALF_UP;
	public static final BigInteger TWO = valueOf(2);
	RoundingMode roundingMode;
	private static final int MIN_DIGITS = 0;
	
	public void MathContext(String val) {
		boolean bad = false;
		int setPrecision;
		if(val == null)
			throw new NullPointerException("null string");
		
		try {
			if (!val.startsWith("Precision=")) throw new RuntimeException();
			int fence =val.indexOf(' ');
			int off = 10;
			setPrecision = Integer.parseInt(val.substring(10, fence));
			
			if (!val.startsWith("roundingMode=", fence+1))
				throw new RuntimeException();
			
			off = fence + 1 + 13;
			String str = val.substring(off, val.length());
			roundingMode = RoundingMode.valueOf(str);
		}catch (RuntimeException re) {
			throw new IllegalArgumentException("bad string format");
		}
		
		if (setPrecision < MIN_DIGITS)
			throw new IllegalArgumentException("Digits < 0");
		
		int precision = setPrecision;
	}
	
    public boolean passesMillerRabin(int iterations, Random rnd) {
        // Find a and m such that m is odd and this == 1 + 2**a * m
        BigInteger thisMinusOne = this.subtract(ONE);
        BigInteger m = thisMinusOne;
        int a = m.getLowestSetBit();
        m = m.shiftRight(a);

        // Do the tests
        if (rnd == null) {
            rnd = ThreadLocalRandom.current();
        }
        for (int i=0; i < iterations; i++) {
            // Generate a uniform random on (1, this)
            BigInteger b;
            do {
                b = new BigInteger(this.bitLength(), rnd);
            } while (b.compareTo(ONE) <= 0 || b.compareTo(this) >= 0);

            int j = 0;
            BigInteger z = b.modPow(m, this);
            while (!((j == 0 && z.equals(ONE)) || z.equals(thisMinusOne))) {
                if (j > 0 && z.equals(ONE) || ++j == a)
                    return false;
                z = z.modPow(TWO, this);
            }
        }
        return true;
    }
    
    final static long LONG_MASK = 0xffffffffL;
    
    public int[] subtract(long val, int[] little) {
        int highWord = (int)(val >>> 32);
        if (highWord == 0) {
            int result[] = new int[1];
            result[0] = (int)(val - (little[0] & LONG_MASK));
            return result;
        } else {
            int result[] = new int[2];
            if (little.length == 1) {
                long difference = ((int)val & LONG_MASK) - (little[0] & LONG_MASK);
                result[1] = (int)difference;
                // Subtract remainder of longer number while borrow propagates
                boolean borrow = (difference >> 32 != 0);
                if (borrow) {
                    result[0] = highWord - 1;
                } else {        // Copy remainder of longer number
                    result[0] = highWord;
                }
                return result;
            } else { // little.length == 2
                long difference = ((int)val & LONG_MASK) - (little[1] & LONG_MASK);
                result[1] = (int)difference;
                difference = (highWord & LONG_MASK) - (little[0] & LONG_MASK) + (difference >> 32);
                result[0] = (int)difference;
                return result;
            }
        }
    }
}
