package fr.naoned.core.workflow



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class StateController {

    static allowedMethods = [save: "POST", update: "PUT"]
	
	static scaffold = State

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond State.list(params), model:[stateInstanceCount: State.count()]
    }

    def show(State stateInstance) {
        respond stateInstance
    }

    def create() {
        respond new State(params)
    }

    @Transactional
    def save(State stateInstance) {
        if (stateInstance == null) {
            notFound()
            return
        }

        if (stateInstance.hasErrors()) {
            respond stateInstance.errors, view:'create'
            return
        }

        stateInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'state.label', default: 'State'), stateInstance.id])
                redirect stateInstance
            }
            '*' { respond stateInstance, [status: CREATED] }
        }
    }

    def edit(State stateInstance) {
        respond stateInstance
    }

    @Transactional
    def update(State stateInstance) {
        if (stateInstance == null) {
            notFound()
            return
        }

        if (stateInstance.hasErrors()) {
            respond stateInstance.errors, view:'edit'
            return
        }

        stateInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'State.label', default: 'State'), stateInstance.id])
                redirect stateInstance
            }
            '*'{ respond stateInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(State stateInstance) {

        if (stateInstance == null) {
            notFound()
            return
        }

        stateInstance.delete flush:true

        redirect action:"index", method:"GET"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'state.label', default: 'State'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
