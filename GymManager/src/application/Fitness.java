package application;

/**
 * Fitness Class: Holds a valid fitness class.
 * @author JasmeanFernando
 */
public enum Fitness
{
	PILATES("PILATES"),
	CARDIO("CARDIO"),
	SPINNING("SPINNING");
	
	private final String fitnessClass;
	
	/**
	 * This method creates a Fitness object with corresponding class info.
	 * @param fitnessClass
	 */
	Fitness (String fitnessClass)
	{
		this.fitnessClass = fitnessClass;
	}
	
	/**
	 * This method concatenates fitnessClass into one string.
	 * @return String
	*/
	@Override
	public String toString() {
		return this.fitnessClass;
	}
	
	/**
	 * This method can compare this fitness to a fitness argument.
	 * @param className Fitness class being compared
	 * @return true if comparisons match, false otherwise
    */
	public boolean equals(String className) {
		if (this.fitnessClass.equalsIgnoreCase(className)) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method returns the fitness class.
	 * @return fitness
	*/
	public String getFitnessClass() { return this.fitnessClass; }
}