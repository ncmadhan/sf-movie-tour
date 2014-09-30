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
<link rel="stylesheet" type="text/css"
	href="/resources/css/sfmain.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="/resources/js/sfmain.js"></script>

<script type="text/javascript">


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
	
	<div class="well well-lg">
		<span>Showing <span id="movieCount">0</span> Movies
		</span> <span class="pipeDivider">|</span> <span>Shot in </span>
		<div id="neighbourhoodDropdown" class="btn-group filterSelection" >
			<button class="btn">All Neighbourhoods</button>
			<button class="btn dropdown-toggle" data-toggle="dropdown">
				<span class="caret"></span>
			</button>
			<ul id="neighbourhoodFilter"
				class="dropdown-menu dropdown-menu-right scrollable-menu" role="menu"
				aria-labelledby="dropdownMenu" >

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
		
		<div class="pull-right">
		<span>Sort By:&nbsp;&nbsp; </span>
		<div id="sortDropdown" class="btn-group filterSelection">
			<button class="btn">Default (Name)</button>
			<button class="btn dropdown-toggle" data-toggle="dropdown">
				<span class="caret"></span>
			</button>
			<ul id="sortList" class="dropdown-menu dropdown-menu-right"
				role="menu" aria-labelledby="dropdownMenu">
				<li><a class="sort" tabindex=-1 data-filter="default">Default (Name)</a>
				<li class='divider'></li>
				<li><a class="sort" tabindex=-1 data-filter="Year">Year</a>
				<li><a class="sort" tabindex=-1 data-filter="Imdb-Rating">IMDB-Rating</a>
			</ul>
		</div>
		</div>
	</div>
	<div id="contentDiv" class="infinite-scroll container-fluid">
	
	<div id="page-nav"><a href="#"></a></div>
	</div>
	<div id="load-more" class="text-center"></div>

	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	

	<script src="/resources/bootstrap-green/js/bootstrap.min.js"></script>
	<script src="/resources/js/isotope.pkgd.min.js"></script>
	<script src="/resources/js/imagesloaded.pkgd.min.js"></script>
	<script src="/resources/js/jquery.infinitescroll.js"></script>
	<script src="/resources/js/jquery.ba-bbq.min.js"></script>

</body>
</html>
