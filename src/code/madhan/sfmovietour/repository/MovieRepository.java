package code.madhan.sfmovietour.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import code.madhan.sfmovietour.model.Movie;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, String>, CustomMovieRepository{ 
	
	Movie findById(String id);
	
	
}
