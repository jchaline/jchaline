<%@ page import="mavenscan.Artifact" %>



<div class="fieldcontain ${hasErrors(bean: artifactInstance, field: 'artifactId', 'error')} required">
	<label for="artifactId">
		<g:message code="artifact.artifactId.label" default="Artifact Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="artifactId" required="" value="${artifactInstance?.artifactId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: artifactInstance, field: 'groupId', 'error')} required">
	<label for="groupId">
		<g:message code="artifact.groupId.label" default="Group Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="groupId" required="" value="${artifactInstance?.groupId}"/>

</div>

