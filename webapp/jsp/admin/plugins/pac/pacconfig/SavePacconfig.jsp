<%@ page errorPage="../../../ErrorPage.jsp" %>
<jsp:include page="../../../AdminHeader.jsp" />

<%@page import="fr.paris.lutece.plugins.pac.web.pacconfig.PacconfigJspBean"%>
<jsp:useBean id="pacconfigJspBean" scope="session" class="fr.paris.lutece.plugins.pac.web.pacconfig.PacconfigJspBean" />
<%
	pacconfigJspBean.init( request, PacconfigJspBean.RIGHT_MANAGE_BEAN);
%>
<%= pacconfigJspBean.getSaveBean( request ) %>

<%@ include file="../../../AdminFooter.jsp" %>
