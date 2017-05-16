<div class="panel panel-default">
    %{--<div class="panel-heading">--}%
    <div class="panel-body">
        <div class="row">
            <div class="col-sm-2">
                %{--<pic:userImage id="${detailedPost.c}"--}%
            </div>

            <div class="col-sm-10 ">
                <div class=" user_details">
                    <label>${detailedPost.fullName}</label>
                    <a href="#" class="pull-right">${detailedPost.topicName}</a>
                </div>

                <div class=" user_details">
                    <label>@${detailedPost.username}</label>
                    <label class="pull-right user_details">
                        ${detailedPost.updated}
                    </label>
                </div>

                <div class=" pull-right">
                    <g:each in="${(1..detailedPost.ratings)}">
                        <span class="glyphicon glyphicon-heart"></span>
                    </g:each>

                    <ls:subscriber topic="${detailedPost.topic}">
                        <div class="dropdown">
                            <button class="btn btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown">Rate
                                <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li><a onclick="insertRating(${detailedPost.resourceID}, 1)">1</a></li>
                                <li><a onclick="insertRating(${detailedPost.resourceID}, 2)">2</a></li>
                                <li><a onclick="insertRating(${detailedPost.resourceID}, 3)">3</a></li>
                                <li><a onclick="insertRating(${detailedPost.resourceID}, 4)">4</a></li>
                                <li><a onclick="insertRating(${detailedPost.resourceID}, 5)">5</a></li>
                            </ul>
                        </div>
                    </ls:subscriber>

                %{-- <span class="glyphicon glyphicon-heart"></span>
                 <span class="glyphicon glyphicon-heart"></span>
                 <span class="glyphicon glyphicon-heart"></span>
                 <span class="glyphicon glyphicon-heart"></span>--}%
                </div>

            </div>
        </div>

        %{--</div>--}%

        %{--<div class="panel-body">--}%
        <p>${detailedPost.description}</p>
        %{--Grails is a powerful web framework, for the Java platform aimed at multiplying developers’
        productivity thanks to a Convention-over-Configuration, sensible defaults and opinionated APIs.
        It integrates smoothly with the JVM, allowing you to be immediately productive whilst providing
        powerful features, including integrated ORM, Domain-Specific Languages, runtime and compile-time
        meta-programming and Asynchronous programming.--}%


        <div class="row myTopic col-xs-offset-1">
            <a href="https://www.facebook.com">
                <i id="social-fb" class="fa fa-facebook-square fa-2x social glyphsize"></i></a>
            <a href="https://twitter.com">
                <i id="social-tw" class="fa fa-twitter-square fa-2x social glyphsize"></i></a>
            <a href="https://plus.google.com">
                <i id="social-gp" class="fa fa-google-plus-square fa-2x social glyphsize"></i></a>
            <a href="mailto:bootsnipp@gmail.com">
                <i id="social-em" class="fa fa-envelope-square fa-2x social glyphsize"></i></a>
            <span class="pull-right">
                %{--<a href="#" class="operations">Delete</a>
                <a href="#" class="operations">Edit</a>--}%
                <ls:canEdit id="${detailedPost.topic.id}"/>
                <g:if test="${detailedPost.isLinkResource()}">
                    <g:link controller="resource" action="newLink" params='["id": "${detailedPost.resourceID}"]'
                            target="_blank">view full site</g:link>
                </g:if>
                <g:elseif test="${!(detailedPost.isLinkResource())}">
                    <g:link action="download" class="pull-right" controller="resource"
                            params='["id": "${detailedPost.resourceID}"]'>Download</g:link>
                </g:elseif>
                %{--<a href="#" class="operations">Download</a>--}%
            </span>

            <div class="row">
                <div class="col-sm-9 pull-right topicEditDiv" id="topicTextBox" style="display: none">
                    <g:form controller="topic" action="update">
                        <input type="text" value="${detailedPost.topicName}" name="newTopicName"/>
                        <input type="hidden" name="topicId" value="${detailedPost.topic.id}">
                        <input type="submit" value="Update"/>
                    </g:form>

                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function insertRating(id ,score) {
//        alert(id + " "+ score)
        $.ajax({
            type:'POST',
            url:'/resource/rateResource?id='+id+'&score='+score,
            success: function () {
//                alert(result)
                location.reload()

            }
        })

    }

</script>