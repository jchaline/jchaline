
<%@ page import="fr.naoned.core.workflow.State" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'state.label', default: 'State')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div id="show-state" class="col-md-12" role="main">
					<fieldset>
						<legend>
							<g:message code="default.show.label" args="[entityName]" />
						</legend>
					</fieldset>
					<g:if test="${flash.message}">
						<div class="message" role="status">${flash.message}</div>
					</g:if>
					<ol class="property-list state">
					
						<g:if test="${stateInstance?.name}">
						<li class="fieldcontain">
							<span id="name-label" class="property-label"><g:message code="state.name.label" default="Name" /></span>
							
								<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${stateInstance}" field="name"/></span>
							
						</li>
						</g:if>
					
					</ol>
					<fieldset class="buttons">
						<g:link class="btn btn-sm btn-primary glyphicon glyphicon-edit" action="edit" resource="${stateInstance}">
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<g:link class="btn btn-sm btn-danger glyphicon glyphicon-trash" action="delete" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" action="delete"  resource="${stateInstance}">
							<g:message code="default.button.delete.label" default="Delete" />
						</g:link>
					</fieldset>
				</div>
			</div>
		</div>
	</body>
</html>
