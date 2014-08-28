package code.madhan.sfmovietour.helper;

import java.net.URLEncoder;
import java.util.Iterator;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import code.madhan.sfmovietour.model.Location;
import code.madhan.sfmovietour.util.AppConstants;

public class PopulateMovieDataHelper {
	
	public Location fetchLocationDetails(String address) {
		RestTemplate client = new RestTemplate();
		
		String geocodeRequestUrl;
		double[] geocode = new double[2];
		try {
			geocodeRequestUrl = AppConstants.GEOCODING_SERVICE_ENDPOINT + URLEncoder.encode(address, "UTF-8");
			String geocodeResponseString = client.getForObject(geocodeRequestUrl, String.class);
			System.out.println("Response is " + geocodeResponseString);
			
			/*ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readValue(geocodeResponseString, JsonNode.class);
			
			double latitude = node.findValue("lat").asDouble();
			double longitude = node.findValue("lng").asDouble();
			geocode[0] = latitude;
			geocode[1] = longitude;*/
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(geocodeResponseString);
			
			Location location = new Location();
			
			Iterator<JsonNode> itr = rootNode.path("results").path(0).path("address_components").getElements();
			
			boolean neighborhoodFetched = false;
			do {
				JsonNode addressComponent = itr.next();
				
				for (JsonNode typeValue : addressComponent.path("types")) {
					if (typeValue.asText().equals("neighborhood")) {
						location.setNeighborhood(addressComponent.path("long_name").asText());
						neighborhoodFetched = true;
						continue;
					}
				}
			} while (itr.hasNext() && !neighborhoodFetched);
			
			location.setLatitude(rootNode.path("results").path(0).path("geometry").path("location").path("lat").asDouble());
			location.setLongitude(rootNode.path("results").path(0).path("geometry").path("location").path("lng").asDouble());
			
			
			/*double latitude = rootNode.get("lat").asDouble();
			System.out.println("latitude is " + latitude);
			
			double longitude = rootNode.get("lng").asDouble();
			System.out.println("longitude is " + longitude);*/
			return location;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
}
