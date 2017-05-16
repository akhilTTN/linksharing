<html>
<head>
    <title>View Post</title>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="col-md-7">
    %{--<g:render template="post" model="[post: post]"/>--}%
    <g:render template="post"/>
</div>

<div class="col-md-5 pull-right">
    <div class="panel panel-default">
        <div class="panel-heading">Trending Topics</div>

        <div class="panel-body">
            <ls:trendingTopic/>
        </div>
    </div>
</div>

</body>
</html>