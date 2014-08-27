package code.madhan.sfmovietour.service.impl;

import java.net.URI;

import org.springframework.stereotype.Service;

import code.madhan.sfmovietour.service.PopulateMovieDataService;

import com.socrata.api.HttpLowLevel;
import com.socrata.api.Soda2Consumer;
import com.socrata.exceptions.LongRunningQueryException;
import com.socrata.exceptions.SodaError;
import com.socrata.model.soql.SoqlQuery;
import com.sun.jersey.api.client.ClientResponse;

@Service
public class SFOpenPopulateMovieDataServiceImpl implements PopulateMovieDataService{
	
	@Override
	public void populateMovieData() {
		System.out.println("Autowiring working http://data.sfgov.org/resource/yitu-d5am.json");
		
		Soda2Consumer consumer = Soda2Consumer.newConsumer("http://data.sfgov.org");
		
		try {
			ClientResponse response = consumer.getHttpLowLevel().queryRaw(URI.create("http://data.sfgov.org/resource/yitu-d5am.json"), HttpLowLevel.JSON_TYPE);
			System.out.println(response.getEntity(String.class));
		} catch (LongRunningQueryException | SodaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
