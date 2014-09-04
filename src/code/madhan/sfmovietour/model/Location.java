package code.madhan.sfmovietour.model;

public class Location {
	
	String address;
	String funFacts;
	GeoJsonCoordinates locationCoordinates;
	/*double latitude;
	double longitude;
	*/
	
	String neighbourhood;
	boolean isApproximate = false;
	
	public Location (String address, String funFacts) {
		this.address = address;
		this.funFacts = funFacts;
	}	
	
	/*public double getLatitude() {
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
	}*/
	public String getNeighbourhood() {
		return neighbourhood;
	}
	public void setNeighborhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
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

	public boolean isApproximate() {
		return isApproximate;
	}

	public void setApproximate(boolean isApproximate) {
		this.isApproximate = isApproximate;
	}

	public GeoJsonCoordinates getLocationCoordinates() {
		return locationCoordinates;
	}

	public void setLocationCoordinates(GeoJsonCoordinates locationCoordinates) {
		this.locationCoordinates = locationCoordinates;
	}
	
	
}
