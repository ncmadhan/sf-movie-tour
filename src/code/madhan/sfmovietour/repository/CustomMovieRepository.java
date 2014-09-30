
package code.madhan.sfmovietour.repository;

import java.util.List;

import org.springframework.data.geo.GeoResults;

import code.madhan.sfmovietour.model.Movie;

public interface CustomMovieRepository {

	List<Movie> findAllMovies(int page, int pageSize, String filter, String sort);
	
	List<Movie> findMoviesNear(Double lat, Double lng);
	
	void updateMovie(Movie movie);
}
