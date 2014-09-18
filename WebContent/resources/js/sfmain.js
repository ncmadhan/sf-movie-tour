var _page = 0;
var _filterSort = "";
var _filters = {};


$( document ).ready( function () {

	$( window ).bind( 'hashchange', function ( e ) {
		var $container = $("#contentDiv");
		var urlPath = $.param.fragment();
		_filterSort = urlPath;
		_page = 0;
		$container.isotope('destroy');
		getMovies();
	} );

	getMovies();

	$( "#decadeDropdown .dropdown-menu" ).on( 'click', 'li a', function () {
		$( "#decadeDropdown .btn:first-child" ).text( $( this ).text() );
		$( "#decadeDropdown .btn:first-child" ).val( $( this ).text() );
		//$("#decadeDropdown").find(".active-filter").removeClass("active-filter");
	} );

	$( "#neighbourhoodDropdown .dropdown-menu" ).on( 'click', 'li a', function () {
		$( "#neighbourhoodDropdown .btn:first-child" ).text( $( this ).text() );
		$( "#neighbourhoodDropdown .btn:first-child" ).val( $( this ).text() );
		//$("#neighbourhoodDropdown").find(".active-filter").removeClass("active-filter");
	} ); 

	$( document ).on( 'mouseenter', '.movie-poster', function () {
		$( this ).find( '.caption' ).removeClass( "fadeOut" ).addClass( "fadeIn" ).show();
	} );

	$( document ).on( 'mouseleave', '.movie-poster', function () {
		$( this ).find( '.caption' ).removeClass( "fadeIn" ).addClass( "fadeOut" );
	} );

	$( document ).on( 'click', '.filter', function ( e ) {
		e.preventDefault();
		
		thisdata = $( this ).attr( 'data-filter' );
		alert ("this data " + thisdata);
		_filters[thisdata] = 1;
		var state = {};
		state ["filter"] = generateFilterHash(_filters);
		$.bbq.pushState( state );
		
		/*
		console.log("onclick filter, this data "  + thisdata );
		
		 var state = $.bbq.getState();
		filterState = state["filter"];
		var filterValues = filterState.spilt(",");
		
		filterValues.forEach(function(entry) {
			console.log("fi" + entry);
		});
		var filter = thisdata.split( ':' );
		//state[ filter[ 0 ] ] = filter[ 1 ];
		state["filter"]  thisdata;
		$.bbq.pushState( state ); */
	} );

	/*
	 * $( ".movie-poster" ).mouseenter(function() { $(this).find('.caption').removeClass("fadeOut").addClass("fadeIn").show(); }) .mouseleave(function() { $(this).find('.caption').removeClass("fadeIn").addClass("fadeOut"); });
	 */
} );

function generateFilterHash(filterMap) {
	var keys = [];
	$.each(filterMap, function (key, value) {
		alert ("key " + key);
		keys.push(key);
	});
	return keys.join(",");
	
}
function imgError ( image ) {
	image.onerror = "";
	image.src = "http://placehold.it/300x200&text=Movie+Image+NA";
	return true;
}

function generateItemDom ( result ) {
	$( "#movieCount" ).text( result.metaFacets.value.count );

	// Populate Decade Filter
	$( "#decadeFilter" ).empty();
	var decadeFilterOutput = "";
	decadeFilterOutput += "<li><a tabindex='-1' href='#'>Any Year</a></li>";
	decadeFilterOutput += "<li class='divider'></li>";
	$.each( result.metaFacets.value.facets[ "releaseDecade" ], function ( key, value ) {
		console.log( key + value );
		decadeFilterOutput += "<li><a class='filter' tabindex='-1' href='#' data-filter='decade:" + key + "'>" + key + " (" + value + ")" + "</a></li>";
	} );
	$( "#decadeFilter" ).append( decadeFilterOutput );

	// Populate Neighbourhood Filter
	$( "#neighbourhoodFilter" ).empty();
	var neighbourhoodFilterOutput = "";
	neighbourhoodFilterOutput += "<li><a tabindex='-1' href='#'>All Neighbourhoods</a></li>";
	neighbourhoodFilterOutput += "<li class='divider'></li>";
	$.each( result.metaFacets.value.facets[ "neighbourhood" ], function ( key, value ) {
		neighbourhoodFilterOutput += "<li><a class='filter' tabindex='-1' href='#' data-filter='neighbourhood:" + key + "'>" + key + " (" + value + ")" + "</a></li>";
	} );
	$( "#neighbourhoodFilter" ).append( neighbourhoodFilterOutput );

	var output = "";
	$.each( result.movies, function ( i, value ) {

		output += "<div class='item col-sm-4 col-lg-2' style='width:24.5%'> \
								<div class='panel panel-default movie-poster'> \
								<div style='min-height: 2; max-height: 2;' class='panel-body'> \
								<div id='hover-caption' style='text-align: center' class='caption animated'>";
		output += "<h4>" + result.movies[ i ].title + "</h4>";
		output += "<p><button style='width: 100%' type='button' class='btn btn-info'> \
								 <span class='glyphicon glyphicon glyphicon-eye-open'></span>Explore \
								 </button></p></div>";
		output += "<img src='" + result.movies[ i ].additionalInfo.Poster + "'class='img-responsive' style='min-height:200px' onerror='imgError(this);'/></div> \
		                         <div class='panel-footer'><p><span class='thin pull-left'><strong>" + result.movies[ i ].title
				+ "</strong></span> \
		                         <span class='pull-right'>" + result.movies[ i ].release_year + "</span></p><br/> \
		                         <p class='thin'><span class='glyphicon glyphicon-facetime-video'/> Shot in " + result.movies[ i ].movieLocations.length
				+ " location(s) in SF</em></p> \
		                         </div></div></div>";
		// alert(output);
	} );
	// alert ("inside generate " + output);
	return output;
}

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

	//$container.isotope( 'on', 'layoutComplete', onLayout );

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
		var $newElems = $( newElements );
		$container.append( $newElems );
		// console.log($("#contentDiv").html());

		
		//opts.state.currPage = page + 1;
		_page++;
		var filterSortUrlPath = getFilterSortUrlPath();
		$( "#page-nav a" ).prop( "href", "/movies.json?page=" + _page + filterSortUrlPath);
		console.log( "page nav value is: " + $( "#page-nav a" ).attr( "href" ) );
		$container.isotope( 'appended', $newElems );
		$container.imagesLoaded( function () {
			console.log( "images loaded again" );
			$container.isotope( 'layout' );
		} );
	} );

	$container.infinitescroll('bind');
}


function getFilterSortUrlPath() {
	if ( _filterSort != null &&  _filterSort != "" ) {
		var filterSortUrlPath = "&filter=" + _filterSort;
		console.log("filtersort not null " + filterSortUrlPath);
		return filterSortUrlPath;
	}
	else 
		return "";
}
function getMovies () {
	
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
			var $container = $("#contentDiv");
			//var output = "<div class='row-fluid'>";
			output = generateItemDom( result );
			//output +="</div>";
			//alert (output);
			_page++;
			//filterSort = "";
			var filterSortUrlPath = getFilterSortUrlPath();
			$( "#page-nav a" ).prop( "href", "/movies.json?page=" + _page + filterSortUrlPath);
			console.log( "page nav value is: " + $( "#page-nav a" ).attr( "href" ) );
			$container.infinitescroll("destroy");
			$container.empty();
			console.log("output in getmovies " + output);
			$container.append( output );
			$container.infinitescroll({                      
			    state: {                                              
			        isDestroyed: false,
			        isDone: false                           
			}
			});
			isotopize();
		}
	} );
}