package code.madhan.sfmovietour.repository;

import java.util.List;

import code.madhan.sfmovietour.model.Movie;

public interface CustomMovieRepository {

	public List<Movie> findMoviesUsingFilters(String decadeFilter, String neighbourhoodFilter);
}
