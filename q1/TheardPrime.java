/**threads to find prime numbers.
*
*@author Idan Calvo
*@version 1.0
*/
public class TheardPrime extends Thread {
	

	private int threadNum; //serial number of thread.
	private PrimeList primeList; //Management of prime numbers list
	
	
	public TheardPrime(int threadNum, PrimeList primeList) {
		this.threadNum = threadNum;
		this.primeList = primeList;
	}
	
	@Override
	public void run() {
		super.run();
		int tempNum = this.primeList.getNumToCheak();
		while (tempNum != -1) {
			this.primeList.cheakNum(tempNum);
//			System.out.println("thread:" + this.threadNum + " check:" + tempNum);
			tempNum = this.primeList.getNumToCheak();
		}
//		System.out.println("thread: " + this.threadNum +" done!");
	}
}
