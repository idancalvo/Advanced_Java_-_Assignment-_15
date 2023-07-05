
/**  "The problem of philosophers" with selecting the number of participants.
*
*@author Idan Calvo
*@version 1.0
*/
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class PhiloFrame extends JFrame {

	// Size variables
	private static final int WIDTH_WINDOW = 800; // (window)
	private static final int HEIGHT_WINDOS = 700;// (window)
	private static final int SMALL_RADIUS = 70;// (objects)
	private static final int BIG_RADIUS = 160;// (objects)

	// The circle (table) changes to an oval at a viewing angle.
	private static final double OVAL_FACTOR = (10.0 / 7.0);

//#Fields
	private int numOfParticipants;
	private Table table;
	private Philosopher[] philosophers;

//#Constructor

//#Constructor	
	public PhiloFrame(int numOfParticipants) {
		super();
		if (numOfParticipants >= 0) {
			this.numOfParticipants = numOfParticipants;
			this.setLayout(new BorderLayout());

			this.addTable();// add background

			// Calculating the locations of the objects
			double[][] LocationsRS = LocationManager(SMALL_RADIUS);
			double[][] LocationsRB = LocationManager(BIG_RADIUS);

			// Initialization and addition of the objects.
			Bowl[] bowls = this.addBowls(LocationsRS);
			Stick[] sticks = this.addstick(LocationsRS);
			this.addPhilo(bowls, sticks, LocationsRB);

			this.setTitle("Philosopher problem");
			this.setSize(WIDTH_WINDOW, HEIGHT_WINDOS);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
			startAllPhilo();// play
		}
	}

//#Methods

	// Initialization and addition of the sticks.

//#Methods
	// Initialization and addition of the Sticks.
	private Stick[] addstick(double[][] locationsRS) {
		Stick[] sticks = new Stick[numOfParticipants];
		for (int i = 0; i < sticks.length; i++) {
			Stick tempStick = new Stick(i);
			// (x,y) location on screen
			int x = (int) locationsRS[0][(2 * i) + 1] - tempStick.getWidth() / 2;
			int y = (int) locationsRS[1][(2 * i) + 1] - tempStick.getHeight() / 2;

			tempStick.setBounds(x, y, tempStick.getWidth(), tempStick.getHeight());
			sticks[i] = tempStick;
			this.table.add(tempStick);
		}
		return sticks;
	}

	// Initialization and addition of the Bowls.

	// Initialization and addition of the Bowls.
	private Bowl[] addBowls(double[][] locationsRS) {
		Bowl[] bowls = new Bowl[numOfParticipants];
		for (int i = 0; i < bowls.length; i++) {
			Bowl tempBowl = new Bowl();
			// (x,y) location on screen
			int x = (int) locationsRS[0][(2 * i)] - tempBowl.getWidth() / 2;
			int y = (int) locationsRS[1][(2 * i)] - tempBowl.getHeight() / 2;
			tempBowl.setBounds(x, y, tempBowl.getWidth(), tempBowl.getHeight());
			bowls[i] = tempBowl;
			this.table.add(tempBowl);
			tempBowl.bowlDo(0);
		}
		return bowls;
	}

	// Initialization and addition of the philosophers.

	// Initialization and addition of the Philosophers
	private void addPhilo(Bowl[] bowls, Stick[] sticks, double[][] locationsRB) {
		this.philosophers = new Philosopher[numOfParticipants];
		for (int i = 0; i < this.philosophers.length; i++) {
			Stick stickL = sticks[i];
			Stick stickR = sticks[(i - 1 + numOfParticipants) % (numOfParticipants)];
			Bowl bowl = bowls[i];
			Philosopher tempPhilo = new Philosopher(bowl, stickL, stickR);
			// (x,y) location on screen
			int x = (int) locationsRB[0][(2 * i)] - tempPhilo.getWidth() / 2;
			int y = (int) locationsRB[1][(2 * i)] - tempPhilo.getHeight() / 2;
			tempPhilo.setBounds(x, y, tempPhilo.getWidth(), tempPhilo.getHeight());
			this.philosophers[i] = tempPhilo;
			this.table.add(tempPhilo);
		}
	}
	// Initialization and addition of the background (Table)

	// Initialization and addition of the Table.
	private void addTable() {
		this.table = new Table();
		add(this.table, BorderLayout.CENTER);
		this.table.setLayout(null);

	}

	// Calculating the locations of the objects

	// Calculating the locations of the objects
	private double[][] LocationManager(int radius) {
		double[][] Locations = new double[2][numOfParticipants * 2];// (x,y) for obj locations
		double x0 = this.table.getWidth() / 2;
		double y0 = this.table.getHeight() / 2;
		double Slice = (2 * Math.PI) / (numOfParticipants * 2); // deviation angle

		for (int i = 0; i < numOfParticipants * 2; i++) {
			Locations[0][i] = (x0 + (radius * Math.cos((i + 1) * Slice) * OVAL_FACTOR));
			Locations[1][i] = (y0 + (radius * Math.sin((i + 1) * Slice)));
		}
		return Locations;
	}

	// Starting the philosophers actions

	private void startAllPhilo() {
		for (int i = 0; i < this.philosophers.length; i++) {
			Thread t = new Thread(this.philosophers[i]);
			t.start();
		}
	}

}
