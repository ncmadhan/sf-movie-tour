package code.madhan.sfmovietour.model;

import java.util.List;

public class MoviesDTO {
	
	private String count;
	private FacetCount metaFacets;
	private List<Movie> movies;
	
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public FacetCount getMetaFacets() {
		return metaFacets;
	}
	public void setMetaFacets(FacetCount metaFacets) {
		this.metaFacets = metaFacets;
	}
	public List<Movie> getMovies() {
		return movies;
	}
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	

}
