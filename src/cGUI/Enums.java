package cGUI;

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
		WHITE_PIECE(new ImageIcon("./images/whitePiece.png")),
		WHITE_KING(new ImageIcon("./images/whiteKing.png")),
		BLACK_PIECE(new ImageIcon("./images/blackPiece.png")),
		BLACK_KING(new ImageIcon("./images/blackKing.png")),
		FRAME(new ImageIcon("./images/checkersIcon.png"));

		private ImageIcon icon;

		private Icons(ImageIcon icon) {
			this.icon = icon;
		}

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
		TOP_PANEL_HEIGHT(50),
		LEFT_PANEL_WIDTH(50),
		BOTTOM_PANEL_HEIGHT(50),
		CENTER_PANEL_SIZE(800),
		CENTER_PANEL_SQUARES(8),
		SQUARE_BORDER_THICKNESS(1);

		private int size;

		private Sizes(int size) {
			this.size = size;
		}

		/**
		 * @return the integer value of this enumerated type
		 */
		public int value() {
			return this.size;
		}

		/**
		 * @return the width of this programs JFrame
		 */
		public static int getFrameWidth() {
			return CENTER_PANEL_SIZE.value() + LEFT_PANEL_WIDTH.value();
		}

		/**
		 * @return the height of this programs JFrame
		 */
		public static int getFrameHeight() {
			return CENTER_PANEL_SIZE.value() + TOP_PANEL_HEIGHT.value() + BOTTOM_PANEL_HEIGHT.value();
		}
	}

	public enum WinColors {
		DARKEST(Color.decode("#2E5266")),
		DARK(Color.decode("#6E8898")),
		MIDDLE(Color.decode("#9FB1BC")),
		LIGHT(Color.decode("#D3D0CB")),
		ACCENT(Color.decode("#E2C044"));

		private Color color;

		private WinColors(Color color) {
			this.color = color;
		}

		public Color get() {
			return this.color;
		}













	}
}
