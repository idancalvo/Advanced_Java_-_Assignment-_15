/**   
*
*@author Idan Calvo
*@version 1.0
*/
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Stick extends JLabel {
	// Size variables
	private final static int WIDTH = 30;
	private final static int HEIGHT = 30;
	
	// Name of the stick image.
	private final static String IMG_NAME_STICK = "stick.png";

//#Fields	
	private ImageIcon img;
	private boolean inUse; //the stick is in use.
	private int stickNum; //serial number of stick.

//#Constructor
	public Stick(int stickNum) {
		super();
		this.setSize(WIDTH, HEIGHT);
		this.inUse = false;
		doExists(true);
		this.stickNum = stickNum;
	}

//#Methods
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		if (width > 0 && height > 0) {
			addimags();
		}
	}

	// Add image.
	private void addimags() {
		ImageIcon icon = new ImageIcon(IMG_NAME_STICK);
		Image image = icon.getImage();
		Image imgScale = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING);
		this.img = new ImageIcon(imgScale);
	}

	//the appearance and disappearance of the stick.
	private void doExists(boolean x) {
		if (x) {
			this.setIcon(img);
		} else {
			this.setIcon(null);
		}
	}

	//Asking for using the stick. 
	public synchronized void wantMe() {
		while (this.inUse) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.inUse = true;
		doExists(false);
	}

	//Return the stick to the table.
	public synchronized void freeMe() {
		this.inUse = false;
		doExists(true);
		notifyAll();
	}
	
	//Return serial number of stick
	public int getnum() {
		return this.stickNum;
	}

}
