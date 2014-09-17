package code.madhan.sfmovietour.model;

import java.util.Map;

public class Facets {
	
	private Map<String, Integer> neighbourhood;
	private Map<String, Integer> releaseDecade;
	
	
	
	public Map<String, Integer> getNeighbourhood() {
		return neighbourhood;
	}
	public void setNeighbourhood(Map<String, Integer> neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
	public Map<String, Integer> getReleaseDecade() {
		return releaseDecade;
	}
	public void setReleaseDecade(Map<String, Integer> releaseDecade) {
		this.releaseDecade = releaseDecade;
	}

}
