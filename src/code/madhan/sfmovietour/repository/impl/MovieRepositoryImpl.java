package code.madhan.sfmovietour.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;

import code.madhan.sfmovietour.model.Movie;
import code.madhan.sfmovietour.repository.CustomMovieRepository;

public class MovieRepositoryImpl implements CustomMovieRepository {

	private final MongoOperations operations;
	
	@Autowired
	public MovieRepositoryImpl(MongoOperations operations) {
		this.operations = operations;
	}
	
	@Override
	public List<Movie> findAllMovies(int page, int pageSize, String filter, String sort) {
		Query query = new Query();
		String neighbourhoodFilter = null;
		String decadeFilter = null;
		
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
		}
		
		List<Criteria> criteriaList = new ArrayList<Criteria>();
		Criteria filterCriteria = null;
		if (filter != null) {
			if (neighbourhoodFilter != null && !neighbourhoodFilter.equals("") ) {
				Criteria criteria = Criteria.where("facets.neighbourhood").in(neighbourhoodFilter);
				criteriaList.add(criteria);
			}
			if (decadeFilter != null && !decadeFilter.equals("") ) {
				Criteria criteria = Criteria.where("facets.releaseDecade").in(decadeFilter);
				criteriaList.add(criteria);
			}
			
			int i = 0;
			for (Criteria criteria: criteriaList) {
				if (i == 0) {
					filterCriteria = criteria;
				}
				else if (i > 0) {
					filterCriteria.andOperator(criteria);
				}
				i++;
			}
		}
		
		if (filterCriteria != null) {
			query.addCriteria(filterCriteria);
		}
		
		query.with(new PageRequest(page, pageSize));
		
		if (sort != null && !sort.equals("")) {
			if (sort.equals("Year")) {
				sort = "releaseYear";
				query.with(new Sort(Sort.Direction.ASC, sort));
			}
			else if (sort.equals("Imdb-Rating")) {
				sort = "additionalInfo.imdbRating";
				query.with(new Sort(Sort.Direction.DESC, sort));
			}
			
		}
		
		System.out.println("Query executed is: "+ query.toString());
		return operations.find(query, Movie.class);
	}

	@Override
	public List<Movie> findMoviesNear(Double lat, Double lng) {
	/*	Point point = new Point(lng, lat);
		Query query = new Query(Criteria.where("movieLocations.locationCoordinates").near(point).maxDistance(4000));
		//query.limit(4);
		System.out.println("query is " + query.toString());
		List<Movie> movies = operations.find(query, Movie.class);*/
		
		Point point = new Point(lng, lat);
		NearQuery nearQuery = NearQuery.near(point).maxDistance(10, Metrics.MILES).num(4);
		GeoResults<Movie> results = operations.geoNear(nearQuery, Movie.class);
		List<Movie> movies = new ArrayList<Movie>();
		for (GeoResult<Movie> movie: results) {
			movies.add(movie.getContent());
		}
		return movies;
	}

	@Override
	public void updateMovie(Movie movie) {
		operations.save(movie);
		
	}

	
}
