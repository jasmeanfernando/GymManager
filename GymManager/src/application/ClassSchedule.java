package application;

import javafx.scene.control.TextArea;

/**
 * ClassSchedule Class: Holds different classes hosted at different gym locations.
 * @author JasmeanFernando
 */
public class ClassSchedule
{
	private FitnessClass[] classes;
	private int numClasses; //tracks capacity
	
	/**
	 * This method creates a ClassSchedule object.
	*/
	public ClassSchedule()
    {
		classes = new FitnessClass[4];
		numClasses = 0;
    }
	
	/**
     * This method grows the classes array by size +4 every time the array is full.
    */
	private void grow()
	{
		FitnessClass[] newlist = new FitnessClass [numClasses+4];
		
		for (int i = 0; i < classes.length; i++) {
			newlist[i] = classes[i];
		}
		
		classes = newlist;
	}
	
	/**
	 * This method adds a class to the schedule.
	 * @param newClass
	 */
	public void addClass(FitnessClass newClass) {
		//capacity reached -> allocation
		if (numClasses%4 == 0) { grow(); }
		
		//add
		classes[numClasses] = newClass;
		numClasses++;
	}
	
	/**
	 * This method returns class at specific index.
	 * @return
	 */
	public FitnessClass getClass(int index) {
		return classes[index];
	}
	
	/**
	 * This method returns number of classes in schedule.
	 * @return
	 */
	public int getSize() {
		return numClasses;
	}
	
	/**
     * This method prints the list of participants for all classes.
	 * @param homeDisplay 
     */
	public void printSchedule(TextArea homeDisplay) {
		//schedule is empty
		if (numClasses == 0) {
			homeDisplay.appendText("\nFitness class schedule is empty.");
			return;
		}
		
		homeDisplay.appendText("\n-Fitness classes-");
		for (int i = 0; i < numClasses; i++) {
			classes[i].printClass(homeDisplay);
		}
		homeDisplay.appendText("\n-End of list.-");
	}
}