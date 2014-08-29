package code.madhan.sfmovietour.helper;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import code.madhan.sfmovietour.model.Location;
import code.madhan.sfmovietour.model.Movie;
import code.madhan.sfmovietour.util.AppConstants;

/**
 * 
 * @author maddy Helper Class for populating movie data from APIs into DB
 */
public class PopulateMovieDataHelper {

	/**
	 * Converts a given String address into lat, long, neighborhood
	 * 
	 * @param address
	 * @return Location object
	 */
	public Location fetchLocationDetails(String address, String funFacts) {
		RestTemplate client = new RestTemplate();
		Location location = new Location(address, funFacts);
		String geocodeRequestUrl;
		try {

			/**
			 * Fetch location details from address using Google Geocoding
			 * service
			 */
			geocodeRequestUrl = AppConstants.GEOCODING_SERVICE_ENDPOINT
					+ URLEncoder.encode(address, "UTF-8");
			String geocodeResponseString = client.getForObject(
					geocodeRequestUrl, String.class);
			System.out.println("Response is " + geocodeResponseString);

			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(geocodeResponseString);
			
			
			if (rootNode.path("status") != null && rootNode.path("status").asText().equals("ZERO_RESULTS")) {
				return null;
			}
			Iterator<JsonNode> itr = rootNode.path("results").path(0)
					.path("address_components").getElements();

			/**
			 * Get lat and long
			 */
			location.setLatitude(rootNode.path("results").path(0)
					.path("geometry").path("location").path("lat").asDouble());
			location.setLongitude(rootNode.path("results").path(0)
					.path("geometry").path("location").path("lng").asDouble());

			/**
			 * Iterate over the address_component array to find the Neighborhood
			 * name
			 */
			boolean neighborhoodFetched = false;
			do {
				JsonNode addressComponent = itr.next();

				for (JsonNode typeValue : addressComponent.path("types")) {
					if (typeValue.asText().equals("neighborhood")) {
						location.setNeighborhood(addressComponent.path(
								"long_name").asText());
						neighborhoodFetched = true;
						continue;
					}
				}
			} while (itr.hasNext() && !neighborhoodFetched);

			return location;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Movie> fetchRawMovieDataFromSFOpenAPI() {
		RestTemplate client = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJacksonHttpMessageConverter());
		client.setMessageConverters(messageConverters);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-App-Token", AppConstants.SOCRATA_API_TOKEN);
		headers.add("Accept", "application/json");
		List<Movie> movies = new ArrayList<Movie>();
		try {
			int offset = 0;
			int limit = 1000;

			boolean hasMoreData = true;
			
			
			/*do {
				List<Movie> moviePageList = new ArrayList<Movie>();
				String sfDataRequestUrl = AppConstants.SF_DATA_ENDPOINT_URL
						+ "?$order=" + "title" + "&$offset=" + offset
						+ "&$limit=" + limit;
				
				moviePageList = Arrays.asList(client.getForObject(
						sfDataRequestUrl, Movie[].class));
				if (moviePageList == null) {
					hasMoreData = false;
				} else if ((moviePageList != null && moviePageList.size() < 1000)) {
					movies.addAll(moviePageList);
					hasMoreData = false;
				} else {
					movies.addAll(moviePageList);
				}
				offset += 1000;
			} while (hasMoreData);*/
			
			// Test Data
			List<Movie> moviePageList = new ArrayList<Movie>();
			String sfDataRequestUrl = AppConstants.SF_DATA_ENDPOINT_URL
					+ "?$order=" + "title" + "&$offset=0&$limit=20";
			
			moviePageList = Arrays.asList(client.getForObject(
					sfDataRequestUrl, Movie[].class));
			movies.addAll(moviePageList);
			// End Test Data
			
			System.out.println("movies size is " + movies.size());
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		return movies;
	}
	
	public static void main(String[] args) {
		PopulateMovieDataHelper p = new PopulateMovieDataHelper();
		Location l = p.fetchLocationDetails("(399 Embarcadero)", "");
		System.out.println(l.getLatitude());
	}
}
