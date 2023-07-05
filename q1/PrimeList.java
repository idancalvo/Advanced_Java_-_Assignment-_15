/**Management of prime numbers list
*
*@author Idan Calvo
*@version 1.0
*/
import java.util.ArrayList;
import java.util.Collections;

public class PrimeList {

	private ArrayList<Integer> numbers; //array of prime numbers.
	private int maxnum; // Top boundary for checking numbers. 
	private int nextToCheck;// The next number that shall be checked.
	private int numOfPrime; // The amount of "Prime numbers" that were discovered.

	protected PrimeList(int maxnum) {
		this.maxnum = maxnum;
		this.numbers = new ArrayList<Integer>();
		this.numOfPrime = 0;
		this.nextToCheck = 1;
		
		 //adding number '2' to the list 
		//because I don't check even numbers, and '2' is an prime & even number. 
		this.numbers.add(2);
	}

	//management of number distribution. 
	public synchronized int getNumToCheak() {
		int ans;
		if (this.nextToCheck <= this.maxnum) {
			ans= this.nextToCheck;
			this.nextToCheck = this.nextToCheck + 2;	
		}
		else {
			ans = -1;
		}
		return ans;
	}

	//Check if 'num1' is a prime number.
	public void cheakNum(int num1) {
		boolean prime = true;
		int end = (int) Math.sqrt(num1);
		for (int i = 3; prime && i < end; i=i+2) {//Check only odd numbers.
			if (num1 % i == 0) {
				prime = false;
				break;
			}
		}
		if (prime) {
			addToArr(num1);
		}
	}

	//Adding 'num1' to the array of prime numbers.
	private synchronized void addToArr (int num1) {
		this.numbers.add(num1);
		this.numOfPrime++;
	}

	//sort the array.
	public void sort() {
		Collections.sort(this.numbers);
	}
	
	@Override
	public String toString() {
		return this.numbers.toString();
	}

	//Return string of numbers ('n' to 'm') from the prime numbers array.
	public String toString(int n, int m) {
		String str = "";
		for (int i = n; i < this.numbers.size() && i < m; i++) {
			str += this.numbers.get(i) + ",";
		}
		return str;
	}

	//Return amount of "Prime numbers" that were discovered.
	public int getNumOfPrime() {
		return this.numOfPrime;
	}

}
