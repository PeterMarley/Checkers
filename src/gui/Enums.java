package gui;

import javax.swing.ImageIcon;

public class Enums {
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

		public ImageIcon get() {
			return this.icon;
		}
	}
	
}
