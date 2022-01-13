package gui;

import javax.swing.ImageIcon;

public class Enums {
	public enum Icons {
		WHITE_PIECE(new ImageIcon("./icons/whitePiece.png")),
		WHITE_KING(new ImageIcon("./icons/whiteKing.png")),
		BLACK_PIECE(new ImageIcon("./icons/blackPiece.png")),
		BLACK_KING(new ImageIcon("./icons/blackKing.png")),
		FRAME(new ImageIcon("./icons/checkersIcon.png"));
		
		private ImageIcon icon;
		
		private Icons(ImageIcon icon) {
			this.icon = icon;
		}
		
		public ImageIcon get() {
			return this.icon;
		}
	}
}
