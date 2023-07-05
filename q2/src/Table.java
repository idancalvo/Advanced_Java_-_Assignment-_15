
/**Background panel with the table image. 
*
*@author Idan Calvo
*@version 1.0
*/

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Table extends JPanel {
	// Size variables
	private final static int WIDTH = 800;
	private final static int HEIGHT = 680;

	// Name of the table image.
	private final static String IMG_NAME_TABLE = "Table.png";

//#Fields
	private Image img;

//#Constructor
	public Table() {
		super();
		this.setSize(WIDTH, HEIGHT);// set size of the panel.
	}

//#Methods
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		addimags();// Add image to the panel

	}

	// Add image to the panel
	private void addimags() {
		ImageIcon icon = new ImageIcon(IMG_NAME_TABLE);
		Image image = icon.getImage();
		// panel size == image size;
		Image imgScale = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING);
		this.img = imgScale;
	}

	// painting the image on background
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.img, 0, 0, this);
	}

}
