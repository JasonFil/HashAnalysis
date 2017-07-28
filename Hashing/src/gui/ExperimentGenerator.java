package gui;

import java.util.LinkedList;
import java.util.Random;

import org.jfree.data.xy.XYSeries;

import hashing.*;


public class ExperimentGenerator {
	
	public void performExperiment(XYSeries insert, XYSeries searchSuccess, XYSeries searchfail, HashTable<Integer> table) {
		

		
		/*
		 * Determine how quickly the operations for insert and search are for each successive insertion
		 * 
		 * Perform an aggregate average over the different key and alpha values
		 */
		Random r = new Random();
		
		int[] inserttimes  = new int[100];
		int[] searchstimes = new int[100];
		int[] searchftimes = new int[100];
		
		for(int i = 0; i <= 20; i++) { //20 random trials
			double loadfactor = .5*(1-r.nextDouble()); //pick a random load factor each time
			
			
			for(int j = 0; j < 100; j++) { //each trial
				table = table.clear(loadfactor);
				LinkedList<Integer> inserted = new LinkedList<Integer>();
				
				for(int k = 0; k<=j; k++){ //insert j values
					System.out.println("("+i+", "+j+", "+k+")");
					Integer val = r.nextInt();
					if(val <= 0) {
						k--;
						continue;
					}
					
					table.insert(val);
					
					inserttimes[j] += table.numProbesOfLastCall();
					inserted.add(val);
					
					for(Integer integer : inserted) {
						table.search(integer); //will succeed
						searchstimes[j] += table.numProbesOfLastCall();
					}
					
					for(Integer integer : inserted) {
						table.search(-integer); //will not succeed
						searchftimes[j] += table.numProbesOfLastCall();
					}
				}

				
				
			}
			
		}
		
		for(int i = 0; i < 100; i++) {
			insert.add(i, inserttimes[i]/20);
			searchSuccess.add(i, searchstimes[i]/20);
			searchfail.add(i, searchftimes[i]/20);
		}
		
	}


}
