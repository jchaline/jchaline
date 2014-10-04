package fr.naoned.core

import org.codehaus.groovy.grails.plugins.web.taglib.FormTagLib

class BootstrapFormTagLib extends FormTagLib{
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
	
	Closure handleFlash = { attrs ->
		if(flash?.current?.message){
			out << flashMessage("alert alert-info",flash.current.message)
		}
		if(flash?.current?.error){
			out << flashMessage("alert alert-danger",flash.current.error)
		}
	}
	
	private String flashMessage(String cssClass, String message){
		return """<div role="alert" class="$cssClass">$message</div>"""
	}
	Closure bsActionSubmit = { attrs, body ->
		if (!attrs.value) {
			throwTagError("Tag [actionSubmit] is missing required attribute [value]")
		}

		attrs.tagName = "actionSubmit"

		// Strip out any 'name' attribute, since this tag overrides it.
		if (attrs.name) {
			log.warn "[actionSubmit] 'name' attribute will be ignored"
			attrs.remove('name')
		}

		// add action and value
		def value = attrs.remove('value')
		def action = attrs.remove('action') ?: value
		// Change value if necessary in requestDataValueProcessor
		value = processFormFieldValueIfNecessary("_action_${action}",value,"submit")
		booleanToAttribute(attrs, 'disabled')

		out << "<button type=\"submit\" name=\"${action}\" value=\"${value}\" "

		// process remaining attributes
		outputAttributes(attrs, out)

		// close tag
		out << '>'
		
		out << body()
		
		out << "</button>"
	}
	
	private processFormFieldValueIfNecessary(name, value, type) {
		if (requestDataValueProcessor != null) {
			value = requestDataValueProcessor.processFormFieldValue(request, name, "${value}", type)
		}
		return value
	}
}
