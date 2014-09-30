<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta name="generator"
	content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Movie: ${movie.title }</title>
<!-- Bootstrap -->
<link
	href="<c:url value="/resources/bootstrap-green/css/bootstrap.min.css"/>"
	rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- Headbereich -->

<link rel="stylesheet" type="text/css"
	href="/resources/bootstrap-green/css/animate.min.css">
<link
	href='http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz:700'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,300'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" type="text/css" href="/resources/css/sfmain.css">
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyDt9F4FUqgRJ7JuSlOR0DQUbdaIImFESJg"
	type="text/javascript"></script>

<style>
.page-header-text {
	font-family: 'Yanone Kaffeesatz', sans-serif;
    font-weight: 700;
    line-height: 46px;
    font-size: 60px;
	color : #27598B;	
	text-shadow: #27598B 0.1px 0.2px 0.4px;
	height:70px;
	text-align:center;
	padding:5px;	
	background-color: rgb(255,255,255);
	background-color: rgba(255,255,255,0.75);
	margin:0px;
	border-bottom: #53bf6b 4px solid;
}


.movie-detail {
	background-color: rgb(127, 0, 0);
	background-color: rgba(255, 255, 255, 0.8);
	padding: 5px;
	-webkit-background-clip: padding-box; /* for Safari */
	background-clip: padding-box; /* for IE9+, Firefox 4+, Opera, Chrome */
	-webkit-box-shadow: 2px 2px 6px 0px rgba(50, 50, 50, 0.75);
	-moz-box-shadow: 2px 2px 6px 0px rgba(50, 50, 50, 0.75);
	box-shadow: 2px 2px 6px 0px rgba(50, 50, 50, 0.75);
	-webkit-border-radius: 4px !important;
	-moz-border-radius: 4px !important;
	border-radius: 4px !important;
	-webkit-border-top: 4px !important;
	-moz-border-top: 4px !important;
	border-top: 4px !important;
	-webkit-border-bottom: 4px !important;
	-moz-border-bottom: 4px !important;
	border-bottom: 4px !important;
}

.location {
	background-color: rgb(127, 0, 0);
	background-color: rgba(255, 255, 255, 0.8);
	padding: 5px;
	-webkit-background-clip: padding-box; /* for Safari */
	background-clip: padding-box; /* for IE9+, Firefox 4+, Opera, Chrome */
	-webkit-box-shadow: 2px 2px 6px 0px rgba(50, 50, 50, 0.75);
	-moz-box-shadow: 2px 2px 6px 0px rgba(50, 50, 50, 0.75);
	box-shadow: 2px 2px 6px 0px rgba(50, 50, 50, 0.75);
	-webkit-border-radius: 4px !important;
	-moz-border-radius: 4px !important;
	border-radius: 4px !important;
	-webkit-border-top: 4px !important;
	-moz-border-top: 4px !important;
	border-top: 4px !important;
	-webkit-border-bottom: 4px !important;
	-moz-border-bottom: 4px !important;
	border-bottom: 4px !important;
}

.location-header {
	cursor: hand;
	cursor: pointer;
	background-color: rgb(127, 0, 0);
	background-color: rgba(150, 205, 205, 0.8);
	padding: 5px;
	-webkit-background-clip: padding-box; /* for Safari */
	background-clip: padding-box; /* for IE9+, Firefox 4+, Opera, Chrome */
	-webkit-border-top: 4px !important;
	-moz-border-top: 4px !important;
	border-top: 4px !important;
	-webkit-border-bottom: 4px !important;
	-moz-border-bottom: 4px !important;
	border-bottom: 4px !important;
	-webkit-border-top-left-radius: 4px !important;
	-moz-border-top-left-radius: 4px !important;
	border-top-left-radius: 4px !important;
	-webkit-border-top-right-radius: 4px !important;
	-moz-border-top-right-radius: 4px !important;
	border-top-right-radius: 4px !important;
}

