package application;

import javafx.scene.control.TextArea;

/**
 * MemberDatabase Class: Holds valid fitness members.
 * @author JasmeanFernando
 */
public class MemberDatabase
{
	private Member[] mlist;
	private int size;
	
	/**
	 * This method creates a MemberDatabase object.
	*/
	public MemberDatabase()
	{
		mlist = new Member [4];
		size = 0; //tracks capacity
	}
	
	/**
     * This method grows the mlist array by size +4 every time the array is full.
    */
	private void grow()
	{
		Member [] newlist = new Member [size+4];
		
		for (int i = 0; i < mlist.length; i++) {
			newlist[i] = mlist[i];
		}
		
		mlist = newlist;
	}
	
	/**
     * This method finds the member and returns the index of the member.
     * @param member member that we are trying to find
     * @return index if member is found, -1 if member is not found
    */
	private int find(Member member)
	{
		int NOT_FOUND = -1;
		
		for (int i = 0; i < mlist.length; i++) {
			if (mlist[i] != null) {
				if (mlist[i].equals(member)) {
						return i;
					}
			}
		}
		
		return NOT_FOUND;
	}
	
	/**
     * This method adds a member to the database.
     * Check the following cases ->
     * 1) member registration status
	 * 2) member is not 18+
     * @param member
	 * @param display 
     * @return true if member was added, false if member was not added
    */
	public boolean add(Member member, TextArea display)
	{
		//1) find member in registry -> cannot add duplicates
		int index = find(member);
		if (index != -1) {
			display.appendText("\n" + member.getFName() + " " + member.getLName() + " is already in the database.");
			return false;
		}
		
		//2) check dob -> birthday cannot be today/future and member is 18+
		Date today = new Date();
		Date birthday = member.getDOB();
		
		if (!birthday.isValid()) {
			display.appendText("\nDOB " + birthday + ": Invalid calendar date!");
			return false;
		}
		else if (birthday.compareTo(today) >= 0) {
			display.appendText("\nDOB " + birthday + ": Cannot be today or a future date!");
			return false;
		}
		else if ( (today.getYear() - birthday.getYear()) < 18 ) {
			display.appendText("\nDOB " + birthday + ": Must be 18 or older to join!");
			return false;
        }
		else if( (today.getYear() - birthday.getYear()) == 18 ) {
            if(today.getMonth() < birthday.getMonth()) {
            	display.appendText("\nDOB " + birthday + ": Must be 18 or older to join!");
            	return false;
            }
            else if(today.getMonth() == birthday.getMonth()) {
            	if(today.getDay() < birthday.getDay()) {
            		display.appendText("\nDOB " + birthday + ": Must be 18 or older to join!");
            		return false;
                }
            }
        }
		
		//capacity reached -> allocation
		if (size%4 == 0) { grow(); }
		
		//add
		mlist[size] = member;
		size++;
		return true;
	}
	
	/**
     * This method removes a member from the database if found.
     * Check the following cases ->
	 * 1) member registration status
     * @param member
     * @param display
     * @return true if member was removed, false if member was not removed
     */
	public boolean remove(Member member, TextArea display)
	{
		//1) find member in registry -> cannot delete non-member
		int index = find(member);
		if (index == -1) {
			display.appendText("\n" + member.getFName() + " " + member.getLName() + " is not in the database.");
			return false;
		}
		
		//remove
		for (int i = index; i < mlist.length-1; i++) {
			mlist[i] = mlist[i+1];
		}
		mlist[mlist.length-1] = null;
		return true;
	}
	
	/**
	 * This method checks if a member is registered in the database.
	 * @return index if registered, -1 otherwise
	 * @param first
	 * @param last
	 * @param dob
	 * @return
	 */
	public int registered(String first, String last, Date dob) {
		int index = find(new Member(first, last, dob, null, null));
		if (index != -1) {
			return index;
		}
		return -1;
	}
	
	/**
	 * This method returns a member at specified index.
	 * @param index
	 * @return member
	*/
	public Member getMember(int index) {
		return mlist[index];
	}
	
	/**
     * This method prints the list of members unsorted.
	 * @param homeDisplay 
     */
	public void print(TextArea homeDisplay)
	{
		if (size == 0) {
			homeDisplay.appendText("\nMember database is empty!");
			return;
		}
		
		homeDisplay.appendText("\n-List of members-");
		for (int i = 0; i < mlist.length; i++) {
			if (mlist[i] != null) {
				homeDisplay.appendText("\n" + mlist[i]);
			}
		}
		homeDisplay.appendText("\n-End of list.-");
	}
	
