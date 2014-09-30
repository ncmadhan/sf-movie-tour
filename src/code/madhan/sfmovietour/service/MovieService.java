package code.madhan.sfmovietour.service;

import java.util.List;

import org.springframework.data.domain.Page;

import code.madhan.sfmovietour.model.Movie;

public interface MovieService {
	
	public Page<Movie> findAllMovies(int page, int pageSize);
	public List<Movie> findAllMovies(int page, int pageSize, String filter, String sort);
	public Movie findMovieById(String id);
	public List<Movie> findMoviesNear(Double lat, Double lng);
	
	public void updateMovie(Movie movie);

}
