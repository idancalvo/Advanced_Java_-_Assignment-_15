/**finding all prime numbers up to 'm' with 'n' threads. 
*
*@author Idan Calvo
*@version 1.0
*/
public class FindPrime {
	final static int NUM_IN_LINE = 250; //The amount of prime numbers in print line. 
	final static int MAX_THREADS = 20; //The max threads that can be selected. 

	private PrimeList primeList; //Management of prime numbers list.
	private TheardPrime [] theards; // Array of threads.

	public FindPrime (int m, int n) {
		if (n > 0 && n < MAX_THREADS && m > 0) {
			this.primeList = new PrimeList(m);
			this.theards = new TheardPrime[n];
			createTheards(); //Creating threads
			startTheards(); //Starting threads.
			printPrime(NUM_IN_LINE);
		} else {
			System.out.println("m or n is illegal");
		}
	}

	//Creating threads.
	private void createTheards() {
		for (int i = 0; i < this.theards.length; i++) {
			this.theards[i] = new TheardPrime(i, this.primeList);
		}
	}
	
	//Starting threads.
	private void startTheards() {
		for (int i = 0; i < theards.length; i++) {
			this.theards[i].start();
		}
	}

	//print threads.
	private void printPrime(int numInLine) {
		try {
			for (int i = 0; i < this.theards.length; i++) {
				this.theards[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.primeList.sort();
		for (int i = 0; i * numInLine < this.primeList.getNumOfPrime(); i++) {
			String str = this.primeList.toString(i * numInLine, (i + 1) * numInLine);
			System.out.println(str);
		}
	}
	
	@Override
	public String toString() {
		return this.primeList.toString();
	}

}
