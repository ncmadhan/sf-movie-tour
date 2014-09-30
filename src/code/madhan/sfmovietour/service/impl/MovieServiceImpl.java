package code.madhan.sfmovietour.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import code.madhan.sfmovietour.model.Movie;
import code.madhan.sfmovietour.repository.MovieRepository;
import code.madhan.sfmovietour.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;
	
	
	@Override
	public Page<Movie> findAllMovies(int page, int pageSize) {
		return movieRepository.findAll(new PageRequest(page, pageSize));
	}
	
	@Override
	public List<Movie> findAllMovies(int page, int pageSize, String filter, String sort) {
		return movieRepository.findAllMovies(page, pageSize, filter, sort);
	}
	

	@Override
	public Movie findMovieById(String id) {
		return movieRepository.findById(id);
	}
	

	public MovieRepository getMovieRepository() {
		return movieRepository;
	}

	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public List<Movie> findMoviesNear(Double lat, Double lng) {
		return movieRepository.findMoviesNear(lat, lng);
	}

	@Override
	public void updateMovie(Movie movie) {
		movieRepository.updateMovie(movie);
	}
	
	
	
}
