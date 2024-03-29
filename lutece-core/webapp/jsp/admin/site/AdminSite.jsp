<%@ page errorPage="../ErrorPage.jsp" %>
<%@ page import="java.util.Enumeration" %>

<jsp:include page="../AdminHeader.jsp" />
<jsp:useBean id="admin" scope="session" class="fr.paris.lutece.portal.web.admin.AdminPageJspBean" />

<% admin.init( request , admin.RIGHT_MANAGE_ADMIN_SITE ); %>

<jsp:include page="AdminPage.jsp" />

<%
	String strParams = "";
	String strSeparator = "?";
	Enumeration paramNames = request.getParameterNames();
	int i = 0;
	while( paramNames.hasMoreElements() )
	{
            i = i + 1;
            if( i > 1 )
            {
                strSeparator = "&#38;";
            }
            String strParamName = (String) paramNames.nextElement();
            String strParamValue = request.getParameter( strParamName );
            strParams += strSeparator + strParamName + "=" + strParamValue;
	}

    String strClassPreview = "";
    
    String strParamBlock = request.getParameter("param_block");

    int nParamBlock = strParamBlock != null ? Integer.parseInt( strParamBlock ) : 0;


%>
<iframe name="preview" src="jsp/admin/site/AdminPagePreview.jsp<%= strParams %>" width="100%" height="750" scrolling="auto">Pr&eacute;visualisation du site</iframe>
<%@ include file="../AdminFooter.jsp"%>