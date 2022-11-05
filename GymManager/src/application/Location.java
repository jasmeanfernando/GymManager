package application;

/**
 * Location Class: Holds a valid gym location.
 * @author JasmeanFernando
 */
public enum Location
{
	BRIDGEWATER("BRIDGEWATER", "08807", "SOMERSET"),
	EDISON("EDISON", "08837", "MIDDLESEX"),
	FRANKLIN("FRANKLIN", "08873", "SOMERSET"),
	PISCATAWAY("PISCATAWAY", "08854", "MIDDLESEX"),
	SOMERVILLE("SOMERVILLE", "08876", "SOMERSET");
	
	private final String city;
	private final String zipcode;
	private final String county;
	
	/**
	 * This method creates a Location object with corresponding gym location info.
	 * @param city
	 * @param zipcode
	 * @param county
	 */
	Location(String city, String zipcode, String county)
	{
		this.city = city;
		this.zipcode = zipcode;
		this.county = county;
	}
	
	/**
	 * This method concatenates city, zipcode, and county all into one string.
	 * @return String
	*/
	@Override
	public String toString() {
		return this.city + ", " + this.zipcode + ", " + this.county;
	}
	
	/**
	 * This method can compare this location to a location argument.
	 * @param address Location being compared
	 * @return true if comparisons match, false otherwise
    */
	public boolean equals(Location address) {
		if (this.city == address.getCity() && this.zipcode == address.getZipcode() && this.county == address.county) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method returns the city.
	 * @return city
	*/
	public String getCity() { return city; }
	
	/**
	 * This method returns the zipcode.
	 * @return zipcode
	*/
	public String getZipcode() { return zipcode; }
	
	/**
	 * This method returns the county.
	 * @return county
	*/
	public String getCounty() { return county; }
}
