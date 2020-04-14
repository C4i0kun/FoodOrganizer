package foodorganizer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class TXTWriterTest {
	String test = "Lets see if everything is being written correctly";
	
	@Test
	void test() {
		TXTWriter.write("TXTWriterTest.txt", this.test);
		
		FileReader file;
		try {
			file = new FileReader("TXTWriterTest.txt");
			BufferedReader bFile = new BufferedReader(file);
			
			String line = bFile.readLine();
			
			bFile.close();
			
			assertEquals(test, line);
		} catch (IOException e) {
			if (e instanceof FileNotFoundException) {
				fail("TXTWriterTest.txt nor found!");
			} else {
				fail("Error reading TXTWriterTest.txt");
			}
		}
	
	}

}
