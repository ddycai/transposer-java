/**
 * Enum for a musical key
 * Contains information on number of sharps/flats for each key
 * @author duncan
 *
 */
public enum Key {
	C(0, 0),
	F(1, 0),
	Bflat(2, 0),
	Eflat(3, 0),
	Aflat(4, 0),
	Dflat(5, 0),
	Gflat(6, 0),
	Cflat(7, 0),
	G(0, 1),
	D(0, 2),
	A(0, 3),
	E(0, 4),
	B(0, 5),
	Fsharp(0, 6),
	Csharp(0, 7);
	
	private int flats;
	private int sharps;
	
	Key(int flats, int sharps) {
		this.flats = flats;
		this.sharps = sharps;
	}
	
	public int flats() {
		return flats;
	}
	
	public int sharps() {
		return sharps;
	}
	
	public boolean isFlat() {
		return flats > 0;
	}
	
	public boolean isSharp() {
		return sharps > 0;
	}
	
	public static Key toKey(String symbol) {
		switch(symbol) {
		case "C":
			return Key.C;
		case "F":
			return Key.F;
		case "Bb":
			return Key.Bflat;
		case "Eb":
			return Key.Eflat;
		case "Ab":
			return Key.Aflat;
		case "Db":
			return Key.Dflat;
		case "Gb":
			return Key.Gflat;
		case "Cb":
			return Key.Cflat;
		case "G":
			return Key.G;
		case "A":
			return Key.A;
		case "E":
			return Key.E;
		case "B":
			return Key.B;
		case "F#":
			return Key.Fsharp;
		case "C#":
			return Key.Csharp;
		}
		return null;
	}
	
}
