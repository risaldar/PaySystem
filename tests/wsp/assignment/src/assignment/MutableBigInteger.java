package app.code;

public class MutableBigInteger {
	/******************************************************************
	 * FUNCTION 3:
	 * https://github.com/openjdk/jdk/tree/master/src/java.base/share/classes/java/math/MutableBigInteger.java
	 ******************************************************************/
	
    static final long LONG_MASK = 0xffffffffL;
    public long divWord(long n, int d) {
        long dLong = d & LONG_MASK;
        long r;
        long q;
        if (dLong == 1) {
            q = (int)n;
            r = 0;
            return (r << 32) | (q & LONG_MASK);
        }

        // Approximate the quotient and remainder
        q = (n >>> 1) / (dLong >>> 1);
        r = n - q*dLong;

        // Correct the approximation
        while (r < 0) {
            r += dLong;
            q--;
        }
        while (r >= dLong) {
            r -= dLong;
            q++;
        }
        // n - q*dlong == r && 0 <= r <dLong, hence we're done.
        return (r << 32) | (q & LONG_MASK);
    }
    
    /******************************************************************
	 * FUNCTION 6:
	 * https://github.com/openjdk/jdk/tree/master/src/java.base/share/classes/java/math/MutableBigInteger.java
	 * Calculate GCD of a and b interpreted as unsigned integers.
	 ******************************************************************/
    public int binaryGcd(int a, int b) {
        if (b == 0)
            return a;
        if (a == 0)
            return b;

        // Right shift a & b till their last bits equal to 1.
        int aZeros = Integer.numberOfTrailingZeros(a);
        int bZeros = Integer.numberOfTrailingZeros(b);
        a >>>= aZeros;
        b >>>= bZeros;

        int t = (aZeros < bZeros ? aZeros : bZeros);

        while (a != b) {
            if ((a+0x80000000) > (b+0x80000000)) {  // a > b as unsigned
                a =a- b;
                a =a>>> Integer.numberOfTrailingZeros(a);
            } else {
                b -= a;
                b >>>= Integer.numberOfTrailingZeros(b);
            }
        }
        return a<<t;
    }
    
}
