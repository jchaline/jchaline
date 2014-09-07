<%@ page import="mavenscan.Dependency" %>



<div class="fieldcontain ${hasErrors(bean: dependencyInstance, field: 'artifactId', 'error')} required">
	<label for="artifactId">
		<g:message code="dependency.artifactId.label" default="Artifact Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="artifactId" required="" value="${dependencyInstance?.artifactId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: dependencyInstance, field: 'groupId', 'error')} required">
	<label for="groupId">
		<g:message code="dependency.groupId.label" default="Group Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="groupId" required="" value="${dependencyInstance?.groupId}"/>

</div>

