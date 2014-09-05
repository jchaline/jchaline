<%@ page import="zencontact.Contact" %>



<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'prenom', 'error')} required">
	<label for="prenom">
		<g:message code="contact.prenom.label" default="Prenom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="prenom" required="" value="${contactInstance?.prenom}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="contact.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" required="" value="${contactInstance?.nom}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'dateNaissance', 'error')} ">
	<label for="dateNaissance">
		<g:message code="contact.dateNaissance.label" default="Date Naissance" />
		
	</label>
	<g:datePicker name="dateNaissance" precision="day"  value="${contactInstance?.dateNaissance}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="contact.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${contactInstance?.email}"/>

</div>

