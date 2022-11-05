package application;

import java.util.ArrayList;
import javafx.scene.control.TextArea;

/**
 * FitnessClass Class: Holds valid gym members and their classes.
 * @author JasmeanFernando
 */
public class FitnessClass
{
	private String instructor;
	private Location location;
	private Fitness fitness;
	private Time time;
	private ArrayList <Member> list;
	
	/**
	 * This method creates a FitnessClass object.
	 * @param instructor
	 * @param location
	 * @param fitness
	 * @param time
	 */
	public FitnessClass(String instructor, Location location, Fitness fitness, Time time)
    {
		this.instructor = instructor;
		this.location = location;
		this.fitness = fitness;
		this.time = time;
		list = new ArrayList <Member> ();
    }
	
	/**
	 * This method finds specific member.
	 * @param member Member to be searched
	 * @return true if member exists, false otherwise
	 */
	public boolean findMember(Member member) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(member)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
     * This method adds a member to the class.
     * @param member
    */
	public void add(Member member)
	{
		list.add(member);
	}
	
	/**
     * This method removes a member from the class.
     * @param member
    */
	public void remove(Member member)
	{
		list.remove(member);
	}
	
	/**
     * This method removes a guest from the class.
     * @param member
    */
	public void removeGuest(Member member)
	{
		for (int i = 0; i < list.size(); i++) {
			//find guest member
			if (list.get(i).equals(member)) {
				//removing premium guests
				if (list.get(i) instanceof Premium && ((Premium)list.get(i)).isGuest()) {
					list.remove(i);
				}
				
				//removing family guests
				if (list.get(i) instanceof Family && ((Family)list.get(i)).isGuest()) {
					list.remove(i);
				}
			}
		}
	}
	
	/**
     * This method returns instructor.
     * @return instructor
    */
	public String getInstructor() { return this.instructor; }
	
	/**
     * This method returns location.
     * @return location
    */
	public Location getLocation() { return this.location; }
	
	/**
     * This method returns fitness.
     * @return fitness
    */
	public Fitness getFitness() { return this.fitness; }
	
	/**
	 * This method returns time.
	 * @return time
	 */
	public Time getTime() { return this.time; }
	
	/**
     * This method prints the list of participants for specified class.
	 * @param homeDisplay 
     */
	public void printClass(TextArea homeDisplay) {
		homeDisplay.appendText("\n" + this.fitness + " - " + this.instructor + ", " + this.time + ", " + this.location.getCity());
		if (!list.isEmpty()) {
			this.printParticipants(homeDisplay);
		}
	}
	
	/**
     * This method prints the list of participants for specified class.
     * @param homeDisplay 
     */
	public void printParticipants(TextArea homeDisplay) {
		homeDisplay.appendText("\n\t- Participants -");
		for (int i = 0; i < list.size(); i++) {
			homeDisplay.appendText("\n\t\t" + list.get(i));
		}
	}
}