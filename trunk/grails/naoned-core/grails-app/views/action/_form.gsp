<%@ page import="fr.naoned.core.workflow.Action" %>



<div class="fieldcontain ${hasErrors(bean: actionInstance, field: 'name', 'error')} required form-group">
	<label for="name" class="col-sm-2 control-label">
		<g:message code="action.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:textField name="name" required="" value="${actionInstance?.name}"/>

	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: actionInstance, field: 'states', 'error')}  form-group">
	<label for="states" class="col-sm-2 control-label">
		<g:message code="action.states.label" default="States" />
		
	</label>
	<div class="col-sm-10">
		<g:select name="states" from="${fr.naoned.core.workflow.State.list()}" multiple="multiple" optionKey="id" size="5" value="${actionInstance?.states*.id}" class="many-to-many"/>

	</div>
</div>

