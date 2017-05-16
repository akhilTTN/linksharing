<html>
<head>
    <meta name="layout" content="main"/>
    <title>User Dashboard</title>
</head>

<body>
<g:render template="../message"/>
<div class="col-md-5">

    <div class="panel panel-default">
        <div class="panel-body">
            <g:render template="/user/show"/>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">Subscriptions <a href="/subscription/viewAllSubscription" class="pull-right">view All</a></div>

        <div class="panel-body">
            <g:render template="/topic/show" model="[topics: subscriptionList]"/>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">Trending Topics</div>

        <div class="panel-body">
            <ls:trendingTopic/>
        </div>
    </div>
</div>

<div class="col-md-7">
    <div class="panel panel-default">
        <div class="panel-heading">
            Inbox
        </div>

        <div class="panel-body">
            <div id="inboxMessages">
                <g:render template="/topic/posts" model="[resourceList: resourceList]"/>
            </div>
            %{--<util:remotePaginate controller='user' action="index" total="${resourceList.size()}"
                                 update="inboxMessages" max="5" />--}%
        </div>
    </div>
</div>
</body>
</html>


