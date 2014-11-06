<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div id="create-${domainClass.propertyName}" class="col-md-12" role="main">
					<fieldset>
						<legend>
							<g:message code="default.create.label" args="[entityName]" />
						</legend>
					</fieldset>
					<g:if test="\${flash.message}">
						<div class="message" role="status">\${flash.message}</div>
					</g:if>
					<g:hasErrors bean="\${${propertyName}}">
						<ul class="errors" role="alert">
							<g:eachError bean="\${${propertyName}}" var="error">
							<li <g:if test="\${error in org.springframework.validation.FieldError}">data-field-id="\${error.field}"</g:if>><g:message error="\${error}"/></li>
							</g:eachError>
						</ul>
					</g:hasErrors>
					<g:form class="form-horizontal" url="[resource:${propertyName}, action:'save']" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>
						<g:render template="form"/>
						<div class="form-actions">
							<b:bsActionSubmit name="create" class="btn btn-sm btn-default glyphicon glyphicon-ok" value="\${message(code: 'default.button.create.label', default: 'Create')}" >
								<g:message code="default.button.create.label" default="Create" />
							</b:bsActionSubmit>
						</div>
					</g:form>
				</div>
			</div>
		</div>
	</body>
</html>