	/**
     * This method prints the list of members sorted by county name, then zipcode.
	 * @param homeDisplay 
     */
	public void printByCounty(TextArea homeDisplay)
	{
		//empty database
		if (size == 0) {
			homeDisplay.appendText("\nMember database is empty!");
			return;
		}
		
		//selection sort
		int smallestMember = 0;
		Member swapTemp;
		
		for(int i = 0; i < mlist.length; i++)
		{
			if (mlist[i] != null)
			{
				smallestMember = i;
				for(int j=i+1; j< mlist.length; j++)
				{
					if (mlist[j] != null)
					{
						//if county is the same
						if(mlist[smallestMember].getLocation().getCounty().equals(mlist[j].getLocation().getCounty()))
						{
							int iloop = Integer.parseInt(mlist[smallestMember].getLocation().getZipcode());
							int jloop = Integer.parseInt(mlist[j].getLocation().getZipcode());
							if (iloop > jloop)
							{
								smallestMember = j;
							}
		                }
						else
						{
							if((mlist[smallestMember].getLocation().getCounty().compareTo(mlist[j].getLocation().getCounty())) > 0)
							{
								smallestMember = j;
							}
						}
					}
				}
			
			swapTemp = mlist[i];
			mlist[i] = mlist[smallestMember];
			mlist[smallestMember] = swapTemp;
			}
		}
		
		//print
		homeDisplay.appendText("\n-List of members sorted by county and zipcode-");
		for (int i = 0; i < mlist.length; i++) {
			if (mlist[i] != null) {
				homeDisplay.appendText("\n" + mlist[i]);
			}
		}
		homeDisplay.appendText("\n-End of list.-");
	}
	
	/**
     * This method prints the list of members sorted by expiration date.
	 * @param homeDisplay 
     */
	public void printByExpirationDate(TextArea homeDisplay)
	{
		//empty database
		if (size == 0) {
			homeDisplay.appendText("\nMember database is empty!");
			return;
		}
		
		//selection sort
		int smallestMember = 0;
		Member swapTemp;
		
		for(int i = 0; i < mlist.length; i++)
		{
			if (mlist[i] != null)
			{
				smallestMember = i;
				for(int j=i+1; j< mlist.length; j++)
				{
					if (mlist[j] != null)
					{
						int num = mlist[smallestMember].getExpire().compareTo(mlist[j].getExpire());
						//swap if i > j
						if ( num > 0 ) {
							smallestMember = j;
						}
					}
				}
					
			swapTemp = mlist[i];
			mlist[i] = mlist[smallestMember];
			mlist[smallestMember] = swapTemp;
			}
		}
		
		//print
		homeDisplay.appendText("\n-List of members sorted by membership expiration date-");
		for (int i = 0; i < mlist.length; i++) {
			if (mlist[i] != null) {
				homeDisplay.appendText("\n" + mlist[i]);
			}
		}
		homeDisplay.appendText("\n-End of list.-");
	}
	
	/**
     * This method prints the list of members sorted by last name, then first name.
	 * @param homeDisplay 
     */
	public void printByName(TextArea homeDisplay)
	{
		//empty database
		if (size == 0) {
			homeDisplay.appendText("\nMember database is empty!");
			return;
		}
		
		//selection sort
		int smallestMember = 0;
		Member swapTemp;
		
		for(int i = 0; i < mlist.length; i++)
		{
			if (mlist[i] != null)
			{
				smallestMember = i;
				for(int j=i+1; j< mlist.length; j++)
				{
					if (mlist[j] != null)
					{
						int num = mlist[smallestMember].compareTo(mlist[j]);
						//swap if last names differ OR last names match but first names differ
						if ( num > 0 ) {
							smallestMember = j;
						}
					}
				}
					
			swapTemp = mlist[i];
			mlist[i] = mlist[smallestMember];
			mlist[smallestMember] = swapTemp;
			}
		}
		
		//print
		homeDisplay.appendText("\n-List of members sorted by last name and first name-");
		for (int i = 0; i < mlist.length; i++) {
			if (mlist[i] != null) {
				homeDisplay.appendText("\n" + mlist[i]);
			}
		}
		homeDisplay.appendText("\n-End of list.-");
	}
	
	/**
     * This method prints the list of members sorted by membership fees.
	 * @param homeDisplay 
     */
	public void printbyMembershipFee(TextArea homeDisplay)
	{
		//empty database
		if (size == 0) {
			homeDisplay.appendText("\nMember database is empty!");
			return;
		}
		
		//selection sort
		int smallestMember = 0;
		Member swapTemp;
		
		for(int i = 0; i < mlist.length; i++)
		{
			if (mlist[i] != null)
			{
				smallestMember = i;
				for(int j=i+1; j< mlist.length; j++)
				{
					if (mlist[j] != null)
					{
						//swap if mlist[i] membership fee is greater
						if (mlist[smallestMember].membershipFee() > mlist[j].membershipFee()) {
							smallestMember = j;
						}
					}
				}
					
			swapTemp = mlist[i];
			mlist[i] = mlist[smallestMember];
			mlist[smallestMember] = swapTemp;
			}
		}
		
		//print
		homeDisplay.appendText("\n-List of members sorted by with membership fees-");
		for (int i = 0; i < mlist.length; i++) {
			if (mlist[i] != null) {
				homeDisplay.appendText("\n" + mlist[i] + ", Membership fee: " + mlist[i].membershipFee());
			}
		}
		homeDisplay.appendText("\n-End of list.-");
	}
}