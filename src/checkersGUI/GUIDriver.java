package checkersGUI;

import javax.swing.SwingUtilities;

import checkersGUI.abstracts.GUIFrame;

/**
 * GUI for my 2 player checkers program
 * @Attributions - Checkers Pieces Images*:<br>https://woodexpressions.com/wp-content/uploads/2016/07/258015Main2DB.jpg<br><br>
 *               - Checker Board Intro Image:<br>https://content.mycutegraphics.com/graphics/play/checker-game-clip-art.png<br>
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

	public static GUIFrame frameIntro = new GUIFrameIntro();
	public static GUIFrame frameMain = new GUIFrameMain();
	
	public static void initGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI_Intro();
			}
		});
	}

	public static void initGUI_Intro() {
		frameIntro.setVisible(true);
	}

	public static void initGUI_Game() {
		frameIntro.dispose();
		frameMain.setVisible(true);
	}
	
}
