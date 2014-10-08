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

	Closure displayMenu = {attrs ->
		String appName = grails.util.Metadata.current.getApplicationName()
		List<GroupFeature> groupfeatures = GroupFeature.list()
		groupfeatures.each {group ->
			if(group.controllerName){
				String href = href(group)
				out << """<li><a href="/${appName}/${href}">${group.name}</a></li>"""
			}
			else{
				out << """<li class="dropdown">"""
				out << """<a href="#" class="dropdown-toggle" data-toggle="dropdown">${group.name}<span class="caret"></span></a>"""
				out << """<ul class="dropdown-menu" role="menu">"""
				group.features.each{f ->
					out << """<li>"""
					String href = href(f)
					out << """<a class="list" href="/${appName}/${href}">"""
					out << """${f.name}"""
					out << """</a>"""
					out << """</li>"""
				}
				out << """</ul>"""
				out << """</li>"""
			}
		}
	}

	private String href(f){
		String href = f.controllerName
		if(f.actionName){
			href += "/"+f.actionName
		}
		href
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
