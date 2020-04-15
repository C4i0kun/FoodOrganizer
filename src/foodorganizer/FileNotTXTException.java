package foodorganizer;

import java.io.IOException;

public class FileNotTXTException extends IOException {
	private static final long serialVersionUID = 1L;

	public FileNotTXTException(String fileName, String line) {
		super();
		System.out.println("Error in '" + fileName + "'. Recipe '" + line + "' is not a .txt file");
	}

}
