package code.madhan.sfmovietour.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import code.madhan.sfmovietour.model.FacetCount;
import code.madhan.sfmovietour.model.Movie;
import code.madhan.sfmovietour.model.MovieThumbnailDTO;
import code.madhan.sfmovietour.model.MoviesDTO;
import code.madhan.sfmovietour.service.FacetCountService;
import code.madhan.sfmovietour.service.MovieService;

@Controller
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	FacetCountService facetCountService;
	
	@RequestMapping(value="/movies")
	public String getMovieListView(Model model, @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="page_size", defaultValue="10") int pageSize) {
		System.out.println("movlistpage: " + page + " pageSize: " + pageSize);
		//model.addAttribute("movies", getMovieListMarshalled(page, pageSize));
		return "movies1";
	}
	
	public String getFacetCountId(String filter) {
		String neighbourhoodFilter = null;
		String decadeFilter = null;
		String facetCountId = "";
		List<String> facets = new ArrayList<String>();
		if (filter != null && !filter.equals("")) {
			String filters[] = filter.split(",");
			
			for (String filterValue : filters) {
				if (filterValue.split(":")[0].equals("neighbourhood")) {
					neighbourhoodFilter = (!filterValue.split(":")[1].equals("all")) ? filterValue.split(":")[1] : ""; 
				}
				if (filterValue.split(":")[0].equals("releaseDecade")) {
					decadeFilter = (!filterValue.split(":")[1].equals("all")) ? filterValue.split(":")[1] : ""; 
				}
			}
			if (decadeFilter != null && !decadeFilter.equals("")) {
				facets.add("releaseDecade:" + decadeFilter);
			}
			if (neighbourhoodFilter != null && !neighbourhoodFilter.equals("")) {
				facets.add("neighbourhood:"+neighbourhoodFilter);
			}
			
			if (facets != null && !facets.isEmpty()) {
				facetCountId = StringUtils.join(facets, "|");
				facetCountId = "|"+facetCountId+"|";
			}
		}
		
		
		System.out.println("Facet count ID is " + facetCountId);
		return facetCountId;
		
	}
	@RequestMapping(value="/movies", produces={"application/json"})
	public @ResponseBody MoviesDTO getMovieListMarshalled(@RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="page_size", defaultValue="10") int pageSize,
			@RequestParam(value="filter", defaultValue="") String filter, @RequestParam(value="sort", defaultValue="") String sort) {
		System.out.println("page: " + page + " pageSize: " + pageSize);
		System.out.println("filter " + filter + "sort " + sort);
		List<Movie> movies = new ArrayList<Movie>();
		MoviesDTO moviesDTO = new MoviesDTO();
	
		movies = movieService.findAllMovies(page, pageSize, filter, sort);
		
		
		String facetCountId = getFacetCountId(filter);
		
		FacetCount metaFacets = facetCountService.findFacetCountById(facetCountId);
		
		moviesDTO.setMovies(movies);
		moviesDTO.setMetaFacets(metaFacets);
		
		System.out.println("Size of movies is " + movies.size());
		return moviesDTO;
	}
	
	@RequestMapping(value="/movies/near", produces={"application/json"})
	public @ResponseBody List<MovieThumbnailDTO> findMoviesNear(@RequestParam(value="lat", defaultValue="0.0") Double lat, @RequestParam(value="lng", defaultValue="0.0") Double lng) {
		List<Movie> moviesNearby = new ArrayList<Movie>();
		
		if (lat != 0.0 && lng != 0.0) {
			moviesNearby = movieService.findMoviesNear(lat, lng);
		}
		
		List<MovieThumbnailDTO> movieThumbnailDTOs = new ArrayList<MovieThumbnailDTO>();
		for (Movie movieNearby: moviesNearby) {
			MovieThumbnailDTO movieThumbnailDTO = new MovieThumbnailDTO();
			movieThumbnailDTO.setId(movieNearby.getId());
			movieThumbnailDTO.setTitle(movieNearby.getTitle());
			if (movieNearby.getAdditionalInfo() != null) {
				movieThumbnailDTO.setPoster(movieNearby.getAdditionalInfo().getPoster());
			}
			else {
				movieThumbnailDTO.setPoster("");
			}
			movieThumbnailDTOs.add(movieThumbnailDTO);
		}
		return movieThumbnailDTOs;
		
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
