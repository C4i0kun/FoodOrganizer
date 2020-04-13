package foodorganizer;

public abstract class StringManager {

	public static String reduceString(String stringToCopy, int initialCharIndex, int endingCharIndex) {
		int newStringSize = endingCharIndex - initialCharIndex + 1;
		char[] auxArray = new char[newStringSize];
		
		for (int i = 0; i < newStringSize; i++) {
			auxArray[i] = stringToCopy.charAt(initialCharIndex + i);
		}
		
		return new String(auxArray);
	}
	
	public static int findIndexOfChar(String string, char charToFind) {
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == charToFind) {
				return(i);
			}
		}
	return string.length();
	}
	
	public static boolean stringStartsWith(String bigger, String smaller) {
		try {
			for (int i = 0; i < smaller.length(); i++) {
				if (bigger.charAt(i) != smaller.charAt(i)) {
					return false;
				}
			}
			
			return true;	
		} catch (StringIndexOutOfBoundsException e) {
			return false;
		}
	}
}
