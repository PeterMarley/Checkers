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
	 * The enumerated GamePiece[][] index modifiers for the 4 diagonal directions.
	 * Used to select the adjacent 4 diagonal pieces, moving from up left, clockwise through to down left.
	 * @author Peter Marley
	 * @StudentNum 13404067
	 * @email pmarley03@qub.ac.uk
	 * @GitHub BigJeffTheChef
	 *
	 */
	public enum Modifier {
		// calls to constructor
		UPLEFT(new int[] { 1, -1 }),
		UPRIGHT(new int[] { 1, 1 }),
		DOWNRIGHT(new int[] { -1, 1 }),
		DOWNLEFT(new int[] { -1, -1 });
		
		// actual modifiers
		private int[] modifiers;
		
		/**
		 * Constructor
		 * @param An int[2] containing index modifiers for the GamePiece[][] field of GameBoard.
		 */
		private Modifier(int[] modifiers) {
			this.modifiers = modifiers;
		}
		/**
		 * @return An int[2] containing index modifiers for the GamePiece[][] field of GameBoard.
		 * Used to select the adjacent 4 diagonal pieces, moving from up left, clockwise through to down left.
		 */
		public int[] get() {
			return this.modifiers;
		}

	}
}
