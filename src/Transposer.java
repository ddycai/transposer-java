import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Transposer.java
 * Contains methods for transposing text (chords/lyrics) from one
 * key to another.
 * @author duncan
 *
 */

public class Transposer {
	
	// From http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
	static public final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
	
	public static String transpose(String text, String currentKey, String newKey) {
		Key ck = Key.toKey(currentKey);
		Key nk = Key.toKey(newKey);
		if(ck == null || nk == null)
			throw new RuntimeException("Given key is not valid.");
		return transpose(text, ck, nk);
	}
	
	public static String transpose(String text, Key currentKey, Key newKey) {
		StringBuilder sb = new StringBuilder();
		Map<String, Chord> translation = new HashMap<>();
		String[] tokens = text.split(String.format(WITH_DELIMITER, "\\s+"));
		
		for(String token : tokens) {
			if(translation.containsKey(token)) {
				sb.append(translation.get(token).toString());
				continue;
			}
			Chord c = ChordFactory.createChord(token);
			if(c != null) {
				c.transpose(currentKey, newKey);
				translation.put(token, c);
				sb.append(c.toString());
			} else
				sb.append(token);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		if(args.length != 3) {
			System.out.println("Usage: filename current-key new-key");
			System.exit(0);
		}
		String text = new String(Files.readAllBytes(Paths.get(args[0])));
		System.out.println(Transposer.transpose(text, args[1], args[2]));
	}
	
}
