package code.madhan.sfmovietour.util;

public class AppConstants {
	
	public static final String GOOGLE_MAPS_API_KEY = "AIzaSyDPpa11BCCOSYjl4lIbQDkf3TBHpx1Y7QU";
	public static final String GEOCODING_SERVICE_ENDPOINT = "https://maps.google.com/maps/api/geocode/json?key="+ GOOGLE_MAPS_API_KEY+"&address=";
	
	
	public static final String SF_DATA_ENDPOINT_URL = "http://data.sfgov.org/resource/yitu-d5am.json";
	public static final String SOCRATA_API_TOKEN = "yD3QpJeg88EgpEgnQqR2wS7cU";
	
	public static final String OMDB_API_ENDPOINT = "http://www.omdbapi.com/?t=";
	
	public static final String TMDB_API_FIND_BY_IMDB_ID = "http://api.themoviedb.org/3/find/{imdb-id}?api_key=212ab682d1afe93df0922f82d65f080a&external_source=imdb_id";
	public static final String TMDB_POSTER_IMAGE_PREFIX = "http://image.tmdb.org/t/p/w500";
	
	public static final String GEO_JSON_TYPE_POINT = "Point";
}
