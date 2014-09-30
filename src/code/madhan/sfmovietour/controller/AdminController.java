package code.madhan.sfmovietour.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import code.madhan.sfmovietour.model.Movie;
import code.madhan.sfmovietour.service.MovieService;
import code.madhan.sfmovietour.service.PopulateMovieDataService;
import code.madhan.sfmovietour.util.AppConstants;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AdminController {
	private static final String POPULATE_MOVIE_DATA = "populateMovieData";
	private static final String POPULATE_MOVIE_POSTER_DATA = "pos";
	
	@Autowired
	private PopulateMovieDataService populateMovieDataService;
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping(value="/admin")
	public void handleAdminActions(@RequestParam(defaultValue=AdminController.POPULATE_MOVIE_DATA) String action) {
		switch (action) {
		case AdminController.POPULATE_MOVIE_DATA:
			populateMovieDataService.populateMovieData();
			break;
			
		default:
			System.out.println("Invalid Action");
			break;
		}
	}

	@RequestMapping(value="/admin/posters")
	public void populatePosterData(@RequestParam int page, @RequestParam int pageSize) {
		Page<Movie> movies = movieService.findAllMovies(page, pageSize);
		List<Movie> movieList = movies.getContent();
		RestTemplate client = new RestTemplate();
		JsonNode rootNode;
		for (Movie movie: movieList) {
			if (movie.getAdditionalInfo() != null && movie.getAdditionalInfo().getImdbID() != null && movie.getAdditionalInfo().getImdbID() != "N/A") {
				String imdbId = movie.getAdditionalInfo().getImdbID();
				String tmdbRequestUrl = AppConstants.TMDB_API_FIND_BY_IMDB_ID.replace("{imdb-id}", imdbId);
				String tmdbResponse = client.getForObject(tmdbRequestUrl, String.class);
				try {
					rootNode = new ObjectMapper().readTree(new StringReader(tmdbResponse));
					String posterPath = rootNode.path("movie_results").path(0).path("poster_path").asText();
					if (posterPath != null && !posterPath.trim().equals("")) {
						String posterFullUrl = AppConstants.TMDB_POSTER_IMAGE_PREFIX + posterPath;
						movie.getAdditionalInfo().setPoster(posterFullUrl);
						movieService.updateMovie(movie);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	public void setPopulateMovieDataService(
			PopulateMovieDataService populateMovieDataService) {
		this.populateMovieDataService = populateMovieDataService;
	}

}
