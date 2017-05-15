<!-- <div class="row"> -->
<g:each in="${topics}" var="topic">
    <div class="well">
        <div class="row">

            <div class="col-sm-4">
                <pic:userImage id="${topic.createdBy.id}"/>
            </div>

            <div class="col-sm-8">
                <div>
                    <g:link action="show" controller="topic" params='["id": "${topic.id}"]'>${topic.topicName}</g:link>
                    <span class="text-muted">
                        (${topic.visibility})
                    </span>
                    %{--<a href="/topicShow/${topic.id}" class=""></a>--}%
                </div>

                <div>
                    <div style="padding-left: 0px" class="col-md-5">
                        <div>${topic.createdBy}</div>

                        <div><a onclick="changeSubscription(this)" name="${topic.id}"><ls:toggleSubscription
                                id="${topic.id}"/></a></div>
                        %{--<a href="" name="${topic.id}">subscribe</a>--}%
                    </div>

                    <div class="col-md-5">
                        <div>subscription</div>
                        %{--<div>${topic.subsCount}</div>--}%
                        <div>
                            <ls:topicSubscriptionCount topicId="${topic.id}"/>
                        </div>
                    </div>

                    <div class="col-md-2">
                        <div>post</div>
                        %{--<div>${topic.count}</div>--}%
                        <div>
                            <ls:resourceCount topicId="${topic.id}"/>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            %{--<hr style="border: 1px solid ">--}%
            <!-- <div class="row"> -->
        </div>

        <div class="row">
            <div class="col-xs-offset-3">
                <span>
                    <ls:showSeriousness topicId="${topic.id}"/>
                    <g:if test="${session.user.username == topic.createdBy.username}">
                        <ls:showVisibility topicId="${topic.id}"/>
                    </g:if></span>

                <div class="row">
                    <div class="col-sm-9 pull-right " id="topicTextBox" style="display: none">
                        <g:form controller="topic" action="update">
                            <input type="text" value="${topic.topicName}" name="newTopicName"/>
                            <input type="hidden" name="topicId" value="${topic.id}">
                            <input type="submit" value="Update"/>
                        </g:form>

                    </div>
                </div>

                <div class="pull-right">
                    <ls:canEdit id="${topic.id}"/>
                </div>
            </div>
        </div>
    </div>
</g:each>

<script type="text/javascript">
    function changeSubscription(element) {
        alert(element.name)
        $.ajax({
            type: 'POST',
            data: {'id': element.name},
            url: '/topic/toggleSubscription',
            success: function () {
                location.reload()
            }/*,
             failure: function () {
             location.reload()
             }*/
        })

    }


    /*function changeSeriousness(element) {
     //        alert(element.name)
     $.ajax({
     type: 'POST',
     data: {'id': element.name},
     url: '/topic/changeSeriousness',
     success: function () {
     location.reload()
     },
     failure: function () {
     location.reload()
     }
     })

     }*/
</script>