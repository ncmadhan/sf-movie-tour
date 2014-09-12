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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
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
	background-color: rgba(27, 42, 35, 0.4);
	padding: 5px;
	-webkit-background-clip: padding-box; /* for Safari */
	background-clip: padding-box; /* for IE9+, Firefox 4+, Opera, Chrome */
}

.panel-footer {
	border: 8px solid rgb(127, 0, 0);
	border: 8px solid rgba(27, 42, 35, 0.4);
	-webkit-background-clip: padding-box; /* for Safari */
	background-clip: padding-box; /* for IE9+, Firefox 4+, Opera, Chrome */
}

body {
	background: url(/resources/bootstrap-green/assets/crossword.png) repeat;
	font-family: 'Montserrat', sans-serif;
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
</style>

<script type="text/javascript">

var page = 1;

$(document).ready(function(){
	
	getMovies(page);
 	
	$(document).on('mouseenter','.movie-poster', function(){
		$(this).find('.caption').removeClass("fadeOut").addClass("fadeIn").show();
	});
	
	$(document).on('mouseleave','.movie-poster', function(){
		$(this).find('.caption').removeClass("fadeIn").addClass("fadeOut");
	});
	
	
   /* $( ".movie-poster" ).mouseenter(function() {
            $(this).find('.caption').removeClass("fadeOut").addClass("fadeIn").show();
        })
        .mouseleave(function() {
            $(this).find('.caption').removeClass("fadeIn").addClass("fadeOut");
     });*/
});

function isotopize() {
	var $container = $('#contentDiv');
	$container.isotope({
		  itemSelector: '.item',
		  layoutMode: 'masonry',
		  masonry: {
			  columnWidth: 20,
			  gutter: 10
			}
		});
	
	$container.imagesLoaded( function() {
		//alert(";");
		  $container.isotope('layout');
		});
}
function getMovies(page) {
	
	$.ajax({
		url:"/movies.json?page="+page,
		 type: "GET",
		 error : function(jq, st, err) {
	            alert(st + " : " + err);
	        },
	        success: function(result){
	        	var output = "<div class='row-fluid'>";
	        	//var output ="";
	        	$.each(result, function(i, value) {
	        		//alert("i is " + i);
	        		//console.log(result[i].title);
	        		 if (i%4 == 0 && i!=0) {
	        			//alert(i);
	        			output += "</div>";
	        			output += "<div class='row-fluid'>";
	        		} 
	        		
	        		output += "<div class='item col-sm-4 col-lg-2' style='width:24.5%'> \
	        								<div class='panel panel-default movie-poster'> \
	        								<div style='min-height: 2; max-height: 2;' class='panel-body'> \
	        								<div id='hover-caption' style='text-align: center' class='caption animated'>";
					output +="<h4>" + result[i].title + "</h4>";
					output += "<p><button style='width: 100%' type='button' class='btn btn-info'> \
	        								 <span class='glyphicon glyphicon glyphicon-eye-open'></span>Explore \
	        								 </button></p></div>";
					output +="<img src='"+result[i].additionalInfo.Poster+"'class='img-responsive'/></div> \
	        		                         <div class='panel-footer'>With Caption</div></div></div>";
	        		//alert(output);
	        	});
	        	output +="</div>";
	        	//alert (output);
	        	$("#contentDiv").append(output);
	        	isotopize();	
	        }
	});
}
</script>


</head>
<body>
	<div class="page-header">
		<p>San Francisco Movie Tour</p>
	</div>
	<div id="contentDiv" class="infinite-scroll container-fluid">

	
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

	<!-- Include all compiled plugins (below), or include individual files as needed -->

	<script src="/resources/bootstrap-green/js/bootstrap.min.js"></script>
	<script src="/resources/js/isotope.pkgd.min.js"></script>
	<script src="/resources/js/imagesloaded.pkgd.min.js"></script>
</body>
</html>
