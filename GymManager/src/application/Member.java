package application;

import java.util.StringTokenizer;

/**
 * Member Class: Holds a valid gym member with a standard membership.
 * @author JasmeanFernando
*/
public class Member implements Comparable<Member>
{
	private String fname;
	private String lname;
	private Date dob;
	private Date expire;
	private Location location;
	
	/**
	 * This method creates a Member object.
	 * @param fname
	 * @param lname
	 * @param dob
	 * @param expire
	 * @param location
	 */
	public Member(String fname, String lname, Date dob, Date expire, Location location)
	{
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.expire = expire;
		this.location = location;
	}
	
	/**
	 * This method concatenates first and last name, dob, expiration, and location all into one string.
	 * @return String
	*/
	@Override
	public String toString() {
		return this.fname + " " + this.lname + ", DOB: " + this.dob + ", Membership expires " + this.expire + ", Location: " + this.location;
	}
	
	/**
	 * This method can compare two members.
	 * @param obj casted into a member being compared
	 * @return true if comparisons match, false otherwise
    */
	@Override
	public boolean equals(Object obj) {
		Member member = (Member)obj; //type casting
		if (this.fname.equalsIgnoreCase(member.getFName()) &&
			this.lname.equalsIgnoreCase(member.getLName()) &&
			this.getDOB().compareTo(member.getDOB()) == 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * This method compares members.
	 * @param member Member object being to be compared
	 * @return 0 if first name and last name matches member argument,
	 * 		  -1 if first name/last name is earlier than member argument,
	 * 		   1 if first name/last name is later than member argument
    */
	@Override
	public int compareTo(Member member)
	{
		String thisLast = this.lname;
		String thisFirst = this.fname;
		String memLast = member.lname;
		String memFirst = member.fname;
		
		//same last name
		if (thisLast.equalsIgnoreCase(memLast)) {
			if (thisFirst.equalsIgnoreCase(memFirst)) {
				return 0;
			}
			else if (thisFirst.compareTo(memFirst) < 0) {
				return -1;
			}
			else {
				return 1;
			}
		}
		//different last name
		else {
			if (thisLast.compareTo(memLast) < 0) {
				return -1;
			}
			else {
				return 1;
			}
		}
	}
	
	/**
	 * This method calculates the standard membership fee for the member.
	 * @return standard fee
    */
	public double membershipFee() {
		double fee = 149.96;
		return fee;
	}
	
	/**
	 * This method returns the first name of the member.
	 * @return fname
	*/
	public String getFName() { return this.fname; }
	
	/**
	 * This method returns the last name of the member.
	 * @return lname
	*/
	public String getLName() { return this.lname; }
	
	/**
	 * This method returns the date of birth of the member.
	 * @return dob
	*/
	public Date getDOB() { return this.dob; }
	
	/**
	 * This method returns the expiration date of the member.
	 * @return expire
	*/
	public Date getExpire() { return this.expire; }
	
	/**
	 * This method returns the location of gym of the member.
	 * @return location
	*/
	public Location getLocation() { return this.location; }
	
	/**
	 * testbed main has different test cases to check if the toCompare() method works.
	 * @param args inputted by the user
    */
	public static void main(String[] args)
	{
		//Test Case 1:
		String line1 = "ALEXA BORDER";
		String line2 = "alexa border";
		StringTokenizer str1 = new StringTokenizer(line1, " ");
		StringTokenizer str2 = new StringTokenizer (line2, " ");
		
		Member memberObject1 = new Member(str1.nextToken(), str1.nextToken(), null, null, null);
		Member memberObject2 = new Member(str2.nextToken(), str2.nextToken(), null, null, null);
		System.out.println();
		System.out.println(memberObject1.fname + " " + memberObject1.lname +
				" & " + memberObject2.fname + " " + memberObject2.lname +
				": " + memberObject1.compareTo(memberObject2));
		
		//Test Case 2:
		line1 = "bob freeud";
		line2 ="bob freeud";
		str1 = new StringTokenizer(line1, " ");
		str2 = new StringTokenizer(line2, " ");
		
		memberObject1 = new Member(str1.nextToken(), str1.nextToken(), null, null, null);
		memberObject2 = new Member(str2.nextToken(), str2.nextToken(), null, null, null);
		
		System.out.println(memberObject1.fname + " " + memberObject1.lname +
				" & " + memberObject2.fname + " " + memberObject2.lname +
				": " + memberObject1.compareTo(memberObject2));
		
		//Test Case 3:
		line1 = "potato           head                   ";
		line2 ="potato head";
		str1 = new StringTokenizer(line1, " ");
		str2 = new StringTokenizer(line2, " ");
		
		memberObject1 = new Member(str1.nextToken(), str1.nextToken(), null, null, null);
		memberObject2 = new Member(str2.nextToken(), str2.nextToken(), null, null, null);
		
		System.out.println(memberObject1.fname + " " + memberObject1.lname +
				" & " + memberObject2.fname + " " + memberObject2.lname +
				": " + memberObject1.compareTo(memberObject2));
		
		//Test Case 4:
		line1 = "John Mark";
		line2 = "John Doe";
		str1 = new StringTokenizer(line1, " ");
		str2 = new StringTokenizer (line2, " ");
		
		memberObject1 = new Member(str1.nextToken(), str1.nextToken(), null, null, null);
		memberObject2 = new Member(str2.nextToken(), str2.nextToken(), null, null, null);
		
		System.out.println(memberObject1.fname + " " + memberObject1.lname +
				" & " + memberObject2.fname + " " + memberObject2.lname +
				": " + memberObject1.compareTo(memberObject2));
		
		//Test Case 5:
		line1 = "Mark Apple";
		line2 = "Dane Apple";
		str1 = new StringTokenizer(line1, " ");
		str2 = new StringTokenizer (line2, " ");
		
		memberObject1 = new Member(str1.nextToken(), str1.nextToken(), null, null, null);
		memberObject2 = new Member(str2.nextToken(), str2.nextToken(), null, null, null);
		
		System.out.println(memberObject1.fname + " " + memberObject1.lname +
				" & " + memberObject2.fname + " " + memberObject2.lname +
				": " + memberObject1.compareTo(memberObject2));
		
		//Test Case 6:
		line1 = " Mark Davis";
		line2 = " Mark Daviy";
		str1 = new StringTokenizer(line1, " ");
		str2 = new StringTokenizer (line2, " ");
		
		memberObject1 = new Member(str1.nextToken(), str1.nextToken(), null, null, null);
		memberObject2 = new Member(str2.nextToken(), str2.nextToken(), null, null, null);
		
		System.out.println(memberObject1.fname + " " + memberObject1.lname +
				" & " + memberObject2.fname + " " + memberObject2.lname +
				": " + memberObject1.compareTo(memberObject2));
		
		//Test Case 7:
		line1 = "APPLE APPLE";
		line2 = "Bork APPLE";
		str1 = new StringTokenizer(line1, " ");
		str2 = new StringTokenizer (line2, " ");
		
		memberObject1 = new Member(str1.nextToken(), str1.nextToken(), null, null, null);
		memberObject2 = new Member(str2.nextToken(), str2.nextToken(), null, null, null);
		
		System.out.println(memberObject1.fname + " " + memberObject1.lname +
				" & " + memberObject2.fname + " " + memberObject2.lname +
				": " + memberObject1.compareTo(memberObject2));
		
		//Test Case 8:
		line2 = "APPLE APPLE";
		line1 = "Bork APPLE";
		str1 = new StringTokenizer(line1, " ");
		str2 = new StringTokenizer (line2, " ");
		
		memberObject1 = new Member(str1.nextToken(), str1.nextToken(), null, null, null);
		memberObject2 = new Member(str2.nextToken(), str2.nextToken(), null, null, null);
		
		System.out.println(memberObject1.fname + " " + memberObject1.lname +
				" & " + memberObject2.fname + " " + memberObject2.lname +
				": " + memberObject1.compareTo(memberObject2));
	}
}