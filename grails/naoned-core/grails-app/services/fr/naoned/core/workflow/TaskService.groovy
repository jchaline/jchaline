package fr.naoned.core.workflow

import grails.transaction.Transactional

abstract class TaskService {

	abstract def processTask()
}
