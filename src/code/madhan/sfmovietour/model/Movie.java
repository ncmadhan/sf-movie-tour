package code.madhan.sfmovietour.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="movies")
public class Movie {
	
	@Id
	private String id;
	
	@JsonProperty("actor_1")
	private String actorOne;
	
	@JsonProperty("actor_2")
	private String actorTwo;
	
	@JsonProperty("production_company")
	private String productionCompany;
	
	private String title;
	private String distributor;
	private String director;
	private String writer;
	
	@JsonProperty("release_year")
	private int releaseYear;
	
	@JsonProperty("locations")
	@Transient
	private String locationAddress;
	
	@JsonProperty("fun_facts")
	private String funFacts;
	
	private MovieAdditionalInfo additionalInfo;
	
	private Facet facets = new Facet();
	
	private List<Location> movieLocations = new ArrayList<Location>();
	
	
	public MovieAdditionalInfo getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(MovieAdditionalInfo additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getActorOne() {
		return actorOne;
	}
	public void setActorOne(String actorOne) {
		this.actorOne = actorOne;
	}
	public String getActorTwo() {
		return actorTwo;
	}
	public void setActorTwo(String actorTwo) {
		this.actorTwo = actorTwo;
	}
	public String getDistributor() {
		return distributor;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getProductionCompany() {
		return productionCompany;
	}
	public void setProductionCompany(String productionCompany) {
		this.productionCompany = productionCompany;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFunFacts() {
		return funFacts;
	}
	public void setFunFacts(String funFacts) {
		this.funFacts = funFacts;
	}
	public String getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}
	public List<Location> getMovieLocations() {
		return movieLocations;
	}
	public void setMovieLocations(List<Location> movieLocations) {
		this.movieLocations = movieLocations;
	}
	public Facet getFacets() {
		return facets;
	}
	public void setFacets(Facet facets) {
		this.facets = facets;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	
	
}
