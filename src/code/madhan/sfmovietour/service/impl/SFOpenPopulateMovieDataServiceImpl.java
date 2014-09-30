
package code.madhan.sfmovietour.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.cypher.internal.compiler.v2_1.ast.rewriters.isolateAggregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import code.madhan.sfmovietour.helper.PopulateMovieDataHelper;
import code.madhan.sfmovietour.model.Location;
import code.madhan.sfmovietour.model.Movie;
import code.madhan.sfmovietour.model.MovieThumbnailDTO;
import code.madhan.sfmovietour.service.MovieService;
import code.madhan.sfmovietour.service.PopulateMovieDataService;

@Service
public class SFOpenPopulateMovieDataServiceImpl implements PopulateMovieDataService{
	
	
	@Autowired
	MongoOperations mongoOperation;
	
	@Autowired
	MovieService movieService;
	
	@Override
	public void populateMovieData() {
		System.out.println("Autowiring working http://data.sfgov.org/resource/yitu-d5am.json");
		PopulateMovieDataHelper populateMovieDataHelper = new PopulateMovieDataHelper();
		
		
		try {
		// Fetch all rows from the SF Movie API
		List<Movie> rawMovieLocationDataSet = populateMovieDataHelper.fetchSFMovieApiResponse();
		
		List<Movie> populatedMovieList = new ArrayList<Movie>();
		
		if (rawMovieLocationDataSet == null || rawMovieLocationDataSet.size() == 0) {
			System.out.println("Error fetching data from SF Open API");
			return;
		}
		else {
			// If raw movie data is fetched from the API, proceed to massage the location and additional movie data
			
			/**
			 * Need to gather locations for the same movie within a single movie object.
			 * Since the movie list is sorted by title, code will detect change of movie when the title
			 * changes between the current row and the previous row.
			 */
			String prevMovie = "";
			Movie populatedMovie = null; // this object will represent a single movie with its multiple locations populated in it.
			
			for (Movie curMovie: rawMovieLocationDataSet) {
				
				if (!curMovie.getTitle().trim().equals(prevMovie.trim())) { // if the current row indicates a new movie
					if (populatedMovie != null) { // add the populated movie to the final movie list. Checking for null to handle the first row, when the populated movie object is null
						populatedMovieList.add(populatedMovie);
					}
					
					populatedMovie = curMovie; // after adding the populated movie, set the currently read movie as the new populated movie (Because this is a new movie)
					populatedMovie.getFacets().getReleaseDecade().add(populateMovieDataHelper.getPeriodFromYear(curMovie.getReleaseYear()));
					
					System.out.println("Cur Movie: " + curMovie.getTitle());
					System.out.println("Cur Movie: " + curMovie.getLocationAddress());
					
					// Some rows do not have location data in the original data set
					if (curMovie.getLocationAddress() == null || curMovie.getLocationAddress().trim().isEmpty()) {
						populatedMovie.getMovieLocations().add(new Location("<Unknown>", curMovie.getFunFacts()));
						populatedMovie.getFacets().getNeighbourhood().add("Unknown");
					}
					else {
					Location location = populateMovieDataHelper.fetchLocationDetails(curMovie.getLocationAddress(), curMovie.getFunFacts());
					
					populatedMovie.getMovieLocations().add(location); // fetch and add the location details for the current movie to the location list
					populatedMovie.getFacets().getNeighbourhood().add(location.getNeighbourhood() != null && !location.getNeighbourhood().trim().isEmpty() ? location.getNeighbourhood() : "Unknown");
					
					}
				}
 				else { // this indicates that the previous row and currently fetched row belong to the same movie. So we just add the location to the list
 					if (curMovie.getLocationAddress() == null || curMovie.getLocationAddress().trim().isEmpty()) {
						populatedMovie.getMovieLocations().add(new Location("<Unknown>", curMovie.getFunFacts()));
						populatedMovie.getFacets().getNeighbourhood().add("Unknown");
					}
					else {
					Location location = populateMovieDataHelper.fetchLocationDetails(curMovie.getLocationAddress(), curMovie.getFunFacts());
					populatedMovie.getMovieLocations().add(location); // fetch and add the location details for the current movie to the location list
					populatedMovie.getFacets().getNeighbourhood().add(location.getNeighbourhood() != null && !location.getNeighbourhood().trim().isEmpty() ? location.getNeighbourhood() : "Unknown");
					}
				}
				
				prevMovie = curMovie.getTitle();
			}
			
			
			// After the full iteration is done, the last populatedMovie needs to be added into the movies list
			if (populatedMovie != null) {
				populatedMovieList.add(populatedMovie);
			}
			
			populatedMovieList = populateMovieDataHelper.populateAdditionalMovieInfo(populatedMovieList);
			//populatedMovieList = populateMoviesShotNearby(populatedMovieList);
			mongoOperation.insert(populatedMovieList, Movie.class);
		}
		
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error populating Movie data");
		}
		
	}
	
	public List<Movie> populateMoviesShotNearby(List<Movie> movies) {
		List<Movie> moviesUpdated = new ArrayList<Movie>();
		for (Movie movie: movies) {
			List<Location> movieLocationsUpdated = new ArrayList<Location>();
			for (Location location: movie.getMovieLocations()) {
				if (location.getLocationCoordinates() != null && location.getLocationCoordinates().getCoordinates() != null && (location.getLocationCoordinates().getCoordinates()[0] != 0.0 || location.getLocationCoordinates().getCoordinates()[1] == 0.0)) {
					List<Movie> moviesNearby = movieService.findMoviesNear(location.getLocationCoordinates().getCoordinates()[1], location.getLocationCoordinates().getCoordinates()[0]);
					List<MovieThumbnailDTO> movieThumbnailDTOs = new ArrayList<MovieThumbnailDTO>();
					for (Movie movieNearby: moviesNearby) {
						MovieThumbnailDTO movieThumbnailDTO = new MovieThumbnailDTO();
						movieThumbnailDTO.setId(movieNearby.getId());
						movieThumbnailDTO.setTitle(movieNearby.getTitle());
						if (movieNearby.getAdditionalInfo() != null) {
							movieThumbnailDTO.setPoster(movieNearby.getAdditionalInfo().getPoster());
						}
						else {
							movieThumbnailDTO.setPoster("");
						}
						movieThumbnailDTOs.add(movieThumbnailDTO);
					}
					location.setMoviesShotNearby(movieThumbnailDTOs);
					
				}
				movieLocationsUpdated.add(location);
			}
			movie.setMovieLocations(movieLocationsUpdated);
			moviesUpdated.add(movie);
		}
		return moviesUpdated;
		
	}

	public void setMongoOperation(MongoOperations mongoOperation) {
		this.mongoOperation = mongoOperation;
	}

	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}

	
	
	

}
