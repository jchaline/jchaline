package fr.cg92.core.taglib

import org.codehaus.groovy.grails.plugins.web.taglib.FormTagLib

import fr.cg92.core.domain.GroupFeature

class BootstrapTagLib extends FormTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = 'c'

    def springSecurityService

    /**
     * Display form input
     */
    Closure formInput = {attrs ->
        String name = attrs.name
        String value = attrs.value
        String label = attrs.label

        out << """<div class="required form-group">"""
        out << """<label for="${name}" class="col-sm-2 control-label">"""
        out << """${label}"""
        out << """</label>"""
        out << """<div class="col-sm-10">"""
        out << """	<input name="${name}"" value="${value}"" />"""
        out << """</div>"""
        out << """</div>"""
    }

    /**
     * Call like this : <b:datepicker name="dateValidite" id="validite" value="${formBean?.dateValidite ?: ""}" options="" />
     * @param name the input name
     * @param id the input id
     * @param value the object value, Date or String, can be null
     * @param options the datepicker options, can be null/empty
     */
    Closure datepicker = {attrs ->
        String name= attrs.name
        String id= attrs.id
        String options = attrs.options?:""
        boolean readonly = attrs.readonly?:false
        def value= attrs.value

        if(!name){
            throwTagError("Attribute [name] of Tag [${namespace}datepicker] is a mandatory field !")
        }
        else if(!id){
            throwTagError("Attribute [id] of Tag [${namespace}datepicker] is a mandatory field !")
        }

        if(value in Date){
            value = String.format('%td/%<tm/%<tY', value)
        }
        out << """<div class="input-group date" id="${id}" data-date="${value?:''}" data-date-format="dd/mm/yyyy">"""
        out << """<input class="form-control" name="${name}" type="text" readonly value="${value?:''}" >"""
        out << """<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>"""
        out << """</div>"""

        if(!readonly){
            out << """<script type="text/javascript">"""
            out << """\$(".input-group.date").datepicker({ format: 'dd/mm/yyyy', language: 'fr', autoclose: true, todayHighlight: true, ${options } });"""
            out << """</script>"""
        }

    }

    Closure displayRightMenu = {attrs ->
        def user = springSecurityService.currentUser
        if(user){
            out << "<li>"+g.link(controller:'logout',{"""D&eacute;connexion"""})+"</li>"
        }
    }

    /**
     * get content for menu, must be use into <u class="xxx"></ul>
     */
    Closure displayMenu = {attrs ->
        log.error("Display menu")
        def user = springSecurityService.currentUser

        if(user){
            List<GroupFeature> groupfeatures = GroupFeature.list( sort:"orderSort", order:"asc")

            groupfeatures.each {group ->
                if(group.controllerName){
                    out << """<li>"""+g.link(controller:group.controllerName, action:(group.actionName?:''), {"${group.name}"})+"""</li>"""
                }
                else{
                    out << """<li class="dropdown">"""
                    out << """<a href="#" class="dropdown-toggle" data-toggle="dropdown">${group.name}<span class="caret"></span></a>"""
                    out << """<ul class="dropdown-menu" role="menu">"""
                    group?.features?.sort{it.orderSort}?.each{f ->
                        if(f.visible){
                            out << """<li>"""
                            out << g.link(controller:f.controllerName, action:(f.actionName?:''), {f.name})
                            out << """</li>"""
                        }
                    }
                    out << """</ul>"""
                    out << """</li>"""
                }
            }
        }
    }

    Closure handleFlash = { attrs ->
        if(flash?.message){
            out << flashMessage("alert alert-info",flash.message)
        }
        if(flash?.error){
            out << flashMessage("alert alert-danger",flash.error)
        }
    }

    private String flashMessage(String cssClass, def message){
        String str = """<div role="alert" class="$cssClass">$message</div>"""
        return str
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

    Closure formatDate = { attrs ->
        def date = attrs.date
        String format = attrs.format?:Format.DATE_FORMAT
        out << g.formatDate(format:format, date:date)
    }
}
