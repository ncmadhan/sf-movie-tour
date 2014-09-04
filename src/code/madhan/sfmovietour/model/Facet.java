package code.madhan.sfmovietour.model;

import java.util.ArrayList;
import java.util.List;

public class Facet {

	private List<String> releaseDecade = new ArrayList<String>();
	private List<String> neighbourhood = new ArrayList<String>();
	
	
	public List<String> getReleasedDecade() {
		return releaseDecade;
	}
	public void setReleasedDecade(List<String> releaseDecade) {
		this.releaseDecade = releaseDecade;
	}
	public List<String> getNeighbourhood() {
		return neighbourhood;
	}
	public void setNeighbourhood(List<String> neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
	
	
	
	
}
