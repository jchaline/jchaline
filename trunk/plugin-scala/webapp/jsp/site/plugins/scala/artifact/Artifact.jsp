<%@page import="fr.paris.lutece.plugins.scala.xpage.RepositoryApp"%>
<jsp:useBean id="repositoryApp" scope="session" class="fr.paris.lutece.plugins.scala.xpage.RepositoryApp" />
<%=
	repositoryApp.searchArtifacts( request )
%>
