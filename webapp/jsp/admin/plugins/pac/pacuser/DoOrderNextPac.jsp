<%@ page errorPage="../../../ErrorPage.jsp" %>

<%@page import="fr.paris.lutece.plugins.pac.web.pacuser.PacuserJspBean"%>
<jsp:useBean id="pacuserJspBean" scope="session" class="fr.paris.lutece.plugins.pac.web.pacuser.PacuserJspBean" />

<%
	pacuserJspBean.init( request, PacuserJspBean.RIGHT_MANAGE_BEAN);
    response.sendRedirect( pacuserJspBean.doOrderNextPac( request ) );
%>