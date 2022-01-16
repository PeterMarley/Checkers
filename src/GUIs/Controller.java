package GUIs;

import javax.swing.SwingUtilities;

import logic.Tools;

/**
 * GUI for my 2 player checkers program
 * 
 * @Attributions - Checkers Pieces Images*:<br>
 *               https://woodexpressions.com/wp-content/uploads/2016/07/258015Main2DB.jpg<br>
 *               <br>
 *               - Checker Board Intro Image:<br>
 *               https://content.mycutegraphics.com/graphics/play/checker-game-clip-art.png<br>
 *               <br>
 *               * Images edited by me to give them the correct size and background transparency on GIMP
 * 
 * @author Peter Marley
 * @StudentNum 13404067
 * @email pmarley03@qub.ac.uk
 * @GitHub BigJeffTheChef
 *
 */
public class Controller {

	public static JFrame_GUI frame = new JFrame_GUI();
	public static JPanel_Intro intro;
	public static JPanel_PlayerNames playerNames;
	public static JPanel_Game game;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI();
			}
		});
	}

	public static void initGUI() {
		frame.setVisible(true);
		intro = new JPanel_Intro();
		frame.add(intro);
		if (Tools.SKIP_INTRO) {
			showPanePlayerNames();
		}
	}

	public static void showPanePlayerNames() {
		intro.setVisible(false);
		frame.remove(intro);
		if (Tools.SKIP_INTRO) {
			playerNames = new JPanel_PlayerNames("Test Name 1", "Test Name 2");
			showPaneGame();
		} else {
			playerNames = new JPanel_PlayerNames();
			frame.add(playerNames);
		}

	}

	public static void showPaneGame() {
		if (!Tools.SKIP_INTRO) {
			playerNames.setVisible(false);
			frame.remove(playerNames);
		}

		game = new JPanel_Game();
		frame.add(game);
	}
	
	public static void redrawPaneGame() {
		game.setVisible(false);
		frame.remove(game);
		Tools.clearMemory();
		game = new JPanel_Game();
		frame.add(game);
		game.setVisible(true);
	}

}
