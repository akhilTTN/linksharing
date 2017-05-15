<!doctype html>
<html>
<head>
    <title>
        <g:layoutTitle default="Linksharing"/>
    </title>
    <asset:stylesheet src="application.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-default" style="background-color: #5bc0de">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle"
                    data-toggle="collapse" data-target="#myNavbar">
                <span class="glyphicon glyphicon-menu-hamburger"></span>
            </button>
            <g:if test="${session.user}">
                <a class="navbar-brand" href="#" style="color:black">Link Sharing</a>
            </g:if>
            <g:else>
                <a class="navbar-brand" href="#" style="color:black">Link Sharing</a>
            </g:else>

        </div>

        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-right">
                <g:if test="${session.user}">
                    <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                            data-target="#sendInvitation">Send Invitation</button>
                    <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                            data-target="#topicCreate">Create Topic</button>
                    <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                            data-target="#linkCreate">Create Link</button>
                    <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                            data-target="#docCreate">Create Doc</button>
                </g:if>
            %{--<span class="glyphicon glyphicon-file" data-toggle="modal" data-target="#docCreate"></span>--}%


                <div class="dropdown"><g:if test="${session.user}">
                    <span class="dropdown-toggle glyphicon glyphicon-music" id="menu1" data-toggle="dropdown">
                        ${session.user.username}
                    </span>
                %{-- <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">Tutorials
                     <span class="caret"></span></button>--}%
                    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                        %{--<li role="presentation"><a role="menuitem" tabindex="-1" href="/user/">Profile</a></li>--}%
                        <li role="presentation"><g:link action="edit" controller="user"
                                                        params='["id": "${session.user.id}"]'>Profile</g:link></li>
                        <g:if test="${session.user.admin}">
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="/admin">Users</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="/admin/showAllTopic">Topics</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="/admin/showAllPosts">Posts</a></li>
                        </g:if>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="/login/logout">Logout
                        %{--<g:if test="${session.user}">
                            Logout
                        </g:if>
                        <g:else>
                            Sign in
                        </g:else>--}%
                        </a></li>

                    </ul>
                </g:if>
                </div>
            </ul>

            <div class="col-sm-3 col-md-3 pull-right">
                <form class="navbar-form" role="search">
                    <div class="input-group">
                        <div class="input-group-btn">
                            <button class="btn btn-default searcher" type="submit"><span
                                    class="glyphicon glyphicon-search"></span></button>
                        </div>

                        <div class="input-group"><input type="text" class="form-control"
                                                        placeholder="Search"
                                                        name="srch-term"
                                                        id="srch-term"/>
                            <span id="searchclear" class="input-group-addon glyphicon glyphicon-remove-circle"></span>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</nav>
<g:layoutBody/>
<g:render template="/resource/linkCreate" model="[resource: resource]"/>
<g:render template="/resource/docResource" model="[resource: resource]"/>
<g:render template="/topic/create" model="[topic: topic]"/>
<g:render template="/topic/invite"/>

<g:include controller="home" action="showMessage"/>
<asset:javascript src="application.js"/>
%{--<asset:deferredScripts/>--}%
</body>
</html>
