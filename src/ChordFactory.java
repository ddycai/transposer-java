import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ChordFactory.java
 * This is a factory class for creating Chord objects from a String.
 * Since Strings are not always valid, we cannot just use a constructor
 * and pass in the String because the object is not guaranteed to be valid.
 * @author duncan
 *
 */

public class ChordFactory {

	// Regex for recognizing chords...
		public static Pattern chordPattern =
				Pattern.compile("^(?<chord>[A-G](#|b)?)(?<suffix>(\\(?(M|maj|major|m|min|minor|dim|sus|dom|aug|\\+|-|add)?\\d*\\)?)*)(\\/(?<bass>[A-G](#|b)?))?$");
	
	public static Chord createChord(String symbol) {
		String chord, suffix, bass;
		Matcher m = chordPattern.matcher(symbol);
		if(m.find()) {
			chord = m.group("chord");
			suffix = m.group("suffix");
			bass = m.group("bass");
			return new Chord(chord, suffix, bass);
		} else return null;
	}
	
}
