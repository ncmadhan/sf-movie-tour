package code.madhan.sfmovietour.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import scala.annotation.meta.getter;
import code.madhan.sfmovietour.helper.PopulateMovieDataHelper;
import code.madhan.sfmovietour.model.Location;
import code.madhan.sfmovietour.model.Movie;
import code.madhan.sfmovietour.service.PopulateMovieDataService;

@Service
public class SFOpenPopulateMovieDataServiceImpl implements PopulateMovieDataService{
	
	
	@Autowired
	MongoOperations mongoOperation;
	
	@Override
	public void populateMovieData() {
		System.out.println("Autowiring working http://data.sfgov.org/resource/yitu-d5am.json");
		PopulateMovieDataHelper populateMovieDataHelper = new PopulateMovieDataHelper();
		
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
				
				if (!curMovie.getTitle().equals(prevMovie)) { // if the current row indicates a new movie
					if (populatedMovie != null) { // add the populated movie to the final movie list. Checking for null to handle the first row, when the populated movie object is null
						populatedMovieList.add(populatedMovie);
					}
					
					populatedMovie = curMovie; // after adding the populated movie, set the currently read movie as the new populated movie (Because this is a new movie)
					
					System.out.println("Cur Movie: " + curMovie.getTitle());
					System.out.println("Cur Movie: " + curMovie.getLocationAddress());
					
					// Some rows do not have location data in the original data set
					if (curMovie.getLocationAddress() == null || curMovie.getLocationAddress().trim().isEmpty()) {
						populatedMovie.getMovieLocations().add(new Location("<Unknown>", curMovie.getFunFacts()));
					}
					else {
					Location location = populateMovieDataHelper.fetchLocationDetails(curMovie.getLocationAddress(), curMovie.getFunFacts());
					populatedMovie.getMovieLocations().add(location); // fetch and add the location details for the current movie to the location list
					}
				}
 				else { // this indicates that the previous row and currently fetched row belong to the same movie. So we just add the location to the list
 					if (curMovie.getLocationAddress() == null || curMovie.getLocationAddress().trim().isEmpty()) {
						populatedMovie.getMovieLocations().add(new Location("<Unknown>", curMovie.getFunFacts()));
					}
					else {
					Location location = populateMovieDataHelper.fetchLocationDetails(curMovie.getLocationAddress(), curMovie.getFunFacts());
					populatedMovie.getMovieLocations().add(location); // fetch and add the location details for the current movie to the location list
					}
				}
				
				prevMovie = curMovie.getTitle();
			}
			
			// After the full iteration is done, the last populatedMovie needs to be added into the movies list
			if (populatedMovie != null) {
				populatedMovieList.add(populatedMovie);
			}
			
			mongoOperation.insert(populatedMovieList, Movie.class);
		}
		
		System.out.println(populatedMovieList.size());
	}

	public void setMongoOperation(MongoOperations mongoOperation) {
		this.mongoOperation = mongoOperation;
	}

	
	
	

}
