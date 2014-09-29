/**
 * Global variables that store the current page, filter and sort
 */
var _page = 0;
var _filterSort = "";
var _filters = {};

$( document ).ready( function () {

	/**
	 * Bind onhashchange. This is called everytime $.bbq calls pushState. pushState is called everytime a filter or sort is clicked
	 */
	$( window ).bind( 'hashchange', function ( e ) {
		var $container = $( "#contentDiv" );
		var urlPath = $.param.fragment();
		var params = $.deparam.querystring( urlPath );
		
		_filterSort = urlPath;
		_page = 0;
		
		$container.isotope( 'destroy' );
		console.log( "Going to get movies " + params["filter"] );
		initFilterSortDropdowns( params[ "filter" ], params[ "sort" ] );
		
		getMovies();
	} );

	// Set the decade filter dropdown text to selected value
	$( "#decadeDropdown .dropdown-menu" ).on( 'click', 'li a', function () {
		$( "#decadeDropdown .btn:first-child" ).text( $( this ).text() );
		$( "#decadeDropdown .btn:first-child" ).val( $( this ).text() );
	} );

	/*
	 * Set the neighbourhood filter dropdown text to selected value TODO: need to use a more generic way to set value of dropdown. Maybe adding more filters
	 */

	$( "#neighbourhoodDropdown .dropdown-menu" ).on( 'click', 'li a', function () {
		$( "#neighbourhoodDropdown .btn:first-child" ).text( $( this ).text() );
		$( "#neighbourhoodDropdown .btn:first-child" ).val( $( this ).text() );
	} );

	/**
	 * Set sort dropdown value on select
	 */
	$( "#sortDropdown .dropdown-menu" ).on( 'click', 'li a', function () {
		$( "#sortDropdown .btn:first-child" ).text( $( this ).text() );
		$( "#sortDropdown .btn:first-child" ).val( $( this ).text() );
	} );

	/*
	 * Show caption on movie hover
	 */
	$( document ).on( 'mouseenter', '.movie-poster', function () {
		
		$( this ).find( '.caption' ).removeClass( "fadeOut" ).addClass( "fadeIn" ).show();
	} );

	$( document ).on( 'mouseleave', '.movie-poster', function () {
		
		$( this ).find( '.caption' ).removeClass( "fadeIn" ).addClass( "fadeOut" );
	} );

	/*
	 * Filter dropdown click handler. Pushes the state to the hash using .bbq Onhashchange will handle the actual fetch of data based on selected value. Every time filter is clicked, the data in contentDiv is emptied and repopulated.
	 */
	$( document ).on( 'click', '.filter', function ( e ) {
		e.preventDefault();

		// data-filter has values of the form "neighbourhood:nobhill", "releaseDecade:1990s"
		thisdata = $( this ).attr( 'data-filter' );
		var state = {};
		var filterParts = thisdata.split( ':' );

		// storing in map ensures the value of neighbourhood or decade filter,if already exists, gets updated instead of appended
		if (filterParts[1] == null || filterParts[1] == "" || filterParts[1] == "all") {
			delete _filters[filterParts[0]];
		}
		else {
			_filters[ filterParts[ 0 ] ] = filterParts[ 1 ];
		}
		
		//_filters[ filterParts[ 0 ] ] = filterParts[ 1 ];
		state[ "filter" ] = generateFilterHash( _filters );

		// infinitescroll custom variable "filter" which takes the filter in the form "&filter=<neighbourhood:value>,<releaseDecade:value>"
		$.infinitescroll.prototype.filter = "&filter=" + generateFilterHash( _filters );

		// push the state map to hash
		$.bbq.pushState( state );

	} );

	/*
	 * Sort dropdown click handler. As with filter, pushes state to hash Also, sets the infinitescroll custom variable "sort"
	 */
	$( document ).on( 'click', '.sort', function ( e ) {
		e.preventDefault();
		thisdata = $( this ).attr( 'data-filter' );
		var state = {};
		if (thisdata == "default") {
			thisdata = "";
		}
		state[ "sort" ] = thisdata;

		// custom infinite scroll variable
		$.infinitescroll.prototype.sort = "&sort=" + thisdata;
		
		$.bbq.pushState( state );

	} );

	// Trigger hashchange to get the initial set of movies and use any filter,sort hash if already present in the url
	$( window ).trigger( 'hashchange' );

} );

