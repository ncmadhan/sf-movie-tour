package code.madhan.sfmovietour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import code.madhan.sfmovietour.service.PopulateMovieDataService;

@Controller
public class AdminController {
	private static final String POPULATE_MOVIE_DATA = "populateMovieData";
	
	@Autowired
	private PopulateMovieDataService populateMovieDataService;
	
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

	public void setPopulateMovieDataService(
			PopulateMovieDataService populateMovieDataService) {
		this.populateMovieDataService = populateMovieDataService;
	}

}
