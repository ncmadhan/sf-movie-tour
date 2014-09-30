package code.madhan.sfmovietour.helper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import code.madhan.sfmovietour.model.GeoJsonCoordinates;
import code.madhan.sfmovietour.model.Location;
import code.madhan.sfmovietour.model.Movie;
import code.madhan.sfmovietour.model.MovieAdditionalInfo;
import code.madhan.sfmovietour.util.AppConstants;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author maddy 
 * 
 * Helper Class for populating movie data from APIs into DB
 */
public class PopulateMovieDataHelper {

	/**
	 * Converts a given String address into lat, long, neighborhood
	 * 
	 * @param address
	 * @return Location object
	 */
	public Location fetchLocationDetails(String address, String funFacts) {
		
		//address = address + " SF";
		
		if (address.indexOf("(") > 0 && address.indexOf(")") > 0) {
			address = address.substring(address.indexOf("(")+1, address.indexOf(")"));
		}
		
		address += " SF";
		
		Location location = new Location(address, funFacts);
		String geocodeResponseString;
		try {
			
			geocodeResponseString = fetchGeocodeApiResponse(address, false);
			System.out.println("Response is " + geocodeResponseString);

			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(geocodeResponseString);
			
			JsonNode refinedRootNode = null;
			
			// Check if location is empty or approximate
			if (isLocationEmpty(rootNode) || isLocationApproximate(rootNode)) {
				
				// Attempt to refine the location
				refinedRootNode = attemptToRefineLocation(address);
				// refinedRootNode is returned as null if refining failed
				
			}
			
			
			if (refinedRootNode == null && isLocationEmpty(rootNode)) {
				// this means that refining failed, and Google's API also sent ZERO_RESULTS
				Double[] coordinates = new Double[2];
				coordinates[0] = 0.0;
				coordinates[1] = 0.0;
				
				location.setLocationCoordinates(new GeoJsonCoordinates(AppConstants.GEO_JSON_TYPE_POINT, coordinates));
				location.setNeighborhood("NA");
				return location;
			}
			else if (refinedRootNode == null && isLocationApproximate(rootNode)) {
				// this means that refining failed. original location fetched was approximate
				location.setApproximate(true);
			}
			else if (refinedRootNode != null) {
				// refining has succeeded. Set rootNode to the new refinedRootNode
				rootNode = refinedRootNode;
			}
			
			Iterator<JsonNode> itr = rootNode.path("results").path(0)
					.path("address_components").elements();

			Double[] coordinates = new Double[2];
			coordinates[0] = rootNode.path("results").path(0)
					.path("geometry").path("location").path("lng").asDouble();
			coordinates[1] = rootNode.path("results").path(0)
					.path("geometry").path("location").path("lat").asDouble();
			location.setLocationCoordinates(new GeoJsonCoordinates(AppConstants.GEO_JSON_TYPE_POINT, coordinates));
			
			
			//Get lat and long
/*			location.setLatitude(rootNode.path("results").path(0)
					.path("geometry").path("location").path("lat").asDouble());
			location.setLongitude(rootNode.path("results").path(0)
					.path("geometry").path("location").path("lng").asDouble());
*/
			// Iterate over the address_component array to find the Neighborhood name
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
	
	
	/**
	 * Check if the response received the Google Geocode API 
	 * is empty (location_type:ZERO_RESULTS) or approximate (location_type:APPROXIMATE)
	 * @param rootNode
	 * @return
	 */
	public boolean isLocationEmpty(JsonNode rootNode) {
		if ((rootNode.path("status") != null && rootNode.path("status")
				.asText().equals("ZERO_RESULTS"))) {
			return true;
		} 
		
		return false;
	}
	
	public boolean isLocationApproximate(JsonNode rootNode) {
		if ((rootNode.path("results").path(0).path("formatted_address").asText().equals("San Francisco, CA, USA"))) {
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * Attempt to provide better accuracy location data for the address
	 * 
	 * @param address - The address that has previously given empty or approximate results
	 * @return JsonNode of the Geocode API response parsed
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public JsonNode attemptToRefineLocation(String address) throws JsonProcessingException, IOException {
		System.out.println("Address " + address + " has to be refined");
		String geocodeResponseString;
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = null;
		// Check if the address is present within brackets
		/*if (address.indexOf("(") > 0 && address.indexOf("(") > 0) {
			address = address.substring(address.indexOf("("), address.indexOf("("));
			address += " SF";
		}
		
		*/
		
		
		String attemptAddress = "";
		if (address.contains("St")) {
		
			String[] words = address.split(" ");
			
			int i = -1;
			for (String word: words) {
				i++;
				if (word.contains("St") && i != 0) {
					attemptAddress = words[i-1] + " " + words[i];
					continue;
				}
			}
			
			attemptAddress += " SF";
			System.out.println("Street name only address is " + attemptAddress);
			geocodeResponseString = fetchGeocodeApiResponse(attemptAddress, true);
			rootNode = mapper.readTree(geocodeResponseString);
			System.out.println("Response after extracting Street name: " + geocodeResponseString);
		}
		else {
			return null;
		}
		
		if (isLocationEmpty(rootNode) || isLocationApproximate(rootNode)) {
			return null;
		}
		
		return rootNode;
	}
	
	
	/**
	 * Fetch the response from Google Geocode API
	 * 
	 * @param address - The raw address fetched from API
	 * @param addressNotFoundDefaultToSF - If addressNotFoundDefaultToSF is true, then the location defaults to the center of SF when there is no match for the address 
	 * @return Geocode API response Json as String
	 * @throws UnsupportedEncodingException
	 */
	public String fetchGeocodeApiResponse(String address, boolean addressNotFoundDefaultToSF) throws UnsupportedEncodingException {
		RestTemplate client = new RestTemplate();
		StringBuilder geocodeRequestUrl = new StringBuilder(AppConstants.GEOCODING_SERVICE_ENDPOINT).append(URLEncoder.encode(address, "UTF-8"));
		/*if (addressNotFoundDefaultToSF) {
			geocodeRequestUrl.append("&components=").append(URLEncoder.encode("locality:SF|country:US", "UTF-8"));
		}*/
		
		String geocodeResponseString = client.getForObject(
				geocodeRequestUrl.toString(), String.class);
		return geocodeResponseString;
	}
	
	
	/**
	 * Fetch the complete list of movies in its raw form from SF Open Data API
	 * @return
	 */
	public List<Movie> fetchSFMovieApiResponse() {
		RestTemplate client = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJackson2HttpMessageConverter());
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
					+ "?$order=" + "title" + "&$offset=0&$limit=2000";
			
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
	
	public List<Movie> populateAdditionalMovieInfo(List<Movie> movies) throws UnsupportedEncodingException {
		RestTemplate client = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
		jacksonConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
		
		messageConverters.add(jacksonConverter);
		client.setMessageConverters(messageConverters);
		
		List<Movie> additionalPopulatedMoviesList = new ArrayList<Movie>();
		for (Movie movie: movies) {
			String omdbApiRequestUrl = AppConstants.OMDB_API_ENDPOINT + movie.getTitle();
			MovieAdditionalInfo additionalInfo = client.getForObject(omdbApiRequestUrl, MovieAdditionalInfo.class);
			if (additionalInfo != null && additionalInfo.getImdbRating() != null && additionalInfo.getImdbRating().equals("N/A")) {
				additionalInfo.setImdbRating("-1");
			}
			movie.setAdditionalInfo(additionalInfo);
			additionalPopulatedMoviesList.add(movie);
		}
		return additionalPopulatedMoviesList;
	}
	
	public String getPeriodFromYear(int releaseYear) {
		if (releaseYear >= 1900 && releaseYear < 1910) {
			return "1900s";
		}
		else if (releaseYear >= 1910 && releaseYear < 1920) {
			return "1910s";
		}
		else if (releaseYear >= 1920 && releaseYear < 1930) {
			return "1920s";
		}
		else if (releaseYear >= 1930 && releaseYear < 1940) {
			return "1930s";
		}
		else if (releaseYear >= 1940 && releaseYear < 1950) {
			return "1940s";
		}
		else if (releaseYear >= 1950 && releaseYear < 1960) {
			return "1950s";
		}
		else if (releaseYear >= 1960 && releaseYear < 1970) {
			return "1960s";
		}
		else if (releaseYear >= 1970 && releaseYear < 1980) {
			return "1970s";
		}
		else if (releaseYear >= 1980 && releaseYear < 1990) {
			return "1980s";
		}
		else if (releaseYear >= 1990 && releaseYear < 2000) {
			return "1990s";
		}
		else if (releaseYear >= 2000 && releaseYear < 2010) {
			return "2000s";
		}
		else if (releaseYear >= 2010 && releaseYear < 2020) {
			return "2010s";
		}
		return "Unknown Decade";
	}
	public static void main(String[] args) {
		PopulateMovieDataHelper p = new PopulateMovieDataHelper();
		Location l = p.fetchLocationDetails("(399 Embarcadero)", "");
		//System.out.println(l.getLatitude());
	}
}
