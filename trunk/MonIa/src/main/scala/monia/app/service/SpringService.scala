package monia.app.service

import monia.app.conf.ConfConsts
import org.springframework.context.support.ClassPathXmlApplicationContext

class SpringService {

}

object SpringService {
	var initialized=false
	var context = new ClassPathXmlApplicationContext( ConfConsts.FILE_SPRING_CONTEXT );
	
	def getBean(targeted:Class[_])={
	  context.getBean(targeted)
	}

	def getBeansOfType(targeted:Class[_])={
		context.getBeansOfType(targeted)
	}
}
