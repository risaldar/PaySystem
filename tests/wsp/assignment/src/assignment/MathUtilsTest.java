package app.test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import app.code.MathUtils;

class MathUtilsTest {

	byte[] ba1 = new byte[18];
	MathUtils mu = new MathUtils(ba1);
	
	@Test
	void MathContextTest() {
		assertThrows(NullPointerException.class, () -> mu.MathContext(null), "No value is being passed inside method");
		assertThrows(RuntimeException.class, () -> mu.MathContext("Second test function"), "Exception Raises");
		assertThrows(IllegalArgumentException.class, () -> mu.MathContext("Precision=Second test function"), "Exception Raises");
	}
	
	@Test
	void passesMillerRabinTest() {
		Random rnd = new Random();
		assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
			mu.passesMillerRabin(300, null);
		});
		assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
			assertTrue(mu.passesMillerRabin(300, rnd), "Should be true");
		});
		assertTrue(mu.passesMillerRabin(0, rnd), "Should be true");
		assertTrue(mu.passesMillerRabin(0, null), "Should be true");
	}
	
	@Test
	void subtractTest() {
		int[] intArray = {10,20,30};
		int[] blankArray = {};
		int[] resultArray = {10,20};
		assertArrayEquals(mu.subtract(10, intArray), resultArray);
		assertArrayEquals(mu.subtract(0, intArray), resultArray);
		assertArrayEquals(mu.subtract(12, blankArray), resultArray);
	}
	
}
