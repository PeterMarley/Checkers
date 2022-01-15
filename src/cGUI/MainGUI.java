package cGUI;

import javax.swing.SwingUtilities;

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
public class MainGUI {

	public static GUIFrame window = new GUIFrame();
	public static GUIPanel_Intro intro;
	public static GUIPanel_PlayerNames playerNames;
	public static GUIPanel_Game game;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI();
			}
		});
	}

	public static void initGUI() {
		window.setVisible(true);
		
		intro = new GUIPanel_Intro();
		window.add(intro);
	}

	public static void showPanePlayerNames() {
		intro.setVisible(false);
		window.remove(intro);
		
		playerNames = new GUIPanel_PlayerNames();
		window.add(playerNames);
	}

	public static void showPaneGame() {
		playerNames.setVisible(false);
		window.remove(playerNames);
		
		game = new GUIPanel_Game();
		window.add(game);
	}

}
