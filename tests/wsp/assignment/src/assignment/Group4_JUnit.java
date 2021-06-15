package assignment;

import java.io.IOException;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Group4_JUnit {
	
	public class Base64Coder {
	    private  char[] map1 = new char[64]; // Mapping table from 6-bit nibbles to Base64 characters.
	    private byte[] map2 = new byte[128]; // Mapping table from Base64 characters to 6-bit nibbles.
	
	    public Base64Coder() {
	        int i = 0;
	        for (char c = 'A'; c <= 'Z'; c++) map1[i++] = c;
	        for (char c = 'a'; c <= 'z'; c++) map1[i++] = c;
	        for (char c = '0'; c <= '9'; c++) map1[i++] = c;
	        map1[i++] = '+';
	        map1[i] = '/';
	
	        for (int index = 0; index < map2.length; index++) {
	            map2[index] = -1;
	        }
	        for (int index = 0; index < 64; index++) {
	            map2[map1[index]] = (byte) index;
	        }
	    }
	
	    /**
	     * Encodes a string into Base64 format.
	     * No blanks or line breaks are inserted.
	     *
	     * @param s A String to be encoded.
	     * @return A String containing the Base64 encoded data.
	     */
	    public  String encodeString(String s) {
	        return new String(encode(s.getBytes(), 0, s.length()));
	    }
	
	    /**
	     * Encodes a byte array into Base64 format.
	     * No blanks or line breaks are inserted in the output.
	     *
	     * @param in   An array containing the data bytes to be encoded.
	     * @param iOff Offset of the first byte in <code>in</code> to be processed.
	     * @param iLen Number of bytes to process in <code>in</code>, starting at <code>iOff</code>.
	     * @return A character array containing the Base64 encoded data.
	     */
	    public char[] encode(byte[] in, int iOff, int iLen) {
	        int oDataLen = (iLen * 4 + 2) / 3;       // output length without padding
	        int oLen = ((iLen + 2) / 3) * 4;         // output length including padding
	        char[] out = new char[oLen];
	        int ip = iOff;
	        int iEnd = iOff + iLen;
	        int op = 0;
	        while (ip < iEnd) {
	            int i0 = in[ip++] & 0xff;
	            int i1 = ip < iEnd ? in[ip++] & 0xff : 0;
	            int i2 = ip < iEnd ? in[ip++] & 0xff : 0;
	            int o0 = i0 >>> 2;
	            int o1 = ((i0 & 3) << 4) | (i1 >>> 4);
	            int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
	            int o3 = i2 & 0x3F;
	            out[op++] = map1[o0];
	            out[op++] = map1[o1];
	            out[op] = op < oDataLen ? map1[o2] : '=';
	            op++;
	            out[op] = op < oDataLen ? map1[o3] : '=';
	            op++;
	        }
	        return out;
	    }
	
	    /**
	     * Decodes a string from Base64 format.
	     * No blanks or line breaks are allowed within the Base64 encoded input data.
	     *
	     * @param s A Base64 String to be decoded.
	     * @return A String containing the decoded data.
	     * @throws IllegalArgumentException If the input is not valid Base64 encoded data.
	     */
	    public  String decodeString(String s) {
	        return new String(decode(s.toCharArray(), 0, s.length()));
	    }
	
	    /**
	     * Decodes a byte array from Base64 format.
	     * No blanks or line breaks are allowed within the Base64 encoded input data.
	     *
	     * @param in   A character array containing the Base64 encoded data.
	     * @param iOff Offset of the first character in <code>in</code> to be processed.
	     * @param iLen Number of characters to process in <code>in</code>, starting at <code>iOff</code>.
	     * @return An array containing the decoded data bytes.
	     * @throws IllegalArgumentException If the input is not valid Base64 encoded data.
	     */
	    public byte[] decode(char[] in, int iOff, int iLen) {
	        if (iLen % 4 != 0)
	            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
	        while (iLen > 0 && in[iOff + iLen - 1] == '=') iLen--;
	        int oLen = (iLen * 3) / 4;
	        byte[] out = new byte[oLen];
	        int ip = iOff;
	        int iEnd = iOff + iLen;
	        int op = 0;
	        while (ip < iEnd) {
	            int i0 = in[ip++];
	            int i1 = in[ip++];
	            int i2 = ip < iEnd ? in[ip++] : 'A';
	            int i3 = ip < iEnd ? in[ip++] : 'A';
	            if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127)
	                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
	            int b0 = map2[i0];
	            int b1 = map2[i1];
	            int b2 = map2[i2];
	            int b3 = map2[i3];
	            if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0)
	                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
	            int o0 = (b0 << 2) | (b1 >>> 4);
	            int o1 = ((b1 & 0xf) << 4) | (b2 >>> 2);
	            int o2 = ((b2 & 3) << 6) | b3;
	            out[op++] = (byte) o0;
	            if (op < oLen) out[op++] = (byte) o1;
	            if (op < oLen) out[op++] = (byte) o2;
	        }
	        return out;
	    }
	}
	
	public class ReadClass {
		
		/******************************************************************
		 * STUBS
		 ******************************************************************/
		
		ObjectsClass Objects = new ObjectsClass();
		byte[] ExpectedRead = new byte[10];
		int ExpectedReadIndex;
		int ExpectedReadLen;
		
		/* Stub class Objects */
		private class ObjectsClass {
			
			public void checkFromIndexSize(int fromIndex, int size, int length) {
				// TODO Auto-generated method stub
			}
		}
		
		public void setExpectedRead(byte a[], int len)
		{
			for(int i = 0; i < len; ++i)
			{
				ExpectedRead[i] = a[i];
			}
			
			ExpectedReadLen = len;
			ExpectedReadIndex = 0;
		}
	
	    /* Stub function read */
		private int read() throws IOException {
			if(ExpectedReadLen > 0)
			{
				ExpectedReadLen--;
				return ExpectedRead[ExpectedReadIndex++];
			}
			else
			{
				return -1;
			}
		}
	
		/******************************************************************
		 * FUNCTION 5:
		 * https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/io/InputStream.Java
		 * Test Type: Statement Coverage
		 ******************************************************************/
	    public int read(byte b[], int off, int len) throws IOException {
	        Objects.checkFromIndexSize(off, len, b.length);
	        if (len == 0) {
	            return 0;
	        }
	
	        int c = read();
	        if (c == -1) {
	            return -1;
	        }
	        b[off] = (byte)c;
	
	        int i = 1;
	        try {
	            for (; i < len ; i++) {
	                c = read();
	                if (c == -1) {
	                    break;
	                }
	                b[off + i] = (byte)c;
	            }
	        } catch (IOException ee) {
	        }
	        return i;
	    }
	}

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
	
	public static class MathUtils extends BigInteger {


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
	
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("setUp");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("tearDown");
	}

	/******************************************************************
	 * FUNCTION 5:
	 * https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/io/InputStream.Java
	 * Test Type: Statement Coverage
	 ******************************************************************/
	@Test
	void test_1() {
		System.out.println("test_1");
		
		byte[] actual_A = new byte[10];
		byte[] expected_A = new byte[10];
		expected_A[0] = 'A';
		expected_A[1] = 'B';
		expected_A[2] = 'C';
		
		ReadClass c = new ReadClass();
		
		c.setExpectedRead(expected_A, 3);
		
		try {
			int ret = c.read(actual_A, 0, 3);
			
			assertEquals(ret, 3);
			
			for(int i = 0; i < actual_A.length; ++i)
			{
				assertTrue(actual_A[i] == expected_A[i]);
			}
			
		} catch (IOException e) {
			fail("test threw an exception");
		}
	}

	/******************************************************************
	 * FUNCTION 5:
	 * https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/io/InputStream.Java
	 * Test Type: Branch Coverage
	 ******************************************************************/
	@Test
	void test_2() {
		System.out.println("test_2");
		
		byte[] actual_A = new byte[10];
		byte[] expected_A = new byte[10];
		
		ReadClass c = new ReadClass();
		
		try {
			int ret = c.read(actual_A, 0, 0);
			
			assertEquals(ret, 0);
			
			for(int i = 0; i < actual_A.length; ++i)
			{
				assertTrue(actual_A[i] == expected_A[i]);
			}
			
		} catch (IOException e) {
			fail("test threw an exception");
		}
	}

	/******************************************************************
	 * FUNCTION 5:
	 * https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/io/InputStream.Java
	 * Test Type: Loop Boundary (Covers loop once)
	 ******************************************************************/
	@Test
	void test_3() {
		System.out.println("test_3");
		
		byte[] actual_A = new byte[10];
		byte[] expected_A = new byte[10];
		expected_A[0] = 'A';
		expected_A[1] = 'B';
		
		ReadClass c = new ReadClass();
		
		c.setExpectedRead(expected_A, 2);
		
		try {
			int ret = c.read(actual_A, 0, 2);
			
			assertEquals(ret, 2);
			
			for(int i = 0; i < actual_A.length; ++i)
			{
				assertTrue(actual_A[i] == expected_A[i]);
			}
			
		} catch (IOException e) {
			fail("test threw an exception");
		}
	}

	/******************************************************************
	 * FUNCTION 5:
	 * https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/io/InputStream.Java
	 * Test Type: Basis Path (stub read shall return -1 to trigger error in our read call)
	 ******************************************************************/
	@Test
	void test_4() {
		System.out.println("test_4");
		
		byte[] actual_A = new byte[10];
		byte[] expected_A = new byte[10];
		
		ReadClass c = new ReadClass();
		
		try {
			int ret = c.read(actual_A, 0, 3);
			
			assertEquals(ret, -1);
			
			for(int i = 0; i < actual_A.length; ++i)
			{
				assertTrue(actual_A[i] == expected_A[i]);
			}
			
		} catch (IOException e) {
			fail("test threw an exception");
		}
	}

	/******************************************************************
	 * FUNCTION 1:
	 * timesheet-master/src/main/java/timeSheet/util/properties/Base64Coder.java
	 * Test Type: Statement Coverage (covers all statements)
	 ******************************************************************/
	@Test
	void test_5() {
		System.out.println("test_5");
		
		byte[] in = new byte[]{'A', 'B', 'C'};
		byte[] expected_out = new byte[]{'Q', 'U', 'J', 'D'};
		
		Base64Coder c = new Base64Coder();
		
		char[] ret = c.encode(in, 0, 3);
		
		assertEquals(ret.length, 4);
		
		for(int i = 0; i < ret.length; ++i)
		{
			assertEquals(ret[i], expected_out[i]);
		}
	}

	/******************************************************************
	 * FUNCTION 1:
	 * timesheet-master/src/main/java/timeSheet/util/properties/Base64Coder.java
	 * Test Type: Branch Coverage (covers cases to generate padding i.e. = character in output)
	 ******************************************************************/
	@Test
	void test_6() {
		System.out.println("test_6");
		
		byte[] in = new byte[]{'A', 'B', 'C'};
		byte[] expected_out = new byte[]{'Q', 'Q', '=', '='};
		
		Base64Coder c = new Base64Coder();
		
		char[] ret = c.encode(in, 0, 1);
		
		assertEquals(ret.length, 4);
		
		for(int i = 0; i < ret.length; ++i)
		{
			assertEquals(ret[i], expected_out[i]);
		}
	}

	/******************************************************************
	 * FUNCTION 1:
	 * timesheet-master/src/main/java/timeSheet/util/properties/Base64Coder.java
	 * Test Type: Basis Path (skips loop completely)
	 ******************************************************************/
	@Test
	void test_7() {
		System.out.println("test_7");
		
		byte[] in = new byte[]{'A', 'B', 'C'};
		
		Base64Coder c = new Base64Coder();
		
		char[] ret = c.encode(in, 0, 0);
		
		assertEquals(ret.length, 0);
	}

	/******************************************************************
	 * FUNCTION 1:
	 * timesheet-master/src/main/java/timeSheet/util/properties/Base64Coder.java
	 * Test Type: Data flow (covers define use pairs for variables iLen, oLen and Op)
	 ******************************************************************/
	@Test
	void test_8() {
		System.out.println("test_8");
		
		byte[] in = new byte[]{'A', 'B', 'C', 'D', 'E', 'F'};
		byte[] expected_out = new byte[]{'Q', 'U', 'J', 'D', 'R', 'E', 'V', 'G'};
		
		Base64Coder c = new Base64Coder();
		
		char[] ret = c.encode(in, 0, 6);
		
		assertEquals(ret.length, 8);
		
		for(int i = 0; i < ret.length; ++i)
		{
			assertEquals(ret[i], expected_out[i]);
		}
	}

	/******************************************************************
	 * FUNCTION 4:
	 * timesheet-master/src/main/java/timeSheet/util/properties/Base64Coder.java
	 * Test Type: Boundary Interior (covers all possible paths within loop)
	 ******************************************************************/
	@Test
	void test_9() {
		System.out.println("test_9");
		
		{
			System.out.println("covers path A");
			
			char[] in = new char[]{'Q', 'U', 'J', 'D'};
			byte[] expected_out = new byte[]{'A', 'B', 'C'};
			
			Base64Coder c = new Base64Coder();
			
			byte[] ret = c.decode(in, 0, 4);
			
			assertEquals(ret.length, 3);
			
			for(int i = 0; i < ret.length; ++i)
			{
				assertEquals(ret[i], expected_out[i]);
			}
		}
		
		{
			System.out.println("covers path B");
			
			char[] in = new char[]{'Q', 'U', '=', '='};
			byte[] expected_out = new byte[]{'A'};
			
			Base64Coder c = new Base64Coder();
			
			byte[] ret = c.decode(in, 0, 4);
			
			assertEquals(ret.length, 1);
			
			for(int i = 0; i < ret.length; ++i)
			{
				assertEquals(ret[i], expected_out[i]);
			}
		}
		
		{
			System.out.println("covers path C");
			
			char[] in = new char[]{'Q', 'U', 'I', '='};
			byte[] expected_out = new byte[]{'A', 'B'};
			
			Base64Coder c = new Base64Coder();
			
			byte[] ret = c.decode(in, 0, 4);
			
			assertEquals(ret.length, 2);
			
			for(int i = 0; i < ret.length; ++i)
			{
				assertEquals(ret[i], expected_out[i]);
			}
		}
	}

	/******************************************************************
	 * FUNCTION 4:
	 * timesheet-master/src/main/java/timeSheet/util/properties/Base64Coder.java
	 * Test Type: Loop Boundary (Consider N=12 for loop. (Note that for valid input N-1 must be 8 and N+1 must be 16))
	 ******************************************************************/
	@Test
	void test_10() {
		System.out.println("test_10");
		
		{
			System.out.println("covers loop 0 times");
			
			char[] in = new char[]{'Q', 'U', 'J', 'D'};
			
			Base64Coder c = new Base64Coder();
			
			byte[] ret = c.decode(in, 0, 0);
			
			assertEquals(ret.length, 0);
		}
		
		{
			System.out.println("covers loop once");

			char[] in = new char[]{'Q', 'U', 'J', 'D'};
			byte[] expected_out = new byte[]{'A', 'B', 'C'};
			
			Base64Coder c = new Base64Coder();
			
			byte[] ret = c.decode(in, 0, 4);
			
			assertEquals(ret.length, 3);
			
			for(int i = 0; i < ret.length; ++i)
			{
				assertEquals(ret[i], expected_out[i]);
			}
		}
		
		{
			System.out.println("covers loop for N-1");

			char[] in = new char[]{'Q', 'U', 'J', 'D', 'R', 'E', 'U', '='};
			byte[] expected_out = new byte[]{'A', 'B', 'C', 'D', 'E'};
			
			Base64Coder c = new Base64Coder();
			
			byte[] ret = c.decode(in, 0, 8);
			
			assertEquals(ret.length, 5);
			
			for(int i = 0; i < ret.length; ++i)
			{
				assertEquals(ret[i], expected_out[i]);
			}
		}
		
		{
			System.out.println("covers loop for N");

			char[] in = new char[]{'Q', 'U', 'J', 'D', 'R', 'E', 'V', 'G', 'R', 'w', '=', '='};
			byte[] expected_out = new byte[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
			
			Base64Coder c = new Base64Coder();
			
			byte[] ret = c.decode(in, 0, 12);
			
			assertEquals(ret.length, 7);
			
			for(int i = 0; i < ret.length; ++i)
			{
				assertEquals(ret[i], expected_out[i]);
			}
		}
		
		{
			System.out.println("covers loop for N+1");

			char[] in = new char[]{'Q', 'U', 'J', 'D', 'R', 'E', 'V', 'G', 'R', '0', 'h', 'J', 'S', 'g', '=', '='};
			byte[] expected_out = new byte[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
			
			Base64Coder c = new Base64Coder();
			
			byte[] ret = c.decode(in, 0, 16);
			
			assertEquals(ret.length, 10);
			
			for(int i = 0; i < ret.length; ++i)
			{
				assertEquals(ret[i], expected_out[i]);
			}
		}
	}

	/******************************************************************
	 * FUNCTION 3:
	 * https://github.com/openjdk/jdk/tree/master/src/java.base/share/classes/java/math/MutableBigInteger.java
	 * Test Type: Branch Coverage
	 ******************************************************************/
	@Test
	// Covers B1853T 
	void testdivWord_1 (){
		MutableBigInteger M = new MutableBigInteger();
		long actual = M.divWord(16,1);
		long expected = 16;
		assertEquals(actual, expected);
	}
	
	/******************************************************************
	 * FUNCTION 3:
	 * https://github.com/openjdk/jdk/tree/master/src/java.base/share/classes/java/math/MutableBigInteger.java
	 * Test Type: Branch Coverage
	 ******************************************************************/
	@Test
	// Covers B1853F , B1864TF, B1864F
	void testdivWord_2 (){
		MutableBigInteger M = new MutableBigInteger();
		long actual = M.divWord(10,3);
		
		// workaround because this much long value is causing the error.
		Long lobject = new Long("4294967299");
		long expected = lobject.longValue();
		assertEquals(actual, expected);
	}
	
	/******************************************************************
	 * FUNCTION 3:
	 * https://github.com/openjdk/jdk/tree/master/src/java.base/share/classes/java/math/MutableBigInteger.java
	 * Test Type: Loop Boundary
	 ******************************************************************/
	@ParameterizedTest
	@CsvSource(value = { "10,5,2","5,3,8589934593", "20,3,8589934598","28,3,4294967305","32,3,8589934602"})
	// Test cases are only for the loop at line 1864.
	// Loop is skipped entirely, Loop is run once, 
	// Choose loop upper bound N=5
	// Loop is run 4 times, Loop is run 5 times, Loop is run 6 times
	void testdivWord_3 (long n, int d, long expected){
		MutableBigInteger M = new MutableBigInteger();
		long actual = M.divWord(n,d);
		
		assertEquals(actual, expected);
	}
	
	/******************************************************************
	 * FUNCTION 6:
	 * https://github.com/openjdk/jdk/tree/master/src/java.base/share/classes/java/math/MutableBigInteger.java
	 * Test Type: Branch Coverage, Statement Coverage
	 ******************************************************************/
	@ParameterizedTest
	@CsvSource(value = { "15,0,15","0,15,15","98,56,14","56,98,14" })
	// Covers B2047T, 
	// Covers B2049T, B2047F
	// Covers B2047F, B2049F, B2060TF, B2061T
	// Covers B2047F, B2049F, B2060TF, B2061F
	void testbinaryGCD_1 (int a, int b, int expected){
		MutableBigInteger M = new MutableBigInteger();
		
		int actual = M.binaryGcd(a,b);		
		assertEquals(actual, expected);
	}
	
	/******************************************************************
	 * FUNCTION 6:
	 * https://github.com/openjdk/jdk/tree/master/src/java.base/share/classes/java/math/MutableBigInteger.java
	 * Test Type: Loop Boundary, Basis Path, Data-Flow Testing (a,b,aZeros)
	 ******************************************************************/
	@ParameterizedTest
	@CsvSource(value = { "12,12,12", "4,2,2", "6,2,2", "10,2,2", "12,2,2", "14,2,2" })
	
	// Loop is skipped entirely, Loop is run only once, Loop is run twice,
	// Choose loop upper bound N = 5
	// Loop is run 4 times, Loop is run 5 times, Loop is run 6 times, 
	// 
	void testbinaryGCD_2 (int a, int b, int expected){
		MutableBigInteger M = new MutableBigInteger();
		
		int actual = M.binaryGcd(a,b);		
		assertEquals(actual, expected);
	}
	
	@Test
	void MathContextTest1() {
		byte[] ba1 = new byte[18];
		MathUtils mu = new MathUtils(ba1);
		assertThrows(NullPointerException.class, () -> mu.MathContext(null), "No value is being passed inside method");
	}

	@Test
	void MathContextTest2() {
		byte[] ba1 = new byte[18];
		MathUtils mu = new MathUtils(ba1);
		assertThrows(RuntimeException.class, () -> mu.MathContext("Second test function"), "Exception Raises");
	}
	
	@Test
	void MathContextTest3() {
		byte[] ba1 = new byte[18];
		MathUtils mu = new MathUtils(ba1);
		assertThrows(IllegalArgumentException.class, () -> mu.MathContext("Precision=Second test function"), "Exception Raises");
	}
	
	@Test
	void passesMillerRabinTest1() {
		byte[] ba1 = new byte[18];
		MathUtils mu = new MathUtils(ba1);
		Random rnd = new Random(12);
		assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
			assertTrue(mu.passesMillerRabin(0, rnd), "Should be true");
		});
	}

	@Test
	void passesMillerRabinTest2() {
		byte[] ba1 = new byte[18];
		MathUtils mu = new MathUtils(ba1);
		Random rnd = new Random();
		assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
			assertTrue(mu.passesMillerRabin(-1, rnd), "Should be true");
		});
	}
	
	@Test
	void passesMillerRabinTest3() {
		byte[] ba1 = new byte[18];
		MathUtils mu = new MathUtils(ba1);
		Random rnd = new Random();
		assertTrue(mu.passesMillerRabin(0, rnd), "Should be true");
	}

	@Test
	void passesMillerRabinTest4() {
		byte[] ba1 = new byte[18];
		MathUtils mu = new MathUtils(ba1);
		Random rnd = new Random();
		assertTrue(mu.passesMillerRabin(0, null), "Should be true");
	}
	
	@Test
	void subtractTest1() {
		byte[] ba1 = new byte[18];
		MathUtils mu = new MathUtils(ba1);
		int[] intArray = {10,20};
		int[] blankArray = {};
		int[] resultArray = {10,20};
		assertNotEquals(mu.subtract(10, intArray), resultArray);
	}
	
	@Test
	void subtractTest2() {
		byte[] ba1 = new byte[18];
		MathUtils mu = new MathUtils(ba1);
		int[] intArray = {10,20};
		int[] blankArray = {};
		int[] resultArray = {10,20};
		assertNotEquals(mu.subtract(0, intArray), resultArray);
	}
	
	@Test
	void subtractTest3() {
		byte[] ba1 = new byte[18];
		MathUtils mu = new MathUtils(ba1);
		int[] intArray = {10,20,30};
		int[] blankArray = {};
		int[] resultArray = {10,20};
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> mu.subtract(12, blankArray), "Exception Raises");
	}
		
}
