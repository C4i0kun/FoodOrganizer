package foodorganizer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StringManagerTest {
	static String stringToTest;
	
	@BeforeAll
	static void preparation() {
		stringToTest = "abcdefghijk";
	}

	@Test
	void test1() {
		assertEquals("bcdefg", StringManager.reduceString(stringToTest, 1, 6));
	}

	@Test
	void test2() {
		assertEquals("abcde", StringManager.reduceString(stringToTest, 0, 4));
	}
	
	@Test
	void test3() {
		assertEquals("efgh", StringManager.reduceString(stringToTest, 4, 7));
	}
	
	@Test
	void test4() {
		assertEquals("defghijk", StringManager.reduceString(stringToTest, 3, 10));
	}
	
	@Test
	void test5() {
		assertEquals("ijk", StringManager.reduceString(stringToTest, 8, 10));
	}
}
