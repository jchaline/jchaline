<%@ page import="fr.naoned.core.Feature" %>
<%@ page import="fr.naoned.core.GroupFeature" %>
<%
	List<GroupFeature> groupfeatures = GroupFeature.list()
%>

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
						<g:displayMenu />
						<g:each in="${groupfeatures}" var="group" >
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">${group.name}<span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<g:each in="${group.features}" var="feature" >
										<li>
											<g:link class="list" controller="${feature.controllerName}" action="${feature.actionName}">
												${feature.name}
											</g:link>
										</li>
									</g:each>
								</ul>
							</li>
						</g:each>
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