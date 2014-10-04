<!DOCTYPE html>
<html>
	<head>
		<title><g:layoutTitle default="Grails" /></title>
		<asset:stylesheet src="application.css" />
		<g:layoutHead />
	</head>
	<body>
		<nav id="top" class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${createLink(uri: '/')}">Naoned</a>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><g:link class="list" controller="feature"><g:message code="default.new.feature" default="Feature" /></g:link></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
					</ul>
				</div>
			</div>
		</nav>
		<g:layoutBody />
		<asset:javascript src="application.js" />
	</body>
</html>