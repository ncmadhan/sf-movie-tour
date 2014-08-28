package code.madhan.sfmovietour.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import scala.annotation.meta.getter;
import code.madhan.sfmovietour.model.Movie;
import code.madhan.sfmovietour.service.PopulateMovieDataService;

@Service
public class SFOpenPopulateMovieDataServiceImpl implements PopulateMovieDataService{
	private static final String SF_DATA_URL = "http://data.sfgov.org/resource/yitu-d5am.json?$limit=1000&$offset=1000";
	
	@Autowired
	MongoOperations mongoOperation;
	
	@Override
	public void populateMovieData() {
		System.out.println("Autowiring working http://data.sfgov.org/resource/yitu-d5am.json");
		
		
		RestTemplate client = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJacksonHttpMessageConverter());
		client.setMessageConverters(messageConverters);
		
		List<Movie> movies = null; 
		try {
			movies = Arrays.asList(client.getForObject(SF_DATA_URL, Movie[].class));
			System.out.println("movies size is " + movies.size());
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		Movie m = movies.get(0);
		mongoOperation.save(m);
		
		
		System.out.println(m.getId());
	}

	public void setMongoOperation(MongoOperations mongoOperation) {
		this.mongoOperation = mongoOperation;
	}

	
	
	

}
