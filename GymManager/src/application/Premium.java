package application;

/**
 * Premium Class: Holds a valid gym member with a premium membership.
 * @author JasmeanFernando
*/
public class Premium extends Family
{
	private int premiumPasses = 3;
	boolean guestStatus = false;
	
	/**
	 * This method creates a Premium member object.
	 * @param fname
	 * @param lname
	 * @param dob
	 * @param expire
	 * @param location
	 */
	public Premium(String fname, String lname, Date dob, Date expire, Location location)
	{
		super(fname, lname, dob, expire, location);
	}
	
	/**
	 * This method concatenates first and last name, dob, expiration, and location all into one string.
	 * @return String
	*/
	@Override
	public String toString() {
		return this.getFName() + " " + this.getLName() + ", DOB: " + this.getDOB() + ", Membership expires " + this.getExpire() + ", Location: " + this.getLocation() + ", (Premium) guest-pass remaining: " + this.premiumPasses;
	}
	
	/**
	 * This method returns the standard membership fee for family member.
	 * @return standard fee
    */
	@Override
	public double membershipFee() {
		double fee = 659.89;
		return fee;
	}
	
	/**
	 * This method decrements guestPasses.
	 * @return true if pass is used, false otherwise
    */
	public boolean usePremiumPass() {
		if (this.premiumPasses == 0) {
			return false;
		}
		this.premiumPasses--;
		return true;
	}
	
	/**
	 * This method increments guestPasses.
	 * @return true if pass is returned, false otherwise
    */
	public boolean returnPremiumPass() {
		if (this.premiumPasses == 3) {
			return false;
		}
		this.premiumPasses++;
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
