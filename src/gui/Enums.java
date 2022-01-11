package gui;

import javax.swing.ImageIcon;

public class Enums {
	public enum Pieces {
		WHITE_PIECE(new ImageIcon("./icons/whitePieceSmol.png")),
		WHITE_KING(new ImageIcon("./icons/whiteKingSmol.png")),
		BLACK_PIECE(new ImageIcon("./icons/blackPieceSmol.png")),
		BLACK_KING(new ImageIcon("./icons/blackKingSmol.png"));
		
		private ImageIcon icon;
		
		private Pieces(ImageIcon icon) {
			this.icon = icon;
		}
		
		public ImageIcon get() {
			return this.icon;
		}
	}
}
