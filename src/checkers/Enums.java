package checkers;

/**
 * Various Enums for Checkers
 * @author Peter Marley
 * @StudentNum 13404067
 * @email pmarley03@qub.ac.uk
 * @GitHub BigJeffTheChef
 *
 */
public class Enums {

	/**
	 * The enumerated board setups
	 * @author Peter Marley
	 * @StudentNum 13404067
	 * @email pmarley03@qub.ac.uk
	 * @GitHub BigJeffTheChef
	 *
	 */
	public enum BoardSetup {
		STANDARD,
		TEST,
		JUMPING1,
		JUMPING2,
		KINGJUMP,
		MULTIPLEJUMPS1,
		MULTIPLEJUMPS2,
		MULTIPLEJUMPSAGAINSTEDGE,
		BLACKATTACKEDGE,
		KINGDIRECTIONALATTACK,
		CS50DEMO,
		VALIDMOVECHECK,
		VALIDMOVECHECK2,
		VALIDMOVECHECK3;
	}

	/**
	 * The enumerated GamePiece[][] index modifiers for the 4 diagonal directions
	 * @author Peter Marley
	 * @StudentNum 13404067
	 * @email pmarley03@qub.ac.uk
	 * @GitHub BigJeffTheChef
	 *
	 */
	public enum Modifier {
		UPLEFT {
			@Override
			public int[] get() {
				return new int[] { 1, -1 };
			}
		},
		UPRIGHT {
			@Override
			public int[] get() {
				return new int[] { 1, 1 };
			}
		},
		DOWNRIGHT {
			@Override
			public int[] get() {
				return new int[] { -1, 1 };
			}
		},
		DOWNLEFT {
			@Override
			public int[] get() {
				return new int[] { -1, -1 };
			}
		};

		public int[] get() { // almost like an abstract method, overrider above inside the Modifier initialiser(?)
			return null;
		}

	}

	public static Modifier[] getModifiers() {
		return new Modifier[] { Modifier.UPLEFT, Modifier.UPRIGHT, Modifier.DOWNRIGHT, Modifier.DOWNLEFT };
	}
}