/**
 * Intializes the filter and sort dropdowns based on the hash url values.
 * TODO: Horribly implemented with no generic use of filter or sort dropdown values.
 * Change to use a more efficient and cleaner way to implement this.
 * 
 **/
function initFilterSortDropdowns ( filterValue, sortValue ) {
	console.log( "filtervalue " + filterValue );
	console.log( "sortvalue " + sortValue );
	var filterParts = [];
	var hasNeighbourhoodFilter = false;
	var hasDecadeFilter = false;
	
	if ( filterValue != undefined && filterValue != null && filterValue != "" ) {
		filterParts = filterValue.split( "," );
		$.each( filterParts, function ( index, value ) {
			if ( value != null && value != "" ) {
				var filterPart = [];
				filterPart = value.split( ":" );
				if ( filterPart[ 0 ] == "neighbourhood" ) {
					if ( ( filterPart[ 1 ] != null) ) {
						console.log( "filter part is neighbourhood " + filterPart[ 1 ] );
						hasNeighbourhoodFilter = true;
						$( "#neighbourhoodDropdown .btn:first-child" ).text( filterPart[ 1 ] );
						$( "#neighbourhoodDropdown .btn:first-child" ).val( filterPart[ 1 ] );
					} else {
						$( "#neighbourhoodDropdown .btn:first-child" ).text( "All Neighbourhoods" );
						$( "#neighbourhoodDropdown .btn:first-child" ).val( "All Neighbourhoods" );
					}
				}
				if ( filterPart[ 0 ] == "releaseDecade" ) {

					if ( filterPart[ 1 ] != null) {
						hasDecadeFilter = true;
						console.log( "filter part is decade " + filterPart[ 1 ] );
						$( "#decadeDropdown .btn:first-child" ).text( filterPart[ 1 ] );
						$( "#decadeDropdown .btn:first-child" ).val( filterPart[ 1 ] );
					} else {
						$( "#decadeDropdown .btn:first-child" ).text( "Any Year" );
						$( "#decadeDropdown .btn:first-child" ).val( "Any Year" );
					}
				}
			}
		} );
	}

	else {
		$( "#neighbourhoodDropdown .btn:first-child" ).text( "All Neighbourhoods" );
		$( "#neighbourhoodDropdown .btn:first-child" ).val( "All Neighbourhoods" );

		$( "#decadeDropdown .btn:first-child" ).text( "Any Year" );
		$( "#decadeDropdown .btn:first-child" ).val( "Any Year" );
	}
	
	if (hasNeighbourhoodFilter == false) {
		$( "#neighbourhoodDropdown .btn:first-child" ).text( "All Neighbourhoods" );
		$( "#neighbourhoodDropdown .btn:first-child" ).val( "All Neighbourhoods" );
	}
	
	if (hasDecadeFilter == false) {
		$( "#decadeDropdown .btn:first-child" ).text( "Any Year" );
		$( "#decadeDropdown .btn:first-child" ).val( "Any Year" );
	}

	if ( sortValue != undefined && sortValue != null && sortValue != "" ) {
		$( "#sortDropdown .btn:first-child" ).text( sortValue );
		$( "#sortDropdown .btn:first-child" ).val( sortValue );
	} else {
		$( "#sortDropdown .btn:first-child" ).text( "Default (Name)" );
		$( "#sortDropdown .btn:first-child" ).val( "Default (Name)" );
	}
}

/**
 * Convert filter map into <filter-field>:<value>,<filter-field>:<value>
 * 
 * @param filterMap
 * @returns the converted map in string format
 */
function generateFilterHash ( filterMap ) {
	var filterValues = [];
	$.each( filterMap, function ( key, value ) {
			filterValues.push( key + ":" + value );		
	} );
	return filterValues.join( "," );
}

/**
 * If a movie does not have poster image, set placeholder image
 * 
 * @param image
 * @returns {Boolean}
 */
function imgError ( image ) {
	image.onerror = "";
	image.src = "http://placehold.it/300x200&text=Movie+Image+NA";
	return true;
}

/**
 * Convert the result of /movies.json into DOM elements
 * 
 * @param result
 * @returns {String}
 */
