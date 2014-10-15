package fr.naoned.core.workflow

/**
 * Abstract common class for task config
 */
abstract class Task {
	
	abstract def doProcess()

    static constraints = {
    }
}
