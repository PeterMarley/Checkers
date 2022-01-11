package gui;

import javax.swing.ImageIcon;

public class Enums {
	public enum Pieces {
		WHITE_PIECE(new ImageIcon("./icons/whitePiece.png")),
		WHITE_KING(new ImageIcon("./icons/whiteKing.png")),
		BLACK_PIECE(new ImageIcon("./icons/blackPiece.png")),
		BLACK_KING(new ImageIcon("./icons/blackKing.png"));
		
		private ImageIcon icon;
		
		private Pieces(ImageIcon icon) {
			this.icon = icon;
		}
		
		public ImageIcon get() {
			return this.icon;
		}
	}
}
