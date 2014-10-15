<%@ page import="fr.naoned.core.workflow.Workflow" %>



<div class="fieldcontain ${hasErrors(bean: workflowInstance, field: 'actions', 'error')}  form-group">
	<label for="actions" class="col-sm-2 control-label">
		<g:message code="workflow.actions.label" default="Actions" />
		
	</label>
	<div class="col-sm-10">
		<g:select name="actions" from="${fr.naoned.core.workflow.Action.list()}" multiple="multiple" optionKey="id" size="5" value="${workflowInstance?.actions*.id}" class="many-to-many"/>

	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: workflowInstance, field: 'name', 'error')} required form-group">
	<label for="name" class="col-sm-2 control-label">
		<g:message code="workflow.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:textField name="name" required="" value="${workflowInstance?.name}"/>

	</div>
</div>

