<?xml version="1.0" encoding="UTF-8"?>
<beans 
 xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	   xmlns:context="http://www.springframework.org/schema/context"
	   xmlns:aop="http://www.springframework.org/schema/aop"
 xsi:schemaLocation="
http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
">

<!-- define the bean here -->
<context:annotation-config />
	<#list plugin.beanList as bean>
		<#-- <bean id="directionJspBean" class="fr.paris.lutece.plugins.ods.web.direction.DirectionJspBean" scope="prototype"/> -->
    </#list>
</beans>