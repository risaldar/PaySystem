package assignment;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyTest {

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
	void test_read_1() {
		System.out.println("test_read_1");
		
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
	void test_read_2() {
		System.out.println("test_read_2");
		
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
	void test_read_3() {
		System.out.println("test_read_3");
		
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
	void test_read_4() {
		System.out.println("test_read_4");
		
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
	void test_read_5() {
		System.out.println("test_read_5");
		
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
	void test_read_6() {
		System.out.println("test_read_6");
		
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
	void test_read_7() {
		System.out.println("test_read_7");
		
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
	void test_read_8() {
		System.out.println("test_read_8");
		
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
	void test_read_9() {
		System.out.println("test_read_9");
		
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
	void test_read_10() {
		System.out.println("test_read_10");
		
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
}
