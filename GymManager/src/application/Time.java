package application;

/**
 * Time Class: Holds a valid fitness class time.
 * @author JasmeanFernando
 */
public enum Time
{
	MORNING(9, 30),
	AFTERNOON(14, 00),
	EVENING(18, 30);
	
	private final int hour;
	private final int minute;
	
	/**
	 * This method creates a Time object with corresponding time info.
	 * @param hour
	 * @param minute
	 */
	Time (int hour, int minute)
	{
		this.hour = hour;
		this.minute = minute;
	}
	
	/**
	 * This method concatenates hour and minute all into one string.
	 * @return String
	*/
	@Override
	public String toString() {
		return this.hour + ":" + this.minute;
	}
	
	/**
	 * This method can compare hour and minute to a Time object.
	 * @param time being compared
	 * @return true if comparisons match, false otherwise
    */
	public boolean equals(Time time) {
		if(this.hour == time.hour && this.minute== time.minute) {
			return true;
		}
		return false;
	}
}