.location-body {
	background-color: rgb(127, 0, 0);
	background-color: rgba(209, 238, 238, 0.8);
	padding: 5px;
	-webkit-background-clip: padding-box; /* for Safari */
	background-clip: padding-box; /* for IE9+, Firefox 4+, Opera, Chrome */
	-webkit-border-top: 4px !important;
	-moz-border-top: 4px !important;
	border-top: 4px !important;
	-webkit-border-bottom: 4px !important;
	-moz-border-bottom: 4px !important;
	border-bottom: 4px !important;
	-webkit-border-bottom-left-radius: 4px !important;
	-moz-border-left-radius: 4px !important;
	border-top-left-radius: 4px !important;
	-webkit-border-bottom-right-radius: 4px !important;
	-moz-border-bottom-right-radius: 4px !important;
	border-bottom-right-radius: 4px !important;
}

html {
	height: 100%
}

body {
	height: 100%;
	margin: 0;
	padding: 0
}

.map-canvas {
	width: 100%;
	height: 100%
}

.panel {
	margin-bottom: 0px;
}

.funFacts {
	padding: 3px;
	font-size: 13px;
}

label {
	font-weight: normal !important
}
</style>

<script type="text/javascript">
	//window.onload = loadScript;
	var _markers = [];
	var _infoWindows = [];
	var map;
	function initializeMaps () {
		
		  var greyPinColor = "D3D3D3";
		    var greyPinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + greyPinColor,
		        new google.maps.Size(21, 34),
		        new google.maps.Point(0,0),
		        new google.maps.Point(10, 34));
		    var greyPinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
		        new google.maps.Size(40, 37),
		        new google.maps.Point(0, 0),
		        new google.maps.Point(12, 35));
		    
		var centerLatLng = new google.maps.LatLng( 37.7749295, -122.4194155 );
		var mapEl = $( "#map-canvas" )[ 0 ];

		var mapOptions = {
			'zoom' : 3,
			'mapTypeId' : google.maps.MapTypeId.ROADMAP
		};

		map = new google.maps.Map( mapEl, mapOptions );
		//var marker;

		$maps = $( ".location-header" );
		var bounds = new google.maps.LatLngBounds();
		var infoWindow = new google.maps.InfoWindow( {
			"maxWidth" : 250
		} );

		$maps.each( function ( index, element ) {
			//alert( "inside for each map element" );
			$infotext = $( element ).children( ".map-info-text" );
			var latLng = $infotext.children( ".latLng" ).text();
			
			var marker = null;
			if (latLng == null || latLng == "," || latLng == "") {
				marker = new google.maps.Marker( {
					map : map,
					position : new google.maps.LatLng(37.7749295, -122.4194155),
					title : $infotext.children( '.address' ).text(),
					visible : true
				} );
				_markers.push(marker);
				bounds.extend( marker.position );

			}
			else if (latLng.split( "," )[0] == 37.7749295 && latLng.split( "," )[1] == -122.4194155) {
				
				var info = "<div style='height:120px;width:150px'><p><strong>" + $infotext.children( ".address" ).text() + "</strong></p><p><em>Unable to locate exact coordinates of address on map</em></p></div>";
				marker = new google.maps.Marker( {
					map : map,
					position : new google.maps.LatLng(37.7749295, -122.4194155),
					title : $infotext.children( '.address' ).text(),
					visible : true,
					icon: greyPinImage,
					shadow: greyPinShadow
				} );
				google.maps.event.addListener( marker, 'click', ( function ( marker ) {
					return function () {
						infoWindow.setContent( info );
						infoWindow.open( map, marker );
						_infoWindows.push( infoWindow );
					}
				} )( marker ) );
				_markers.push( marker );
				bounds.extend( marker.position );
			}
			else {
				var coords = $infotext.children( ".latLng" ).text().split( "," );
				var isApproximate = $infotext.children( ".isApproximate" ).text();
			//	alert(coords[0] + " asdf" + coords[1] + " " + isApproximate);
				var markerLatLng = new google.maps.LatLng( coords[ 0 ], coords[ 1 ] );
				var info = "<div style='height:80px;width:120px'><p><strong>" + $infotext.children( ".address" ).text() + "</strong></p></div>";
				
				marker = new google.maps.Marker( {
					map : map,
					position : new google.maps.LatLng( coords[ 0 ], coords[ 1 ] ),
					title : $infotext.children( '.address' ).text(),
					visible : true
				} );

				google.maps.event.addListener( marker, 'click', ( function ( marker ) {
					return function () {
						infoWindow.setContent( info );
						infoWindow.open( map, marker );
						_infoWindows.push( infoWindow );
					}
				} )( marker ) );
				console.log( coords[ 0 ] + "," + coords[ 1 ] );
				_markers.push( marker );
				bounds.extend( marker.position );

			}

		} );
		
		//alert ("lenght" + _markers.length);
		if (_markers.length == 1) {
			map.setCenter(bounds.getCenter());
			map.setZoom( 14 );	
		}
		else {
			map.fitBounds( bounds );
		}
		
	};

	function initializeStreetViewMaps () {
		$maps = $( ".row .map .map-canvas" );
		$maps.each( function ( index, element ) {
			console.log( "inside for each map element" );
			$infotext = $( element ).children( ".map-info-text" );

			var coords = $infotext.children( ".latLng" ).text().split( "," );
			
			var centerLatLng = new google.maps.LatLng( coords[ 0 ], coords[ 1 ] );

			var streetViewOptions = {
				'zoom' : 0,
				'pov' : {
					heading : 34,
					pitch : 10
				},
				'position' : centerLatLng
			};

			var streetViewMap = new google.maps.StreetViewPanorama( element, streetViewOptions );
			streetViewMap.setVisible( true );
		} );

	};

	google.maps.event.addDomListener( window, "load", initializeMaps );
	function loadScript () {

		var script = document.createElement( 'script' );
		script.type = 'text/javascript';
		script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyDt9F4FUqgRJ7JuSlOR0DQUbdaIImFESJg&' + 'callback=initializeMaps';
		document.body.appendChild( script );
	}
	//$('#accordion').on('shown.bs.collapse', function() { alert('shown'); });

	function highlightMarker ( index, lat, lng ) {
		//alert(lat+""+lng);
		var marker = _markers[ index ];
		var latLng = new google.maps.LatLng( lat, lng );
		google.maps.event.trigger( marker, "click" );
		map.panTo( latLng );
		map.setZoom( 14 );
		var sel = "#moviesShotNearBy"+index;
		$contentDiv = $(sel);
		
		if (lat == 37.7749295 && lng == -122.4194155) {
			$contentDiv.empty();
			$contentDiv.append("<h5><strong>Unable to find exact co-ordinates of location on the map.</strong></h5>");
			$contentDiv.show();
		}
		else {
			$contentDiv.empty();
			$contentDiv.append("<h5><strong>Finding movies shot nearby</strong></h5>");
			$contentDiv.show();
			var output = "<h5>Movies Shot Near By</h5><div class='row'>";
			var url = "/movies/near?lat="+lat+"&lng="+lng;
			$.getJSON(url, function(data) {
				
				$.each(data, function(index, obj) {
					output += "<div class='col-sm-6 col-md-3'>";
					output += "<a href=/movies/"+obj.id+" class='thumbnail'>";
					output += "<img src='" + obj.poster + "'></a>";
					output += "<span><strong>" + obj.title + "</strong></span></div>";
				});
				output += "</div>";
				$contentDiv.empty();
				$contentDiv.append(output);
			});
		}
		
	}
