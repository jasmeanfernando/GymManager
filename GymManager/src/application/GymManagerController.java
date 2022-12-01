package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * MVC Pattern: Controller File.
 * @author JasmeanFernando
 */
public class GymManagerController
{
	//-> display
	@FXML
	private TextArea homeDisplay, databaseDisplay, classDisplay, classDisplayGuest;
	
	//-> databases
	MemberDatabase memberList = new MemberDatabase ();
	ClassSchedule schedule = new ClassSchedule ();
	
	//-> member information for member database
	@FXML
	private TextField firstNameDatabase, lastNameDatabase, dobDatabase, locationDatabase;
	@FXML
	private RadioButton standard, family, premium;
	
	//-> member information for scheduling fitness classes
	@FXML
	private TextField fitnessClass, instructorClass, locationClass, firstNameClass, lastNameClass;
	@FXML
	private DatePicker dobClass;
	
	//-> member information for scheduling fitness classes
	@FXML
	private TextField fitnessClassGuest, instructorClassGuest, locationClassGuest, firstNameClassGuest, lastNameClassGuest;
	@FXML
	private DatePicker dobClassGuest;
	
	/**
	 * COMMAND LM: Loads list of members from memberList.txt.
	 * @param event
	 */
	@FXML
	public void theLMCommand(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.setInitialFileName("classSchedule.txt");
		Stage stage = new Stage();
		File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
		
		//write code to read from the file
		homeDisplay.appendText("\n-List of members loaded-");
		try {
			Scanner scanFile = new Scanner(sourceFile);
			while (scanFile.hasNextLine()) {
				//display
				String fileLine = scanFile.nextLine();
				homeDisplay.appendText("\n" + fileLine);
				
				//initialize member info
	    		StringTokenizer fileInfo = new StringTokenizer (fileLine, " "); //StringTokenizer
	    		String first = fileInfo.nextToken();
	    		String last = fileInfo.nextToken();
	    		Date dob = new Date(fileInfo.nextToken());
	    		Date expiration = new Date(fileInfo.nextToken());
	    		Location location = Location.valueOf(fileInfo.nextToken().toUpperCase());
	    		
	    		//add member to database
	    		Member member = new Member (first, last, dob, expiration, location);
	    		memberList.add(member, homeDisplay);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		homeDisplay.appendText("\n-End of list.-");
	}
	
	/**
	 * COMMAND LS: Loads the fitness class schedule from classSchedule.txt.
	 * @param event
	 */
	@FXML
	public void theLSCommand(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.setInitialFileName("memberList.txt");
		//chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"), new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
		
		//write code to read from the file
		homeDisplay.appendText("\n-Fitness classes loaded-");
		try {
			Scanner scanFile = new Scanner(sourceFile);
			while (scanFile.hasNextLine()) {
				//display
				String fileLine = scanFile.nextLine();
				homeDisplay.appendText("\n" + fileLine);
				
				//initialize instructor info
				StringTokenizer fileInfo = new StringTokenizer (fileLine, " "); //StringTokenizer
	    		Fitness fitness = Fitness.valueOf(fileInfo.nextToken().toUpperCase());
	    		String instructor = fileInfo.nextToken();
	    		Time time = Time.valueOf(fileInfo.nextToken().toUpperCase());
	    		Location location = Location.valueOf(fileInfo.nextToken().toUpperCase());
	    		
	    		//create fitnessClass object
	    		FitnessClass newClass = new FitnessClass(instructor, location, fitness, time);
	    		
	    		//add new class to schedule
	    		schedule.addClass(newClass);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		homeDisplay.appendText("\n-End of list.-");
	}
	
	/**
	 * COMMAND S: Displays the fitness class schedule and its participants.
	 * @param event
	 */
	public void theSCommand(ActionEvent event) {
		schedule.printSchedule(homeDisplay);
	}
	
	/**
	 * COMMAND P: Displays list of members without sorting.
	 * @param event
	 */
	public void thePCommand(ActionEvent event) {
		memberList.print(homeDisplay);
	}
	
	/**
	 * COMMAND PN: Displays list of members sorted by last name, then first name.
	 * @param event
	 */
	public void thePNCommand(ActionEvent event) {
		memberList.printByName(homeDisplay);
	}
	
	/**
	 * COMMAND PC: Displays list of members sorted by county name, then zipcode.
	 * @param event
	 */
	public void thePCCommand(ActionEvent event) {
		memberList.printByCounty(homeDisplay);
	}
	
	/**
	 * COMMAND PD: Displays list of members sorted by expiration dates.
	 * @param event
	 */
	public void thePDCommand(ActionEvent event) {
		memberList.printByExpirationDate(homeDisplay);
	}
	
	/**
	 * COMMAND PF: Displays list of members with the membership fees.
	 * @param event
	 */
	public void thePFCommand(ActionEvent event) {
		memberList.printbyMembershipFee(homeDisplay);
	}
	
	/**
	 * COMMANDS A, AF, AP: Adds a member to database with specific membership.
	 * @param event
	 */
	public void addMemberCommands(ActionEvent event) {
		//initialize member info
		String first = firstNameDatabase.getText();
		String last = lastNameDatabase.getText();
		
		String formattedDate = dobDatabase.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		Date dob = new Date(formattedDate);
		
		//check location
		Location location;
		String locString = locationDatabase.getText().toUpperCase();
		try { location = Location.valueOf(locString); }
		catch(IllegalArgumentException i) {
			databaseDisplay.appendText("\n" + locString + ": Invalid location!");
			return;
		}
		
		//add standard member
		if (standard.isSelected()) {
			//set expiration
			Date expiration = new Date ();
			expiration.setMonth(3);
			
			//add to database
			Member member = new Member(first, last, dob, expiration, location);
			boolean result = memberList.add(member, databaseDisplay);
			if (result) { databaseDisplay.appendText("\n" + first + " " + last + " added."); }
		}
		//add family member
		else if (family.isSelected()) {
			//set expiration
			Date expiration = new Date ();
			expiration.setMonth(3);
			
			//add to database
			Family member = new Family(first, last, dob, expiration, location);
			boolean result = memberList.add(member, databaseDisplay);
			if (result) { databaseDisplay.appendText("\n" + first + " " + last + " added."); }
		}
		//add premium member
		else {
			//set expiration
			Date expiration = new Date ();
			expiration.setYear(1);
			
			//add to database
			Premium member = new Premium(first, last, dob, expiration, location);
			boolean result = memberList.add(member, databaseDisplay);
			if (result) { databaseDisplay.appendText("\n" + first + " " + last + " added."); }
		}
	}
	
	/**
	 * COMMANDS R: Removes a member from database.
	 * @param event
	 */
	public void removeMemberCommand(ActionEvent event) {
		//initialize member info
		String first = firstNameDatabase.getText();
		String last = lastNameDatabase.getText();
		
		String formattedDate = dobDatabase.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		Date dob = new Date(formattedDate);
		
		//check location
		Location location;
		String locString = locationDatabase.getText().toUpperCase();
		try { location = Location.valueOf(locString); }
		catch(IllegalArgumentException i) { }
		
		//remove from database
		Member member = new Member(first, last, dob, null, null);
		boolean result = memberList.remove(member, databaseDisplay);
		if (result) { databaseDisplay.appendText("\n" + first + " " + last + " removed."); }
	}
	
	/**
	 * COMMAND C: Adds a member with specific membership to a class.
	 * @param event
	 */
	public void addClassCommand(ActionEvent event) {
		//initialize instructor info
		Fitness fitness;
		String fitString = fitnessClass.getText().toUpperCase();
		try { fitness = Fitness.valueOf(fitString); }
		catch(IllegalArgumentException i) {
			classDisplay.appendText("\n" + fitString + ": Class does not exist!");
			return;
		}
		
		String instructor = instructorClass.getText();
		
		//initialize member info
		String first = firstNameClass.getText();
		String last = lastNameClass.getText();
		
		String formattedDate = dobClass.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		Date dob = new Date(formattedDate);
		
		//check location
		Location location;
		String locString = locationClass.getText().toUpperCase();
		try { location = Location.valueOf(locString); }
		catch(IllegalArgumentException i) {
			classDisplay.appendText("\n" + locString + ": Invalid location!");
			return;
		}
		
		//find member in registry
		int index = memberList.registered(first, last, dob);
		if (index == -1) {
			classDisplay.appendText("\n" + first + " " + last + " " + dob + " is not in the database.");
			return;
		}
		Member member = memberList.getMember(index);
		
		//check expiration
		Date today = new Date();
		if (today.compareTo(member.getExpire()) == 1) {
			classDisplay.appendText("\n" + first + " " + last + " " + dob + " membership expired.");
			return;
		}
		
		//check membership type
		if (member instanceof Premium) {
			Premium newMember = new Premium(first, last, dob, member.getExpire(), member.getLocation());
			member = newMember;
		}
		else if (member instanceof Family) {
			Family newMember = new Family(first, last, dob, member.getExpire(), member.getLocation());
			member = newMember;
		}
		else { //can only add classes where member is registered
			if (!location.equals(member.getLocation())) {
				classDisplay.appendText("\n" + first + " " + last + " " + " checking in " + location + " - standard membership location restriction.");
				return;
			}
		}
		
		//find class in schedule
		boolean found_instructor = false; //change to true if instructor found
		FitnessClass addingClass = null;
		for (int i = 0; i < schedule.getSize(); i++) {
			//check instructor
			if (schedule.getClass(i).getInstructor().equalsIgnoreCase(instructor)) {
				found_instructor = true;
				//check fitness class and location
				if (schedule.getClass(i).getFitness().equals(fitness) && schedule.getClass(i).getLocation().equals(location)) {
					addingClass = schedule.getClass(i);
				}
			}
		}
		
		if (!found_instructor) {
			classDisplay.appendText("\n" + instructor + ": Instructor does not exist!");
			return;
		}
		
		if (addingClass == null) {
			classDisplay.appendText("\n" + fitness + " by " + instructor + " does not exist at " + location.getCity() + ".");
			return;
		}
		
		//double check-in
		if (addingClass.findMember(member)) {
			classDisplay.appendText("\n" + first + " " + last + " " + " already checked in.");
			return;
		}
		
		//time conflict
		for (int i = 0; i < schedule.getSize(); i++) {
			if (schedule.getClass(i).findMember(member)) {
				if (schedule.getClass(i).getTime().equals(addingClass.getTime())) {
					classDisplay.appendText("\nTime conflict - " + fitness + " - " + instructor + ", " + addingClass.getTime() + ", " + location);
					return;
				}
			}
		}
		
		//add to class
		addingClass.add(member);
		classDisplay.appendText("\n" + first + " " + last + " checked in " + fitness.getFitnessClass() + " - " + instructor + ", " + addingClass.getTime() + ", " + location.getCity());
		addingClass.printParticipants(classDisplay);
	}
	
	/**
	 * COMMAND D: Drops a member with specific membership from a class.
	 * @param event
	 */
	public void dropClassCommand(ActionEvent event) {
		//initialize instructor info
		Fitness fitness;
		String fitString = fitnessClass.getText().toUpperCase();
		try { fitness = Fitness.valueOf(fitString); }
		catch(IllegalArgumentException i) {
			classDisplay.appendText("\n" + fitString + ": Class does not exist!");
			return;
		}
		
		String instructor = instructorClass.getText();
		
		//initialize member info
		String first = firstNameClass.getText();
		String last = lastNameClass.getText();
		
		String formattedDate = dobClass.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		Date dob = new Date(formattedDate);
		
		//check location
		Location location;
		String locString = locationClass.getText().toUpperCase();
		try { location = Location.valueOf(locString); }
		catch(IllegalArgumentException i) {
			classDisplay.appendText("\n" + locString + ": Invalid location!");
			return;
		}
		
		//find member in registry
		int index = memberList.registered(first, last, dob);
		if (index == -1) {
			classDisplay.appendText("\n" + first + " " + last + " " + dob + " is not in the database.");
			return;
		}
		Member member = memberList.getMember(index);
		
		//check expiration
		Date today = new Date();
		if (today.compareTo(member.getExpire()) == 1) {
			classDisplay.appendText("\n" + first + " " + last + " " + dob + " membership expired.");
			return;
		}
		
		//find class in schedule
		boolean found_instructor = false; //change to true if instructor found
		FitnessClass addingClass = null;
		for (int i = 0; i < schedule.getSize(); i++) {
			//check instructor
			if (schedule.getClass(i).getInstructor().equalsIgnoreCase(instructor)) {
				found_instructor = true;
				//check fitness class and location
				if (schedule.getClass(i).getFitness().equals(fitness) && schedule.getClass(i).getLocation().equals(location)) {
					addingClass = schedule.getClass(i);
				}
			}
		}
		
		if (!found_instructor) {
			classDisplay.appendText("\n" + instructor + ": Instructor does not exist!");
			return;
		}
		
		if (addingClass == null) {
			classDisplay.appendText("\n" + fitness + " by " + instructor + " does not exist at " + location.getCity() + ".");
			return;
		}
		
		//member has not checked in
		if (!addingClass.findMember(member)) {
			classDisplay.appendText("\n" + first + " " + last + " " + " did not check in.");
			return;
		}
		
		//remove from class
		addingClass.remove(member);
		classDisplay.appendText("\n" + first + " " + last + " done with the class.");
	}
	
	/**
	 * Command CG: Checks a family guest into a fitness class.
	 * @param event
	 */
	public void addClassGuestCommand(ActionEvent event) {
		//initialize instructor info
		Fitness fitness;
		String fitString = fitnessClassGuest.getText().toUpperCase();
		try { fitness = Fitness.valueOf(fitString); }
		catch(IllegalArgumentException i) {
			classDisplayGuest.appendText("\n" + fitString + ": Class does not exist!");
			return;
		}
		
		String instructor = instructorClassGuest.getText();
		
		//check location
		Location location;
		String locString = locationClassGuest.getText().toUpperCase();
		try { location = Location.valueOf(locString); }
		catch(IllegalArgumentException i) {
			classDisplayGuest.appendText("\n" + locString + ": Invalid location!");
			return;
		}
		
		//initialize member info
		String first = firstNameClassGuest.getText();
		String last = lastNameClassGuest.getText();
		
		String formattedDate = dobClassGuest.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		Date dob = new Date(formattedDate);
		
		//find member in registry
		int index = memberList.registered(first, last, dob);
		if (index == -1) {
			classDisplayGuest.appendText("\n" + first + " " + last + " " + dob + " is not in the database.");
			return;
		}
		Member member = memberList.getMember(index);
		
		//find class in schedule
		FitnessClass addingClass = null;
		for (int i = 0; i < schedule.getSize(); i++) {
			//check fitness class and location
			if (schedule.getClass(i).getFitness().equals(fitness) && schedule.getClass(i).getLocation().equals(location)) {
				addingClass = schedule.getClass(i);
			}
		}
		
		//check membership type
		//***PREMIUM
		if (member instanceof Premium) {
			//guests can only check in where member is registered
			if (!location.equals(member.getLocation())) {
				classDisplayGuest.appendText("\n" + first + " " + last + " checking in " + location + " - guest location restriction.");
				return;
			}
			
			//decrement guest passes for registered member
			boolean result = ((Premium)member).usePremiumPass();
			if (!result) {
				classDisplayGuest.appendText("\n" + first + " " + last + " ran out of guest passes.");
				return;
			}
			
			//add guest to class
			Premium newMember = ((Premium)member);
			newMember.newGuestStatus();
			addingClass.add(newMember);
			classDisplayGuest.appendText("\n" + first + " " + last + " (guest) checked in " + fitness.getFitnessClass() + " - " + instructor + ", " + addingClass.getTime() + ", " + location.getCity());
			addingClass.printParticipants(classDisplayGuest);
		}
		//***FAMILY
		else if (member instanceof Family) {
			//guests can only check in where member is registered
			if (!location.equals(member.getLocation())) {
				classDisplayGuest.appendText("\n" + first + " " + last + " checking in " + location + " - guest location restriction.");
				return;
			}
			
			//decrement guest passes for registered member
			boolean result = ((Family)member).useFamilyPass();
			if (!result) {
				classDisplayGuest.appendText("\n" + first + " " + last + " ran out of guest passes.");
				return;
			}
			
			//add guest to class
			Family newMember = ((Family)member);
			newMember.newGuestStatus();
			addingClass.add(newMember);
			classDisplayGuest.appendText("\n" + first + " " + last + " (guest) checked in " + fitness.getFitnessClass() + " - " + instructor + ", " + addingClass.getTime() + ", " + location.getCity());
			addingClass.printParticipants(classDisplayGuest);
		}
		//***MEMBER
		else {
			classDisplayGuest.appendText("\nStandard membership - guest check-in is not allowed.");
		}
	}
	
	/**
	 * Command DG: Checks a family guest out of a fitness class after completion.
	 * @param event
	 */
	public void dropClassGuestCommand(ActionEvent event) {
		//initialize instructor info
		Fitness fitness;
		String fitString = fitnessClassGuest.getText().toUpperCase();
		try { fitness = Fitness.valueOf(fitString); }
		catch(IllegalArgumentException i) {
			classDisplayGuest.appendText("\n" + fitString + ": Class does not exist!");
			return;
		}
		
		String instructor = instructorClassGuest.getText();
		
		//check location
		Location location;
		String locString = locationClassGuest.getText().toUpperCase();
		try { location = Location.valueOf(locString); }
		catch(IllegalArgumentException i) {
			classDisplayGuest.appendText("\n" + locString + ": Invalid location!");
			return;
		}
		
		//initialize member info
		String first = firstNameClassGuest.getText();
		String last = lastNameClassGuest.getText();
		
		String formattedDate = dobClassGuest.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		Date dob = new Date(formattedDate);
		
		//find member in registry
		int index = memberList.registered(first, last, dob);
		if (index == -1) {
			classDisplayGuest.appendText("\n" + first + " " + last + " " + dob + " is not in the database.");
			return;
		}
		Member member = memberList.getMember(index);
		
		//find class in schedule
		FitnessClass addingClass = null;
		for (int i = 0; i < schedule.getSize(); i++) {
			//check fitness class and location
			if (schedule.getClass(i).getFitness().equals(fitness) && schedule.getClass(i).getLocation().equals(location)) {
				addingClass = schedule.getClass(i);
			}
		}
		
		//check membership type
		//***PREMIUM
		if (member instanceof Premium) {
			//increment guest passes for registered member
			((Premium)member).returnPremiumPass();
		}
		//***FAMILY
		if (member instanceof Family) {
			//increment guest passes for registered member
			((Family)member).returnFamilyPass();
		}
		//***MEMBER
		else {
			return;
		}
		
		//remove from class
		addingClass.removeGuest(member);
		classDisplayGuest.appendText("\n" + first + " " + last + " (guest) done with the class.");
	}
}
