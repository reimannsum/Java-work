package util;

public class StringUtil {
	public static final String NEWLINE = System.getProperty("line.separator");
	
	private StringUtil() {}
	
	public static String appendNewLine(String text) {
		StringBuilder buffer = new StringBuilder();
		buffer.append(text);
		buffer.append(NEWLINE);
		return buffer.toString();
	}
}
