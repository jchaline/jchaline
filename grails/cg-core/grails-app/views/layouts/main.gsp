<!DOCTYPE html>
<html>
<head>
    <title><g:layoutTitle default="site name" /></title>
    <asset:stylesheet src="application.css" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <g:layoutHead />
</head>
<body>
<div id="top" class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <span class="navbar-brand" >SoGrails</span>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <c:displayMenu />
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:displayRightMenu />
            </ul>
        </div>
    </div>
</div>
<asset:javascript src="application.js" />
<g:javascript src="bootstrap-datepicker.js"/>
<g:javascript src="bootstrap-datepicker.fr.js"/>
<g:layoutBody />
</body>
</html>