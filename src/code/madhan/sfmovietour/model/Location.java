package code.madhan.sfmovietour.model;

public class Location {
	
	String address;
	String funFacts;
	double latitude;
	double longitude;
	String neighborhood;
	
	public Location (String address, String funFacts) {
		this.address = address;
		this.funFacts = funFacts;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFunFacts() {
		return funFacts;
	}
	public void setFunFacts(String funFacts) {
		this.funFacts = funFacts;
	}
	
}
