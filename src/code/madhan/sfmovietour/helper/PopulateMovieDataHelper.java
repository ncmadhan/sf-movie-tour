package code.madhan.sfmovietour.helper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import code.madhan.sfmovietour.util.AppConstants;

public class PopulateMovieDataHelper {
	
	public double[] geocodeAddress(String address) {
		RestTemplate client = new RestTemplate();
		
		String geocodeRequestUrl;
		double[] geocode = new double[2];
		try {
			geocodeRequestUrl = AppConstants.GEOCODING_SERVICE_ENDPOINT + URLEncoder.encode(address, "UTF-8");
			String geocodeResponseString = client.getForObject(geocodeRequestUrl, String.class);
			System.out.println("Response is " + geocodeResponseString);
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readValue(geocodeResponseString, JsonNode.class);
			
			double latitude = node.findValue("lat").asDouble();
			double longitude = node.findValue("lng").asDouble();
			
			JsonNode addressArray = new ObjectMapper().readTree(geocodeResponseString).get("address_components");
			
			if (addressArray.isArray()) {
				for (JsonNode arrayObj: addressArray) {
					System.out.println("long name " + arrayObj.get("long_name").asText());
				}
			}
			geocode[0] = latitude;
			geocode[1] = longitude;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	public static void main(String[] args) {
		PopulateMovieDataHelper p = new PopulateMovieDataHelper();
			p.geocodeAddress("100 Block of Union Street, SF");
		
	}
}