</script>
</head>
<body>
	<div class="page-header-text">
		<p>San Francisco Movie Tour</p>
	</div>

	<!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-bottom" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">SF Movie Tour</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="https://github.com/ncmadhan/sf-movie-tour">Github Project</a>
                    </li>
                    <li>
                        <a href="mailto:ncmadhan@gmail.com">Suggestions/Bugs/Feedback</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
	
	<!-- Page Content -->
	<div class="container">

		<!-- Portfolio Item Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">${movie.title}
					<small>(<span>${movie.releaseYear}</span>)
					</small>
				</h1>
			</div>
		</div>
		<!-- /.row -->

		<!-- Portfolio Item Row -->
		<div class="row movie-detail">

			<div class="col-md-4">
				<img class="img-responsive img-rounded"
					src="${movie.additionalInfo.poster}" alt="">
			</div>

			<div class="col-md-4">
				<h3>Plot</h3>
				<p>${movie.additionalInfo.plot }</p>

				<h3>Youtube Trailer:</h3>
				<iframe id="ytplayer" type="text/html" width="330" height="180"
					src="http://www.youtube.com/embed?listType=search&list=${movie.title}+movie+trailer"
					frameborder="0"></iframe>

			</div>

			<div class="col-md-4">
				<h3>More Details</h3>
				<p>
					<strong>IMDB Rating: </strong><span class="label label-info">${movie.additionalInfo.imdbRating }</span>
				</p>
				<p>
					<strong>Actors: </strong><span>${movie.additionalInfo.actors}</span>
				</p>
				<p>
					<strong>Director: </strong><span>${movie.director}</span>
				</p>
				<p>
					<strong>Genre: </strong>${movie.additionalInfo.genre }</p>
				<p>
					<strong>Language: </strong><span>${movie.additionalInfo.language }</span>
				</p>
				<p>
					<strong>Country: </strong><span>${movie.additionalInfo.country}</span>
				</p>
				<p>
					<strong>Director: </strong><span>${movie.director}</span>
				</p>
				<p>
					<strong>Writer: </strong>${movie.writer}</p>
			</div>

		</div>
		<!-- /.row -->

		<br />

		<!-- Related Projects Row -->
		<div class="row">

			<div class="col-lg-12">
				<h3 class="page-header">Shot in locations:</h3>
			</div>
		</div>
		<!-- /.row -->


		<div class="row location">
			<div class="col-sm-6 col-xs-6 map" style="height: 600px">
				<div id="map-canvas" class="map-canvas"
					style="height: 600px; width: 100%">
					<p></p>
				</div>
			</div>

			<div class="col-sm-6 col-xs-6 accordion location-list"
				style="height: 600px; overflow-y: auto" id="accordion">
				<c:forEach var="movieLocation" items="${movie.movieLocations}"
					varStatus="loop">
					<c:choose>
						<c:when test="${empty movieLocation.locationCoordinates }">
							<div class="panel location-content">
								<div class="location-header">

									<div class="address">
										<h5>
											<strong>Exact Address Unavailable</strong>
										</h5>
									</div>

									<c:if test="${not empty movieLocation.funFacts }">
										<div class="funFacts">
											<i class="fa fa-lightbulb-o fa-3x pull-left"
												style="color: yellow;"></i> ${movieLocation.funFacts }
										</div>
									</c:if>
								</div>
							</div>
							<br />

						</c:when>
						<c:otherwise>
							<div class="panel location-content">
								<div class="location-header" data-toggle="collapse"
									data-target="#collapse${loop.index }" data-parent="#accordion"
									onClick="highlightMarker(${loop.index },${movieLocation.locationCoordinates.coordinates[1]},${movieLocation.locationCoordinates.coordinates[0]})">
									<div class="map-info-text" style="display: none">
										<div class="title">${movie.title }</div>
										<div class="address">${movieLocation.address }</div>
										<div class="latLng">${movieLocation.locationCoordinates.coordinates[1]},${movieLocation.locationCoordinates.coordinates[0]}</div>
										<div class="isApproximate">${movieLocation.approximate}</div>
										<div class="neighbourhood">${movieLocation.neighbourhood}</div>
									</div>
									<div class="address">
										<h5>
											<strong> ${movieLocation.address }</strong><small>
												${movieLocation.neighbourhood}</small>
										</h5>
									</div>

									<c:if test="${not empty movieLocation.funFacts }">
										<div class="funFacts">
											<i class="fa fa-lightbulb-o fa-3x pull-left"
												style="color: yellow;"></i> ${movieLocation.funFacts }
										</div>
									</c:if>

								</div>
								<div id="collapse${loop.index }" class="collapse location-body">
									<c:choose>
									<c:when test="${not movieLocation.approximate.booleanValue()}">
										<small>
										Coordinates: <em>${movieLocation.locationCoordinates.coordinates[1]},${movieLocation.locationCoordinates.coordinates[0]}</em>
									</small>
									</c:when>
									</c:choose>
								 	
									<div id="moviesShotNearBy${loop.index }">
									</div>
								</div>
							</div>

							<br />
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
		<br />
		<hr>

	</div>
	<!-- /.container -->

	<script src="/resources/bootstrap-green/js/bootstrap.min.js"></script>
</body>
</html>