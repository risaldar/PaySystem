package app.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import app.code.MutableBigInteger;

public class MutableBigIntegerTests {

	@BeforeEach
	void beforeEachTestCase() {
		System.out.println("@BeforeEach: BeforeEachTestCase called");
	}
	
	@AfterEach
	void AfterEachTestCase() {
		System.out.println("@AfterEach: AfterEachTestCase called");
	}
	
	@BeforeAll
	public static void BeforeAllTestCase() {
		System.out.println("@BeforeAll: BeforeAllTestCase called");
	}
	
	@AfterAll
	public static void AfterAllTestCase() {
		System.out.println("@AfterAll: AfterAllTestCase called");
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
	
}
