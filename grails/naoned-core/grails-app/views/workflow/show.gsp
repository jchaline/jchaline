
<%@ page import="fr.naoned.core.workflow.Workflow" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'workflow.label', default: 'Workflow')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div id="show-workflow" class="col-md-12" role="main">
					<fieldset>
						<legend>
							<g:message code="default.show.label" args="[entityName]" />
						</legend>
					</fieldset>
					<g:if test="${flash.message}">
						<div class="message" role="status">${flash.message}</div>
					</g:if>
					<ol class="property-list workflow">
					
						<g:if test="${workflowInstance?.actions}">
						<li class="fieldcontain">
							<span id="actions-label" class="property-label"><g:message code="workflow.actions.label" default="Actions" /></span>
							
								<g:each in="${workflowInstance.actions}" var="a">
								<span class="property-value" aria-labelledby="actions-label"><g:link controller="action" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
								</g:each>
							
						</li>
						</g:if>
					
						<g:if test="${workflowInstance?.name}">
						<li class="fieldcontain">
							<span id="name-label" class="property-label"><g:message code="workflow.name.label" default="Name" /></span>
							
								<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${workflowInstance}" field="name"/></span>
							
						</li>
						</g:if>
					
					</ol>
					<fieldset class="buttons">
						<g:link class="btn btn-sm btn-primary glyphicon glyphicon-edit" action="edit" resource="${workflowInstance}">
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<g:link class="btn btn-sm btn-danger glyphicon glyphicon-trash" action="delete" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" action="delete"  resource="${workflowInstance}">
							<g:message code="default.button.delete.label" default="Delete" />
						</g:link>
					</fieldset>
				</div>
			</div>
		</div>
	</body>
</html>
