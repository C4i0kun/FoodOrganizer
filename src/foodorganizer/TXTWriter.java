package foodorganizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public abstract class TXTWriter {
	public static void write(String fileName, String text) {
		File file = new File(fileName);
		
		try {
			if(Files.deleteIfExists(file.toPath())) {
				System.out.println("Overriding already existing file " + fileName);
			}
		} catch (IOException e1) {
			System.out.println("Something went wrong when overriding the file " + fileName);
			return;
		}
		
		
		try {
			FileWriter fileWriter = new FileWriter(file, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
	    
			printWriter.printf("%s", text);
	    
			printWriter.close();
		} catch (IOException e1) {
			System.out.println("Something went wrong when writing the file " + fileName);
		}
	}
}
