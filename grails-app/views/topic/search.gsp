<html>
<head>
    <title>Topic Show</title>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="col-md-5">
    <div class="panel panel-default">
        <div class="panel-heading">Trending Topics</div>

        <div class="panel-body">
            <ls:trendingTopic/>
        </div>
    </div>


    <div class="panel panel-default">
        <div class="panel-heading">Posts</div>

        <div class="panel-body">
            %{--<g:render template="/topic/posts" model="[postsList: postsList]"/>--}%
            <ls:topPosts/>
        </div>
    </div>

</div>

<div class="col-md-7 col-sm-7 pull-right">
    <div class="panel panel-default">
        <div class="panel-heading">
            <span>Search for : ${topics.name}</span>
        </div>

        <div class="panel-body">
            <g:render template="/topic/posts" model="[resourceList: searchResult]"/>
        </div>
        <g:if test="${totalResult > 5}">
            <div class="panel-footer text-justify">
                <ul class="pagination">
                    <g:paginate total="${totalResult}"
                                next="Forward" prev="Back" max="10" maxsteps="10"/>
                </ul>
            </div>
        </g:if>
    </div>
</div>

</body>
</html>