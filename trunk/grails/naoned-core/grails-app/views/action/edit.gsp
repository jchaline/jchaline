<%@ page import="fr.naoned.core.workflow.Action" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'action.label', default: 'Action')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div id="edit-action" class="col-md-12" role="main">
					<fieldset>
						<legend>
							<g:message code="default.edit.label" args="[entityName]" />
						</legend>
						<g:if test="${flash.message}">
							<div class="message" role="status">${flash.message}</div>
						</g:if>
						<g:hasErrors bean="${actionInstance}">
							<ul class="errors" role="alert">
								<g:eachError bean="${actionInstance}" var="error">
								<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
								</g:eachError>
							</ul>
						</g:hasErrors>
						<g:form class="form-horizontal"  url="[resource:actionInstance, action:'update']" method="PUT" >
							<g:hiddenField name="version" value="${actionInstance?.version}" />
							<g:render template="form"/>
							<div class="form-actions">
								<g:bsActionSubmit class="btn btn-sm btn-primary glyphicon glyphicon-ok" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" >
									<g:message code="default.button.update.label" default="Update" />
								</g:bsActionSubmit>
							</div>
						</g:form>
					</fieldset>
				</div>
			</div>
		</div>
	</body>
</html>
