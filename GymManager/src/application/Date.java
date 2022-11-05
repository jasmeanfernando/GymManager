package application;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Date Class: Holds a valid calendar date.
 * @author JasmeanFernando
*/
public class Date implements Comparable<Date>
{
	private int year;
	private int month;
	private int day;
	
	public static final int QUADRENNIAL = 4;
	public static final int CENTENNIAL = 100;
	public static final int QUATERCENTENNIAL = 400;
	private static final int JAN = 1;
	private static final int FEB = 2;
	private static final int MAR = 3;
	private static final int APR = 4;
	private static final int MAY = 5;
	private static final int JUN = 6;
	private static final int JUL = 7;
	private static final int AUG = 8;
	private static final int SEPT = 9;
	private static final int OCT = 10;
	private static final int NOV = 11;
	private static final int DEC = 12;
	private static final int oddMonth = 31;
	private static final int evenMonth = 30;
	private static final int leapYear = 29;
	private static final int notleapYear = 28;
	private static final int day1 = 1;

	/**
	 * This method creates a Date object with today’s date (see Calendar class).
	 */
	public Date() {
		Calendar today = Calendar.getInstance();
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH)+1;
		day = today.get(Calendar.DATE);
	}
	
	/**
	 * This method creates a Date object with a specific date.
	 * @param date (mm/dd/yyyy)
	*/
	public Date(String date) {
		StringTokenizer str = new StringTokenizer (date, "/");
		
		String strSplit = str.nextToken(); //mm
		int num = Integer.parseInt(strSplit);
		month = num;
		
		strSplit = str.nextToken(); //dd
		num = Integer.parseInt(strSplit);
		day = num;
		
		strSplit = str.nextToken(); //yyyy
		num = Integer.parseInt(strSplit);
		year = num;
	}
	
	/**
	 * This method concatenates the day, month, and year all into one string.
	 * @return String with day/month/year
	*/
	@Override
	public String toString() {
		return this.month + "/" + this.day + "/" + this.year;
	}
	
	/**
	 * This method compares dates (days, months, and years).
	 * @param date Date object being to be compared
	 * @return 0 if date matches date argument, -1 if date is BEFORE date argument, 1 if date is AFTER date argument
    */
	@Override
	public int compareTo(Date date) {
		if (this.year == date.year && this.month == date.month && this.day == date.day) { return 0; }
		if (this.year < date.year) { return -1; }
		else if (this.year == date.year && this.month < date.month) { return -1; }
		else if (this.year == date.year && this.month == date.month && this.day < date.day) { return -1; }
		return 1;
	}
	
	/**
	 * This methods checks if a date is a valid calendar date.
	 * @return false if date is invalid, true if date is valid
	*/
	public boolean isValid() {
		//non-existent month
		if (this.month < JAN || this.month > DEC) { return false; }
		
		//non-existent day
		if (this.day < day1 || this.day > oddMonth ) { return false; }
		
		//non-existent year
		if (this.year < 0) { return false; }
		
		//months with 31 days
		if (this.month == JAN || this.month == MAR || this.month == MAY || this.month == JUL || this.month == AUG || this.month == OCT || this.month == DEC) {
			if (this.day > oddMonth) { return false; }
		}
		
		//months with 30 days
		if (this.month == APR || this.month == JUN || this.month == SEPT || this.month == NOV) {
			if (this.day > evenMonth) { return false; }
		}
		
		//month with 28-29 days
		if (this.month == FEB) {
			if (this.day >= evenMonth) { return false; }
			
			if (this.year % QUADRENNIAL == 0) {
				if (this.year % CENTENNIAL == 0) {
					if (this.year % QUATERCENTENNIAL == 0) {
						//leap year
						if (this.day > leapYear) {
							return false;
						}
					}
				}
			}
			else {
				//not leap year
				if (this.day > notleapYear) {return false; }
			}
		}
		
		return true;
	}
	
	/**
	 * This method changes the month.
	 * @param num Number of months to be added to current month
	*/
	public void setMonth(int num) {
		int sum = this.month + num;
		if (sum > DEC) {
			this.month = sum%12;
			this.year = this.year + 1;
			return;
		}
		
		this.month = sum;
	}
	
	/**
	 * This method changes the year.
	 * @param num Number of years to be added to current year
	*/
	public void setYear(int num) {
		this.year = this.year + num;
	}
	
	/**
	 * This method returns the month from the date.
	 * @return month
	*/
	public int getMonth() { return this.month; }
	
	/**
	 * This method returns the day from the date.
	 * @return day
	*/
	public int getDay() { return this.day; }
	
	/**
	 * This method returns the year from the date.
	 * @return year
	*/
	public int getYear() { return this.year; }
	
	/**
	 * testbed main has different test cases to check if the isValid() method works.
	 * @param args inputted by the user
    */
	public static void main(String[] args)
	{
		//Test Case 1:
		String test = "2/29/2018";
		Date dateObject = new Date(test);
		System.out.println(dateObject.toString() + ": " + dateObject.isValid());
		
		//Test Case 2:
		String test2 = "13/01/2019";
		Date dateObject2 = new Date(test2);
		System.out.println(dateObject2.toString() + ": " + dateObject2.isValid());
		
		//Test Case 3:
		String test3 = "03/00/2020";
		Date dateObject3 = new Date(test3);
		System.out.println(dateObject3.toString() + ": " + dateObject3.isValid());
		
		//Test Case 4:
		String test4 = "-8/09/2022";
		Date dateObject4 = new Date(test4);
		System.out.println(dateObject4.toString() + ": " + dateObject4.isValid());
		
		//Test Case 5:
		String test5 = "-9/-21/-1987";
		Date dateObject5 = new Date(test5);
		System.out.println(dateObject5.toString() + ": " + dateObject5.isValid());
		
		//Test Case 6:
		String test6 = "06/-31/2022";
		Date dateObject6 = new Date(test6);
		System.out.println(dateObject6.toString() + ": " + dateObject6.isValid());
		
		//Test Case 7:
		String test7 = "2/29/2016";
		Date dateObject7 = new Date(test7);
		System.out.println(dateObject7.toString() + ": " + dateObject7.isValid());
		
		//Test Case 8:
		String test8 = "07/08/2009";
		Date dateObject8 = new Date(test8);
		System.out.println(dateObject8.toString() + ": " + dateObject8.isValid());
		
		//Test Case 9:
		String test9 = "04/18/2020";
		Date dateObject9 = new Date(test9);
		System.out.println(dateObject9.toString() + ": " + dateObject9.isValid());
		
		//Test Case 10:
		String test10 = "12/23/2021";
		Date dateObject10 = new Date(test10);
		System.out.println(dateObject10.toString() + ": " + dateObject10.isValid());
	}
}