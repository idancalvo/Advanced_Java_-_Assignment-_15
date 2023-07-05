
/** bowl of rice with 0,1 or 2 sticks.  
*
*@author Idan Calvo
*@version 1.0
*/
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bowl extends JLabel {
	// Size variables
	private final static int WIDTH = 50;
	private final static int HEIGHT = 45;

	// The names of the images.
	private final static String IMG_NAME_BOWL_2 = "bowl_2.png";
	private final static String IMG_NAME_BOWL_1 = "bowl_1.png";
	private final static String IMG_NAME_BOWL_0 = "bowl_0.png";

	// The amount of the image.
	private final static int NUM_TYPES_BOWL = 3;

//#Fields	
	private ImageIcon[] img;

//#Constructor
	public Bowl() {
		super();
		this.img = new ImageIcon[NUM_TYPES_BOWL];
		this.setSize(WIDTH, HEIGHT); // set size of images;

	}

//#Methods

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		if (width > 0 && height > 0) {
			addimags(IMG_NAME_BOWL_0, 0);
			addimags(IMG_NAME_BOWL_1, 1);
			addimags(IMG_NAME_BOWL_2, 2);
		}
	}

	// Add image to the array.
	private void addimags(String filename, int index) {
		ImageIcon icon = new ImageIcon(filename);
		Image image = icon.getImage();
		Image imgScale = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING);
		this.img[index] = new ImageIcon(imgScale);
	}

	// changing the bowl status.
	public void bowlDo(int numStick) {
		if (numStick < img.length && numStick >= 0) {
			this.setIcon(img[numStick]);
		}
	}

}
