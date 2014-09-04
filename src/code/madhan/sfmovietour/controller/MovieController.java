package code.madhan.sfmovietour.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import code.madhan.sfmovietour.model.Movie;
import code.madhan.sfmovietour.service.MovieService;

@Controller
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@RequestMapping(value="/movies")
	public String viewMovieList(Model model) {
		Page<Movie> moviesPageList = movieService.findAllMovies();
		List<Movie> movies = new ArrayList<Movie>();
		
		Iterator<Movie> itr = moviesPageList.iterator();
		while (itr.hasNext()) {
			movies.add(itr.next());
		}
		
		model.addAttribute("movies", movies);
		return "movies";
	}
	
	@RequestMapping(value="/movies/{movieId}")
	public String viewMovieById(Model model, @PathVariable String movieId) {
		Movie movie = movieService.findMovieById(movieId);
		model.addAttribute("movie", movie);
		return "movie";
	}

	public MovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}
	
	

}
