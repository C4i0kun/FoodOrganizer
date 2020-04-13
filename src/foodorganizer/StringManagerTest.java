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
	void testreduceString1() {
		assertEquals("bcdefg", StringManager.reduceString(stringToTest, 1, 6));
	}

	@Test
	void testreduceString2() {
		assertEquals("abcde", StringManager.reduceString(stringToTest, 0, 4));
	}
	
	@Test
	void testreduceString3() {
		assertEquals("efgh", StringManager.reduceString(stringToTest, 4, 7));
	}
	
	@Test
	void testreduceString4() {
		assertEquals("defghijk", StringManager.reduceString(stringToTest, 3, 10));
	}
	
	@Test
	void testreduceString5() {
		assertEquals("ijk", StringManager.reduceString(stringToTest, 8, 10));
	}
	
	@Test
	void testfindIndexOfChar1() {
		assertEquals(3, StringManager.findIndexOfChar(stringToTest, 'd'));
	}
	
	@Test
	void testfindIndexOfChar2() {
		assertEquals(0, StringManager.findIndexOfChar(stringToTest, 'a'));
	}

	@Test
	void testfindIndexOfChar3() {
		assertEquals(10, StringManager.findIndexOfChar(stringToTest, 'k'));
	}

	@Test
	void testfindIndexOfChar4() {
		assertEquals(4, StringManager.findIndexOfChar(stringToTest, 'e'));
	}

	@Test
	void testfindIndexOfChar5() {
		assertEquals(6, StringManager.findIndexOfChar(stringToTest, 'g'));
	}
	
	@Test
	void testStringStartsWith1() {
		assertEquals(true, StringManager.stringStartsWith(stringToTest, "abc"));
	}
	
	@Test
	void testStringStartsWith2() {
		assertEquals(true, StringManager.stringStartsWith(stringToTest, "abcdef"));
	}
	
	@Test
	void testStringStartsWith3() {
		assertEquals(false, StringManager.stringStartsWith(stringToTest, "abcdefghijklmn"));
		/* This function tests the case which an StringIndexOutOfBoundsException happens: smaller string shouldn't be "bigger" than the "bigger" string */
	}
}
