package code.madhan.sfmovietour.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code.madhan.sfmovietour.model.FacetCount;
import code.madhan.sfmovietour.repository.FacetCountRepository;
import code.madhan.sfmovietour.service.FacetCountService;

@Service
public class FacetCountServiceImpl implements FacetCountService {

	@Autowired 
	FacetCountRepository facetCountRepository;
	
	@Override
	public FacetCount findFacetCountById(String id) {
		return facetCountRepository.findById(id);
	}

	
}