function generateItemDom ( result ) {

	// Populate total movie count
	if ( result.metaFacets != null ) {
		$( "#movieCount" ).text( result.metaFacets.value.count );
	}

	// Populate Decade Filter dropdown
	$( "#decadeFilter" ).empty();
	var decadeFilterOutput = "";
	decadeFilterOutput += "<li><a class='filter' tabindex='-1' href='#' data-filter='releaseDecade:all'>Any Year</a></li>";
	
	
	if (result.metaFacets.value.facets != null) {
		
		if (result.metaFacets.value.facets[ "releaseDecade" ] != null) {
			decadeFilterOutput += "<li class='divider'></li>";
		$.each( result.metaFacets.value.facets[ "releaseDecade" ], function ( key, value ) {
			console.log( key + value );
			decadeFilterOutput += "<li><a class='filter' tabindex='-1' href='#' data-filter='releaseDecade:" + key + "'>" + key + " (" + value + ")" + "</a></li>";
		} );
		}
	}
	
	$( "#decadeFilter" ).append( decadeFilterOutput );

	// Populate Neighbourhood Filter dropdown
	$( "#neighbourhoodFilter" ).empty();
	var neighbourhoodFilterOutput = "";
	neighbourhoodFilterOutput += "<li><a class='filter' tabindex='-1' href='#' data-filter='neighbourhood:all'>All Neighbourhoods</a></li>";
	
	if (result.metaFacets.value.facets != null) {
	
		
		if (result.metaFacets.value.facets[ "neighbourhood" ] != null) {
			neighbourhoodFilterOutput += "<li class='divider'></li>";
		$.each( result.metaFacets.value.facets[ "neighbourhood" ], function ( key, value ) {
			neighbourhoodFilterOutput += "<li><a class='filter' tabindex='-1' href='#' data-filter='neighbourhood:" + key + "'>" + key + " (" + value + ")" + "</a></li>";
		} );
		}
	
	}
	
	$( "#neighbourhoodFilter" ).append( neighbourhoodFilterOutput );

	
	// Iterate through the movies in the result and create the grid
	var output = "";
	$.each( result.movies, function ( i, value ) {

		output += "<div class='item col-sm-4 col-lg-2' style='width:24.5%'> \
								<div class='panel panel-default movie-poster'> \
								<div style='min-height: 2; max-height: 2;' class='panel-body'> \
								<div id='hover-caption' style='text-align: center' class='caption animated'>";
		var plot = result.movies[ i ].additionalInfo.Plot;
		var country = result.movies[ i ].additionalInfo.Country;
		var neighbourhoods = [];
		neighbourhoods = result.movies[i].facets.neighbourhood;
		/*if (country != null) {
			output += "<p><strong>Country: </strong>";
			output += "<em>" + country + "</em></p><hr>";
		}
		
		/*if (plot != null) {
			output += "<h4>Plot</h4>";
			output += "<p>" + plot + "</p><hr>";
		}*/
		
		if (neighbourhoods != null && neighbourhoods != []) {
			if (neighbourhoods.length == 1 && neighbourhoods[0] == "Unknown") {
				output += "<p><em>Exact Neighbourhood Name Unknown</em></p>";
			}
			else {
				output += "<h4>Shot in Neighbourhoods</h4>";
				output += "<p><em>" + neighbourhoods.join() + "</em></p>";
			}
		}
		
		output += "<p><a style='width: 100%' class='btn btn-info' href='/movies/"+result.movies[i].id+"'>Explore</a></p></div>";
		var imdbRating = (result.movies[i].additionalInfo.imdbRating == null || result.movies[i].additionalInfo.imdbRating == "-1") ? "N/A" : result.movies[i].additionalInfo.imdbRating;
		output += "<img src='" + result.movies[ i ].additionalInfo.Poster + "'class='img-responsive' style='min-height:200px' onerror='imgError(this);'/></div> \
		                         <div class='panel-footer'><p><span class='thin pull-left'><strong>" + result.movies[ i ].title
				+ "</strong></span> \
		                         <span class='pull-right'>" + result.movies[ i ].release_year + "</span></p><br/> \
		                         <p class='thin'><span class='glyphicon glyphicon-facetime-video'/> Shot in " + result.movies[ i ].movieLocations.length
				+ " location(s) in SF</em><span class='pull-right label label-info'>"+imdbRating +"</p> \
		                         </div></div></div>";

	} );
	return output;
}

