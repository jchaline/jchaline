package fr.naoned.core.workflow



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ActionController {

    static allowedMethods = [save: "POST", update: "PUT"]
	
	static scaffold = Action

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Action.list(params), model:[actionInstanceCount: Action.count()]
    }

    def show(Action actionInstance) {
        respond actionInstance
    }

    def create() {
        respond new Action(params)
    }

    @Transactional
    def save(Action actionInstance) {
        if (actionInstance == null) {
            notFound()
            return
        }

        if (actionInstance.hasErrors()) {
            respond actionInstance.errors, view:'create'
            return
        }

        actionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'action.label', default: 'Action'), actionInstance.id])
                redirect actionInstance
            }
            '*' { respond actionInstance, [status: CREATED] }
        }
    }

    def edit(Action actionInstance) {
        respond actionInstance
    }

    @Transactional
    def update(Action actionInstance) {
        if (actionInstance == null) {
            notFound()
            return
        }

        if (actionInstance.hasErrors()) {
            respond actionInstance.errors, view:'edit'
            return
        }

        actionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Action.label', default: 'Action'), actionInstance.id])
                redirect actionInstance
            }
            '*'{ respond actionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Action actionInstance) {

        if (actionInstance == null) {
            notFound()
            return
        }

        actionInstance.delete flush:true

        redirect action:"index", method:"GET"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'action.label', default: 'Action'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
