package cGUI;

import javax.swing.SwingUtilities;

import cGUI.GUIObjects.GUIFrame;
import cGUI.GUIObjects.GUIPanel_Game;
import cGUI.GUIObjects.GUIPanel_Intro;


/**
 * GUI for my 2 player checkers program
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
public class GUIDriver {

	public static GUIFrame window = new GUIFrame();
	public static GUIPanel_Intro intro = new GUIPanel_Intro();
	public static GUIPanel_Game game = new GUIPanel_Game();

	public static void initGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				window.setVisible(true);
				initGUI_Intro();
			}
		});
	}

	public static void initGUI_Intro() {
		window.add(intro);
	}

	public static void initGUI_Game() {
		window.getContentPane().remove(intro);
		window.add(game);
		//frameMain.setVisible(true);
	}

}
