package code.madhan.sfmovietour.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MovieAdditionalInfo {
	
	private String imdbID;
	private String imdbRating;
	private String imdbVotes;
	
	@JsonProperty("tomatoRating")
	private String rottenTomatoesRating;
	
	@JsonProperty("Plot")
	private String plot;
	
	@JsonProperty("Language")
	private String language;
	
	@JsonProperty("Country")
	private String country;
	
	@JsonProperty("Released")
	private String released;
	
	@JsonProperty("Actors")
	private String actors;
	
	@JsonProperty("Genre")
	private String genre;
	
	@JsonProperty("Rated")
	private String rated;
	
	@JsonProperty("Poster")
	private String poster;

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(String imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	public String getRottenTomatoesRating() {
		return rottenTomatoesRating;
	}

	public void setRottenTomatoesRating(String rottenTomatoesRating) {
		this.rottenTomatoesRating = rottenTomatoesRating;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	
}
