package code.madhan.sfmovietour.model;

import java.util.HashSet;
import java.util.Set;

public class Facet {

	private Set<String> releaseDecade = new HashSet<String>();
	private Set<String> neighbourhood = new HashSet<String>();
	
	public Set<String> getReleaseDecade() {
		return releaseDecade;
	}
	public void setReleaseDecade(Set<String> releaseDecade) {
		this.releaseDecade = releaseDecade;
	}
	public Set<String> getNeighbourhood() {
		return neighbourhood;
	}
	public void setNeighbourhood(Set<String> neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
	
	
	
}
