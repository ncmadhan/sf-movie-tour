<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta name="generator"
	content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Bootstrap 101 Template</title>
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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="/resources/js/sfmain.js"></script>
<style>
.panel-footer {
	font-color: black;
}

.navbar {
	background-color: transparent;
	background: transparent;
	border-color: transparent;
	font-family: 'Josefin Slab', serif;
	font-color: black;
}

.navbar .navbar-nav {
	display: inline-block;
	float: none;
	vertical-align: top;
}

.navbar .navbar-collapse .navbar-header .page-header {
	display: inline-block;
	float: none;
	vertical-align: top;
	text-align: center;
	font-weight: 300em;
	font-color: #000000;
	font-family: 'Josefin Slab', serif;
}

.page-header p {
	font-size: 6em;
	text-align: center;
	font-family: 'Josefin Slab', serif;
}

.page-header {
	margin: 0px;
}

.navbar li {
	color: #000
}

.panel-body {
	background-color: rgb(127, 0, 0);
	background-color: rgba(247, 246, 240, 0.4);
	padding: 5px;
	-webkit-background-clip: padding-box; /* for Safari */
	background-clip: padding-box; /* for IE9+, Firefox 4+, Opera, Chrome */
}

.panel-footer {
	border: 8px solid rgb(127, 0, 0);
	border: 8px solid rgba(247, 246, 240, 0.4);
	-webkit-background-clip: padding-box; /* for Safari */
	background-clip: padding-box; /* for IE9+, Firefox 4+, Opera, Chrome */
}

body {
	background: url(/resources/bootstrap-green/assets/skulls.png) repeat;
	font-family: 'Oswald', sans-serif;
	font-weight: 300;
}

#contentDiv {
	margin: 0 auto;
	width: 100%;
}

.thumbnail {
	position: relative;
	overflow: hidden;
	-vendor-animation-duration: 13s;
	-vendor-animation-delay: 2s;
	-vendor-animation-iteration-count: infinite;
}

.movie-poster {
	position: relative;
	overflow: hidden;
}

.caption {
	position: absolute;
	top: 0;
	right: 0;
	background: rgba(67, 77, 85, 0.75);
	width: 100%;
	height: 100%;
	padding: 2%;
	display: none;
	text-align: center;
	vertical-align: middle !important;
	color: #fff !important;
	z-index: 2;
	-webkit-animation-duration: 1s;
	-webkit-animation-fill-mode: both;
}

#hover-caption a {
	background-color: #fff;
}

.panel:hover {
	cursor: hand;
}

#hover-caption a:active {
	background-color: #fff;
}

.btn {
	text-transform: none;
}

.btn-info {
	font-size: 1.2em;
}

p.thin {
	line-height: 80%;
}

.pipeDivider {
	margin-left: 10px;
	margin-right: 10px;
}
</style>

<script type="text/javascript">


</script>


</head>
<body>
	<div class="page-header">
		<p>San Francisco Movie Tour</p>
	</div>
	<div class="well well-lg">
		<span>Showing <span id="movieCount">0</span> Movies
		</span> <span class="pipeDivider">|</span> <span>Shot in </span>
		<div id="neighbourhoodDropdown" class="btn-group filterSelection">
			<button class="btn">All Neighbourhoods</button>
			<button class="btn dropdown-toggle" data-toggle="dropdown">
				<span class="caret"></span>
			</button>
			<ul id="neighbourhoodFilter"
				class="dropdown-menu dropdown-menu-right" role="menu"
				aria-labelledby="dropdownMenu">

			</ul>
		</div>
		<span class="pipeDivider">|</span> <span>Released during </span>
		<div id="decadeDropdown" class="btn-group filterSelection">
			<button class="btn">Any Year</button>
			<button class="btn dropdown-toggle" data-toggle="dropdown">
				<span class="caret"></span>
			</button>
			<ul id="decadeFilter" class="dropdown-menu dropdown-menu-right"
				role="menu" aria-labelledby="dropdownMenu">

			</ul>
		</div>

	</div>
	<div id="contentDiv" class="infinite-scroll container-fluid"></div>
	<div id="load-more" class="text-center"></div>

	<div id="page-nav">
		<a href="javascript:getMovies(1,'aadsf');"></a>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	

	<script src="/resources/bootstrap-green/js/bootstrap.min.js"></script>
	<script src="/resources/js/isotope.pkgd.min.js"></script>
	<script src="/resources/js/imagesloaded.pkgd.min.js"></script>
	<script src="/resources/js/jquery.infinitescroll.min.js"></script>
	<script src="/resources/js/jquery.ba-bbq.min.js"></script>

</body>
</html>
