
<%@ page import="fr.naoned.core.workflow.Action" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'action.label', default: 'Action')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div id="show-action" class="col-md-12" role="main">
					<fieldset>
						<legend>
							<g:message code="default.show.label" args="[entityName]" />
						</legend>
					</fieldset>
					<g:if test="${flash.message}">
						<div class="message" role="status">${flash.message}</div>
					</g:if>
					<ol class="property-list action">
					
						<g:if test="${actionInstance?.name}">
						<li class="fieldcontain">
							<span id="name-label" class="property-label"><g:message code="action.name.label" default="Name" /></span>
							
								<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${actionInstance}" field="name"/></span>
							
						</li>
						</g:if>
					
						<g:if test="${actionInstance?.states}">
						<li class="fieldcontain">
							<span id="states-label" class="property-label"><g:message code="action.states.label" default="States" /></span>
							
								<g:each in="${actionInstance.states}" var="s">
								<span class="property-value" aria-labelledby="states-label"><g:link controller="state" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
								</g:each>
							
						</li>
						</g:if>
					
					</ol>
					<fieldset class="buttons">
						<g:link class="btn btn-sm btn-primary glyphicon glyphicon-edit" action="edit" resource="${actionInstance}">
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<g:link class="btn btn-sm btn-danger glyphicon glyphicon-trash" action="delete" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" action="delete"  resource="${actionInstance}">
							<g:message code="default.button.delete.label" default="Delete" />
						</g:link>
					</fieldset>
				</div>
			</div>
		</div>
	</body>
</html>
