package fr.naoned.core.workflow.task

import fr.naoned.core.workflow.Task

class TaskLog extends Task {

    static constraints = {
    }
	
	def doProcess(){
		log.debug("Debug task begin")
	}
}
