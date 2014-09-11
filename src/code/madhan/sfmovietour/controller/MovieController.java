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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import code.madhan.sfmovietour.model.Movie;
import code.madhan.sfmovietour.service.MovieService;

@Controller
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@RequestMapping(value="/movies")
	public String getMovieListView(Model model, @RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="page_size", defaultValue="10") int pageSize) {
		model.addAttribute("movies", getMovieListMarshalled(page, pageSize));
		return "movies1";
	}
	
	@RequestMapping(value="/movies", produces={"application/json"})
	public @ResponseBody List<Movie> getMovieListMarshalled(@RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="page_size", defaultValue="10") int pageSize) {
		Page<Movie> moviesPageList = movieService.findAllMovies(page, pageSize);
		List<Movie> movies = new ArrayList<Movie>();
		
		Iterator<Movie> itr = moviesPageList.iterator();
		while (itr.hasNext()) {
			movies.add(itr.next());
		}
		return movies;
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
