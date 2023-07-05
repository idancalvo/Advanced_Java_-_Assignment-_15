import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Philosopher extends JLabel implements Runnable {
	// Size variables
	private final static int WIDTH = 100;
	private final static int HEIGHT = 150;

	// The names of the images.
	private final static String IMG_NAME_EAT_PHILO = "eat_phil.png";
	private final static String IMG_NAME_THINK_PHILO = "think_phil.png";
	private final static String IMG_NAME_WAIT_PHILO = "wait_phil.png";

	// The amount of the image.
	private final static int NUM_TYPES_PHILOSOPHER = 3;

	// The actions of the philosophers
	public final static int EAT = 0;
	public final static int THINK = 1;
	public final static int WAIT = 2;

	// waiting time range.
	private final static int MIN_TIME_SLEEP = 500;
	private final static int MAX_TIME_SLEEP = 2500;

//#Fields
	private ImageIcon[] img;
	private Bowl bowl; // The bowl of the philosopher.
	private Stick stickL, stickR; // The stick of the philosopher.

//#Constructor
	public Philosopher(Bowl bowl, Stick stickL, Stick stickR) {
		super();
		if (bowl != null && stickL != null && stickR != null) {
			this.img = new ImageIcon[NUM_TYPES_PHILOSOPHER];
			this.setSize(WIDTH, HEIGHT);
			this.bowl = bowl;
			this.stickL = stickL;
			this.stickR = stickR;
			this.PhiloDo(WAIT);
		}
	}

//#Methods
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		if (width > 0 && height > 0) {
			addimags(IMG_NAME_EAT_PHILO, EAT);
			addimags(IMG_NAME_THINK_PHILO, THINK);
			addimags(IMG_NAME_WAIT_PHILO, WAIT);
		}
	}

	// Add image.
	private void addimags(String filename, int index) {
		ImageIcon icon = new ImageIcon(filename);
		Image image = icon.getImage();
		Image imgScale = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING);
		this.img[index] = new ImageIcon(imgScale);
	}

	// changing the philosopher status.
	private void PhiloDo(int indexAct) {
		switch (indexAct) {
		case EAT:
			this.setIcon(img[EAT]);
			break;
		case THINK:
			this.setIcon(img[THINK]);
			break;
		case WAIT:
			this.setIcon(img[WAIT]);
			break;
		}
	}

	@Override
	public void run() {
		if (img != null && bowl != null && stickL != null && stickR != null) {
			while (true) {
				wantThink();
				wantEat();
			}
		}
	}

	// The philosopher wants to think.
	private void wantThink() {
		PhiloDo(THINK);
		getSlepp();
		PhiloDo(WAIT);
	}

	// The philosopher wants to eat.
	private void wantEat() {

		// Asking to use the stick with the low serial number.
		if (this.stickL.getnum() < this.stickR.getnum()) {
			this.stickL.wantMe(); // Asking for using the left stick.
			this.bowl.bowlDo(1); // Updating the bowl status.
			this.stickR.wantMe(); // Asking for using the right stick.
			this.bowl.bowlDo(2); // Updating the bowl status.
		} else {
			this.stickR.wantMe(); // Asking for using the right stick.
			this.bowl.bowlDo(1); // Updating the bowl status.
			this.stickL.wantMe(); // Asking for using the left stick.
			this.bowl.bowlDo(2); // Updating the bowl status.
		}

		PhiloDo(EAT);
		getSlepp();
		PhiloDo(WAIT);
		this.bowl.bowlDo(0); // Updating the bowl status.
		this.stickL.freeMe();// Return the left stick to the table.
		this.stickR.freeMe();// Return the right stick to the table.
	}

	// The philosopher moves to standby mode (random time)
	private void getSlepp() {
		Random random = new Random();
		try {
			Thread.sleep(MIN_TIME_SLEEP + random.nextInt(MAX_TIME_SLEEP - MIN_TIME_SLEEP));
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

}
