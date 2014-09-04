package code.madhan.sfmovietour.model;

public class GeoJsonCoordinates {
	
	private String type;
	private Double[] coordinates = new Double[2];
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double[] getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Double[] coordinates) {
		this.coordinates = coordinates;
	}
	public GeoJsonCoordinates(String type, Double[] coordinates) {
		this.type = type;
		this.coordinates = coordinates;
	}
	
	

}
