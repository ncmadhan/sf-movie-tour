package code.madhan.sfmovietour.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import code.madhan.sfmovietour.model.FacetCount;

@Repository
public interface FacetCountRepository extends MongoRepository<FacetCount, Serializable> {

	FacetCount findById(String id);
}