/**
 * Initialize jquery.isotope, infinitescroll. Should be called after dom elements are loaded
 */
function isotopize () {
	var $container = $( '#contentDiv' );
	$container.isotope( {
		itemSelector : '.item',
		layoutMode : 'masonry',
		masonry : {
			columnWidth : 20,
			gutter : 10
		}
	} );

	$container.imagesLoaded( function () {
		console.log( "images loaded" );
		$container.isotope( 'layout' );
	} );

	$container.infinitescroll( {
		navSelector : '#page-nav', // selector for the paged navigation
		nextSelector : '#page-nav a', // selector for the NEXT link (to page 2)
		itemSelector : '.item', // selector for all items you'll retrieve
		loading : {
			selector : '#load-more',
			finishedMsg : 'No more movies to load.',
			img : 'http://i.imgur.com/qkKy8.gif',
			msgText : 'Loading more movies...'
		},
		state : {
			isDuringAjax : false,
			isInvalidPage : false,
			isDestroyed : false,
			isDone : false, // For when it goes all the way through the archive.
			isPaused : false,
			currPage : 0
		},
		dataType : 'json',
		appendCallback : false

	}, function ( json, opts ) {
		// Get current page
		if ( json.movies.length == 0 ) {
			opts.state.isDone = true;
		}
		var newElements = generateItemDom( json );
		_page++;
		var filterSortUrlPath = getFilterSortUrlPath();
		//var navUrl = "/movies.json?page=" + _page + filterSortUrlPath;
		var navUrl = "/movies.json?" + _filterSort + "&page=" + _page;
		newElements += "<div id='page-nav'><a href=" + navUrl + "></a></div>";
		var $newElems = $( newElements );
		$container.append( $newElems );
		$container.isotope( 'appended', $newElems );
		$container.imagesLoaded( function () {
			console.log( "images loaded again" );
			$container.isotope( 'layout' );
		} );
	} );
	
	$container.infinitescroll( 'bind' );
	
}

/**
 * _filterSort contains the hash URL part. ie "filter=<..>,sort=<..>" This is a convience function only to append & to _filterSort if it is not null
 * 
 * @returns {String}
 */
function getFilterSortUrlPath () {
	if ( _filterSort != null && _filterSort != "" ) {
		var filterSortUrlPath = "&" + _filterSort;
		console.log( "filtersort not null " + filterSortUrlPath );
		return filterSortUrlPath;
	} else
		return "";
}

/**
 * Resets the page number (_page). Sets the Request URL to the default page and default filter_sort (fetched from Hash) Sets the contentDiv container to empty and repopulates the data. This is called initially and whenever a filter,sort is clicked.
 */
function getMovies () {
	
	_page = 0;
	var filterSortUrlPath = getFilterSortUrlPath();
	requestUrl = "/movies.json?page=" + _page + filterSortUrlPath;
	console.log( "inside get movies: " + requestUrl );
	$.ajax( {
		url : requestUrl,
		type : "GET",
		error : function ( jq, st, err ) {
			alert( st + " : " + err );
		},
		success : function ( result ) {
			var output = "";
			var $container = $( "#contentDiv" );
			output = generateItemDom( result );
			// Set the next navigation link
			var filterSortUrlPath = getFilterSortUrlPath();
			_page++;
			//var navUrl = "/movies.json?page=" + _page + filterSortUrlPath;
			//var navUrl = "/movies.json?page=" + _page;
			var navUrl = "/movies.json?" + _filterSort + "&page=" + _page;
			output += "<div id='page-nav'><a href=" + navUrl + "></a></div>";
			$( "#page-nav a" ).prop( "href", navUrl );
			
			// Re-initialize the infinite scroll plugin
			$container.infinitescroll( "destroy" );
			$container.data('infinitescroll', null);
			$container.empty();
			$container.append( output );
			$container.infinitescroll( {
				state : {
					isDestroyed : false,
					isDone : false,
					isInvalidPage: false
				}
			} );
			
			//$.infinitescroll.prototype.sort = "&sort=" + params["sort"];
			isotopize();
			
		}
	} );
}