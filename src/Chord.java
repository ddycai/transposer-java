import java.util.ArrayList;
import java.util.Arrays;

/**
 * Chord.java
 * This class represents a Chord consisting of the underlying chord,
 * the suffix (indicating the type of chord: major, minor, dim etc.)
 * and the bass note.
 * @author duncan
 *
 */

public class Chord {
	
	public static final ArrayList<String> flats;
	public static final ArrayList<String> sharps;
	public static final ArrayList<Key> keys;
	
	// The data is hardcoded...
	static {
		String[] flatlist = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "Cb"};
		String[] sharplist = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
		Key[] keylist = {Key.C, Key.Dflat, Key.D, Key.Eflat, Key.E, Key.F, Key.Gflat, Key.G, Key.Aflat, Key.A, Key.Bflat, Key.B};
		flats = new ArrayList<>(Arrays.asList(flatlist));
		sharps = new ArrayList<>(Arrays.asList(sharplist));
		keys = new ArrayList<>(Arrays.asList(keylist));
	}
	
	private String chord, suffix, bass; 
	
	Chord(String chord, String suffix, String bass) {
		this.chord = chord == null ? "" : chord;
		this.suffix = suffix == null ? "" : suffix;
		this.bass = bass == null ? "" : bass;
	}
	
	public void transpose(Key currentKey, Key newKey) {
		chord = transposeNote(chord, currentKey, newKey);
		if(!bass.isEmpty())
			bass = transposeNote(bass, currentKey, newKey);
	}
	
	public static String transposeNote(String note, Key currentKey, Key newKey) {
		int steps = keys.indexOf(newKey) - keys.indexOf(currentKey);
		int i = -1;
		if(flats.contains(note))
			i = flats.indexOf(note);
		else
			i = sharps.indexOf(note);
		if(i == -1)
			throw new RuntimeException("Transposition failed. Note (" + note + ") is invalid.");
		if(newKey.isFlat())
			return flats.get((i + steps + flats.size()) % flats.size());
		else
			return sharps.get((i + steps + flats.size()) % sharps.size());
	}
	
	public String toString() {
		if(bass.isEmpty())
			return String.format("%s%s", chord, suffix);
		else
			return String.format("%s%s/%s", chord, suffix, bass);
	}
}
