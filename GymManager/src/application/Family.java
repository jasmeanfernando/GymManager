package application;

/**
 * Family Class: Holds a valid gym member with a family membership.
 * @author JasmeanFernando
*/
public class Family extends Member
{
	private int familyPasses = 1;
	boolean guestStatus = false;
	
	/**
	 * This method creates a Family member object.
	 * @param fname
	 * @param lname
	 * @param dob
	 * @param expire
	 * @param location
	 */
	public Family(String fname, String lname, Date dob, Date expire, Location location)
	{
		super(fname, lname, dob, expire, location);
	}
	
	/**
	 * This method concatenates first and last name, dob, expiration, and location all into one string.
	 * @return String
	*/
	@Override
	public String toString() {
		return this.getFName() + " " + this.getLName() + ", DOB: " + this.getDOB() + ", Membership expires " + this.getExpire() + ", Location: " + this.getLocation() + ", (Family) guest-pass remaining: " + this.familyPasses;
	}
	
	/**
	 * This method returns the standard membership fee for family member.
	 * @return standard fee
    */
	@Override
	public double membershipFee() {
		double fee = 209.96;
		return fee;
	}
	
	/**
	 * This method decrements guestPasses.
	 * @return true if pass is used, false otherwise
    */
	public boolean useFamilyPass() {
		if (this.familyPasses == 0) {
			return false;
		}
		this.familyPasses--;
		return true;
	}
	
	/**
	 * This method increments guestPasses.
	 * @return true if pass is returned, false otherwise
    */
	public boolean returnFamilyPass() {
		if (this.familyPasses == 1) {
			return false;
		}
		this.familyPasses++;
		return true;
	}
	
	/**
	 * This method changes guestStatus.
	 */
	public void newGuestStatus() { this.guestStatus = true; }
	
	/**
	 * This method returns guestStatus.
	 * @return guestStatus
    */
	public boolean isGuest() { return this.guestStatus; }
}
