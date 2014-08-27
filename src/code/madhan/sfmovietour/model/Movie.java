package code.madhan.sfmovietour.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Movie {
	
	private String title;
	
	@JsonProperty("actor_1")
	private String actorOne;
	
	@JsonProperty("actor_2")
	private String actorTwo;
	private String distributor;
	private String director;
	private String writer;
	
	@JsonProperty("production_company")
	private String productionCompany;
	private String locations;
	
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
	public String getLocations() {
		return locations;
	}
	public void setLocations(String locations) {
		this.locations = locations;
	}

	
}
