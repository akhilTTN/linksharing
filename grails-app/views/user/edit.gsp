<html>
<head>
    <meta name="layout" content="main"/>
    <title>Edit Profile</title>
</head>

<body>
<g:render template="../message"/>
<div class="col-md-5">

    <div class="panel panel-default">
        <div class="panel-body">
            <ls:loggedInUser/>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">Topics</div>

        <div class="panel-body">
            <g:render template="/topic/show" model="[topics: topicsCreated]"/>
        </div>
    </div>
</div>

<div class="col-md-7">
    <g:render template="editProfile"/>
    <g:render template="changePassword"/>
</div>
</body>
</html>


