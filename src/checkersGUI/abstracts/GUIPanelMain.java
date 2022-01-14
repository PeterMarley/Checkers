package checkersGUI.abstracts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import checkers.GamePiece;
import checkersGUI.Enums.Sizes;
import checkersGUI.GUISquareButton;

@SuppressWarnings("serial")
public abstract class GUIPanelMain extends JPanel {


	/**
	 * An abstract class for the main JPanel of GUI
	 */
	public GUIPanelMain() {
		int squareCount = Sizes.CENTER_PANEL_SQUARES.value();
		int length = Sizes.CENTER_PANEL_SIZE.value();
		this.setLayout(new GridLayout(squareCount, squareCount));
		this.setPreferredSize(new Dimension(length, length));
	}
}
