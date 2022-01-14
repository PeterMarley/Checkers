package checkersGUI;

import javax.swing.SwingUtilities;

import checkersGUI.abstracts.GUIFrame;

/**
 * GUI for my 2 player checkers program
 * @Attributions Images: https://woodexpressions.com/wp-content/uploads/2016/07/258015Main2DB.jpg<br>
 *               Images edited by me to give them the correct size and background transparency on GIMP
 * @author Peter Marley
 * @StudentNum 13404067
 * @email pmarley03@qub.ac.uk
 * @GitHub BigJeffTheChef
 *
 */
public class GUI {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI();
			}
		});
	}

	public static void initGUI() {

		GUIFrame frameMain = new GUIFrameMain();
		GUIFrame frameIntro = new GUIFrameIntro();

		frameMain.setVisible(true);
		frameIntro.setVisible(false);

	}

}
