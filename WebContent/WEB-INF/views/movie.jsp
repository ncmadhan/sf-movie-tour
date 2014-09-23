<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta name="generator"
	content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>SF Movie Tour :: Movies</title>
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
<link href='http://fonts.googleapis.com/css?family=Josefin+Slab:400,700'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="/resources/css/sfmain.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<style>
.movie-detail {
	background-color: rgb(127, 0, 0);
	background-color: rgba(247, 246, 240, 0.8);
	padding: 5px;
	-webkit-background-clip: padding-box; /* for Safari */
	background-clip: padding-box; /* for IE9+, Firefox 4+, Opera, Chrome */

}
</style>
</head>
<body>
	<div class="page-header">
		<p>San Francisco Movie Tour</p>
	</div>
	
	    <!-- Page Content -->
    <div class="container">

        <!-- Portfolio Item Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">${movie.title}
                    <small>(<span>${movie.releaseYear}</span>)</small>
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Portfolio Item Row -->
        <div class="row movie-detail">

            <div class="col-md-4">
                <img class="img-responsive" src="${movie.additionalInfo.poster}" alt="">
            </div>

            <div class="col-md-4">
                <h3>Plot</h3>
                <p>${movie.additionalInfo.plot }</p>
                
                <h3>Youtube Trailer: </h3>
                <iframe id="ytplayer" type="text/html" width="330" height="180" src="http://www.youtube.com/embed?listType=search&list=${movie.title}+movie+trailer" frameborder="0"></iframe>
                
            </div>
			
			<div class="col-md-4">
			<h3>More Details</h3>
                    <p><strong>IMDB Rating: </strong><span class="label label-info">${movie.additionalInfo.imdbRating }</span></p>
                    <p><strong>Actors: </strong><span>${movie.additionalInfo.actors}</span></p>
                    <p><strong>Director: </strong><span>${movie.director}</span></p>
                    <p><strong>Genre: </strong>${movie.additionalInfo.genre }</p>
                    <p><strong>Language: </strong><span>${movie.additionalInfo.language }</span></p>
                    <p><strong>Country: </strong><span>${movie.additionalInfo.country}</span></p>
                    <p><strong>Director: </strong><span>${movie.director}</span></p>
                    <p><strong>Writer: </strong>${movie.writer}</p>
            </div>
            
        </div>
        <!-- /.row -->

        <!-- Related Projects Row -->
        <div class="row">

            <div class="col-lg-12">
                <h3 class="page-header">Shot in locations: </h3>
            </div>

			<c:forEach  var="movieLocation" items="${movie.movieLocations}">
				<div class="col-sm-3 col-xs-6">
                <a href="#">
                    <img class="img-responsive portfolio-item" src="http://maps.googleapis.com/maps/api/streetview?size=400x400&location=${movieLocation.locationCoordinates.coordinates[1]},${movieLocation.locationCoordinates.coordinates[0]}&fov=90&heading=235&pitch=10" alt="">
                </a>
            </div>
			</c:forEach>
            



        </div>
        <!-- /.row -->

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
            <!-- /.row -->
        </footer>

    </div>
    <!-- /.container -->

	<script src="/resources/bootstrap-green/js/bootstrap.min.js"></script>
</body>
</html>