<%@ page import="fr.naoned.core.workflow.State" %>



<div class="fieldcontain ${hasErrors(bean: stateInstance, field: 'name', 'error')} required form-group">
	<label for="name" class="col-sm-2 control-label">
		<g:message code="state.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:textField name="name" required="" value="${stateInstance?.name}"/>

	</div>
</div>

