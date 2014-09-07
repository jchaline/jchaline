
<%@ page import="mavenscan.Artifact" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'artifact.label', default: 'Artifact')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-artifact" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-artifact" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list artifact">
			
				<g:if test="${artifactInstance?.artifactId}">
				<li class="fieldcontain">
					<span id="artifactId-label" class="property-label"><g:message code="artifact.artifactId.label" default="Artifact Id" /></span>
					
						<span class="property-value" aria-labelledby="artifactId-label"><g:fieldValue bean="${artifactInstance}" field="artifactId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${artifactInstance?.groupId}">
				<li class="fieldcontain">
					<span id="groupId-label" class="property-label"><g:message code="artifact.groupId.label" default="Group Id" /></span>
					
						<span class="property-value" aria-labelledby="groupId-label"><g:fieldValue bean="${artifactInstance}" field="groupId"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:artifactInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${artifactInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
