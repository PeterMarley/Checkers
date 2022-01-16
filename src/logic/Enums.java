package logic;

import java.awt.Color;

import javax.swing.ImageIcon;

/**
 * Enumerated types for Checkers Program
 * @author Peter Marley
 * @StudentNum 13404067
 * @email pmarley03@qub.ac.uk
 * @GitHub BigJeffTheChef
 *
 */
public class Enums {
	/**
	 * A set of icons for GUI display
	 * @author Peter Marley
	 * @StudentNum 13404067
	 * @email pmarley03@qub.ac.uk
	 * @GitHub BigJeffTheChef
	 *
	 */
	public enum Icons {
		// CONSTANTS CALLING CONSTRUCTORS
		WHITE_PIECE(new ImageIcon("./images/whitePiece.png")),
		WHITE_KING(new ImageIcon("./images/whiteKing.png")),
		BLACK_PIECE(new ImageIcon("./images/blackPiece.png")),
		BLACK_KING(new ImageIcon("./images/blackKing.png")),
		FRAME(new ImageIcon("./images/checkersIcon.png"));

		// FIELD
		private ImageIcon icon;

		// CONSTRUCTOR
		private Icons(ImageIcon icon) {
			this.icon = icon;
		}

		// GETTER
		/**
		 * @return An IconImage object for this enumerated type
		 */
		public ImageIcon get() {
			return this.icon;
		}
	}

	/**
	 * A set of enumerated sizes for GUI component sizing
	 * @author Peter Marley
	 * @StudentNum 13404067
	 * @email pmarley03@qub.ac.uk
	 * @GitHub BigJeffTheChef
	 *
	 */
	public enum Sizes {
		// CONSTANTS CALLING CONSTRUCTORS
		TOP_PANEL_HEIGHT(50),
		LEFT_PANEL_WIDTH(50),
		BOTTOM_PANEL_HEIGHT(50),
		CENTER_PANEL_SIZE(800),
		CENTER_PANEL_SQUARES(8),
		SQUARE_BORDER_THICKNESS(1);

		// FIELD
		private int size;
		
		// CONSTRUCTOR
		private Sizes(int size) {
			this.size = size;
		}

		// GETTER
		/**
		 * @return the integer value of this enumerated type
		 */
		public int get() {
			return this.size;
		}

		/**
		 * @return the width of this programs JFrame
		 */
		public static int getFrameWidth() {
			return CENTER_PANEL_SIZE.get() + LEFT_PANEL_WIDTH.get();
		}

		/**
		 * @return the height of this programs JFrame
		 */
		public static int getFrameHeight() {
			return CENTER_PANEL_SIZE.get() + TOP_PANEL_HEIGHT.get() + BOTTOM_PANEL_HEIGHT.get();
		}
	}

	/**
	 * A set of enumerated Color objects for setting GUI colours
	 * @author Peter Marley
	 * @StudentNum 13404067
	 * @email pmarley03@qub.ac.uk
	 * @GitHub BigJeffTheChef
	 *
	 */
	public enum WinColors {
		// CONSTANTS CALLING CONSTRUCTORS
		DARKEST(Color.decode("#2E5266")),
		DARK(Color.decode("#6E8898")),
		MIDDLE(Color.decode("#9FB1BC")),
		LIGHT(Color.decode("#D3D0CB")),
		ACCENT(Color.decode("#E2C044"));

		// FIELD
		private Color color;

		// CONSTRUCTOR
		private WinColors(Color color) {
			this.color = color;
		}

		// GETTER
		public Color get() {
			return this.color;
		}
	}
	
	/**
	 * The enumerated board setups
	 * @author Peter Marley
	 * @StudentNum 13404067
	 * @email pmarley03@qub.ac.uk
	 * @GitHub BigJeffTheChef
	 *
	 */
	public enum BoardSetup {
		// CONSTANTS
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
		// CONSTANTS CALLING CONSTRUCTORS
		UPLEFT(new int[] { 1, -1 }),
		UPRIGHT(new int[] { 1, 1 }),
		DOWNRIGHT(new int[] { -1, 1 }),
		DOWNLEFT(new int[] { -1, -1 });
		
		// FIELD
		private int[] modifiers;
		
		// CONSTRUCTOR
		private Modifier(int[] modifiers) {
			this.modifiers = modifiers;
		}
		
		// GETTER
		/**
		 * @return An int[2] containing index modifiers for the GamePiece[][] field of GameBoard.
		 * Used to select the adjacent 4 diagonal pieces, moving from up left, clockwise through to down left.
		 */
		public int[] get() {
			return this.modifiers;
		}

	}
}
