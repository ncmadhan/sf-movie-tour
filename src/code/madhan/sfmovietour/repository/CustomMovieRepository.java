package code.madhan.sfmovietour.repository;

import java.util.List;

import code.madhan.sfmovietour.model.Movie;

public interface CustomMovieRepository {

	List<Movie> findAllMovies(int page, int pageSize, String filter, String sort);
}
