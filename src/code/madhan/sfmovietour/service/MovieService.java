package code.madhan.sfmovietour.service;

import org.springframework.data.domain.Page;

import code.madhan.sfmovietour.model.Movie;

public interface MovieService {
	
	public Page<Movie> findAllMovies(int page, int pageSize, String decadeFilter, String neighbourhoodFilter);
	public Movie findMovieById(String id);

}
