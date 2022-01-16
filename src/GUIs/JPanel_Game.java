package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.lang.ModuleLayer.Controller;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.TestVariables;
import logic.Enums.Sizes;
import logic.Enums.WinColors;
import logic.Main;

/**
 * Creates a JPanel instance which is used to house the main games play window - where the game is played
 * @author Peter Marley
 * @StudentNum 13404067
 * @email pmarley03@qub.ac.uk
 * @GitHub BigJeffTheChef
 *
 */
@SuppressWarnings("serial")
public class JPanel_Game extends JPanel {
	JPanel centrePanel;

	/**
	 * A constructor for the main game display JPanel of GUI
	 */
	public JPanel_Game() {
		super();
		int squareCount = Sizes.CENTER_PANEL_SQUARES.value();
		int length = Sizes.CENTER_PANEL_SIZE.value();
		//this.setLayout(new GridLayout(squareCount, squareCount));
		this.setLayout(new BorderLayout());
		//this.setPreferredSize(new Dimension(length, length));


		// create panels
		JPanel topPanel, leftPanel, bottomPanel;
		//GUIPanel_Game_Squares centrePanel;
		Color bgColor = Color.LIGHT_GRAY;

		topPanel = getPanelTop();
		leftPanel = new JPanel();
		bottomPanel = new JPanel();
		centrePanel = new JPanel_Game_Squares(); // the game board itself

		// set panel sizes
		topPanel.setPreferredSize(new Dimension(1, Sizes.TOP_PANEL_HEIGHT.value()));		// if you use .setSize it doesn't work? says:
		leftPanel.setPreferredSize(new Dimension(Sizes.LEFT_PANEL_WIDTH.value(), 1));		// "This method changes layout-related information, and therefore, invalidates the component hierarchy."
		bottomPanel.setPreferredSize(new Dimension(1, Sizes.BOTTOM_PANEL_HEIGHT.value()));	// in the tool tip

		// set panel backgrounds
		//		topPanel.setBackground(bgColor);
		//		leftPanel.setBackground(bgColor);
		//		bottomPanel.setBackground(bgColor);
		//		centrePanel.setBackground(bgColor);
		topPanel.setBackground(Color.RED);
		leftPanel.setBackground(Color.GREEN);
		bottomPanel.setBackground(Color.BLUE);
		centrePanel.setBackground(Color.MAGENTA);

		// add components
		this.add(topPanel, BorderLayout.NORTH);
		this.add(leftPanel, BorderLayout.WEST);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(centrePanel, BorderLayout.CENTER);
	}

	private JPanel getPanelTop() {
		String[] names = Main.getPlayerNames();
		JPanel panel = new JPanel(new GridLayout(1, 2));
		JLabel player1 = new JLabel("Player 1: " + names[0]);
		JLabel player2 = new JLabel("Player 2: " + names[1]);
		player1.setVerticalAlignment(JLabel.CENTER);
		player1.setHorizontalAlignment(JLabel.CENTER);
		player2.setVerticalAlignment(JLabel.CENTER);
		player2.setHorizontalAlignment(JLabel.CENTER);
		//player1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		player1.setBackground(WinColors.DARK.get());
		player1.setForeground(WinColors.ACCENT.get());
		player1.setOpaque(true);
		panel.add(player1);
		panel.add(player2);
		return panel;
	}

}
