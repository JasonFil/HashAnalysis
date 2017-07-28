
import java.util.Scanner;
import hashing.*;

public class Main {
	public static void main(String[] args) {
		QuadraticHashing<Integer> linear = new QuadraticHashing<Integer>(.5);

		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("insert x, search x, delete x");
			String[] input = sc.nextLine().split(" ");
			switch(input[0]) {
			case "insert" : System.out.println("Insertion of "+input[1]+", result: "+linear.insert(Integer.valueOf(input[1])));
			break;case "search" : System.out.println("Search of "+input[1]+", result: "+linear.search(Integer.valueOf(input[1])));
			}
			
			
			System.out.println("After "+input[0]+": "+linear+"\nSize: "+linear.size()+"\nProbes: "+linear.numProbesOfLastCall());
		}

	}
}
