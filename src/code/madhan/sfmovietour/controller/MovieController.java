package code.madhan.sfmovietour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieController {
	
	@RequestMapping(value="/movies")
	public void populateData() {
		
	}

}
