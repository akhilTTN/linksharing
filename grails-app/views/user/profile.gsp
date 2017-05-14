<html>
<head>
    <meta name="layout" content="main"/>
    <title>User Profile</title>
</head>

<body>
<div class="col-md-5">
    <div class="panel panel-default">
        <div class="panel-body">
            %{--<g:render template="show"/>--}%
            <ls:loggedInUser/>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">Topics</div>

        <div class="panel-body">
            %{--<ls:topicsCreated />--}%
            <g:render template="/topic/show" model="[topics: topicsCreated]"/>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">Subscriptions <a href="" class="pull-right">view All</a></div>

        <div class="panel-body">
            <g:render template="/topic/show" model="[topics: subscriptionList]"/>
        </div>
    </div>

</div>

<div class="col-md-7">
    <div class="panel panel-default">
        <div class="panel-heading">
            Posts
        </div>

        <div class="panel-body">
            <g:render template="/topic/posts" model="[resourceList: allPosts]"/>
        </div>
    </div>
</div>
</body>
</html>


