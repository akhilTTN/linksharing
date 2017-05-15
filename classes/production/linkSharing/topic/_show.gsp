<!-- <div class="row"> -->
<g:each in="${topics}" var="topic">
    <div class="well">
        <div class="row">

            <div class="col-sm-2">
                <!--<span class="glyphicon glyphicon-user "></span>-->
                <!-- <img src="../images/user_image.jpg"/> -->
            </div>

            <div class="col-sm-10 ">
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

                        <div><a>subscribe</a></div>
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
            <hr style="border: 1px solid ">
            <!-- <div class="row"> -->
        </div>

        <div class="row pull-right">
            <span class="dropdown">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">serious <b
                        class="caret"></b></button>
                <ul class="dropdown-menu" role="menu">
                    <li role="presentation"><a href="#" role="menuitem">Casual</a></li>
                    <li role="presentation"><a href="#" role="menuitem">Serious</a></li>
                    <li role="presentation"><a href="#" role="menuitem">very Serious</a></li>
                </ul>
            </span>
            <span class="dropdown">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">private <b
                        class="caret"></b></button>
                <ul class="dropdown-menu" role="menu">
                    <li role="presentation"><a href="#" role="menuitem">public</a></li>
                    <li role="presentation"><a href="#" role="menuitem">private</a></li>
                </ul>
            </span>
            <span style="display: inline-block;">
                <a href="#"><span class="glyphicon glyphicon-envelope glyphsize"></span></a>
                <a href="#"><span class="glyphicon glyphicon-edit glyphsize"></span></a>
                <a href="#"><span class="glyphicon glyphicon-trash glyphsize"></span></a>
            </span>
        </div><br>
    </div>
</g:each>