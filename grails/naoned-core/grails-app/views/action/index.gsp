
<%@ page import="fr.naoned.core.workflow.Action" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'action.label', default: 'Action')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div id="list-action" class="col-md-12" role="main">
					<fieldset>
						<legend>
							<g:message code="default.list.label" args="[entityName]" />
							<span class="pull-right">
								<g:link class="btn btn-primary btn-sm" action="create">
									<i class="glyphicon glyphicon-plus"></i>
									<g:message code="default.new.label" args="[entityName]" />
								</g:link>
							</span>
						</legend>
						<g:if test="${flash.message}">
							<div class="message" role="status">${flash.message}</div>
						</g:if>
						<table class="table table-condensed table-striped">
							<thead>
								<tr>
								
									<g:sortableColumn property="name" title="${message(code: 'action.name.label', default: 'Name')}" />
								
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
							<g:each in="${actionInstanceList}" status="i" var="actionInstance">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								
									<td><g:link action="show" id="${actionInstance.id}">${fieldValue(bean: actionInstance, field: "name")}</g:link></td>
								
									<td>
										<g:link class="btn btn-sm btn-primary glyphicon glyphicon-edit" action="edit" resource="${actionInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
										<g:link class="btn btn-sm btn-danger glyphicon glyphicon-trash" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" action="delete" resource="${actionInstance}"><g:message code="default.button.delete.label" default="Delete" /></g:link>
									</td>
								</tr>
							</g:each>
							</tbody>
						</table>
						<div class="pagination">
							<g:paginate total="${actionInstanceCount ?: 0}" />
						</div>
					</fieldset>
				</div>
			</div>
		</div>
	</body>
</html>
