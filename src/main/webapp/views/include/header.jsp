<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet"
	media="screen">
<link href="<c:url value="/css/font-awesome.css"/>" rel="stylesheet"
	media="screen">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet"
	media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
		<a class="navbar-brand" href="dashboard.jsp"> Application - Computer Database </a>
			<div id="navbar" class="collapse navbar-collapse pull-right">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle "
						data-toggle="dropdown" role="button" aria-expanded="false"><spring:message
								code="header.lang_dropdown" /> <span class="caret"></span> </a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="?lang=en"><spring:message
										code="header.lang_english" /></a></li>
							<li><a href="?lang=fr"><spring:message
										code="header.lang_french" /></a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</header>