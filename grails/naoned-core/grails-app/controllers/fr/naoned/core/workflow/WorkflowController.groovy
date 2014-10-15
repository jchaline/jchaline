package fr.naoned.core.workflow



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class WorkflowController {

    static allowedMethods = [save: "POST", update: "PUT"]
	
	static scaffold = Workflow

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Workflow.list(params), model:[workflowInstanceCount: Workflow.count()]
    }

    def show(Workflow workflowInstance) {
        respond workflowInstance
    }

    def create() {
        respond new Workflow(params)
    }

    @Transactional
    def save(Workflow workflowInstance) {
        if (workflowInstance == null) {
            notFound()
            return
        }

        if (workflowInstance.hasErrors()) {
            respond workflowInstance.errors, view:'create'
            return
        }

        workflowInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'workflow.label', default: 'Workflow'), workflowInstance.id])
                redirect workflowInstance
            }
            '*' { respond workflowInstance, [status: CREATED] }
        }
    }

    def edit(Workflow workflowInstance) {
        respond workflowInstance
    }

    @Transactional
    def update(Workflow workflowInstance) {
        if (workflowInstance == null) {
            notFound()
            return
        }

        if (workflowInstance.hasErrors()) {
            respond workflowInstance.errors, view:'edit'
            return
        }

        workflowInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Workflow.label', default: 'Workflow'), workflowInstance.id])
                redirect workflowInstance
            }
            '*'{ respond workflowInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Workflow workflowInstance) {

        if (workflowInstance == null) {
            notFound()
            return
        }

        workflowInstance.delete flush:true

        redirect action:"index", method:"GET"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'workflow.label', default: 'Workflow'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
