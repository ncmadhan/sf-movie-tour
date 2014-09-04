package code.madhan.sfmovietour.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import code.madhan.sfmovietour.model.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String>{ 
	
	Movie findById(String id);
	
	
}
